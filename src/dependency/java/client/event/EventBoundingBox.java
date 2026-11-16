package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventBoundingBox extends AbstractEvent {
   public double value;
   public double value4;
   public double value2;
   public double value5;
   public double value6;
   public static final int count = 13;
   public double value3;
   public double value7;

   public void processAction(double offset) {
      this.value = offset;
   }

   public double getValue6() {
      return this.value6;
   }

   public void processAction2(double offset) {
      this.value7 = offset;
   }

   public void onBoundingBoxCallback(EventBoundingBoxCallback event) {
      event.onBoundingBox(this);
   }

   public double getValue2() {
      return this.value2;
   }

   public double getValue4() {
      return this.value4;
   }

   public double getValue7() {
      return this.value7;
   }

   public void processAction3(double offset) {
      this.value6 = offset;
   }

   public void call(Object entry) {
      EventBoundingBoxCallback event = (EventBoundingBoxCallback)entry;
      this.onBoundingBoxCallback(event);
   }

   public double getValue3() {
      return this.value3;
   }

   public static void onBoundingBox(EventBoundingBox event, double posX, double posX2, double posX3, double posX4, double posX5, double posY, double posZ) {
      event.applyDouble(posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   public void processAction4(double offset) {
      this.value3 = offset;
   }

   public void processAction5(double offset) {
      this.value2 = offset;
   }

   public void processAction6(double offset) {
      this.value5 = offset;
   }

   public double getValue() {
      return this.value;
   }

   public double getValue5() {
      return this.value5;
   }

   public void processAction7(double offset) {
      this.value4 = offset;
   }

   private void applyDouble(double posX, double posX2, double posX3, double posX4, double posX5, double posY, double posZ) {
      this.value2 = posX;
      this.value4 = posX2;
      this.value7 = posX3;
      this.value6 = posX4;
      this.value3 = posX5;
      this.value5 = posY;
      this.value = posZ;
   }
}
