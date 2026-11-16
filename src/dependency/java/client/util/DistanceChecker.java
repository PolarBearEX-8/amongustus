package client.util;

import client.event.PostMotionObserver;

public class DistanceChecker extends LocalStringEntry {
   @Override
   public int getCount() {
      return 1;
   }

   @Override
   public double getValue() {
      return Math.exp(this.computeValue(0));
   }

   public DistanceChecker(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
