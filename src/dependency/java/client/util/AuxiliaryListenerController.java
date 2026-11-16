package client.util;

import client.event.PostMotionObserver;

public class AuxiliaryListenerController implements PostMotionObserver {
   public final double value;

   public static AuxiliaryListenerController parseDouble(String text) {
      return new AuxiliaryListenerController(Double.parseDouble(text));
   }

   public static AuxiliaryListenerController resolveAuxiliaryListenerController(double offset) {
      return new AuxiliaryListenerController(offset);
   }

   public static AuxiliaryListenerController resolveAuxiliaryListenerController(float factor) {
      return new AuxiliaryListenerController((double)factor);
   }

   public static AuxiliaryListenerController parseFloat(String text) {
      return new AuxiliaryListenerController((double)Float.parseFloat(text));
   }

   @Override
   public double getValue() {
      return this.value;
   }

   public static AuxiliaryListenerController resolveAuxiliaryListenerController(PostMotionObserver postMotionObserver, PostMotionObserver postMotionObserver2) {
      return resolveAuxiliaryListenerController(postMotionObserver.getValue() - postMotionObserver2.getValue());
   }

   public AuxiliaryListenerController(double offset) {
      this.value = offset;
   }
}
