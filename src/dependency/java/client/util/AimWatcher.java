package client.util;

import java.util.concurrent.Callable;

public class AimWatcher implements Callable {
   public final RandomSeedManager randomSeedManager;

   public String getText() throws Exception {
      return String.valueOf(RandomSeedManager.computeCount2(this.randomSeedManager));
   }

   public AimWatcher(RandomSeedManager randomSeedManager2) {
      this.randomSeedManager = randomSeedManager2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
