package client.util;

import client.event.PostMotionObserver;

public class DistanceTracker extends LocalStringEntry {
   @Override
   public int getCount() {
      return 1;
   }

   @Override
   public double getValue() {
      return Math.log(this.computeValue(0));
   }

   public DistanceTracker(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
