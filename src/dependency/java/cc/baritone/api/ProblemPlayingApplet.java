package cc.baritone.api;

import cc.baritone.utils.AStarOptimizer;
import cc.baritone.utils.AuxiliaryExceptionAdapter;
import cc.baritone.utils.InternalCustom;
import java.applet.Applet;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ProblemPlayingApplet extends Applet implements Runnable {
   public static final String labelText = "audioURL";
   public TertiaryItemEntry tertiaryItemEntry = null;
   public Thread thread = null;
   public String labelText2 = null;

   @Override
   public void run() {
      if (this.tertiaryItemEntry != null) {
         try {
            this.tertiaryItemEntry.updateState2();
         } catch (AuxiliaryExceptionAdapter exception) {
            System.err.println("Problem playing audio: " + exception);
         }
      }
   }

   @Override
   public void start() {
      String parameter = this.getParameter();

      try {
         InputStream inputStream = this.getInputStream();
         InternalCustom internalCustom = this.getInternalCustom();
         this.applyInputStream(inputStream, internalCustom);
      } catch (AuxiliaryExceptionAdapter exception) {
         AuxiliaryExceptionAdapter auxiliaryExceptionAdapter = exception;
         synchronized (System.err) {
            System.err.println("Unable to play " + parameter);
            auxiliaryExceptionAdapter.printStackTrace(System.err);
         }
      }
   }

   @Override
   public void init() {
   }

   public InternalCustom getInternalCustom() throws AuxiliaryExceptionAdapter {
      return AStarOptimizer.getAStarOptimizer().getInternalCustom();
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public InputStream getInputStream() {
      InputStream inputStream = null;

      try {
         URL uRL = this.getURL();
         if (uRL != null) {
            inputStream = uRL.openStream();
         }
      } catch (IOException ioException) {
         System.err.println(ioException);
      }

      return inputStream;
   }

   public String getParameter() {
      String labelText3 = this.labelText2;
      if (labelText3 == null) {
         labelText3 = this.getParameter("audioURL");
      }

      return labelText3;
   }

   public void setLabelText2(String labelText3) {
      this.labelText2 = labelText3;
   }

   public void updateState() throws AuxiliaryExceptionAdapter {
      if (this.tertiaryItemEntry != null) {
         this.tertiaryItemEntry.updateState();
         this.tertiaryItemEntry = null;
         this.thread = null;
      }
   }

   @Override
   public void destroy() {
   }

   @Override
   public void stop() {
      try {
         this.updateState();
      } catch (AuxiliaryExceptionAdapter exception) {
         System.err.println(exception);
      }
   }

   public Thread getThread() {
      return new Thread(this, "Audio player thread");
   }

   public URL getURL() {
      String parameter = this.getParameter();
      URL url = null;
      if (parameter != null) {
         try {
            url = new URL(this.getDocumentBase(), parameter);
         } catch (Exception exception) {
            System.err.println(exception);
         }
      }

      return url;
   }

   public void applyInputStream(InputStream inputStream, InternalCustom internalCustom) throws AuxiliaryExceptionAdapter {
      this.updateState();
      if (inputStream != null && internalCustom != null) {
         this.tertiaryItemEntry = new TertiaryItemEntry(inputStream, internalCustom);
         this.thread = this.getThread();
         this.thread.start();
      }
   }
}
