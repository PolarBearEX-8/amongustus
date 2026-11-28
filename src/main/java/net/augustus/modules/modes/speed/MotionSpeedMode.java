package net.augustus.modules.modes.speed;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.input.KeyboardInputUtil;
import net.augustus.modules.movement.Speed;

public class MotionSpeedMode extends SpeedMode {
   public final RandomizedRangeSettings multiplierAirForward;
   public final RandomizedRangeSettings multiplierGroundForward;
   public final RandomizedRangeSettings multiplierAirBackward;
   public final RandomizedRangeSettings multiplierGroundBackward;
   public final RandomizedRangeSettings multiplierAirSideways;
   public final RandomizedRangeSettings multiplierGroundSideways;
   public final RandomizedRangeSettings multiplierAirForwardSideways;
   public final RandomizedRangeSettings multiplierGroundForwardSideways;
   public final RandomizedRangeSettings multiplierAirBackwardSideways;
   public final RandomizedRangeSettings multiplierGroundBackwardSideways;
   private float moveForwardInput;
   private float moveStrafeInput;

   public MotionSpeedMode(String str, Speed speed2) {
       super(str, speed2);
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(this.speed, " multiplier air forward", 1.0, 1.0, 0.1, 2.0, 2)).withoutNotInARow();
      SettingVisibilityCondition condition = () -> {
         return this.speed.mode.getSelectedValue().equals(this.getName());
      };
      this.multiplierAirForward = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(condition);
      RandomizedRangeSettings randomizedRangeSettings2 = (new RandomizedRangeSettings(this.speed, " multiplier ground forward", 1.0, 1.0, 0.1, 2.0, 2)).withoutNotInARow();
      SettingVisibilityCondition condition2 = () -> {
         return this.speed.mode.getSelectedValue().equals(this.getName());
      };
      this.multiplierGroundForward = (RandomizedRangeSettings)randomizedRangeSettings2.withVisibility(condition2);
      randomizedRangeSettings2 = (new RandomizedRangeSettings(this.speed, " multiplier air backward", 1.0, 1.0, 0.1, 2.0, 2)).withoutNotInARow();
      SettingVisibilityCondition condition3 = () -> {
         return this.speed.mode.getSelectedValue().equals(this.getName());
      };
      this.multiplierAirBackward = (RandomizedRangeSettings)randomizedRangeSettings2.withVisibility(condition3);
      randomizedRangeSettings2 = (new RandomizedRangeSettings(this.speed, " multiplier ground backward", 1.0, 1.0, 0.1, 2.0, 2)).withoutNotInARow();
      SettingVisibilityCondition condition4 = () -> {
         return this.speed.mode.getSelectedValue().equals(this.getName());
      };
      this.multiplierGroundBackward = (RandomizedRangeSettings)randomizedRangeSettings2.withVisibility(condition4);
      randomizedRangeSettings2 = (new RandomizedRangeSettings(this.speed, " multiplier air sideways", 1.0, 1.0, 0.1, 2.0, 2)).withoutNotInARow();
      SettingVisibilityCondition condition5 = () -> {
         return this.speed.mode.getSelectedValue().equals(this.getName());
      };
      this.multiplierAirSideways = (RandomizedRangeSettings)randomizedRangeSettings2.withVisibility(condition5);
      randomizedRangeSettings2 = (new RandomizedRangeSettings(this.speed, " multiplier ground sideways", 1.0, 1.0, 0.1, 2.0, 2)).withoutNotInARow();
      SettingVisibilityCondition condition6 = () -> {
         return this.speed.mode.getSelectedValue().equals(this.getName());
      };
      this.multiplierGroundSideways = (RandomizedRangeSettings)randomizedRangeSettings2.withVisibility(condition6);
      RandomizedRangeSettings randomizedRangeSettings3 = (new RandomizedRangeSettings(this.speed, " multiplier air forward-sideways", 1.0, 1.0, 0.1, 2.0, 2)).withoutNotInARow();
      SettingVisibilityCondition condition7 = () -> {
         return this.speed.mode.getSelectedValue().equals(this.getName());
      };
      this.multiplierAirForwardSideways = (RandomizedRangeSettings)randomizedRangeSettings3.withVisibility(condition7);
      RandomizedRangeSettings randomizedRangeSettings4 = (new RandomizedRangeSettings(this.speed, " multiplier ground forward-sideways", 1.0, 1.0, 0.1, 2.0, 2)).withoutNotInARow();
      SettingVisibilityCondition condition8 = () -> {
         return this.speed.mode.getSelectedValue().equals(this.getName());
      };
      this.multiplierGroundForwardSideways = (RandomizedRangeSettings)randomizedRangeSettings4.withVisibility(condition8);
      RandomizedRangeSettings randomizedRangeSettings5 = (new RandomizedRangeSettings(this.speed, " multiplier air backward-sideways", 1.0, 1.0, 0.1, 2.0, 2)).withoutNotInARow();
      SettingVisibilityCondition condition9 = () -> {
         return this.speed.mode.getSelectedValue().equals(this.getName());
      };
      this.multiplierAirBackwardSideways = (RandomizedRangeSettings)randomizedRangeSettings5.withVisibility(condition9);
      RandomizedRangeSettings randomizedRangeSettings6 = (new RandomizedRangeSettings(this.speed, " multiplier ground backward-sideways", 1.0, 1.0, 0.1, 2.0, 2)).withoutNotInARow();
      SettingVisibilityCondition condition10 = () -> {
         return this.speed.mode.getSelectedValue().equals(this.getName());
      };
      this.multiplierGroundBackwardSideways = (RandomizedRangeSettings)randomizedRangeSettings6.withVisibility(condition10);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreUpdate() {
   }

