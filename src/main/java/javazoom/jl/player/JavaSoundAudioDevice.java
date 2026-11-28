package javazoom.jl.player;

import javazoom.jl.decoder.JavaLayerException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.FloatControl.Type;

public class JavaSoundAudioDevice extends AudioDeviceBase {
   private SourceDataLine source = null;
   private AudioFormat fmt = null;
   private byte[] byteBuf = new byte[4096];

   @Override
   public void flushImpl() {
      if (this.source != null) {
         this.source.drain();
      }
   }

   public void open2(AudioFormat audioFormat) throws JavaLayerException {
      if (!this.isOpen()) {
         this.setFmt(audioFormat);
         this.openImpl();
         this.setOpen(true);
      }
   }

   public byte[] getByteArray(int n) {
      if (this.byteBuf.length < n) {
         this.byteBuf = new byte[n + 1024];
      }

      return this.byteBuf;
   }

   public AudioFormat getAudioFormat() {
      if (this.fmt == null) {
         this.fmt = new AudioFormat(44100.0F, 16, 2, true, false);
      }

      return this.fmt;
   }

   public Info getSourceLineInfo() {
      AudioFormat audioFormat = this.getAudioFormat();
      return new Info(SourceDataLine.class, audioFormat);
   }

   public boolean setLineGain(float f) {
      if (this.source != null) {
         FloatControl control = (FloatControl)this.source.getControl(Type.MASTER_GAIN);
         control.setValue(Math.min(Math.max(f, control.getMinimum()), control.getMaximum()));
         return true;
      } else {
         return false;
      }
   }

   public void createSource() throws JavaLayerException {
      Throwable t = null;

      try {
         Line line = AudioSystem.getLine(this.getSourceLineInfo());
         if (line instanceof SourceDataLine) {
            this.source = (SourceDataLine)line;
            this.source.open(this.fmt);
            this.source.start();
         }
      } catch (LinkageError | LineUnavailableException | RuntimeException e) {
         t = e;
      }

      if (this.source == null) {
         throw new JavaLayerException("cannot obtain source audio line", t);
      }
   }

   public void setFmt(AudioFormat audioFormat) {
      this.fmt = audioFormat;
   }

   @Override
   public void closeImpl() {
      if (this.source != null) {
         this.source.close();
      }
   }

   public int millisecondsToBytes(AudioFormat audioFormat, int n) {
      return (int)((double)((float)n * audioFormat.getSampleRate() * (float)audioFormat.getChannels() * (float)audioFormat.getSampleSizeInBits()) / 8000.0);
   }

   public byte[] toByteArray(short[] sArr, int n, int n2) {
      byte[] byteArray = this.getByteArray(n2 * 2);
      int n3 = 0;

      while (n2-- > 0) {
         short s = sArr[n++];
         byteArray[n3++] = (byte)s;
         byteArray[n3++] = (byte)(s >>> 8);
      }

      return byteArray;
   }

   @Override
   public void openImpl() throws JavaLayerException {
   }

   public void test() throws JavaLayerException {
      try {
         this.open2(new AudioFormat(22000.0F, 16, 1, true, false));
         short[] sArr = new short[2200];
         this.write(sArr, 0, sArr.length);
         this.flush();
         this.close();
      } catch (RuntimeException e) {
         throw new JavaLayerException("Device test failed: " + e);
      }
   }

   @Override
   public int getPosition() {
      int n = 0;
      if (this.source != null) {
         n = (int)(this.source.getMicrosecondPosition() / 1000L);
      }

      return n;
   }

   @Override
   public void writeImpl(short[] sArr, int n, int n2) throws JavaLayerException {
      if (this.source == null) {
         this.createSource();
      }

      byte[] bytes = this.toByteArray(sArr, n, n2);
      this.source.write(bytes, 0, n2 * 2);
   }
}
