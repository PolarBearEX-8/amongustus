package cc.baritone.utils;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.FloatControl.Type;

public class HeuristicScheduler extends AbstractBreadthFirstPlannerInternalCustom {
   public SourceDataLine sourceDataLine = null;
   public AudioFormat audioFormat = null;
   public byte[] byteFlagArray = new byte[4096];

   @Override
   public int getMicrosecondPosition() {
      int count = 0;
      if (this.sourceDataLine != null) {
         count = (int)(this.sourceDataLine.getMicrosecondPosition() / 1000L);
      }

      return count;
   }

   public byte[] resolveByteArray(short[] shortValueArray, int count, int count2) {
      byte[] byteValueArray = this.resolveByteArray(count2 * 2);
      int count3 = 0;

      while (count2-- > 0) {
         short shortValue = shortValueArray[count++];
         byteValueArray[count3++] = (byte)shortValue;
         byteValueArray[count3++] = (byte)(shortValue >>> 8);
      }

      return byteValueArray;
   }

   @Override
   public void drain() {
      if (this.sourceDataLine != null) {
         this.sourceDataLine.drain();
      }
   }

   public int computeCount(AudioFormat audioFormat2, int count) {
      return (int)((double)((float)count * audioFormat2.getSampleRate() * (float)audioFormat2.getChannels() * (float)audioFormat2.getSampleSizeInBits()) / 8000.0);
   }

   @Override
   public void updateState3() throws AuxiliaryExceptionAdapter {
   }

   public AudioFormat getAudioFormat() {
      if (this.audioFormat == null) {
         this.audioFormat = new AudioFormat(44100.0F, 16, 2, true, false);
      }

      return this.audioFormat;
   }

   public void setAudioFormat(AudioFormat audioFormat2) {
      this.audioFormat = audioFormat2;
   }

   public Info getInfo() {
      AudioFormat audioFormat2 = this.getAudioFormat();
      return new Info(SourceDataLine.class, audioFormat2);
   }

   public byte[] resolveByteArray(int count) {
      if (this.byteFlagArray.length < count) {
         this.byteFlagArray = new byte[count + 1024];
      }

      return this.byteFlagArray;
   }

   public void updateState5() throws AuxiliaryExceptionAdapter {
      Throwable throwable = null;

      try {
         Line line = AudioSystem.getLine(this.getInfo());
         if (line instanceof SourceDataLine) {
            this.sourceDataLine = (SourceDataLine)line;
            this.sourceDataLine.open(this.audioFormat);
            this.sourceDataLine.start();
         }
      } catch (LinkageError | LineUnavailableException | RuntimeException linkageError) {
         throwable = linkageError;
      }

      if (this.sourceDataLine == null) {
         throw new AuxiliaryExceptionAdapter("cannot obtain source audio line", throwable);
      }
   }

   public boolean checkFloat(float factor) {
      if (this.sourceDataLine != null) {
         FloatControl control = (FloatControl)this.sourceDataLine.getControl(Type.MASTER_GAIN);
         float factor2 = Math.min(Math.max(factor, control.getMinimum()), control.getMaximum());
         control.setValue(factor2);
         return true;
      } else {
         return false;
      }
   }

   public void updateState4() throws AuxiliaryExceptionAdapter {
      try {
         this.applyAudioFormat(new AudioFormat(22000.0F, 16, 1, true, false));
         short[] shortValueArray = new short[2200];
         this.applyShortArray(shortValueArray, 0, shortValueArray.length);
         this.updateState2();
         this.updateState();
      } catch (RuntimeException exception) {
         throw new AuxiliaryExceptionAdapter("Device test failed: " + exception);
      }
   }

   public void applyAudioFormat(AudioFormat audioFormat2) throws AuxiliaryExceptionAdapter {
      if (!this.isEnabled()) {
         this.setAudioFormat(audioFormat2);
         this.updateState3();
         this.setEnabled(true);
      }
   }

   @Override
   public void close() {
      if (this.sourceDataLine != null) {
         this.sourceDataLine.close();
      }
   }

   @Override
   public void applyShortArrayByShortArray(short[] shortValueArray, int count, int count2) throws AuxiliaryExceptionAdapter {
      if (this.sourceDataLine == null) {
         this.updateState5();
      }

      byte[] byteValueArray = this.resolveByteArray(shortValueArray, count, count2);
      this.sourceDataLine.write(byteValueArray, 0, count2 * 2);
   }
}
