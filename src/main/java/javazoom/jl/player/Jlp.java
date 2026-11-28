package javazoom.jl.player;

import javazoom.jl.decoder.JavaLayerException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Jlp {
   private String fFilename = null;
   private boolean remote = false;

   public AudioDevice getAudioDevice() throws JavaLayerException {
      return FactoryRegistry.systemRegistry().createAudioDevice();
   }

   public Jlp(String str) {
      this.setFFilename(str);
   }

   public boolean parseArgs(String[] strings) {
      boolean flag = false;
      if (strings.length == 1) {
         this.setFFilename(strings[0]);
         flag = true;
         this.remote = false;
      } else if (strings.length == 2) {
         if (!strings[0].equals("-url")) {
            this.showUsage();
         } else {
            this.setFFilename(strings[1]);
            flag = true;
            this.remote = true;
         }
      } else {
         this.showUsage();
      }

      return flag;
   }

   public static Jlp createInstance(String[] strings) {
      Jlp jlp = new Jlp();
      if (!jlp.parseArgs(strings)) {
         jlp = null;
      }

      return jlp;
   }

   public void setFFilename(String str) {
      this.fFilename = str;
   }

   public InputStream getURLInputStream() throws Exception {
      InputStream in = new URL(this.fFilename).openStream();
      return new BufferedInputStream(in);
   }

   public InputStream getInputStream() throws IOException {
      FileInputStream fileInputStream = new FileInputStream(this.fFilename);
      return new BufferedInputStream(fileInputStream);
   }

   private Jlp() {
   }

   public static void main(String[] args) {
      byte b = 0;

      try {
         Jlp jlp = createInstance(args);
         if (jlp != null) {
            jlp.play();
         }
      } catch (Exception e) {
         System.err.println(e);
         e.printStackTrace(System.err);
         b = 1;
      }

      System.exit(b);
   }

   public void play() throws JavaLayerException {
      try {
         System.out.println("playing " + this.fFilename + "...");
         Object obj = null;
         if (this.remote) {
            obj = this.getURLInputStream();
         } else {
            obj = this.getInputStream();
         }

         AudioDevice audioDevice = this.getAudioDevice();
         new Player((InputStream)obj, audioDevice).play();
      } catch (IOException e) {
         throw new JavaLayerException("Problem playing file " + this.fFilename, e);
      } catch (Exception e2) {
         throw new JavaLayerException("Problem playing file " + this.fFilename, e2);
      }
   }

   public void showUsage() {
      System.out.println("Usage: jlp [-url] <filename>");
      System.out.println();
      System.out.println(" e.g. : java javazoom.jl.player.jlp localfile.mp3");
      System.out.println("        java javazoom.jl.player.jlp -url http://www.server.com/remotefile.mp3");
      System.out.println("        java javazoom.jl.player.jlp -url http://www.shoutcastserver.com:8000");
   }
}
