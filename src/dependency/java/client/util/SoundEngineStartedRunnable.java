package client.util;

import client.setting.AutoPlayTargetEntity;
import paulscode.sound.SoundSystemConfig;

public class SoundEngineStartedRunnable implements Runnable {
   public final SSSManager sSSManager;

   public SoundEngineStartedRunnable(SSSManager sSSManager2) {
      this.sSSManager = sSSManager2;
   }

   @Override
   public void run() {
      SoundSystemConfig.setLogger(new ErrorInClassSoundSystemLogger(this));
      SSSManager.resolveSoundSystemAdapter(this.sSSManager, new SoundSystemAdapter(this.sSSManager, null));
      SSSManager.matchesSSSManager(this.sSSManager, true);
      SSSManager.resolveSoundSystemAdapter(this.sSSManager)
         .setMasterVolume(SSSManager.resolveGameSettings(this.sSSManager).getScaleFactor(AutoPlayTargetEntity.MASTER));
      SSSManager.getLogger().info(SSSManager.getMarker(), "Sound engine started");
   }
}
