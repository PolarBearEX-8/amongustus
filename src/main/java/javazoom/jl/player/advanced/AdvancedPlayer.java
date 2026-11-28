package javazoom.jl.player.advanced;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import java.io.InputStream;

public class AdvancedPlayer {
   private Bitstream bitstream;
   private Decoder decoder;
   private AudioDevice audio;
   private boolean closed = false;
   private boolean complete = false;
   private int lastPosition = 0;
   private PlaybackListener listener;

   public boolean play(int n, int n2) throws JavaLayerException {
      boolean flag = true;
      int n3 = n;

      while (n3-- > 0 && flag) {
         flag = this.skipFrame();
      }

      return this.play(n2 - n);
   }

   private PlaybackEvent createEvent(AudioDevice audioDevice, int n) {
      return new PlaybackEvent(this, n, audioDevice.getPosition());
   }

   public void setListener(PlaybackListener listener2) {
      this.listener = listener2;
   }

   public void stop() {
      this.listener.playbackFinished(this.createEvent(PlaybackEvent.STOPPED));
      this.close();
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
      this.play(Integer.MAX_VALUE);
   }

   private PlaybackEvent createEvent(int n) {
      return this.createEvent(this.audio, n);
   }

   public PlaybackListener getListener() {
      return this.listener;
   }

   public AdvancedPlayer(InputStream in, AudioDevice audioDevice) throws JavaLayerException {
      this.bitstream = new Bitstream(in);
      if (audioDevice != null) {
         this.audio = audioDevice;
      } else {
         this.audio = FactoryRegistry.systemRegistry().createAudioDevice();
      }

      this.audio.open(this.decoder = new Decoder());
   }

   public AdvancedPlayer(InputStream in) throws JavaLayerException {
      this(in, null);
   }

   public boolean play(int n) throws JavaLayerException {
      boolean flag = true;
      if (this.listener != null) {
         this.listener.playbackStarted(this.createEvent(PlaybackEvent.STARTED));
      }

      while (n-- > 0 && flag) {
         flag = this.decodeFrame();
      }

      AudioDevice audio2 = this.audio;
      if (audio2 != null) {
         audio2.flush();
         synchronized (this) {
            this.complete = !this.closed;
            this.close();
         }

         if (this.listener != null) {
            this.listener.playbackFinished(this.createEvent(audio2, PlaybackEvent.STOPPED));
         }
      }

      return flag;
   }

   public boolean skipFrame() throws JavaLayerException {
      if (this.bitstream.readFrame() == null) {
         return false;
      } else {
         this.bitstream.closeFrame();
         return true;
      }
   }
}
