package client.util;

import client.event.PostMotionObserver;

public class RayTraceAdapter implements PostMotionObserver {
   public PostMotionObserver postMotionObserver;

   @Override
   public double getValue() {
      return -this.postMotionObserver.getValue();
   }

   @Override
   public String toString() {
      return "-" + this.postMotionObserver.toString();
   }

   public RayTraceAdapter(PostMotionObserver postMotionObserver2) {
      this.postMotionObserver = postMotionObserver2;
   }
}
