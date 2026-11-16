package client.util;

import client.event.PostMotionObserver;

public class DistanceMatcher extends LocalStringEntry {
   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public double getValue() {
      return Helper3.computeValueByDouble(this.computeValue(0), this.computeValue(1), this.computeValue(2));
   }

   public DistanceMatcher(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
