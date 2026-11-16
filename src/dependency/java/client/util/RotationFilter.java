package client.util;

import client.event.PostRenderConsumer;

public class RotationFilter implements PostRenderConsumer {
   public int count2;
   public int count;

   public RotationFilter(int count3, int count4) {
      this.count2 = count3;
      this.count = count4;
   }
}
