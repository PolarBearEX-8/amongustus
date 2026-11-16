package client.util;

import client.event.PostMotionObserver;

public class DistanceWriter extends LocalStringEntry {
   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public double getValue() {
      return this.computeValue(0) % this.computeValue(1);
   }

   public DistanceWriter(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
