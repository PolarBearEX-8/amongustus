package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventSafeWalk extends AbstractEvent {
   public double value;
   public double value3;
   public String labelText;
   public double value2;
   public static final int count = 35;

   private void applyText(double posX, double posY, double posZ, String text) {
      this.value3 = posX;
      this.value2 = posY;
      this.value = posZ;
      this.labelText = text;
   }

   public void applySafeWalkListener(SafeWalkListener safeWalkListener) {
      safeWalkListener.onSafeWalk(this);
   }

   public void setValue3(double value4) {
      this.value3 = value4;
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   public static void onSafeWalk(EventSafeWalk event, double posX, double posY, double posZ, String text) {
      event.applyText(posX, posY, posZ, text);
   }

   public void setValue(double value4) {
      this.value = value4;
   }

   public void call(Object entry) {
      SafeWalkListener safeWalkListener = (SafeWalkListener)entry;
      this.applySafeWalkListener(safeWalkListener);
   }

   public double getCoordinate() {
      return this.value2;
   }

   public String getText() {
      return this.labelText;
   }

   public double getCoordinate2() {
      return this.value;
   }

   public void setValue2(double value4) {
      this.value2 = value4;
   }

   public double getCoordinate3() {
      return this.value3;
   }
}
