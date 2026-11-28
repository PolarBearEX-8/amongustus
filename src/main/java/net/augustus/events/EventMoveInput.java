package net.augustus.events;

import net.augustus.Augustus;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.utils.rotation.MoveFixMode;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventMoveInput extends AbstractEvent<MoveInputListener> {
   private float moveStrafe;
   private double sneakForwardMultiplier;
   private boolean sneak;
   private float moveForward;
   private boolean preventInstantReverse;
   private double moveDirection;
   private float yaw;
   private boolean originalJump;
   private MoveFixMode moveFixMode = MoveFixMode.NONE;
   private boolean originalSneak;
   public static final int ID = 21;
   private MoveInputStage stage;
   private double sneakStrafeMultiplier;
   private boolean jump;

   public void copyFrom(EventMoveInput event) {
      this.moveStrafe = event.moveStrafe;
      this.moveForward = event.moveForward;
      this.jump = event.jump;
      this.sneak = event.sneak;
      this.stage = event.stage;
   }

   public boolean isOriginalSneak() {
      return this.originalSneak;
   }

   @Override
   public void call(MoveInputListener listener) {
      if (this.stage == MoveInputStage.PRE) {
         listener.onPreMoveInput(this);
      } else if (this.stage == MoveInputStage.POST) {
         listener.onPostMoveInput(this);
      } else {
         listener.onPostJumpMoveInput(this);
      }
   }

   public float getMoveStrafe() {
      return this.moveStrafe;
   }

   public boolean isOriginalJump() {
      return this.originalJump;
   }

   public float getYaw() {
      return this.yaw;
   }

   public double getSneakForwardMultiplier() {
      return this.sneakForwardMultiplier;
   }

   public void setMoveFixMode(MoveFixMode moveFixMode2) {
      this.moveFixMode = moveFixMode2;
   }

   public boolean isJump() {
      return this.jump;
   }

   public void setSneak(boolean flag) {
      this.sneak = flag;
   }

   public void setMoveForward(float f) {
      this.moveForward = f;
   }

   public void setPreventInstantReverse(boolean flag) {
      this.preventInstantReverse = flag;
   }

   public void setSneakStrafeMultiplier(double d) {
      this.sneakStrafeMultiplier = d;
   }

   public float getMoveForward() {
      return this.moveForward;
   }

   public void setYaw(float f) {
      this.yaw = f;
   }

   public double getSneakStrafeMultiplier() {
      return this.sneakStrafeMultiplier;
   }

   public void setSneakForwardMultiplier(double d) {
      this.sneakForwardMultiplier = d;
   }

   public EventMoveInput(float f, float f2, boolean flag, boolean flag2, MoveInputStage moveInputStage) {
      this.preventInstantReverse = false;
      this.sneakForwardMultiplier = 0.3;
      this.sneakStrafeMultiplier = 0.3;
      this.moveDirection = 0.0 / 0.0;
      this.yaw = Augustus.getInstance().getYawPitchHelper().realYaw;
      this.moveStrafe = f;
      this.moveForward = f2;
      this.originalJump = flag;
      this.originalSneak = flag2;
      this.jump = flag;
      this.sneak = flag2;
      this.stage = moveInputStage;
   }

   public EventMoveInput(EventMoveInput event) {
      this.preventInstantReverse = false;
      this.sneakForwardMultiplier = 0.3;
      this.sneakStrafeMultiplier = 0.3;
      this.moveDirection = 0.0 / 0.0;
      this.yaw = Augustus.getInstance().getYawPitchHelper().realYaw;
      this.moveStrafe = event.moveStrafe;
      this.moveForward = event.moveForward;
      this.sneakForwardMultiplier = event.sneakForwardMultiplier;
      this.sneakStrafeMultiplier = event.sneakStrafeMultiplier;
      this.jump = event.jump;
      this.sneak = event.sneak;
      this.preventInstantReverse = event.preventInstantReverse;
      this.stage = event.stage;
   }

   public boolean isSneak() {
      return this.sneak;
   }

   public void setMoveStrafe(float f) {
      this.moveStrafe = f;
   }

   public boolean isPreventInstantReverse() {
      return this.preventInstantReverse;
   }

   public MoveFixMode getMoveFixMode() {
      return this.moveFixMode;
   }

   public void setJump(boolean flag) {
      this.jump = flag;
   }

}
