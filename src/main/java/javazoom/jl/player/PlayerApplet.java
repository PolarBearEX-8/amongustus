package javazoom.jl.player;

import javazoom.jl.decoder.JavaLayerException;
import java.applet.Applet;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PlayerApplet extends Applet implements Runnable {
   public static final String AUDIO_PARAMETER = null;
   private Player player = null;
   private Thread playerThread = null;
   private String fileName = null;

   public AudioDevice getAudioDevice() throws JavaLayerException {
      return FactoryRegistry.systemRegistry().createAudioDevice();
   }

   public String getAudioFileName() {
      String fileName2 = this.fileName;
      if (fileName2 == null) {
         fileName2 = this.getParameter("audioURL");
      }

      return fileName2;
   }

   public void setFileName(String str) {
      this.fileName = str;
   }

   public void stopPlayer() throws JavaLayerException {
      if (this.player != null) {
         this.player.close();
         this.player = null;
         this.playerThread = null;
      }
   }

   @Override
   public void run() {
      if (this.player != null) {
         try {
            this.player.play();
         } catch (JavaLayerException e) {
            System.err.println("Problem playing audio: " + e);
         }
      }
   }

   public URL getAudioURL() {
      String audioFileName = this.getAudioFileName();
      URL url = null;
      if (audioFileName != null) {
         try {
            url = new URL(this.getDocumentBase(), audioFileName);
         } catch (Exception e) {
            System.err.println(e);
         }
      }

      return url;
   }

   @Override
   public void init() {
   }

   @Override
   public void destroy() {
   }

   @Override
   public void stop() {
      try {
         this.stopPlayer();
      } catch (JavaLayerException e) {
         System.err.println(e);
      }
   }

   public InputStream getAudioStream() {
      InputStream in = null;

      try {
         URL audioURL = this.getAudioURL();
         if (audioURL != null) {
            in = audioURL.openStream();
         }
      } catch (IOException e) {
         System.err.println(e);
      }

      return in;
   }

   public void play(InputStream in, AudioDevice audioDevice) throws JavaLayerException {
      this.stopPlayer();
      if (in != null && audioDevice != null) {
         this.player = new Player(in, audioDevice);
         this.playerThread = this.createPlayerThread();
         this.playerThread.start();
      }
   }

   @Override
   public void start() {
      String audioFileName = this.getAudioFileName();

      try {
         this.play(this.getAudioStream(), this.getAudioDevice());
      } catch (JavaLayerException e) {
         synchronized (System.err) {
            System.err.println("Unable to play " + audioFileName);
            e.printStackTrace(System.err);
         }
      }
   }

   public Thread createPlayerThread() {
      return new Thread(this, "Audio player thread");
   }

   public String getFileName() {
      return this.fileName;
   }
}
