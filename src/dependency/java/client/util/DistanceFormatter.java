package client.util;

import client.event.PostMotionObserver;

public class DistanceFormatter extends LocalStringEntry {
   @Override
   public int getCount() {
      return 1;
   }

   @Override
   public double getValue() {
      return Math.cos(this.computeValue(0) / 180.0 * Math.PI);
   }

   public DistanceFormatter(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
