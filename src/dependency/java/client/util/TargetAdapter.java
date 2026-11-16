package client.util;

import client.event.PostMotionObserver;

public class TargetAdapter extends LocalStringEntry {
   @Override
   public int getCount() {
      return 1;
   }

   @Override
   public double getValue() {
      return (double)Math.round(this.computeValue(0));
   }

   public TargetAdapter(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
