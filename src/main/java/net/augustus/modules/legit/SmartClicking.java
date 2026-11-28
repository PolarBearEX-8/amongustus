package net.augustus.modules.legit;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.ClickListener;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.modules.modes.criticals.LagCriticalsMode;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.utils.combat.CombatOptimizationUtil;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.util.MovingObjectPosition;

public class SmartClicking extends Module implements ClickListener, SilentSlotListener, PacketListener {
   private final RandomizedRangeSettings smartHurttimeStart;
   private final RandomizedRangeSettings smartHurttimeEnd;
   private final RandomizedRangeSettings maxDuration;
   private final DoubleValue chance;
   private final BooleanValue smartHurttime;
   private final BooleanValue reduceClickingInAir;
   private final BooleanValue fakeAttacks;
   private final BooleanValue ratioBypass;
   private final DoubleValue ratioStrength;
   private final BooleanValue extraClicksWithKnockbackEnchantmentBool;
   private final SettingGroup extraClicksWithKnockbackEnchantment;
   private final RandomizedRangeSettings extraClicksWithKnockbackEnchantment2;
   private final BooleanValue onlyIfClickExists;
   private final BooleanValue onlyWhileKnockback;
   private final DoubleValue cpsLimiter;
   private final TimeHelper lastClickTimer;
   private boolean shouldSuppressClick;
   private boolean wasSuppressingClick;
   private boolean bypassMaxDuration;
   private EntityLivingBase currentTarget;
   private final TimeHelper targetTimeoutTimer;
   private boolean trackingLagCritClicks;
   private int lagCritClickCount;
   private int ticksWithoutClick;
   private boolean chancePassed;
   private long lastEntityAttackTimeMs;
   private boolean smartHurtTimeArmed;
   private long smartHurtTimeTriggerMs;
   private int lastAttackedEntityHurtResistantTime;
   private boolean extraKnockbackClickPending;
   private DoubleSampleWindow clickRatioWindow;
   private long lastKnockbackTimeMs;

   public SmartClicking() {
       super("SmartClicking", "IntelligentesKlicken", new Color(235, 125, 52), Categorys.LEGIT);
      this.smartHurttimeStart = new RandomizedRangeSettings(this, "Smart hurttime start", 2.0, 3.0, 0.0, 10.0, 0);
      this.smartHurttimeEnd = new RandomizedRangeSettings(this, "Smart hurttime end", 9.0, 10.0, 0.0, 10.0, 0);
      this.maxDuration = (new RandomizedRangeSettings(this, "MaxDuration", 10.0, 10.0, 0.0, 10.0, 0)).withoutNotInARow();
      this.chance = new DoubleValue(this, "Chance", 100.0, 0.0, 100.0, 0);
      this.smartHurttime = new BooleanValue(this, "SmartHurttime", false);
      this.reduceClickingInAir = new BooleanValue(this, "ReduceClickingInAir", false);
      this.fakeAttacks = new BooleanValue(this, "FakeAttacks", false);
      this.ratioBypass = (BooleanValue)new BooleanValue(this, "RatioBypass", false).withVisibility(() -> this.isRatioBypassVisible());
      this.ratioStrength = new DoubleValue(this, "RatioStrength", 1.0, 0.0, 1.0, 2).visibleWhen(() -> this.isRatioStrengthVisible());
      this.extraClicksWithKnockbackEnchantmentBool = (BooleanValue)new BooleanValue(this, "ExtraClicksWithKnockbackEnchantmentBool", false).withDisplayName("ExtraClicksWithKnockbackEnchantment");
      SettingGroup settingGroup = new SettingGroup(this, "Extra clicks with knockback enchantment");
      BooleanValue booleanValue3 = this.extraClicksWithKnockbackEnchantmentBool;
      this.extraClicksWithKnockbackEnchantment = settingGroup.visibleWhen(() -> booleanValue3.getValue());
      this.extraClicksWithKnockbackEnchantment2 = new RandomizedRangeSettings(this, "ExtraClicksWithKnockbackEnchantment", 1.0, 1.0, 0.0, 3.0, 0, this.extraClicksWithKnockbackEnchantment);
      this.onlyIfClickExists = new BooleanValue(this, "OnlyIfClickExists", true, this.extraClicksWithKnockbackEnchantment);
      this.onlyWhileKnockback = new BooleanValue(this, "OnlyWhileKnockback", false, this.extraClicksWithKnockbackEnchantment);
      this.cpsLimiter = new DoubleValue(this, "CPSLimiter", 20.0, 1.0, 40.0, 0, this.extraClicksWithKnockbackEnchantment);
      this.lastClickTimer = new TimeHelper();
      this.shouldSuppressClick = false;
      this.wasSuppressingClick = false;
      this.bypassMaxDuration = false;
      this.currentTarget = null;
      this.targetTimeoutTimer = new TimeHelper();
      this.trackingLagCritClicks = false;
      this.lagCritClickCount = 0;
      this.ticksWithoutClick = 0;
      this.chancePassed = true;
      this.clickRatioWindow = new DoubleSampleWindow(40);
      this.lastKnockbackTimeMs = 0L;
   }

