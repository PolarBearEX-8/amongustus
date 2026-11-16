package cc.baritone.utils;

import cc.baritone.api.TertiaryItemEntry;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class UrlPlayingController {
   public String labelText = null;
   public boolean enabled = false;

   private UrlPlayingController() {
   }

   public static UrlPlayingController resolveUrlPlayingController(String[] textArray) {
      UrlPlayingController urlPlayingController = new UrlPlayingController();
      if (!urlPlayingController.matchesTextArray(textArray)) {
         urlPlayingController = null;
      }

      return urlPlayingController;
   }

   public UrlPlayingController(String text) {
      this.setLabelText(text);
   }

   public static void main(String[] textArray) {
      byte byteValue = 0;

      try {
         UrlPlayingController urlPlayingController = resolveUrlPlayingController(textArray);
         if (urlPlayingController != null) {
            urlPlayingController.updateState2();
         }
      } catch (Exception exception) {
         System.err.println(exception);
         exception.printStackTrace(System.err);
         byteValue = 1;
      }

      System.exit(byteValue);
   }

   public boolean matchesTextArray(String[] textArray) {
      boolean flag = false;
      if (textArray.length == 1) {
         this.setLabelText(textArray[0]);
         flag = true;
         this.enabled = false;
      } else if (textArray.length == 2) {
         if (!textArray[0].equals("-url")) {
            this.updateState();
         } else {
            this.setLabelText(textArray[1]);
            flag = true;
            this.enabled = true;
         }
      } else {
         this.updateState();
      }

      return flag;
   }

   public InputStream openStream() throws Exception {
      InputStream inputStream = new URL(this.labelText).openStream();
      return new BufferedInputStream(inputStream);
   }

   public InternalCustom getInternalCustom() throws AuxiliaryExceptionAdapter {
      return AStarOptimizer.getAStarOptimizer().getInternalCustom();
   }

   public void updateState2() throws AuxiliaryExceptionAdapter {
      try {
         System.out.println("playing " + this.labelText + "...");
         InputStream inputStream;
         if (this.enabled) {
            inputStream = this.openStream();
         } else {
            inputStream = this.getInputStream();
         }

         InternalCustom internalCustom = this.getInternalCustom();
         new TertiaryItemEntry(inputStream, internalCustom).updateState2();
      } catch (IOException ioException) {
         throw new AuxiliaryExceptionAdapter("Problem playing file " + this.labelText, ioException);
      } catch (Exception exception) {
         throw new AuxiliaryExceptionAdapter("Problem playing file " + this.labelText, exception);
      }
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   public void updateState() {
      System.out.println("Usage: jlp [-url] <filename>");
      System.out.println();
      System.out.println(" e.g. : java javazoom.jl.player.jlp localfile.mp3");
      System.out.println("        java javazoom.jl.player.jlp -url http://www.server.com/remotefile.mp3");
      System.out.println("        java javazoom.jl.player.jlp -url http://www.shoutcastserver.com:8000");
   }

   public InputStream getInputStream() throws IOException {
      FileInputStream inputStream = new FileInputStream(this.labelText);
      return new BufferedInputStream(inputStream);
   }
}