   @Override
   public void onPostUpdate() {
   }

   @Override
   public void onMotion(EventMotion event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
      if (KeyboardInputUtil.isPureForward(this.moveForwardInput, this.moveStrafeInput)) {
         RandomizedRangeSettings randomizedRangeSettings = this.multiplierGroundForward;
         this.applyDirectionalMultiplier(event, this.multiplierAirForward, randomizedRangeSettings);
      } else if (KeyboardInputUtil.isPureBackward(this.moveForwardInput, this.moveStrafeInput)) {
         RandomizedRangeSettings randomizedRangeSettings2 = this.multiplierGroundBackward;
         this.applyDirectionalMultiplier(event, this.multiplierAirBackward, randomizedRangeSettings2);
      } else if (KeyboardInputUtil.isForwardDiagonal(this.moveForwardInput, this.moveStrafeInput)) {
         RandomizedRangeSettings randomizedRangeSettings3 = this.multiplierGroundForwardSideways;
         this.applyDirectionalMultiplier(event, this.multiplierAirForwardSideways, randomizedRangeSettings3);
      } else if (KeyboardInputUtil.isBackwardDiagonal(this.moveForwardInput, this.moveStrafeInput)) {
         RandomizedRangeSettings randomizedRangeSettings5 = this.multiplierGroundBackwardSideways;
         this.applyDirectionalMultiplier(event, this.multiplierAirBackwardSideways, randomizedRangeSettings5);
      } else if (KeyboardInputUtil.isPureStrafe(this.moveForwardInput, this.moveStrafeInput)) {
         RandomizedRangeSettings randomizedRangeSettings7 = this.multiplierGroundSideways;
         this.applyDirectionalMultiplier(event, this.multiplierAirSideways, randomizedRangeSettings7);
      }
   }

   @Override
   public void onPostMoveInput(EventMoveInput event) {
      this.moveForwardInput = event.getMoveForward();
      this.moveStrafeInput = event.getMoveStrafe();
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
   }

   private void applyDirectionalMultiplier(EventMoveEntity event, RandomizedRangeSettings randomizedRangeSettings, RandomizedRangeSettings randomizedRangeSettings2) {
      double d = mc.thePlayer.onGround ? randomizedRangeSettings2.nextValue() : randomizedRangeSettings.nextValue();
      event.setX(event.getX() * d);
      event.setZ(event.getZ() * d);
   }

}
