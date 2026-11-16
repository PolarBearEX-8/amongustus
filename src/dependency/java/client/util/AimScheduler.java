package client.util;

import client.event.PostRenderConsumer;

public class AimScheduler implements PostRenderConsumer {
   public int count;

   public AimScheduler(int count2) {
      this.count = count2;
   }
}
