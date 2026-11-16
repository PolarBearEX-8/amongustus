package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;
import de.florianmichael.dietrichevents2.StateTypes;

public class EventMotionUpdate extends AbstractEvent {
   public double coordOffset;
   public double coordOffset1;
   public double coordOffset3;
   public boolean state;
   public StateTypes stateTypes;
   public float scaleFactor;
   public static final int count = 18;
   public float scaleFactor2;

   private void applyStateTypes(boolean flag, float factor, float factor2, double posX, double posY, double posZ, StateTypes stateTypes2) {
      this.state = flag;
      this.scaleFactor = factor;
      this.scaleFactor2 = factor2;
      this.coordOffset3 = posX;
      this.coordOffset1 = posY;
      this.coordOffset = posZ;
      this.stateTypes = stateTypes2;
   }

   public float getScaleFactor2() {
      return this.scaleFactor2;
   }

   public void setCoordOffset(double coordOffset2) {
      this.coordOffset = coordOffset2;
   }

   public static void onMotionUpdate(EventMotionUpdate event, boolean flag, float factor, float factor2, double posX, double posY, double posZ, StateTypes stateTypes2) {
      event.applyStateTypes(flag, factor, factor2, posX, posY, posZ, stateTypes2);
   }

   public boolean isState() {
      return this.state;
   }

   public double getCoordOffset() {
      return this.coordOffset;
   }

   public void call(Object entry) {
      MotionUpdateListener motionUpdateListener = (MotionUpdateListener)entry;
      this.applyMotionUpdateListener(motionUpdateListener);
   }

   public double getCoordOffset1() {
      return this.coordOffset1;
   }

   public double getCoordOffset3() {
      return this.coordOffset3;
   }

   public void setCoordOffset1(double coordOffset2) {
      this.coordOffset1 = coordOffset2;
   }

   public void setState(boolean state2) {
      this.state = state2;
   }

   public float getScaleFactor() {
      return this.scaleFactor;
   }

   public void applyMotionUpdateListener(MotionUpdateListener motionUpdateListener) {
      if (this.stateTypes == StateTypes.PRE) {
         motionUpdateListener.onMotionUpdate(this);
      } else {
         motionUpdateListener.onPreMotionUpdate();
      }
   }

   public void setScaleFactor2(float scaleFactor3) {
      this.scaleFactor2 = scaleFactor3;
   }

   public void setScaleFactor(float scaleFactor3) {
      this.scaleFactor = scaleFactor3;
   }

   public void setCoordOffset3(double coordOffset2) {
      this.coordOffset3 = coordOffset2;
   }

   public EventMotionUpdate(boolean flag, float factor, float factor2, double posX, double posY, double posZ, StateTypes stateTypes2) {
      this.state = flag;
      this.scaleFactor = factor;
      this.scaleFactor2 = factor2;
      this.coordOffset3 = posX;
      this.coordOffset1 = posY;
      this.coordOffset = posZ;
      this.stateTypes = stateTypes2;
   }

   public EventMotionUpdate() {
   }
}
