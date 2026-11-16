package client.util;

import client.event.PostMotionObserver;

public class DistanceMonitor extends LocalStringEntry {
   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public double getValue() {
      return Math.max(this.computeValue(0), this.computeValue(1));
   }

   public DistanceMonitor(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
