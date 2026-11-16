package client.util;

import client.event.PostMotionObserver;

public class CustomStringArrayEntry extends LocalStringEntry {
   @Override
   public int getCount() {
      return 1;
   }

   @Override
   public double getValue() {
      return Math.sqrt(this.computeValue(0));
   }

   public CustomStringArrayEntry(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
