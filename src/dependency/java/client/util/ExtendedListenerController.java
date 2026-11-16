package client.util;

import client.event.PostMotionObserver;

public class ExtendedListenerController implements PostMotionObserver {
   public double value;

   @Override
   public double getValue() {
      return this.value;
   }

   public void setValue(double value2) {
      this.value = value2;
   }

   public ExtendedListenerController(double offset) {
      this.value = offset;
   }

   @Override
   public String toString() {
      return String.valueOf(this.value);
   }
}
