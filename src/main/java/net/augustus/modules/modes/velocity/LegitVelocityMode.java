package net.augustus.modules.modes.velocity;

import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.settings.IntervalNoiseSetting;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.TickCounter;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.utils.rotation.RotationUtil;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;

public class LegitVelocityMode extends VelocityMode implements MoveInputListener {
   private final BooleanValue jumpInInv;
   public final StringValue legitJumpDelayMode;
   private final RandomizedRangeSettings jumpInterval;
   private final RandomizedRangeSettings tickDelayBetweenJumps;
   public final DoubleValue jumpChanceLegit;
   public final IntervalNoiseSetting dynamicChance;
   private final SettingGroup legitJumpFailGroup;
   private final BooleanValue legitFailJump;
   private final RandomizedRangeSettings failJumpDelay;
   private final RandomizedRangeSettings inReachDistance;
   private final RandomizedRangeSettings targetInReachTickOffset;
   public final BooleanValue checkFallDamageLegit;
   public final DoubleValue fallDistanceLegit;
   public final DoubleValue fallDistanceBufferSizeLegit;
   public final BooleanValue notWhileSpeedLegit;
   public final BooleanValue notWhileJumpBoostLegit;
   public final BooleanValue debug;
   private int hitsSinceLastJump;
   private int targetInReachTicks;
   private int successfulJumpCount;
   private DoubleSampleWindow fallDistanceHistory;
   private final TickCounter tickCounter;

