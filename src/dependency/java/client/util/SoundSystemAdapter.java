package client.util;

import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.Source;

public class SoundSystemAdapter extends SoundSystem {
   public final SSSManager sSSManager;

   public boolean playing(String text) {
      synchronized (SoundSystemConfig.THREAD_SYNC) {
         if (this.soundLibrary == null) {
            return false;
         } else {
            Source source = (Source)this.soundLibrary.getSources().get(text);
            return source == null ? false : source.playing() || source.paused() || source.preLoad;
         }
      }
   }

   public SoundSystemAdapter(SSSManager sSSManager2, SoundEngineStartedRunnable soundEngineStartedRunnable) {
      this(sSSManager2);
   }

   private SoundSystemAdapter(SSSManager sSSManager2) {
      this.sSSManager = sSSManager2;
   }
}
