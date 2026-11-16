package client.util;

import java.util.concurrent.Callable;

public class TargetQueue implements Callable {
   public final RandomSeedManager randomSeedManager;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      String text = "Unknown?";

      try {
         switch (RandomSeedManager.computeCount6(this.randomSeedManager)) {
            case 19132:
               text = "McRegion";
               break;
            case 19133:
               text = "Anvil";
         }
      } catch (Throwable throwable) {
      }

      return String.format("0x%05X - %s", RandomSeedManager.computeCount6(this.randomSeedManager), text);
   }

   public TargetQueue(RandomSeedManager randomSeedManager2) {
      this.randomSeedManager = randomSeedManager2;
   }
}
