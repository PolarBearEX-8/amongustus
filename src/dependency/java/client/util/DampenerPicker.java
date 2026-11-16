package client.util;

import java.util.concurrent.Callable;

public class DampenerPicker implements Callable {
   public final RandomSeedManager randomSeedManager;

   public String getText() throws Exception {
      return String.format(
         "ID %02d - %s, ver %d. Features enabled: %b",
         RandomSeedManager.resolveInfoManager(this.randomSeedManager).getCount2(),
         RandomSeedManager.resolveInfoManager(this.randomSeedManager).getText(),
         RandomSeedManager.resolveInfoManager(this.randomSeedManager).getCount(),
         RandomSeedManager.matchesRandomSeedManager2(this.randomSeedManager)
      );
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public DampenerPicker(RandomSeedManager randomSeedManager2) {
      this.randomSeedManager = randomSeedManager2;
   }
}
