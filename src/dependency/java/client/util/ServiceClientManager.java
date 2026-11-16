package client.util;

import client.event.PostRenderConsumer;
import java.util.Random;
import net.minecraft.potion.Potion;

public class ServiceClientManager implements PostRenderConsumer {
   public int count;

   public void applyRandom(Random random) {
      int count2 = random.nextInt(5);
      if (count2 <= 1) {
         this.count = Potion.potion2.indexOffset3;
      } else if (count2 <= 2) {
         this.count = Potion.potion.indexOffset3;
      } else if (count2 <= 3) {
         this.count = Potion.potion11.indexOffset3;
      } else if (count2 <= 4) {
         this.count = Potion.potion30.indexOffset3;
      }
   }
}
