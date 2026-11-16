package client.util;

import paulscode.sound.SoundSystemLogger;

public class ErrorInClassSoundSystemLogger extends SoundSystemLogger {
   public final SoundEngineStartedRunnable soundEngineStartedRunnable;

   public void importantMessage(String text, int count) {
      if (!text.isEmpty()) {
         SSSManager.getLogger().warn(text);
      }
   }

   public void errorMessage(String text, String text2, int count) {
      if (!text2.isEmpty()) {
         SSSManager.getLogger().error("Error in class '" + text + "'");
         SSSManager.getLogger().error(text2);
      }
   }

   public ErrorInClassSoundSystemLogger(SoundEngineStartedRunnable soundEngineStartedRunnable2) {
      this.soundEngineStartedRunnable = soundEngineStartedRunnable2;
   }

   public void message(String text, int count) {
      if (!text.isEmpty()) {
         SSSManager.getLogger().info(text);
      }
   }
}
