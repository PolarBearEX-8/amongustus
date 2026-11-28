package javazoom.jl.player.advanced;

import javazoom.jl.decoder.JavaLayerException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Jlap {

   public void showUsage() {
      System.out.println("Usage: jla <filename>");
      System.out.println();
      System.out.println(" e.g. : java javazoom.jl.player.advanced.jlap localfile.mp3");
   }

   public static AdvancedPlayer playMp32(InputStream in, int n, int n2, PlaybackListener listener) throws JavaLayerException {
      AdvancedPlayer advancedPlayer = new AdvancedPlayer(in);
      advancedPlayer.setListener(listener);
      new AdvancedPlayerThread(advancedPlayer, n, n2).start();
      return advancedPlayer;
   }

   public void play(String str) throws JavaLayerException, IOException {
      InfoPlaybackListener listener = new InfoPlaybackListener(this);
      playMp3(new File(str), listener);
   }

   public static AdvancedPlayer playMp3(File file, PlaybackListener listener) throws IOException, JavaLayerException {
      return playMp3(file, 0, Integer.MAX_VALUE, listener);
   }

   public static void main(String[] args) {
      Jlap jlap = new Jlap();
      if (args.length != 1) {
         jlap.showUsage();
         System.exit(0);
      } else {
         try {
            jlap.play(args[0]);
         } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
         }
      }
   }

   public static AdvancedPlayer playMp3(File file, int n, int n2, PlaybackListener listener) throws IOException, JavaLayerException {
      return playMp32(new BufferedInputStream(new FileInputStream(file)), n, n2, listener);
   }
}
