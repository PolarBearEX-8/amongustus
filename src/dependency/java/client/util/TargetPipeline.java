package client.util;

import client.event.PostMotionObserver;

public class TargetPipeline extends LocalStringEntry {
   @Override
   public int getCount() {
      return 1;
   }

   @Override
   public double getValue() {
      return Math.abs(this.computeValue(0));
   }

   public TargetPipeline(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
