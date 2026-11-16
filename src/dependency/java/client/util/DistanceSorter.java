package client.util;

import client.event.PostMotionObserver;

public class DistanceSorter extends LocalStringEntry {
   @Override
   public int getCount() {
      return 1;
   }

   @Override
   public double getValue() {
      return Math.ceil(this.computeValue(0));
   }

   public DistanceSorter(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
