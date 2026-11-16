package client.util;

import java.util.concurrent.Callable;

public class AimPool implements Callable {
   public final RandomSeedManager randomSeedManager;

   public AimPool(RandomSeedManager randomSeedManager2) {
      this.randomSeedManager = randomSeedManager2;
   }

   public String getText() throws Exception {
      return String.format(
         "%d game time, %d day time",
         RandomSeedManager.computeTimestamp(this.randomSeedManager),
         RandomSeedManager.computeTimestampByRandomSeedManage(this.randomSeedManager)
      );
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
