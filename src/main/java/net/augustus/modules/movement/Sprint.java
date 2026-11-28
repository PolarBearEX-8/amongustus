package net.augustus.modules.movement;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.input.KeyboardInputUtil;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.events.listeners.SprintListener;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.settings.Setting;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.rotation.MoveFixMode;
import net.augustus.utils.rotation.RotationController;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventSprint;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;

public class Sprint extends Module implements SprintListener, MoveInputListener, TickListener, MoveEntityListener {
   public final StringValue mode;
   public final BooleanValue ignoreCollisions;
   public final BooleanValue hungerSprint;
   public final BooleanValue hungerSprintOnlyOnGround;
   public final BooleanValue jumpSprint;
   public final BooleanValue editMotion;
   public final SettingGroup motionMultiplierAtSprinting;
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
   private float lastForwardInput;
   private float lastStrafeInput;

   public Sprint() {
       super("Sprint", "Sprinten", new Color(170, 181, 182, 255), Categorys.MOVEMENT);
      this.mode = (StringValue)(new StringValue(
            this, "Mode", "Legit", new String[]{"Legit", "LegitRotationAllDirection", "AllDirection", "AllDirectionMotion"}
         ))
         .markVisualOnly();
      this.ignoreCollisions = (BooleanValue)(new BooleanValue(this, "IgnoreCollisions", true)).markVisualOnly();
      this.hungerSprint = (BooleanValue)(new BooleanValue(this, "HungerSprint", true)).markVisualOnly();
      BooleanValue booleanValue = new BooleanValue(this, "HungerSprintOnlyOnGround", true);
      BooleanValue hungerSprint2 = this.hungerSprint;
      BooleanValue booleanValue2 = hungerSprint2;
      this.hungerSprintOnlyOnGround = (BooleanValue)((BooleanValue)booleanValue.withVisibility(() -> booleanValue2.getValue())).markVisualOnly();
      this.jumpSprint = (BooleanValue)(new BooleanValue(this, "JumpSprint", false)).markVisualOnly();
      this.editMotion = (BooleanValue)(new BooleanValue(this, "EditMotion", false)).markVisualOnly();
      SettingGroup motionMultiplierAtSprinting2 = new SettingGroup(this, "Motion multiplier at sprinting", true);
      hungerSprint2 = this.editMotion;
      BooleanValue booleanValue3 = hungerSprint2;
      this.motionMultiplierAtSprinting = (SettingGroup)motionMultiplierAtSprinting2.visibleWhen(() -> booleanValue3.getValue()).markVisualOnly();
      this.multiplierAirForward = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, " multiplier air forward", 1.0, 1.0, 0.1, 2.0, 2, this.motionMultiplierAtSprinting)).withoutNotInARow().markAllVisualOnly();
      this.multiplierGroundForward = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, " multiplier ground forward", 1.0, 1.0, 0.1, 2.0, 2, this.motionMultiplierAtSprinting))
         .withoutNotInARow()
         .markAllVisualOnly();
      this.multiplierAirBackward = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, " multiplier air backward", 1.0, 1.0, 0.1, 2.0, 2, this.motionMultiplierAtSprinting)).withoutNotInARow().markAllVisualOnly();
      this.multiplierGroundBackward = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, " multiplier ground backward", 1.0, 1.0, 0.1, 2.0, 2, this.motionMultiplierAtSprinting))
         .withoutNotInARow()
         .markAllVisualOnly();
      this.multiplierAirSideways = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, " multiplier air sideways", 1.0, 1.0, 0.1, 2.0, 2, this.motionMultiplierAtSprinting)).withoutNotInARow().markAllVisualOnly();
      this.multiplierGroundSideways = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, " multiplier ground sideways", 1.0, 1.0, 0.1, 2.0, 2, this.motionMultiplierAtSprinting))
         .withoutNotInARow()
         .markAllVisualOnly();
      this.multiplierAirForwardSideways = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, " multiplier air forward-sideways", 1.0, 1.0, 0.1, 2.0, 2, this.motionMultiplierAtSprinting))
         .withoutNotInARow()
         .markAllVisualOnly();
      this.multiplierGroundForwardSideways = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, " multiplier ground forward-sideways", 1.0, 1.0, 0.1, 2.0, 2, this.motionMultiplierAtSprinting))
         .withoutNotInARow()
         .markAllVisualOnly();
      this.multiplierAirBackwardSideways = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, " multiplier air backward-sideways", 1.0, 1.0, 0.1, 2.0, 2, this.motionMultiplierAtSprinting))
         .withoutNotInARow()
         .markAllVisualOnly();
      this.multiplierGroundBackwardSideways = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, " multiplier ground backward-sideways", 1.0, 1.0, 0.1, 2.0, 2, this.motionMultiplierAtSprinting))
         .withoutNotInARow()
         .markAllVisualOnly();
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(36, -4);
      this.toggleEventSubscription(21, 10);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(20);
   }

   @Override
   public void onSprint(EventSprint event) {
      event.setSprintKeyDown(true);
      if (this.mode.getSelectedValue().equals("AllDirection")) {
         event.setForceSprint(true);
      }

      if (this.mode.getSelectedValue().equals("Legit") && this.ignoreCollisions.getValue()) {
         event.setKeepSprintOnCollision(true);
      }

      if (this.hungerSprint.getValue()) {
         event.setIgnoreHunger(true);
      }
   }

   @Override
   public void onJumpSprint(EventSprint event) {
      if (this.jumpSprint.getValue() && MoveUtil.isMoving()) {
         event.setOmniJumpSprint(true);
      }

      if (this.hungerSprint.getValue() && (float)mc.thePlayer.getFoodStats().getFoodLevel() <= 6.0F && this.hungerSprintOnlyOnGround.getValue()) {
         event.setApplyJumpBoost(false);
      }
   }

   @Override
   public void onMotionSprint(EventSprint event) {
      if (this.mode.getSelectedValue().equals("AllDirectionMotion")) {
         event.setMotionSprint(true);
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (mc.thePlayer.isSprinting() && ClientManagers.moduleManager.scaffold.isToggled() && ClientManagers.moduleManager.scaffold.mode.getSelectedValue().equals("None")) {
         event.setMoveForward(0.0F);
      }

      if (this.canUseLegitAllDirection() && ClientManagers.rotationController.isOwnedBy(this)) {
         if (event.getMoveForward() > 0.0F && event.getMoveStrafe() == 0.0F) {
            return;
         }

         event.setMoveFixMode(MoveFixMode.DEFAULT);
      }
   }

   @Override
   public void onEarlyTick() {
      if (this.canUseLegitAllDirection()) {
         int forwardAxis = KeyboardInputUtil.getForwardAxis();
         int strafeAxis = KeyboardInputUtil.getStrafeAxis();
         if (forwardAxis > 0 && strafeAxis == 0) {
            return;
         }

         double direction = MoveUtil.getDirection((float)forwardAxis, (float)strafeAxis, mc.thePlayer.rotationYaw);
         RotationController rotationController = ClientManagers.rotationController;
         float f = RotationUtil.applyYawGcd(
            RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().yaw, (float)Math.toDegrees(direction), 180.0F), ClientManagers.rotationController.getPreviousRotation().yaw
         );
         rotationController.requestYaw(f, (byte)11, this);
      }
   }

   @Override
   public void onPostMoveInput(EventMoveInput event) {
      this.lastForwardInput = event.getMoveForward();
      this.lastStrafeInput = event.getMoveStrafe();
   }

   @Override
   public void onPreMoveEntity(EventMoveEntity event) {
      if (this.editMotion.getValue() && (mc.thePlayer.isSprinting() || this.mode.getSelectedValue().equals("AllDirectionMotion"))) {
         if (KeyboardInputUtil.isPureForward(this.lastForwardInput, this.lastStrafeInput)) {
            RandomizedRangeSettings randomizedRangeSettings = this.multiplierGroundForward;
            this.applySprintMotionMultiplier(event, this.multiplierAirForward, randomizedRangeSettings);
         } else if (KeyboardInputUtil.isPureBackward(this.lastForwardInput, this.lastStrafeInput)) {
            RandomizedRangeSettings randomizedRangeSettings2 = this.multiplierGroundBackward;
            this.applySprintMotionMultiplier(event, this.multiplierAirBackward, randomizedRangeSettings2);
         } else if (KeyboardInputUtil.isForwardDiagonal(this.lastForwardInput, this.lastStrafeInput)) {
            RandomizedRangeSettings randomizedRangeSettings3 = this.multiplierGroundForwardSideways;
            this.applySprintMotionMultiplier(event, this.multiplierAirForwardSideways, randomizedRangeSettings3);
         } else if (KeyboardInputUtil.isBackwardDiagonal(this.lastForwardInput, this.lastStrafeInput)) {
            RandomizedRangeSettings randomizedRangeSettings5 = this.multiplierGroundBackwardSideways;
            this.applySprintMotionMultiplier(event, this.multiplierAirBackwardSideways, randomizedRangeSettings5);
         } else if (KeyboardInputUtil.isPureStrafe(this.lastForwardInput, this.lastStrafeInput)) {
            RandomizedRangeSettings randomizedRangeSettings7 = this.multiplierGroundSideways;
            this.applySprintMotionMultiplier(event, this.multiplierAirSideways, randomizedRangeSettings7);
         }
      }
   }

   private void applySprintMotionMultiplier(EventMoveEntity event, RandomizedRangeSettings randomizedRangeSettings, RandomizedRangeSettings randomizedRangeSettings2) {
      double d = mc.thePlayer.onGround ? randomizedRangeSettings2.nextValue() : randomizedRangeSettings.nextValue();
      event.setX(event.getX() * d);
      event.setZ(event.getZ() * d);
   }

   private boolean canUseLegitAllDirection() {
      return mc.isNotInGui()
         && this.mode.getSelectedValue().equals("LegitRotationAllDirection")
         && !ClientManagers.moduleManager.scaffold.isToggled()
         && (KeyboardInputUtil.getForwardAxis() != 0 || KeyboardInputUtil.getStrafeAxis() != 0)
         && (!ClientManagers.moduleManager.antiBot.isToggled() || ClientManagers.moduleManager.aimBot.target == null)
         && (!ClientManagers.moduleManager.aimAssist.isToggled() || ClientManagers.moduleManager.aimAssist.target == null);
   }


}
