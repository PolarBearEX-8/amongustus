package client.util;

import client.Client;
import javafx.scene.media.AudioClip;

public class AudioClipPlayer {
   public AudioClip audioClip = null;

   public void applyDouble(double offset) {
      if (this.checkCondition()) {
         this.audioClip.setVolume(offset);
      }
   }

   public void applyText(String text) {
      this.audioClip = new AudioClip(ConfigManager.resolveText(text));
      this.audioClip.play();
   }

   public void processAction(int count) {
      if (this.audioClip != null) {
         this.audioClip.setCycleCount(count);
      }
   }

   public void applyString2(String text, boolean flag) {
      String text2 = text;
      if (text.startsWith("assets/augustus/")) {
         text2 = text.replace("assets/augustus/", "");
      }

      String[] textArray = text2.split("/");
      StringBuilder builder = new StringBuilder(Client.getClient().getString4().toLowerCase() + "/temp/musicplayer/");

      for (int index = 0; index < textArray.length - 1; index++) {
         builder.append(textArray[index]);
      }

      String text3 = "assets/augustus/" + text2;
      String text4 = builder.toString();
      ConfigManager.applyString(text3, text4, flag);
      this.audioClip = new AudioClip(ConfigManager.resolveText(ConfigManager.labelText + builder + "/" + textArray[textArray.length - 1]));
      this.audioClip.play();
   }

   public boolean checkCondition() {
      return this.audioClip != null && this.audioClip.isPlaying();
   }

   public void processChar() {
      try {
         if (this.checkCondition()) {
            this.audioClip.stop();
         }
      } catch (Throwable throwable) {
         System.err.println("Failed to stop music player");
      }
   }

   public void applyTextByText(String text) {
      this.applyString2(text, false);
   }
}
