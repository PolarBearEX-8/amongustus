package client.util;

import client.event.PostMotionObserver;

public class InternalListenerRegistry implements PostMotionObserver {
   public String labelText2;
   public double value;

   public void setValue(double value2) {
      this.value = value2;
   }

   @Override
   public double getValue() {
      return this.value;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public InternalListenerRegistry(String text, double offset) {
      this.labelText2 = text;
      this.value = offset;
   }

   @Override
   public String toString() {
      return this.labelText2;
   }
}
