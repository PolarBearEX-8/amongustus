package client.util;

import client.event.PostMotionObserver;

public class DistanceCounter extends LocalStringEntry {
   @Override
   public int getCount() {
      return 1;
   }

   @Override
   public double getValue() {
      return Math.sin(this.computeValue(0) / 180.0 * Math.PI);
   }

   public DistanceCounter(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