   public LegitVelocityMode(Velocity velocity, SettingGroup doubleValue) {
       super(velocity, doubleValue, "Legit");
      this.jumpInInv = (BooleanValue)new BooleanValue(this.parentModule, "JumpInInv", false, this.modeSetting).withVisibility(() -> this.isSelected());
      StringValue setting = new StringValue(
         this.parentModule, "LegitJumpDelayMode", "IntervalDelay", new String[]{"IntervalDelay", "Chance", "DynamicChance"}, this.modeSetting
      );
      setting = (StringValue)setting.withDisplayName("JumpDelayMode");
      this.legitJumpDelayMode = (StringValue)setting.withVisibility(() -> this.isSelected());
      RandomizedRangeSettings randomizedRangeSettings2 = (RandomizedRangeSettings)new RandomizedRangeSettings(this.parentModule, "JumpInterval", 0.0, 0.0, 0.0, 5.0, 0, this.modeSetting).withVisibility(() -> this.isJumpIntervalVisible());
      double d = 30.0;
      double d2 = 0.0;
      double d3 = 5.0;
      double d4 = 0.0;
      double d5 = 3.0;
      RandomizedRangeSettings randomizedRangeSettings3 = randomizedRangeSettings2.withOutliers(0.0, d5, d4, d3, d2, d, false);
      double d7 = 5.0;
      double d8 = 0.5;
      this.jumpInterval = randomizedRangeSettings3.withNormalDistribution(1.0, d8, d7, false);
      this.tickDelayBetweenJumps = (RandomizedRangeSettings)(new RandomizedRangeSettings(this.parentModule, "TickDelayBetweenJumps", 10.0, 10.0, 0.0, 100.0, 0, this.modeSetting)).withoutNotInARow().withVisibility(() -> this.isTickDelayBetweenJumpsVisible());
      this.jumpChanceLegit = new DoubleValue(this.parentModule, "JumpChanceLegit", 50.0, 0.0, 100.0, 0, this.modeSetting).named("JumpChance").visibleWhen(() -> this.isJumpChanceVisible());
      IntervalNoiseSetting intervalNoiseSetting = new IntervalNoiseSetting(this.parentModule, "Dynamic Chance", this.modeSetting, false);
      double d10 = 69.0;
      double d11 = 67.0;
      double d12 = 100.0;
      this.dynamicChance = intervalNoiseSetting.withInterval(0.0, d12, d11, d10, (byte)1).visibleWhen(() -> this.isDynamicChanceVisible());
      this.legitJumpFailGroup = (SettingGroup)new SettingGroup(this.parentModule, "LegitJumpFailGroup", this.modeSetting).visibleWhen(() -> this.isSelected()).withDisplayName("Fail Jump");
      this.legitFailJump = (BooleanValue)((BooleanValue)new BooleanValue(this.parentModule, "LegitFailJump", false, this.legitJumpFailGroup).withVisibility(() -> this.isSelected())).withDisplayName("Fail jump");
      RandomizedRangeSettings randomizedRangeSettings5 = new RandomizedRangeSettings(this.parentModule, "FailJumpDelay", 10.0, 20.0, 0.0, 100.0, 0, this.legitJumpFailGroup);
      BooleanValue legitFailJump2 = this.legitFailJump;
      this.failJumpDelay = (RandomizedRangeSettings)randomizedRangeSettings5.withVisibility(() -> legitFailJump2.getValue());
      RandomizedRangeSettings randomizedRangeSettings6 = (new RandomizedRangeSettings(this.parentModule, "In reach distance", 3.0, 3.0, 2.0, 4.0, 2, this.legitJumpFailGroup)).withoutNotInARow();
      BooleanValue legitFailJump3 = this.legitFailJump;
      this.inReachDistance = (RandomizedRangeSettings)randomizedRangeSettings6.withVisibility(() -> legitFailJump3.getValue());
      RandomizedRangeSettings randomizedRangeSettings7 = new RandomizedRangeSettings(this.parentModule, "Target in reach tick offset", 0.0, 0.0, 0.0, 20.0, 0, this.legitJumpFailGroup);
      BooleanValue legitFailJump4 = this.legitFailJump;
      this.targetInReachTickOffset = (RandomizedRangeSettings)randomizedRangeSettings7.withVisibility(() -> legitFailJump4.getValue());
      this.checkFallDamageLegit = (BooleanValue)((BooleanValue)new BooleanValue(this.parentModule, "CheckFallDamageLegit", true, this.modeSetting).withDisplayName("CheckFallDamage")).withVisibility(() -> this.isSelected());
      this.fallDistanceLegit = new DoubleValue(this.parentModule, "FallDistanceLegit", 3.0, 1.0, 10.0, 2, this.modeSetting).named("FallDistance").visibleWhen(() -> this.isFallDistanceVisible());
      this.fallDistanceBufferSizeLegit = new DoubleValue(this.parentModule, "FallDistanceBufferSizeLegit", 2.0, 1.0, 5.0, 0, this.modeSetting).named("FallDistanceBufferSize").visibleWhen(() -> this.isFallDistanceBufferVisible());
      this.notWhileSpeedLegit = (BooleanValue)((BooleanValue)new BooleanValue(this.parentModule, "NotWhileSpeedLegit", true, this.modeSetting).withDisplayName("NotWhileSpeed")).withVisibility(() -> this.isSelected());
      this.notWhileJumpBoostLegit = (BooleanValue)((BooleanValue)new BooleanValue(this.parentModule, "NotWhileJumpBoostLegit", true, this.modeSetting).withDisplayName("NotWhileJumpBoost")).withVisibility(() -> this.isSelected());
      this.debug = (BooleanValue)((BooleanValue)new BooleanValue(this.parentModule, "Debug", false, this.modeSetting).withDisplayName("Debug")).withVisibility(() -> this.isSelected());
      this.hitsSinceLastJump = 0;
      this.targetInReachTicks = 0;
      this.successfulJumpCount = 0;
      this.fallDistanceHistory = new DoubleSampleWindow(5);
      this.tickCounter = new TickCounter();
      this.dynamicChance.addDefaultLayer();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(21);
   }

