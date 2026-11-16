package net.minecraft.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.FloatControl.Type;

public class ClipController {
   public float factor;
   public Clip clip = null;

   public boolean isActive() {
      return this.clip.isActive();
   }

   public void applyFile(File file, float factor2) {
      try {
         if (this.clip != null && this.clip.isActive()) {
            this.clip.stop();
         }

         this.clip = AudioSystem.getClip();
         this.clip.open(AudioSystem.getAudioInputStream(file));
         this.clip.start();
         FloatControl control = (FloatControl)this.clip.getControl(Type.MASTER_GAIN);
         control.setValue(factor2);
         this.factor = factor2;
      } catch (UnsupportedAudioFileException | IOException | LineUnavailableException unsupportedAudioFileException) {
         unsupportedAudioFileException.printStackTrace();
      }
   }

   public float getFactor() {
      return this.factor;
   }

   public void applyTime(long value) {
      value = 0x3babdf3d52dbL ^ value;
      if (this.clip != null && this.clip.isActive()) {
         this.clip.stop();
      }
   }

   public void applyURL(URL url, float factor2) {
      long value = 0x2cbdf4290396L;
      value = 0x3babdf3d52dbL ^ value;

      try {
         if (this.clip != null && this.clip.isActive()) {
            this.clip.stop();
         }

         this.clip = AudioSystem.getClip();
         this.clip.open(AudioSystem.getAudioInputStream(url));
         this.clip.start();
         FloatControl control = (FloatControl)this.clip.getControl(Type.MASTER_GAIN);
         control.setValue(factor2);
         this.factor = factor2;
      } catch (UnsupportedAudioFileException | IOException | LineUnavailableException unsupportedAudioFileException) {
         unsupportedAudioFileException.printStackTrace();
      }
   }

   public void loop(int count) {
      this.clip.loop(count);
   }

   public void start() {
      this.clip.start();
   }

   public void applyFloat(float factor2) {
      long value = 0x3700f0694570L;
      value = 0x3babdf3d52dbL ^ value;
      factor2 = MathHelper.clamp_float(factor2, -79.0F, 20.0F);
      FloatControl control = (FloatControl)this.clip.getControl(Type.MASTER_GAIN);
      control.setValue(factor2);
      this.factor = factor2;
   }
}
