package client.util;

import client.event.PostMotionObserver;

public class DistanceRanker extends LocalStringEntry {
   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public double getValue() {
      return Math.min(this.computeValue(0), this.computeValue(1));
   }

   public DistanceRanker(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
