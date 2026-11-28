package net.augustus.events;

import net.augustus.events.listeners.MoveListener;
import de.florianmichael.dietrichevents2.CancellableEvent;

public class EventMove extends CancellableEvent<MoveListener> {
   private float forward;
   private float strafe;
   private float friction;
   public static final int ID = 28;
   private float yaw;
   private MoveEventType type;

   public void setYaw(float f) {
      this.yaw = f;
   }

   public static void reset(EventMove event, float f, MoveEventType moveEventType) {
      event.reset(f, moveEventType);
   }

   private void reset(float f, MoveEventType moveEventType) {
      this.yaw = f;
      this.type = moveEventType;
      this.forward = 0.0F;
      this.strafe = 0.0F;
      this.friction = 0.0F;
      this.setCancelled(false);
   }

   public void setFriction(float f) {
      this.friction = f;
   }

   private void reset(float f, float f2, float f3, float f4, MoveEventType moveEventType) {
      this.yaw = f;
      this.forward = f2;
      this.strafe = f3;
      this.friction = f4;
      this.type = moveEventType;
      this.setCancelled(false);
   }

   public static void reset(EventMove event, float f, float f2, float f3, float f4, MoveEventType moveEventType) {
      event.reset(f, f2, f3, f4, moveEventType);
   }

   public void setStrafe(float f) {
      this.strafe = f;
   }


   public float getStrafe() {
      return this.strafe;
   }

   public void setForward(float f) {
      this.forward = f;
   }

   public float getYaw() {
      return this.yaw;
   }

   public float getFriction() {
      return this.friction;
   }

   @Override
   public void call(MoveListener listener) {
      if (this.type == MoveEventType.MOVE_FLYING) {
         listener.onMoveFlying(this);
      } else {
         listener.onJumpMove(this);
      }
   }

   public float getForward() {
      return this.forward;
   }
}
