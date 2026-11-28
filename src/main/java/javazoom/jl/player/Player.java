package javazoom.jl.player;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.SampleBuffer;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player {
   private int frame = 0;
   private Bitstream bitstream;
   private InputStream inputStream;
   private Decoder decoder;
   private AudioDevice audio;
   private boolean closed = false;
   private boolean complete = false;
   private int lastPosition = 0;

   public synchronized void close() {
      AudioDevice audio2 = this.audio;
      if (audio2 != null) {
         this.closed = true;
         this.audio = null;
         audio2.close();
         this.lastPosition = audio2.getPosition();

         try {
            this.bitstream.close();
         } catch (BitstreamException e) {
         }
      }
   }

   public void play() throws JavaLayerException {
      this.play2(Integer.MAX_VALUE);
   }

   public synchronized boolean isComplete() {
      return this.complete;
   }

   public boolean skip(int n) throws JavaLayerException {
      boolean flag = true;
      int n2 = n / 26;

      while (n2-- > 0 && flag) {
         flag = this.skipFrame();
      }

      return flag;
   }

   public boolean play(int n, AtomicBoolean atomicBoolean) throws JavaLayerException {
      boolean flag = true;

      while (n-- > 0 && flag && atomicBoolean.get()) {
         flag = this.decodeFrame();
      }

      if (flag && !atomicBoolean.get()) {
         flag = false;
      }

      if (!flag) {
         AudioDevice audio2 = this.audio;
         if (audio2 != null) {
            audio2.flush();
            synchronized (this) {
               this.complete = !this.closed;
               this.close();
            }
         }
      }

      return flag;
   }

   public boolean decodeFrame() throws JavaLayerException {
      try {
         AudioDevice audio2 = this.audio;
         if (audio2 == null) {
            return false;
         } else {
            Header header = this.bitstream.readFrame();
            if (header == null) {
               return false;
            } else {
               SampleBuffer sampleBuffer = (SampleBuffer)this.decoder.decodeFrame(header, this.bitstream);
               synchronized (this) {
                  audio2 = this.audio;
                  if (audio2 != null) {
                     audio2.write(sampleBuffer.getBuffer(), 0, sampleBuffer.getBufferLength());
                  }
               }

               this.bitstream.closeFrame();
               return true;
            }
         }
      } catch (RuntimeException e) {
         throw new JavaLayerException("Exception decoding audio frame", e);
      }
   }

   public Player(InputStream in) throws JavaLayerException {
      this(in, null);
   }

   public boolean skipFrame() throws JavaLayerException {
      if (this.bitstream.readFrame() == null) {
         return false;
      } else {
         this.bitstream.closeFrame();
         return true;
      }
   }

   public void play(AtomicBoolean atomicBoolean) throws JavaLayerException {
      this.play(Integer.MAX_VALUE, atomicBoolean);
   }

   public void play2(int n) throws JavaLayerException {
      this.play(n, new AtomicBoolean(true));
   }

   public Player(InputStream in, AudioDevice audioDevice) throws JavaLayerException {
      this.inputStream = in;
      this.bitstream = new Bitstream(in);
      this.decoder = new Decoder();
      if (audioDevice != null) {
         this.audio = audioDevice;
      } else {
         this.audio = FactoryRegistry.systemRegistry().createAudioDevice();
      }

      this.audio.open(this.decoder);
   }

   public boolean setGain(float f) {
      if (this.audio instanceof JavaSoundAudioDevice) {
         JavaSoundAudioDevice audio2 = (JavaSoundAudioDevice)this.audio;

         try {
            audio2.write(null, 0, 0);
         } catch (JavaLayerException e) {
            e.printStackTrace();
         }

         audio2.setLineGain(f);
      }

      return false;
   }

   public int getPosition() {
      int lastPosition2 = this.lastPosition;
      AudioDevice audio2 = this.audio;
      if (audio2 != null) {
         lastPosition2 = audio2.getPosition();
      }

      return lastPosition2;
   }
}
