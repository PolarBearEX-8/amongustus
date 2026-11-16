package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.util.Vec3;

public class EventStrafe extends AbstractEvent {
   public float factor3;
   public float factor;
   public static final int count = 24;
   public float factor2;
   public Vec3 vec3;
   public boolean enabled = false;

   public void applyStrafeListener(StrafeListener strafeListener) {
      strafeListener.onStrafe(this);
   }

   public Vec3 getPositionVec() {
      return this.vec3;
   }

   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static void onStrafe(EventStrafe event, float posX, float posY, float posZ) {
      event.applyFloat(posX, posY, posZ);
   }

   public float getFactor() {
      return this.factor;
   }

   private void applyFloat(float posX, float posY, float posZ) {
      this.factor3 = posX;
      this.factor = posY;
      this.factor2 = posZ;
      this.vec3 = new Vec3(0.0, 0.0, 0.0);
      this.enabled = false;
   }

   public void call(Object entry) {
      StrafeListener strafeListener = (StrafeListener)entry;
      this.applyStrafeListener(strafeListener);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public float getScaleFactor() {
      return this.factor3;
   }

   public void processAction(float factor4) {
      this.factor2 = factor4;
   }

   public void processAction2(float factor4) {
      this.factor = factor4;
   }

   public void processAction3(float factor4) {
      this.factor3 = factor4;
   }

   public void setPositionVec(Vec3 positionVec) {
      this.vec3 = positionVec;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public EventStrafe() {
      this.vec3 = new Vec3(0.0, 0.0, 0.0);
   }
}
