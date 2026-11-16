package client.util;

import client.event.PostMotionObserver;
import client.setting.CompositeOptionType;

public class RotationCounter implements PostMotionObserver {
   public PostMotionObserver postMotionObserver;
   public PostMotionObserver postMotionObserver2;
   public CompositeOptionType compositeOptionType;

   @Override
   public double getValue() {
      CompositeOptionType compositeOptionType2 = this.compositeOptionType;
      double value = this.postMotionObserver.getValue();
      double value2 = this.postMotionObserver2.getValue();
      double offset = value;
      return compositeOptionType2.pow(offset, value2);
   }

   @Override
   public String toString() {
      return this.postMotionObserver.toString() + " " + this.compositeOptionType.sign + " " + this.postMotionObserver2.toString();
   }

   public RotationCounter(CompositeOptionType compositeOptionType2, PostMotionObserver postMotionObserver3, PostMotionObserver postMotionObserver4) {
      this.compositeOptionType = compositeOptionType2;
      this.postMotionObserver = postMotionObserver3;
      this.postMotionObserver2 = postMotionObserver4;
   }
}
