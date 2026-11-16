package client.util;

import client.event.PreRenderBroadcaster;
import client.render.AllocateTextureMapManager;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerEntryHandler extends DefaultLoggerEntry {
   public static final Logger logger2 = LogManager.getLogger();
   public static final AtomicInteger atomicInteger = new AtomicInteger(0);
   public final File file;
   public final String labelText;
   public final PreRenderBroadcaster preRenderBroadcaster;
   public BufferedImage bufferedImage;
   public Thread thread;
   public boolean enabled8;
   public Boolean memberRef = null;
   public boolean enabled7 = false;

   private void updateState4() {
      this.memberRef = this.bufferedImage != null;
      if (this.preRenderBroadcaster instanceof AbstractClientPlayerEntry) {
         ((AbstractClientPlayerEntry)this.preRenderBroadcaster).updateState2();
      }
   }

   public static Logger getLogger2() {
      return logger2;
   }

   public static boolean matchesLoggerEntryHandler(LoggerEntryHandler loggerEntryHandler) {
      return loggerEntryHandler.isActive();
   }

   private boolean isActive() {
      if (!this.enabled7) {
         return false;
      } else {
         Proxy proxy = Minecraft.getMinecraft().getProxy();
         return proxy.type() != Type.DIRECT && proxy.type() != Type.SOCKS ? false : this.labelText.startsWith("http://");
      }
   }

   public PreRenderBroadcaster getPreRenderBroadcaster() {
      return this.preRenderBroadcaster;
   }

   public static File resolveFile(LoggerEntryHandler loggerEntryHandler) {
      return loggerEntryHandler.file;
   }

   public static String resolveText(LoggerEntryHandler loggerEntryHandler) {
      return loggerEntryHandler.labelText;
   }

   public static void applyLoggerEntryHandlerByLoggerEntryHandl(LoggerEntryHandler loggerEntryHandler) {
      loggerEntryHandler.updateState4();
   }

   @Override
   public int getCount() {
      this.updateState2();
      return super.getCount();
   }

   private void updateState6() {
      try {
         ComplexStringEntry complexStringEntry = AuxiliaryResponseManager.resolveComplexStringEntry(
            AuxiliaryResponseManager.resolveHEADManager(this.labelText, Minecraft.getMinecraft().getProxy())
         );
         if (complexStringEntry.getCount() / 100 == 2) {
            byte[] byteFlagArray = complexStringEntry.getByteFlagArray();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteFlagArray);
            BufferedImage bufferedImage2;
            if (this.file != null) {
               FileUtils.copyInputStreamToFile(byteArrayInputStream, this.file);
               bufferedImage2 = ImageIO.read(this.file);
            } else {
               bufferedImage2 = SpecialLoggerEntry.resolveBufferedImage(byteArrayInputStream);
            }

            if (this.preRenderBroadcaster != null) {
               bufferedImage2 = this.preRenderBroadcaster.resolveBufferedImage(bufferedImage2);
            }

            this.applyBufferedImage(bufferedImage2);
            return;
         }
      } catch (Exception exception) {
         logger2.error("Couldn't download http texture: " + exception.getClass().getName() + ": " + exception.getMessage());
         return;
      } finally {
         this.updateState4();
      }
   }

   public static PreRenderBroadcaster resolvePreRenderBroadcaster(LoggerEntryHandler loggerEntryHandler) {
      return loggerEntryHandler.preRenderBroadcaster;
   }

   public void applyBufferedImage(BufferedImage bufferedImage2) {
      this.bufferedImage = bufferedImage2;
      if (this.preRenderBroadcaster != null) {
         this.preRenderBroadcaster.updateState();
      }

      this.memberRef = this.bufferedImage != null;
   }

   public void updateState5() {
      this.thread = new SecondaryThreadAdapter(this, "Texture Downloader #" + atomicInteger.incrementAndGet());
      this.thread.setDaemon(true);
      this.thread.start();
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) throws IOException {
      if (this.bufferedImage == null && this.resourceLocation2 != null) {
         super.applyIResourceManager(iResourceManager);
      }

      if (this.thread == null) {
         if (this.file != null && this.file.isFile()) {
            logger2.debug("Loading http texture from local cache ({})", new Object[]{this.file});

            try {
               this.bufferedImage = ImageIO.read(this.file);
               if (this.preRenderBroadcaster != null) {
                  this.applyBufferedImage(this.preRenderBroadcaster.resolveBufferedImage(this.bufferedImage));
               }

               this.updateState4();
            } catch (IOException ioException) {
               logger2.error("Couldn't load skin " + this.file, ioException);
               this.updateState5();
            }
         } else {
            this.updateState5();
         }
      }
   }

   public static void applyLoggerEntryHandler(LoggerEntryHandler loggerEntryHandler) {
      loggerEntryHandler.updateState6();
   }

   private void updateState2() {
      if (!this.enabled8 && this.bufferedImage != null) {
         this.enabled8 = true;
         if (this.resourceLocation2 != null) {
            this.updateState3();
         }

         if (BuildManager.canExecute()) {
            AllocateTextureMapManager.computeCountByBufferedImage(
               super.getCount(), this.bufferedImage, false, false, BuildManager.getIResourceManager(), this.resourceLocation2, this.getDistanceTransformer()
            );
         } else {
            SpecialLoggerEntry.computeCount(super.getCount(), this.bufferedImage);
         }
      }
   }

   public LoggerEntryHandler(File file2, String text, ResourceLocation location, PreRenderBroadcaster preRenderBroadcaster2) {
      super(location, true);
      this.file = file2;
      this.labelText = text;
      this.preRenderBroadcaster = preRenderBroadcaster2;
   }
}
