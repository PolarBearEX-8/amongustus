package client.util;

import java.util.concurrent.Callable;

public class RotationSynchronizer implements Callable {
   public final RandomSeedManager randomSeedManager;

   public RotationSynchronizer(RandomSeedManager randomSeedManager2) {
      this.randomSeedManager = randomSeedManager2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      return String.format(
         "Rain time: %d (now: %b), thunder time: %d (now: %b)",
         RandomSeedManager.computeCount5(this.randomSeedManager),
         RandomSeedManager.matchesRandomSeedManagerByRandomSeedManage(this.randomSeedManager),
         RandomSeedManager.computeCount3(this.randomSeedManager),
         RandomSeedManager.matchesRandomSeedManager4(this.randomSeedManager)
      );
   }
}
