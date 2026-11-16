package client.util;

import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.FileUtils;

public class SecondaryThreadAdapter extends Thread {
   public final LoggerEntryHandler loggerEntryHandler;

   public SecondaryThreadAdapter(LoggerEntryHandler loggerEntryHandler2, String text) {
      super(text);
      this.loggerEntryHandler = loggerEntryHandler2;
   }

   @Override
   public void run() {
      HttpURLConnection httpURLConnection = null;
      LoggerEntryHandler.getLogger2()
         .debug(
            "Downloading http texture from {} to {}",
            new Object[]{LoggerEntryHandler.resolveText(this.loggerEntryHandler), LoggerEntryHandler.resolveFile(this.loggerEntryHandler)}
         );
      if (LoggerEntryHandler.matchesLoggerEntryHandler(this.loggerEntryHandler)) {
         LoggerEntryHandler.applyLoggerEntryHandler(this.loggerEntryHandler);
      } else {
         try {
            httpURLConnection = (HttpURLConnection)new URL(LoggerEntryHandler.resolveText(this.loggerEntryHandler)).openConnection(Minecraft.getMinecraft().getProxy());
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(false);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() / 100 == 2) {
               BufferedImage bufferedImage;
               if (LoggerEntryHandler.resolveFile(this.loggerEntryHandler) != null) {
                  FileUtils.copyInputStreamToFile(httpURLConnection.getInputStream(), LoggerEntryHandler.resolveFile(this.loggerEntryHandler));
                  bufferedImage = ImageIO.read(LoggerEntryHandler.resolveFile(this.loggerEntryHandler));
               } else {
                  bufferedImage = SpecialLoggerEntry.resolveBufferedImage(httpURLConnection.getInputStream());
               }

               if (LoggerEntryHandler.resolvePreRenderBroadcaster(this.loggerEntryHandler) != null) {
                  bufferedImage = LoggerEntryHandler.resolvePreRenderBroadcaster(this.loggerEntryHandler).resolveBufferedImage(bufferedImage);
               }

               this.loggerEntryHandler.applyBufferedImage(bufferedImage);
               return;
            }

            if (httpURLConnection.getErrorStream() != null) {
               BuildManager.resolveByteArray(httpURLConnection.getErrorStream());
            }

            return;
         } catch (Exception exception) {
            LoggerEntryHandler.getLogger2().error("Couldn't download http texture: " + exception.getClass().getName() + ": " + exception.getMessage());
         } finally {
            if (httpURLConnection != null) {
               httpURLConnection.disconnect();
            }

            LoggerEntryHandler.applyLoggerEntryHandlerByLoggerEntryHandl(this.loggerEntryHandler);
         }
      }
   }
}
