package net.augustus.modules.modes.velocity;

import net.augustus.Augustus;
import net.augustus.events.EventAttackSlowdown;
import net.augustus.events.EventClick;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventPacket;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.listeners.AttackSlowdownListener;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.combat.CombatOptimizationUtil;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.utils.rotation.EntityBoundsUtil;
import net.augustus.utils.rotation.RotationUtil;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class IntaveVelocityMode extends VelocityMode implements PacketListener, AttackSlowdownListener, PacketDelayListener, MoveInputListener, ClickListener {
   public final DoubleValue xzOnHit;
   public final DoubleValue xzonhitknockbackitem2;
   public final DoubleValue xzOnHitSprint;
   public final DoubleValue xzOnHitAtDoubleHit;
   public final BooleanValue forceDoubleHit;
   public final BooleanValue intaveSmart;
   public final BooleanValue intaveBoost;
   private final SettingGroup boostGroup;
   public final DoubleValue intaveBoostDelay;
   public final DoubleValue intaveBoostOnGroundTicks;
   public final DoubleValue xzVelocityIntave;
   public final DoubleValue yVelocityIntave;
   public final BooleanValue sneakInAir;
   private final TimeHelper timeHelper;
   private boolean boostPending;
   private boolean doubleHitTriggered;

   public IntaveVelocityMode(Velocity velocity, SettingGroup doubleValue) {
       super(velocity, doubleValue, "Intave");
      this.xzOnHit = new DoubleValue(this.parentModule, "XZOnHit", 0.6, -1.0, 1.0, 2, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.xzonhitknockbackitem2 = new DoubleValue(this.parentModule, "XZOnHitKnockbackItem_2", 0.6, -1.0, 1.0, 2, this.modeSetting).visibleWhen(() -> this.isSelected()).named("XZOnHitKnockbackItem");
      this.xzOnHitSprint = new DoubleValue(this.parentModule, "XZOnHitSprint", 0.6, -1.0, 1.0, 2, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.xzOnHitAtDoubleHit = new DoubleValue(this.parentModule, "XZOnHitAtDoubleHit", 1.0, -1.0, 1.0, 2, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.forceDoubleHit = (BooleanValue)new BooleanValue(this.parentModule, "ForceDoubleHit", false, this.modeSetting).withVisibility(() -> this.isForceDoubleHitVisible());
      this.intaveSmart = (BooleanValue)new BooleanValue(this.parentModule, "IntaveSmart", false, this.modeSetting).withVisibility(() -> this.isSelected());
      this.intaveBoost = (BooleanValue)new BooleanValue(this.parentModule, "IntaveBoost", false, this.modeSetting).withVisibility(() -> this.isSelected());
      this.boostGroup = new SettingGroup(this.parentModule, "BoostGroup", true, this.modeSetting).visibleWhen(() -> this.isBoostGroupVisible());
      this.intaveBoostDelay = new DoubleValue(this.parentModule, "IntaveBoostDelay", 2000.0, 0.0, 10000.0, 0, this.boostGroup).visibleWhen(() -> this.isBoostDelayVisible());
      this.intaveBoostOnGroundTicks = new DoubleValue(this.parentModule, "IntaveBoostOnGroundTicks", 2.0, 0.0, 10.0, 0, this.boostGroup).visibleWhen(() -> this.isBoostOnGroundTicksVisible());
      this.xzVelocityIntave = new DoubleValue(this.parentModule, "XZVelocityIntave", 100.0, -300.0, 300.0, 0, this.boostGroup).named("XZVelocity").visibleWhen(() -> this.isBoostXZVelocityVisible());
      this.yVelocityIntave = new DoubleValue(this.parentModule, "YVelocityIntave", 100.0, -100.0, 200.0, 0, this.boostGroup).named("YVelocity").visibleWhen(() -> this.isBoostYVelocityVisible());
      this.sneakInAir = (BooleanValue)new BooleanValue(this.parentModule, "SneakInAir", false, this.boostGroup).withVisibility(() -> this.isSneakInAirVisible());
      this.timeHelper = new TimeHelper();
      this.doubleHitTriggered = false;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(25);
      this.toggleEventSubscription(2, 100);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(7, (short)1000);
   }

   @Override
   public void onEnable() {
      this.boostPending = false;
      this.timeHelper.reset();
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new IntaveVelocityMode((Velocity)obj, (SettingGroup)obj2);
   }

   @Override
   public void onClick(EventClick event) {
      if (this.forceDoubleHit.getValue() && !this.doubleHitTriggered && mc.thePlayer.hurtTime != 0 && mc.objectMouseOver != null && mc.objectMouseOver.entityHit instanceof EntityLivingBase) {
         if ((event.getLeftClicks() + (event.isIgnoreVanillaClicks() ? 0 : mc.gameSettings.keyBindAttack.getPressTime())) == 1) {
            event.addLeftClicks((byte)1);
            this.doubleHitTriggered = true;
         }
      }

      if (mc.thePlayer.hurtTime == 0) {
         this.doubleHitTriggered = false;
      }
   }

   @Override
   public void onAttackSlowdownSecond(EventAttackSlowdown event) {
      if (this.canApplyVelocity()) {
         boolean flag = true;
         if (this.intaveSmart.getValue() && mc.objectMouseOver.entityHit instanceof EntityLivingBase) {
            double hitMotionMultiplier = this.getHitMotionMultiplier();
            float rotationYaw = mc.thePlayer.rotationYaw;
            Vec3 vec = PlayerUtil.predictNextTickWithAttack(true, mc.objectMouseOver.entityHit, hitMotionMultiplier, rotationYaw).addVector(0.0, (double)mc.thePlayer.getEyeHeight(), 0.0);
            float rotationYaw2 = mc.thePlayer.rotationYaw;
            Vec3 vec2 = PlayerUtil.predictNextTickWithAttack(false, mc.objectMouseOver.entityHit, hitMotionMultiplier, rotationYaw2)
               .addVector(0.0, (double)mc.thePlayer.getEyeHeight(), 0.0);
            float collisionBorderSize = mc.objectMouseOver.entityHit.getCollisionBorderSize();
            AxisAlignedBB box = EntityBoundsUtil.createPlayerBoundingBox(CombatOptimizationUtil.getPredictedEntityPosition(mc.objectMouseOver.entityHit), false).expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
            Vec3 vec4 = RotationUtil.clampPointToBox(vec, box);
            Vec3 vec5 = RotationUtil.clampPointToBox(vec2, box);
            if (vec.distanceTo(vec4) >= vec2.distanceTo(vec5)) {
               flag = false;
            }
         }

         if (mc.thePlayer.hurtTime != 0 && flag && (((Velocity)this.parentModule).attackSlowdownCount == 0 || this.xzOnHitAtDoubleHit.getValue() != 1.0)) {
            double hitMotionMultiplier2 = this.getHitMotionMultiplier();
            event.setApplySecondStage(true);
            event.setSlowDownFactor(((Velocity)this.parentModule).attackSlowdownCount == 0 ? hitMotionMultiplier2 : this.xzOnHitAtDoubleHit.getValue());
            event.setSprint(false);
         }
      }
   }

   @Override
   public void onAttackSlowdownFirst(EventAttackSlowdown event) {
      if (this.canApplyVelocity()) {
         if (mc.thePlayer.hurtTime != 0 && ((Velocity)this.parentModule).attackSlowdownCount == 0) {
            event.setSlowDownFactor(this.xzOnHitSprint.getValue());
            event.setSprint(false);
            if (EnchantmentHelper.getKnockbackModifier(mc.thePlayer) > 0) {
               event.setSlowDownFactor(this.xzonhitknockbackitem2.getValue());
            }
         }
      }
   }

   @Override
   public void onIncomingPacketDelay(EventPacketDelay event) {
      if (this.canApplyVelocity()) {
         Packet packet = event.getPacket();
         EntityPlayerSP thePlayer = mc.thePlayer;
         if (packet instanceof S12PacketEntityVelocity
            && thePlayer != null
            && thePlayer.onGround
            && MoveUtil.isMoving()
            && !thePlayer.isCollidedHorizontally
            && this.intaveBoost.getValue()
            && (double)thePlayer.onGroundTicks > this.intaveBoostOnGroundTicks.getValue()) {
            if (((S12PacketEntityVelocity)packet).getEntityID() == thePlayer.getEntityId()) {
               if (this.timeHelper.reached(this.intaveBoostDelay.getValue())) {
                  this.timeHelper.reset();
                  this.boostPending = true;
               }
            }
         }

         event.setDelayed(thePlayer != null && thePlayer.ticksExisted > 20 && this.boostPending && this.intaveBoost.getValue());
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.canApplyVelocity()) {
         if (!this.intaveBoost.getValue()) {
            this.boostPending = false;
         } else {
            if (this.boostPending) {
               if (mc.thePlayer.onGround) {
                  event.setSneak(true);
                  event.setMoveForward(0.0F);
               }

               this.boostPending = false;
            }

            if (this.sneakInAir.getValue() && mc.thePlayer.hurtTime > 2) {
               event.setSneak(true);
            }
         }
      }
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (!event.isCancelled()) {
         Packet packet = event.getPacket();
         if (packet instanceof S12PacketEntityVelocity) {
            S12PacketEntityVelocity packet2 = (S12PacketEntityVelocity)packet;
            if (mc.thePlayer != null && packet2.getEntityID() == mc.thePlayer.getEntityId() && this.canApplyVelocity()) {
               if (mc.thePlayer != null
                  && mc.thePlayer.onGround
                  && MoveUtil.isMoving()
                  && !mc.thePlayer.isCollidedHorizontally
                  && this.intaveBoost.getValue()
                  && (double)mc.thePlayer.onGroundTicks > this.intaveBoostOnGroundTicks.getValue()
                  && packet2.getEntityID() == mc.thePlayer.getEntityId()) {
                  if (this.timeHelper.reached(this.intaveBoostDelay.getValue())) {
                     if (this.xzVelocityIntave.getValue() == 0.0 && this.yVelocityIntave.getValue() == 0.0) {
                        event.cancel();
                        return;
                     }

                     packet2.setMotionX((int)((double)packet2.getMotionX() * this.xzVelocityIntave.getValue() / 100.0));
                     packet2.setMotionY((int)((double)packet2.getMotionY() * this.yVelocityIntave.getValue() / 100.0));
                     packet2.setMotionZ((int)((double)packet2.getMotionZ() * this.xzVelocityIntave.getValue() / 100.0));
                  }
               }
            }
         }
      }
   }

   private double getHitMotionMultiplier() {
      return mc.thePlayer != null && EnchantmentHelper.getKnockbackModifier(mc.thePlayer) > 0 ? this.xzonhitknockbackitem2.getValue() : this.xzOnHit.getValue();
   }

   private boolean isSneakInAirVisible() {
      return this.isSelected() && this.intaveBoost.getValue();
   }

   private boolean isBoostYVelocityVisible() {
      return this.isSelected() && this.intaveBoost.getValue();
   }

   private boolean isBoostXZVelocityVisible() {
      return this.isSelected() && this.intaveBoost.getValue();
   }

   private boolean isBoostOnGroundTicksVisible() {
      return this.isSelected() && this.intaveBoost.getValue();
   }

   private boolean isBoostDelayVisible() {
      return this.isSelected() && this.intaveBoost.getValue();
   }

   private boolean isBoostGroupVisible() {
      return this.isSelected() && this.intaveBoost.getValue();
   }

   private boolean isForceDoubleHitVisible() {
      return this.isSelected() && this.xzOnHitAtDoubleHit.getValue() != 1.0;
   }

}
