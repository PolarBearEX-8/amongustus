package client.util;

import java.util.concurrent.Callable;

public class SmoothAdapter implements Callable {
   public final RandomSeedManager randomSeedManager;

   public SmoothAdapter(RandomSeedManager randomSeedManager2) {
      this.randomSeedManager = randomSeedManager2;
   }

   public String getText() throws Exception {
      return String.format(
         "Game mode: %s (ID %d). Hardcore: %b. Cheats: %b",
         RandomSeedManager.resolveWorldSettingsGameType(this.randomSeedManager).getLabelText(),
         RandomSeedManager.resolveWorldSettingsGameType(this.randomSeedManager).getIndex(),
         RandomSeedManager.matchesRandomSeedManager(this.randomSeedManager),
         RandomSeedManager.matchesRandomSeedManager3(this.randomSeedManager)
      );
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
