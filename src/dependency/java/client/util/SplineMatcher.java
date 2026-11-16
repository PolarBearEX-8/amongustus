package client.util;

import java.util.concurrent.Callable;

public class SplineMatcher implements Callable {
   public final RandomSeedManager randomSeedManager;

   public String getText() throws Exception {
      return String.valueOf(this.randomSeedManager.getTimestamp5());
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public SplineMatcher(RandomSeedManager randomSeedManager2) {
      this.randomSeedManager = randomSeedManager2;
   }
}
