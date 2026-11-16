package client.util;

import client.event.PostMotionObserver;

public class GeometryAccumulator implements PostMotionObserver {
   public PostMotionObserver postMotionObserver;

   @Override
   public String toString() {
      return "(" + this.postMotionObserver.toString() + ")";
   }

   @Override
   public double getValue() {
      return this.postMotionObserver.getValue();
   }

   public GeometryAccumulator(PostMotionObserver postMotionObserver2) {
      this.postMotionObserver = postMotionObserver2;
   }
}
