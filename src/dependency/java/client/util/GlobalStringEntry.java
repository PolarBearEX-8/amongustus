package client.util;

import client.event.PostMotionObserver;

public class GlobalStringEntry extends LocalStringEntry {
   @Override
   public int getCount() {
      return 1;
   }

   @Override
   public double getValue() {
      double offset = this.computeValue(0);
      return offset < 0.0 ? Math.ceil(offset) : Math.floor(offset);
   }

   public GlobalStringEntry(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
