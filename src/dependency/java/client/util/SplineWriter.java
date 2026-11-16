package client.util;

import java.util.concurrent.Callable;

public class SplineWriter implements Callable {
   public final RandomSeedManager randomSeedManager;

   public SplineWriter(RandomSeedManager randomSeedManager2) {
      this.randomSeedManager = randomSeedManager2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      return RandomSeedManager.resolveText(this.randomSeedManager);
   }
}
