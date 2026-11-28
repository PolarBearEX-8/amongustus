package net.augustus.modules.combat;

import net.minecraft.util.MovingObjectPosition;
import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.events.EventOrientCamera;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.utils.combat.CombatOptimizationUtil;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.TickCounter;
import net.augustus.events.listeners.OrientCameraListener;
import net.augustus.events.EventAttackEntity;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.utils.UnaryFunction;
import net.augustus.settings.options.MoreKbModeOption;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class MoreKB extends Module implements LoadWorldListener, MoveInputListener, TickListener, OrientCameraListener, PacketListener, AttackEntityListener {
   private final MultiOptionSetting modes;
   private final SettingGroup modeSettings;
   private BooleanValue hideSneakAnimation;
   private final DoubleValue fov;
   private final BooleanValue ignoreEatingTargets;
   private final BooleanValue onlyAtSprinting;
   private final BooleanValue dontSprintResetIfYouAlreadyDidLegit;
   private final BooleanValue waitForDamage;
   private final SettingGroup timing;
   private final RandomizedRangeSettings sprintResetTime;
   private final RandomizedRangeSettings minimalSprintResetDelay;
   private final SettingGroup duration;
   private final RandomizedRangeSettings sprintResetTicks;
   private final SettingGroup doubleReset;
   private final BooleanValue doubleSprintReset;
   private final RandomizedRangeSettings doubleResetTiming;
   private final RandomizedRangeSettings doubleResetDelay;
   private final BooleanValue noDoubleSprintresetWhenReducingNeeded;
   private final SettingGroup fail;
   private final BooleanValue failSprintReset;
   private final RandomizedRangeSettings failDelay;
   private final RandomizedRangeSettings failDuration;
   private final BooleanValue onlyWhileKBItem;
   private final BooleanValue onlyWhenNotClicking;
   private final TickCounter tickCounter;
   private final TimeHelper timeHelper;
   private int remainingFailHits;
   private int remainingResetTicks;
   private int hitsSinceLastFail;
   private int ticksSinceHit;
   private boolean pendingSprintReset;
   private EntityLivingBase target;
   private boolean resettingSprint;
   private int ticksSinceLegitSneak;
   private boolean doubleResetPerformed;
   private boolean sentStartSprintingPacket;
   private boolean restartedSprintBeforeHit;
   private boolean legitResetDetected;
   private MoreKbModeOption activeMode;
   private int minDelayTicks;

   public MoreKB() {
       super("MoreKB", "MehrRückstoß", new Color(252, 73, 3), Categorys.COMBAT);
      this.modes = new MultiOptionSetting(this, "Modes", MoreKbModeOption.values());
      this.modeSettings = new SettingGroup(this, "ModeSettings", true);
      this.fov = new DoubleValue(this, "FOV", 180.0, 20.0, 180.0, 0);
      this.ignoreEatingTargets = new BooleanValue(this, "IgnoreEatingTargets", false);
      this.onlyAtSprinting = new BooleanValue(this, "OnlyAtSprinting", true);
      this.dontSprintResetIfYouAlreadyDidLegit = new BooleanValue(this, "DontSprintResetIfYouAlreadyDidLegit", false);
      this.waitForDamage = new BooleanValue(this, "WaitForDamage", false);
      this.timing = new SettingGroup(this, "Timing", true);
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(this, "SprintResetTime", 3.0, 5.0, 0.0, 10.0, 0, this.timing)).withMaxInARow();
      double d = 30.0;
      double d2 = 15.0;
      double d3 = 10.0;
      double d4 = 0.0;
      double d5 = 7.0;
      randomizedRangeSettings = randomizedRangeSettings.withOutliers(1.0, d5, d4, d3, d2, d);
      double d7 = 5.0;
      double d8 = 0.5;
      this.sprintResetTime = randomizedRangeSettings.withNormalDistribution(1.0, d8, d7, false);
      this.minimalSprintResetDelay = new RandomizedRangeSettings(this, "MinimalSprintResetDelay", 0.0, 0.0, 0.0, 20.0, 0, this.timing);
      this.duration = new SettingGroup(this, "Duration", true);
      randomizedRangeSettings = (new RandomizedRangeSettings(this, "SprintResetTicks", 1.0, 1.0, 1.0, 5.0, 0, this.duration)).withMaxInARow();
      double d10 = 3.0;
      double d11 = 0.0;
      double d12 = 5.0;
      double d13 = 1.0;
      double d14 = 2.0;
      randomizedRangeSettings = randomizedRangeSettings.withOutliers(2.0, d14, d13, d12, d11, d10);
      randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("SprintResetDuration");
      double d16 = 4.0;
      double d17 = 0.5;
      this.sprintResetTicks = randomizedRangeSettings.withNormalDistribution(1.0, d17, d16, false);
      this.doubleReset = new SettingGroup(this, "DoubleReset", true);
      this.doubleSprintReset = new BooleanValue(this, "DoubleSprintReset", true, this.doubleReset);
      randomizedRangeSettings = new RandomizedRangeSettings(this, "DoubleResetTiming", 10.0, 14.0, 0.0, 20.0, 0, this.doubleReset);
      double d19 = 5.0;
      double d20 = 0.5;
      randomizedRangeSettings = randomizedRangeSettings.withNormalDistribution(1.0, d20, d19, false);
      BooleanValue doubleSprintReset2 = this.doubleSprintReset;
      BooleanValue booleanValue = doubleSprintReset2;
      this.doubleResetTiming = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> booleanValue.getValue());
      randomizedRangeSettings = (new RandomizedRangeSettings(this, "DoubleResetDelay", 10.0, 15.0, 0.0, 30.0, 0, this.doubleReset)).withoutNotInARow();
      double d22 = 15.0;
      double d23 = 10.0;
      double d24 = 30.0;
      double d25 = 0.0;
      double d26 = 20.0;
      randomizedRangeSettings = randomizedRangeSettings.withOutliers(5.0, d26, d25, d24, d23, d22);
      doubleSprintReset2 = this.doubleSprintReset;
      BooleanValue booleanValue2 = doubleSprintReset2;
      this.doubleResetDelay = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> booleanValue2.getValue());
      this.noDoubleSprintresetWhenReducingNeeded = new BooleanValue(this, "No double sprintreset when reducing needed", false, this.doubleReset);
      this.fail = new SettingGroup(this, "Fail", true);
      this.failSprintReset = new BooleanValue(this, "FailSprintReset", true, this.fail);
      RandomizedRangeSettings randomizedRangeSettings3 = ((RandomizedRangeSettings)new RandomizedRangeSettings(this, "FailDelay", 15.0, 18.0, 0.0, 150.0, 0, this.fail).withDisplayNamePrefix("FailInterval")).withoutNotInARow();
      BooleanValue failSprintReset2 = this.failSprintReset;
      this.failDelay = (RandomizedRangeSettings)randomizedRangeSettings3.withVisibility(() -> failSprintReset2.getValue());
      RandomizedRangeSettings randomizedRangeSettings4 = new RandomizedRangeSettings(this, "FailDuration", 1.0, 2.0, 1.0, 10.0, 0, this.fail);
      double d28 = 5.0;
      double d29 = 0.5;
      RandomizedRangeSettings randomizedRangeSettings5 = randomizedRangeSettings4.withNormalDistribution(1.0, d29, d28, false);
      BooleanValue failSprintReset3 = this.failSprintReset;
      this.failDuration = (RandomizedRangeSettings)randomizedRangeSettings5.withVisibility(() -> failSprintReset3.getValue());
      this.onlyWhileKBItem = new BooleanValue(this, "OnlyWhileKBItem", false);
      BooleanValue booleanValue5 = new BooleanValue(this, "OnlyWhenNotClicking", true);
      BooleanValue onlyAtSprinting2 = this.onlyAtSprinting;
      this.onlyWhenNotClicking = (BooleanValue)booleanValue5.withVisibility(() -> onlyAtSprinting2.getValue());
      this.tickCounter = new TickCounter();
      this.timeHelper = new TimeHelper();
      this.remainingFailHits = 0;
      this.remainingResetTicks = 0;
      this.hitsSinceLastFail = 0;
      this.ticksSinceHit = 0;
      this.pendingSprintReset = true;
      this.resettingSprint = false;
      this.ticksSinceLegitSneak = 0;
      this.doubleResetPerformed = false;
      this.sentStartSprintingPacket = false;
      this.restartedSprintBeforeHit = false;
      this.legitResetDetected = false;
      this.minDelayTicks = 0;

      for (MoreKbModeOption moreKbModeOption : MoreKbModeOption.values()) {
         MoreKbModeOption.initOptionSettings(moreKbModeOption, this, this.modeSettings);
         if (moreKbModeOption == MoreKbModeOption.LEGIT_SNEAK) {
            this.hideSneakAnimation = new BooleanValue(this, "HideSneakAnimation", true, MoreKbModeOption.getSettingsGroup(moreKbModeOption));
         }
      }

      String str3 = "Only sprintresets if the player is holding an item with knockback enchantment";
      this.onlyWhileKBItem.setDescription(str3);
   }

   public static void initialize() {
   }

   @Override
   public void onEnable() {
      this.pendingSprintReset = true;
      this.resettingSprint = false;
      this.restartedSprintBeforeHit = false;
      this.sentStartSprintingPacket = false;
      this.doubleResetDelay.nextValue();
      this.doubleResetTiming.nextValue();
      this.legitResetDetected = false;
      this.activeMode = this.selectWeightedMode();
   }

   @Override
   public void onDisable() {
      this.target = null;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(24);
      this.toggleEventSubscription(17);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(1);
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (event.isSneak() || event.getMoveForward() != 1.0F) {
         this.legitResetDetected = true;
      }

      this.ticksSinceLegitSneak++;
      if (this.activeMode == MoreKbModeOption.LEGIT_SNEAK && this.remainingResetTicks > 0) {
         this.remainingResetTicks--;
         event.setSneak(true);
         this.resettingSprint = true;
         this.ticksSinceLegitSneak = 0;
      }
   }

   @Override
   public void onTick() {
      this.setSuffix(this.activeMode.getName());
      EntityLivingBase entity = this.findTarget();
      if (entity != null) {
         this.target = entity;
      }

      if (this.target != null && this.target.hurtTime == 10) {
         this.legitResetDetected = false;
      }

      if (mc.thePlayer.isBlocking()) {
         this.legitResetDetected = true;
      }

      if (this.legitResetDetected && this.dontSprintResetIfYouAlreadyDidLegit.getValue()) {
         this.doubleResetPerformed = false;
         this.pendingSprintReset = false;
         this.remainingResetTicks = 0;
      }

      if (mc.thePlayer.hurtTime == 10) {
         this.timeHelper.reset();
      }

      if (this.target != null && this.target.hurtTime == 10 && !this.isBacktrackTooFar() && this.isTargetAllowed()) {
         this.activeMode = this.selectWeightedMode();
         this.sentStartSprintingPacket = false;
         if (((double)RotationUtil.getEntityLookAngleToPlayer(this.target)) <= this.fov.getValue() && (!this.onlyWhileKBItem.getValue() || EnchantmentHelper.getKnockbackModifier(mc.thePlayer) > 0)) {
            this.pendingSprintReset = !this.shouldFailSprintReset();
         }

         this.ticksSinceHit = 0;
         this.target = null;
         this.tickCounter.increment();
         if (this.remainingFailHits <= 0) {
            this.hitsSinceLastFail++;
         }

         this.doubleResetPerformed = false;
      }

      int n;
      if (this.waitForDamage.getValue()) {
if (this.timeHelper.reached(500L)) {
n = -1;
} else {
n = this.ticksSinceHit;
}
} else {
n = this.ticksSinceHit;
}

      boolean isServerSprintState = mc.thePlayer.isServerSprintState();
      if (this.onlyWhenNotClicking.getValue()) {
         isServerSprintState = mc.thePlayer.isSprinting();
      }

      if (this.pendingSprintReset && n == this.sprintResetTime.getLastIntValue() && (!this.onlyAtSprinting.getValue() || isServerSprintState)) {
         this.sprintResetTime.nextSecureValue();
         this.remainingResetTicks = (int)this.sprintResetTicks.nextSecureValue();
         this.pendingSprintReset = false;
         if (ClientManagers.moduleManager.debug.isToggled() && ClientManagers.moduleManager.debug.devToggleA != null && ClientManagers.moduleManager.debug.devToggleA.getValue()) {
            String str = "Sprint rest count: " + n + " " + this.remainingResetTicks;
            ClientManagers.moduleManager.debug.sendChatMessage(str);
         }
      }

      this.resettingSprint = this.remainingResetTicks > 0;
      if (this.remainingResetTicks > 0 && this.activeMode != MoreKbModeOption.LEGIT_SNEAK) {
         this.remainingResetTicks--;
         this.executeSprintReset(false);
      }

      if ((!this.legitResetDetected || !this.dontSprintResetIfYouAlreadyDidLegit.getValue()) && this.doubleSprintReset.getValue() && !this.resettingSprint && n == this.doubleResetTiming.getLastIntValue()) {
         TickCounter tickCounter2 = this.tickCounter;
         int lastIntValue = this.doubleResetDelay.getLastIntValue();
         if (tickCounter2.hasReached(lastIntValue) && !this.doubleResetPerformed) {
            if (!this.noDoubleSprintresetWhenReducingNeeded.getValue()
               || (this.target != null && CombatOptimizationUtil.shouldReduceSprintKnockback(this.target, mc.thePlayer.rotationYaw))) {
               this.executeSprintReset(true);
               this.doubleResetDelay.nextValue();
               this.doubleResetTiming.nextValue();
               this.tickCounter.reset();
               this.doubleResetPerformed = true;
            }
         }
      }

      this.ticksSinceHit++;
   }

   private EntityLivingBase findTarget() {
      EntityLivingBase entity = null;
      if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mc.objectMouseOver.entityHit instanceof EntityLivingBase) {
         entity = (EntityLivingBase)mc.objectMouseOver.entityHit;
      }

      return CombatTargetUtil.getActiveTargetOrDefault(entity);
   }

   private void executeSprintReset(boolean flag) {
      if (!flag && this.minDelayTicks < this.minimalSprintResetDelay.getLastIntValue()) {
         this.minDelayTicks++;
      } else {
         this.minimalSprintResetDelay.nextInt();
         UnaryFunction sprintResetAction = MoreKbModeOption.getSprintResetAction(this.activeMode);
         Boolean bool = flag;
         boolean flag2 = (Boolean)sprintResetAction.apply(bool);
         if (flag2) {
            this.remainingResetTicks = 0;
         }
      }
   }

   private boolean shouldFailSprintReset() {
      if (this.remainingFailHits > 0 && this.restartedSprintBeforeHit) {
         this.remainingFailHits++;
      }

      this.remainingFailHits--;
      if (this.failSprintReset.getValue() && this.hitsSinceLastFail >= this.failDelay.getLastIntValue()) {
         this.remainingFailHits = (int)this.failDuration.nextValue();
         this.hitsSinceLastFail = 0;
         this.failDelay.nextSecureValue();
      }

      return this.remainingFailHits > 0;
   }

   private boolean isBacktrackTooFar() {
      return ClientManagers.moduleManager.backTrack.isToggled()
         && ClientManagers.moduleManager.backTrack.improve.getValue()
         && ClientManagers.moduleManager.backTrack.isBacktracking()
         && mc.thePlayer.getDistance(this.target.realPosX, this.target.realPosY, this.target.realPosZ) > 3.0;
   }

   private boolean isTargetAllowed() {
      return !this.ignoreEatingTargets.getValue() || !this.target.isEating();
   }

   @Override
   public void onOrientCamera(EventOrientCamera event) {
      if (this.ticksSinceLegitSneak < 3 && this.hideSneakAnimation.getValue()) {
         event.setEyeHeight(1.62F);
      }
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.target = null;
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      Entity target2 = event.getTarget();
      if (target2 instanceof EntityLivingBase) {
         if (((EntityLivingBase)target2).hurtTime < 3) {
            this.restartedSprintBeforeHit = this.sentStartSprintingPacket;
            this.sentStartSprintingPacket = false;
         }
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof C0BPacketEntityAction) {
         if (((C0BPacketEntityAction)packet).getAction() == C0BPacketEntityAction.Action.START_SPRINTING) {
            this.sentStartSprintingPacket = true;
         }
      }
   }

   private MoreKbModeOption selectWeightedMode() {
      int n = 0;

      for (MoreKbModeOption moreKbModeOption : MoreKbModeOption.values()) {
         if (moreKbModeOption.isSelected()) {
            int value = (int)MoreKbModeOption.getChanceSetting(moreKbModeOption).getValue(true);
            n += value;
         }
      }

      int n2 = n - 1;
      int n3 = DefaultRandomGenerator.instance.nextInt((byte)0, n2);
      n = 0;

      for (MoreKbModeOption moreKbModeOption2 : MoreKbModeOption.values()) {
         if (moreKbModeOption2.isSelected()) {
            int value2 = (int)MoreKbModeOption.getChanceSetting(moreKbModeOption2).getValue(true);
            if (n3 >= n && n3 < n + value2) {
               return moreKbModeOption2;
            }

            n += value2;
         }
      }

      return MoreKbModeOption.LEGIT;
   }

}
