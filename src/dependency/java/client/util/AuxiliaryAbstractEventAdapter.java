package client.util;

import de.florianmichael.dietrichevents2.AbstractEvent;

public class AuxiliaryAbstractEventAdapter extends AbstractEvent {
   public boolean enabled;
   public double value2;
   public static final int count = 49;
   public boolean enabled2;
   public double value;

   public static void applyAuxiliaryAbstractEventAdap(AuxiliaryAbstractEventAdapter event, double offset, double offset2, boolean flag, boolean flag2) {
      event.applyDouble(offset, offset2, flag, flag2);
   }

   private void applyDouble(double offset, double offset2, boolean flag, boolean flag2) {
      this.value2 = offset;
      this.value = offset2;
      this.enabled = flag;
      this.enabled2 = flag2;
   }

   public void setValue2(double value3) {
      this.value2 = value3;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public void setValue(double value3) {
      this.value = value3;
   }

   public void call(Object entry) {
      SmoothSynchronizer smoothSynchronizer = (SmoothSynchronizer)entry;
      this.applySmoothSynchronizer(smoothSynchronizer);
   }

   public void setEnabled(boolean enabled3) {
      this.enabled = enabled3;
   }

   public void applySmoothSynchronizer(SmoothSynchronizer smoothSynchronizer) {
      smoothSynchronizer.applyAuxiliaryAbstractEventAdap(this);
   }

   public void setEnabled2(boolean enabled3) {
      this.enabled2 = enabled3;
   }

   public double getValue2() {
      return this.value2;
   }

   public double getValue() {
      return this.value;
   }
}
