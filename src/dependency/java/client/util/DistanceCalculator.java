package client.util;

import client.event.PostMotionObserver;

public class DistanceCalculator extends LocalStringEntry {
   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public double getValue() {
      return Helper3.computeValue(this.computeValue(0), this.computeValue(1), this.computeValue(2));
   }

   public DistanceCalculator(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
