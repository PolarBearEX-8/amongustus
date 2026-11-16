package client.util;

import client.event.PostMotionObserver;

public class DistanceValidator extends LocalStringEntry {
   @Override
   public int getCount() {
      return 1;
   }

   @Override
   public double getValue() {
      return Math.sin(this.computeValue(0));
   }

   public DistanceValidator(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