   @Override
   public void onEnable() {
      this.resetJumpDelays();
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new LegitVelocityMode((Velocity)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
   }

   @Override
   public void onPostJumpMoveInput(EventMoveInput event) {
      if (this.fallDistanceHistory.getMaxSize() != (int)this.fallDistanceBufferSizeLegit.getValue(true)) {
         this.fallDistanceHistory = new DoubleSampleWindow((int)this.fallDistanceBufferSizeLegit.getValue(true));
      }

      this.tickCounter.increment();
      if (!this.canApplyVelocity()) {
         double fallDistance = (double)mc.thePlayer.fallDistance;
         this.fallDistanceHistory.add(fallDistance);
      } else {
         if (this.debug.getValue()
            && !mc.thePlayer.onGround
            && mc.thePlayer.hurtTime >= 9
            && (!this.checkFallDamageLegit.getValue() || this.fallDistanceHistory.max() < this.fallDistanceLegit.getValue())) {
            ((Velocity)this.parentModule).sendChatMessage("Currently in air, not jumping even though i got hit tE=" + mc.thePlayer.ticksExisted);
         }

         if (this.canPerformLegitJump()) {
            if (mc.thePlayer.hurtTime >= 9) {
               if (this.shouldJumpThisHit()) {
                  this.successfulJumpCount++;
                  if (this.debug.getValue()) {
                     ((Velocity)this.parentModule).sendChatMessage("Jumped! tE=" + mc.thePlayer.ticksExisted);
                  }

                  event.setJump(true);
                  this.resetJumpDelays();
                  this.hitsSinceLastJump++;
                  return;
               }

               if (this.debug.getValue()) {
                  ((Velocity)this.parentModule).sendChatMessage("No jump because of randomization tE=" + mc.thePlayer.ticksExisted);
               }

               this.hitsSinceLastJump++;
            } else if (mc.thePlayer.hurtTime == 0) {
               if (CombatTargetUtil.getActiveTarget() != null) {
                  EntityLivingBase activeTarget = CombatTargetUtil.getActiveTarget();
                  if (RotationUtil.getNearestPointFromEntity(activeTarget, mc.thePlayer).distanceTo(activeTarget.getPositionEyes(1.0F)) <= this.inReachDistance.getLastValue()) {
                     this.targetInReachTicks++;
                     if (this.targetInReachTicks >= this.targetInReachTickOffset.getLastIntValue() && this.failJumpDelay.getLastIntValue() <= this.successfulJumpCount) {
                        if (this.debug.getValue()) {
                           ((Velocity)this.parentModule).sendChatMessage("Jumping too early... tE=" + mc.thePlayer.ticksExisted);
                        }

                        this.successfulJumpCount = 0;
                        this.failJumpDelay.nextInt();
                        event.setJump(true);
                     }
                  } else {
                     this.targetInReachTicks = 0;
                  }
               } else {
                  this.targetInReachTicks = 0;
               }
            }
         } else {
            this.targetInReachTicks = 0;
         }

         double fallDistance2 = (double)mc.thePlayer.fallDistance;
         this.fallDistanceHistory.add(fallDistance2);
      }
   }

   private boolean canPerformLegitJump() {
      return mc.thePlayer.isSprinting()
         && (mc.isNotInGui() || this.jumpInInv.getValue())
         && (this.fallDistanceHistory.max() < this.fallDistanceLegit.getValue() || !this.checkFallDamageLegit.getValue())
         && !mc.thePlayer.isBurning()
         && mc.thePlayer.onGround
         && this.passesPotionChecks();
   }

   private boolean shouldJumpThisHit() {
      switch (this.legitJumpDelayMode.getSelectedValue()) {
         case "IntervalDelay":
            return this.tickCounter.hasReached(this.tickDelayBetweenJumps.getLastIntValue()) && this.hitsSinceLastJump > this.jumpInterval.getLastIntValue();
         case "Chance":
            return (double)DefaultRandomGenerator.instance.nextInt((byte)0, (byte)99) < this.jumpChanceLegit.getValue();
         case "DynamicChance":
            this.dynamicChance.update();
            double d = this.dynamicChance.sample();
            float f = 99.0F;
            float f2 = 0.0F;
            boolean flag = (double)DefaultRandomGenerator.instance.nextFloat(f2, f) < d;
            if (this.debug.getValue()) {
               ChatUtil.sendChat("Chance: " + d + " jump=" + flag);
            }

            return flag;
         default:
            return false;
      }
   }

   private void resetJumpDelays() {
      this.tickDelayBetweenJumps.nextValue();
      this.jumpInterval.nextValue();
      this.tickCounter.reset();
      this.hitsSinceLastJump = 0;
   }

   private boolean passesPotionChecks() {
      return (!this.notWhileSpeedLegit.getValue() || !mc.thePlayer.isPotionActive(Potion.moveSpeed)) && (!this.notWhileJumpBoostLegit.getValue() || !mc.thePlayer.isPotionActive(Potion.jump));
   }

   private boolean isFallDistanceBufferVisible() {
      return this.isSelected() && this.checkFallDamageLegit.getValue();
   }

   private boolean isFallDistanceVisible() {
      return this.isSelected() && this.checkFallDamageLegit.getValue();
   }

   private boolean isDynamicChanceVisible() {
      return this.isSelected() && this.legitJumpDelayMode.getSelectedValue().equals("DynamicChance");
   }

   private boolean isJumpChanceVisible() {
      return this.isSelected() && this.legitJumpDelayMode.getSelectedValue().equals("Chance");
   }

   private boolean isTickDelayBetweenJumpsVisible() {
      return this.isSelected() && this.legitJumpDelayMode.getSelectedValue().equals("IntervalDelay");
   }

   private boolean isJumpIntervalVisible() {
      return this.isSelected() && this.legitJumpDelayMode.getSelectedValue().equals("IntervalDelay");
   }

}
