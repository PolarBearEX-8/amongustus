package net.augustus.events;

import net.augustus.events.listeners.OrientCameraListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.util.Vec3;

public class EventOrientCamera extends AbstractEvent<OrientCameraListener> {
   private Vec3 offset;
   private float pitch;
   public static final int ID = 24;
   private float yaw;
   private boolean overrideRotation = false;
   private float eyeHeight;

   public void setPitch(float f) {
      this.pitch = f;
   }

   public Vec3 getOffset() {
      return this.offset;
   }

   public EventOrientCamera() {
      this.offset = new Vec3(0.0, 0.0, 0.0);
   }

   public float getEyeHeight() {
      return this.eyeHeight;
   }

   private void reset(float f, float f2, float f3) {
      this.eyeHeight = f;
      this.yaw = f2;
      this.pitch = f3;
      this.offset = new Vec3(0.0, 0.0, 0.0);
      this.overrideRotation = false;
   }

   public float getYaw() {
      return this.yaw;
   }

   public void setOffset(Vec3 vec) {
      this.offset = vec;
   }

   public static void reset(EventOrientCamera event, float f, float f2, float f3) {
      event.reset(f, f2, f3);
   }


   public boolean isOverrideRotation() {
      return this.overrideRotation;
   }

   public float getPitch() {
      return this.pitch;
   }

   public void setOverrideRotation(boolean flag) {
      this.overrideRotation = flag;
   }

   public void setEyeHeight(float f) {
      this.eyeHeight = f;
   }

   @Override
   public void call(OrientCameraListener listener) {
      listener.onOrientCamera(this);
   }

   public void setYaw(float f) {
      this.yaw = f;
   }
}
