package client.util;

import client.event.PostMotionObserver;
import java.util.Random;

public class SecondaryRandomEntry extends LocalStringEntry {
   public Random random = new Random();

   @Override
   public double getValue() {
      double offset;
      if (this.postMotionObserverArray.length >= 3) {
         this.random.setSeed((long)this.computeValue(2));
         offset = this.random.nextDouble();
      } else {
         offset = Math.random();
      }

      if (this.postMotionObserverArray.length >= 2) {
         double offset2 = this.computeValue(0);
         double offset3 = this.computeValue(1);
         double offset4 = Math.min(offset2, offset3);
         double offset5 = Math.max(offset2, offset3);
         offset = offset * (offset5 - offset4) + offset4;
      } else if (this.postMotionObserverArray.length >= 1) {
         offset *= this.computeValue(0);
      }

      return offset;
   }

   public SecondaryRandomEntry(PostMotionObserver[] postMotionObserverArray, String text) throws Exception {
      super(postMotionObserverArray, text);
   }
}