   @Override
   public void onEnable() {
      this.shouldSuppressClick = false;
      this.bypassMaxDuration = false;
      this.currentTarget = null;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7, 100000);
      this.toggleEventSubscription(34, -1);
      this.toggleEventSubscription(26, 1);
   }

   @Override
   public void onClick(EventClick event) {
      int n = event.getLeftClicks() + (event.isIgnoreVanillaClicks() ? 0 : mc.gameSettings.keyBindAttack.getPressTime());
      if (n > 0) {
         this.lastClickTimer.reset();
      }

      if (this.extraKnockbackClickPending) {
         this.extraKnockbackClickPending = false;
         if ((n > 0 || !this.onlyIfClickExists.getValue()) && (!this.onlyWhileKnockback.getValue() || System.currentTimeMillis() - this.lastKnockbackTimeMs < 1000L)) {
            int n2 = this.extraClicksWithKnockbackEnchantment2.nextInt(true) + (n == 0 ? 1 : 0);
            int n3 = (int)((double)(n + n2) + EventClick.recentClicksBuffer.sum());
            int n4 = (int)(this.cpsLimiter.getValue() - (double)n3);
            if (n4 < 0) {
               n2 = Math.max(0, n2 + n4);
            }

            event.addLeftClicks(n2);
         }
      }

      if (!event.isCriticalHit()) {
         n = event.getLeftClicks() + (event.isIgnoreVanillaClicks() ? 0 : mc.gameSettings.keyBindAttack.getPressTime());
         boolean flag2 = false;
         if (n <= 0 && !this.bypassMaxDuration && this.maxDuration.getLastIntValue() < 10 && this.ticksWithoutClick >= this.maxDuration.getLastIntValue() && (this.shouldSuppressClick || this.wasSuppressingClick)) {
            event.addLeftClicks((byte)1);
            flag2 = true;
            this.maxDuration.nextValue();
         }

         n = event.getLeftClicks() + (event.isIgnoreVanillaClicks() ? 0 : mc.gameSettings.keyBindAttack.getPressTime());
         if (n > 0) {
            this.ticksWithoutClick = 0;
         } else {
            this.ticksWithoutClick++;
         }

         boolean flag3 = false;
         if (this.isSuppressingClicks() && !flag2) {
            if (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && this.fakeAttacks.getValue() && event.getLeftClicks() > 0) {
               mc.objectMouseOver.typeOfHit = MovingObjectPosition.MovingObjectType.MISS;
               flag3 = true;
            } else {
               event.clearLeftClicks();
            }

            this.clickRatioWindow.add(1.0 - this.ratioStrength.getValue(true));
         } else {
            this.clickRatioWindow.add((double)event.getLeftClicks());
            if (this.ratioBypass.getValue() && !this.fakeAttacks.getValue()) {
               double d2 = this.clickRatioWindow.mean();
               if (Math.random() > d2) {
                  event.clearLeftClicks();
               }
            }
         }

         if (this.trackingLagCritClicks) {
            this.lagCritClickCount += n;
         }

         if (!flag3 && event.getLeftClicks() > 0 && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            this.lastEntityAttackTimeMs = System.currentTimeMillis();
            this.lastAttackedEntityHurtResistantTime = mc.objectMouseOver.entityHit.hurtResistantTime;
            if (this.smartHurtTimeArmed) {
               this.smartHurtTimeTriggerMs = System.currentTimeMillis();
               this.smartHurtTimeArmed = false;
            }
         }
      }
   }

   public boolean isSuppressingClicks() {
      return this.isToggled() && this.shouldSuppressClick;
   }

   @Override
   public void onPostSilentSlot() {
      if (this.currentTarget != null) {
         if (this.targetTimeoutTimer.reached(1500L)) {
            this.currentTarget = null;
         }
      }

      this.wasSuppressingClick = this.shouldSuppressClick;
      this.shouldSuppressClick = false;
      this.bypassMaxDuration = false;
      MovingObjectPosition objectMouseOver = mc.objectMouseOver;
      EntityLivingBase activeTargetOrDefault = CombatTargetUtil.getActiveTargetOrDefault(objectMouseOver != null && objectMouseOver.entityHit instanceof EntityLivingBase ? (EntityLivingBase)objectMouseOver.entityHit : null);
      if (activeTargetOrDefault != null) {
         if (true) {
            this.currentTarget = activeTargetOrDefault;
            this.targetTimeoutTimer.reset();
         }
      }

      if (this.currentTarget != null && this.currentTarget.hurtTime == 10) {
         this.chancePassed = Math.random() <= this.chance.getValue() / 100.0;
      }

      if (objectMouseOver != null && (objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY || this.reduceClickingInAir.getValue()) && objectMouseOver.entityHit instanceof EntityLivingBase) {
         if (this.currentTarget != null && this.currentTarget.hurtTime == 10) {
            this.smartHurttimeStart.nextValue();
            this.smartHurttimeEnd.nextValue();
         }

         if (this.chancePassed) {
            if (this.currentTarget != null) {
               boolean flag = this.currentTarget.hurtTime > this.smartHurttimeStart.getLastIntValue() && this.currentTarget.hurtTime <= this.smartHurttimeEnd.getLastIntValue();
               float rotationYaw = mc.thePlayer.rotationYaw;
               boolean flag2 = CombatOptimizationUtil.shouldReduceSprintKnockback(this.currentTarget, rotationYaw);
               if (this.smartHurttime.getValue()) {
                  if ((System.currentTimeMillis() - this.lastEntityAttackTimeMs) > 600L && this.lastAttackedEntityHurtResistantTime > 4) {
                     this.smartHurtTimeArmed = true;
                  }

                  if (!flag && this.currentTarget.hurtTime > 0 && flag2 && !this.smartHurtTimeArmed && System.currentTimeMillis() - this.smartHurtTimeTriggerMs < 500L) {
                     flag = true;
                  }
               }

               if (flag && flag2) {
                  this.shouldSuppressClick = true;
               }

               if (!flag2 && this.hasKnockbackEnchantment() && this.extraClicksWithKnockbackEnchantmentBool.getValue()) {
                  if (!this.lastClickTimer.reached(500L)) {
                     this.extraKnockbackClickPending = true;
                  }
               }

               if (ClientManagers.moduleManager.criticals.isToggled()) {
                  Class<LagCriticalsMode> clazz = LagCriticalsMode.class;
                  if (ClientManagers.moduleManager.criticals.mode.isSubModeActive(clazz)) {
                     if (LagCriticalsMode.isDelayingPackets() && this.lagCritClickCount == 0) {
                        this.shouldSuppressClick = false;
                        this.trackingLagCritClicks = true;
                     } else {
                        this.trackingLagCritClicks = false;
                        this.lagCritClickCount = 0;
                     }

                     return;
                  }
               }

               this.trackingLagCritClicks = false;
            }
         }
      } else {
         this.bypassMaxDuration = false;
      }
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (event.getPacket() instanceof S27PacketExplosion) {
         S27PacketExplosion packet = (S27PacketExplosion)event.getPacket();
         if (Math.abs(packet.getMotionX()) > 0.0F || Math.abs(packet.getMotionZ()) > 0.0F) {
            this.lastKnockbackTimeMs = System.currentTimeMillis();
         }
      }

      if (event.getPacket() instanceof S12PacketEntityVelocity) {
         S12PacketEntityVelocity packet2 = (S12PacketEntityVelocity)event.getPacket();
         if (mc.thePlayer != null && packet2.getEntityID() == mc.thePlayer.getEntityId() && (Math.abs(packet2.getMotionX()) > 0 || Math.abs(packet2.getMotionZ()) > 0)) {
            this.lastKnockbackTimeMs = System.currentTimeMillis();
         }
      }
   }

   private boolean hasKnockbackEnchantment() {
      return EnchantmentHelper.getKnockbackModifier(mc.thePlayer) > 0;
   }

   private boolean isRatioStrengthVisible() {
      return this.ratioBypass.getValue() && this.ratioBypass.isVisible();
   }

   private boolean isRatioBypassVisible() {
      return !this.fakeAttacks.getValue();
   }





}
