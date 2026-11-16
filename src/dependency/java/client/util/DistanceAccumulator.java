package client.util;

import client.event.PostMotionObserver;

public class DistanceAccumulator extends LocalStringEntry {
   @Override
   public int getCount() {
      return 1;
   }

   @Override
   public double getValue() {
      return Math.cos(this.computeValue(0));
   }

   public DistanceAccumulator(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
