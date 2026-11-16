package client.util;

import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.item.SecondaryItemCallback;

public class SpecialAbstractEventAdapter extends AbstractEvent {
   public static final int count = 15;
   public double value;

   public void applySecondaryItemCallback(SecondaryItemCallback secondaryItemCallback) {
      secondaryItemCallback.applySpecialAbstractEventAdapte(this);
   }

   public void call(Object entry) {
      SecondaryItemCallback secondaryItemCallback = (SecondaryItemCallback)entry;
      this.applySecondaryItemCallback(secondaryItemCallback);
   }

   public void setValue(double value2) {
      this.value = value2;
   }

   private void setValueByDouble(double valueByDouble) {
      this.value = valueByDouble;
   }

   public static void applySpecialAbstractEventAdapte(SpecialAbstractEventAdapter event, double offset) {
      event.setValueByDouble(offset);
   }

   public double getValue() {
      return this.value;
   }
}
