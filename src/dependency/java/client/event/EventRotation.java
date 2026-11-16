package client.event;

import client.setting.BugUpMoveFlyingType;
import de.florianmichael.dietrichevents2.CancellableEvent;

public class EventRotation extends CancellableEvent {
   public float scaleFactor;
   public float scaleFactor2;
   public static final int count = 28;
   public BugUpMoveFlyingType bugUpMoveFlyingType;
   public float scaleFactor1;
   public float scaleFactor3;

   private void applyBugUpMoveFlyingType(float posX, float posX2, float posY, float posZ, BugUpMoveFlyingType bugUpMoveFlyingType2) {
      this.scaleFactor = posX;
      this.scaleFactor3 = posX2;
      this.scaleFactor1 = posY;
      this.scaleFactor2 = posZ;
      this.bugUpMoveFlyingType = bugUpMoveFlyingType2;
      this.setCancelled(false);
   }

   public float getScaleFactor() {
      return this.scaleFactor;
   }

   public void applyRotationListener(RotationListener rotationListener) {
      if (this.bugUpMoveFlyingType == BugUpMoveFlyingType.MOVE_FLYING) {
         rotationListener.onRotation(this);
      } else {
         rotationListener.onPostRotation(this);
      }
   }

   public static void onRotation(EventRotation event, float posX, float posX2, float posY, float posZ, BugUpMoveFlyingType bugUpMoveFlyingType2) {
      event.applyBugUpMoveFlyingType(posX, posX2, posY, posZ, bugUpMoveFlyingType2);
   }

   public void setScaleFactor1(float scaleFactor4) {
      this.scaleFactor1 = scaleFactor4;
   }

   public void call(Object entry) {
      RotationListener rotationListener = (RotationListener)entry;
      this.applyRotationListener(rotationListener);
   }

   public void setScaleFactor2(float scaleFactor4) {
      this.scaleFactor2 = scaleFactor4;
   }

   public float getScaleFactor3() {
      return this.scaleFactor3;
   }

   public static void onRotation(EventRotation event, float factor, BugUpMoveFlyingType bugUpMoveFlyingType2) {
      event.applyBugUpMoveFlyingType(factor, bugUpMoveFlyingType2);
   }

   public float getScaleFactor2() {
      return this.scaleFactor2;
   }

   private void applyBugUpMoveFlyingType(float factor, BugUpMoveFlyingType bugUpMoveFlyingType2) {
      this.scaleFactor = factor;
      this.bugUpMoveFlyingType = bugUpMoveFlyingType2;
      this.scaleFactor3 = 0.0F;
      this.scaleFactor1 = 0.0F;
      this.scaleFactor2 = 0.0F;
      this.setCancelled(false);
   }

   public void setScaleFactor(float scaleFactor4) {
      this.scaleFactor = scaleFactor4;
   }

   public void setScaleFactor3(float scaleFactor4) {
      this.scaleFactor3 = scaleFactor4;
   }

   public float getScaleFactor1() {
      return this.scaleFactor1;
   }
}
