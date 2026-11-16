package client.util;

import client.event.PostMotionObserver;

public class TargetBuilder implements PostMotionObserver {
   public PostMotionObserver postMotionObserver;

   @Override
   public String toString() {
      return "!" + this.postMotionObserver.toString();
   }

   public TargetBuilder(PostMotionObserver postMotionObserver2) {
      this.postMotionObserver = postMotionObserver2;
   }

   @Override
   public double getValue() {
      return this.postMotionObserver.getValue() == 0.0 ? 1.0 : 0.0;
   }
}
