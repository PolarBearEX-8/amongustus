package net.augustus.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.FloatControl.Type;
import net.minecraft.util.MathHelper;

public class AudioClipPlayer {
   private float volume;
   private Clip clip = null;

   public void setVolume(float f) {
      f = MathHelper.clamp_float(f, -79.0F, 20.0F);
      ((FloatControl)this.clip.getControl(Type.MASTER_GAIN)).setValue(f);
      this.volume = f;
   }

   public void stop() {
      if (this.clip != null && this.clip.isActive()) {
         this.clip.stop();
      }
   }

   public void loop(int n) {
      this.clip.loop(n);
   }

   public void playUrl(URL url, float f) {

      try {
         if (this.clip != null && this.clip.isActive()) {
            this.clip.stop();
         }

         this.clip = AudioSystem.getClip();
         this.clip.open(AudioSystem.getAudioInputStream(url));
         this.clip.start();
         ((FloatControl)this.clip.getControl(Type.MASTER_GAIN)).setValue(f);
         this.volume = f;
      } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
         e.printStackTrace();
      }
   }

   public float getVolume() {
      return this.volume;
   }

   public boolean isPlaying() {
      return this.clip.isActive();
   }

   public void start() {
      this.clip.start();
   }

   public void playFile(File file, float f) {

      try {
         if (this.clip != null && this.clip.isActive()) {
            this.clip.stop();
         }

         this.clip = AudioSystem.getClip();
         this.clip.open(AudioSystem.getAudioInputStream(file));
         this.clip.start();
         ((FloatControl)this.clip.getControl(Type.MASTER_GAIN)).setValue(f);
         this.volume = f;
      } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
         e.printStackTrace();
      }
   }
}
