package client.util;

import client.event.PostMotionObserver;

public class DistanceWatcher extends LocalStringEntry {
   @Override
   public int getCount() {
      return 1;
   }

   @Override
   public double getValue() {
      return Math.floor(this.computeValue(0));
   }

   public DistanceWatcher(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
