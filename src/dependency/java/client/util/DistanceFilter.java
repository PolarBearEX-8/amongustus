package client.util;

import client.event.PostMotionObserver;

public class DistanceFilter extends LocalStringEntry {
   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public double getValue() {
      return Math.pow(this.computeValue(0), this.computeValue(1));
   }

   public DistanceFilter(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
