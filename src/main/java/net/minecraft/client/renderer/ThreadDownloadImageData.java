package net.minecraft.client.renderer;

import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.http.HttpPipeline;
import net.optifine.http.HttpRequest;
import net.optifine.http.HttpResponse;
import net.optifine.player.CapeImageBuffer;
import net.optifine.shaders.ShadersTex;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadDownloadImageData extends SimpleTexture {
   private static final Logger logger = LogManager.getLogger();
   private static final AtomicInteger threadDownloadCounter = new AtomicInteger(0);
   private final File cacheFile;
   private final String imageUrl;
   private final IImageBuffer imageBuffer;
   private BufferedImage bufferedImage;
   private Thread imageThread;
   private boolean textureUploaded;
   public Boolean imageFound = null;
   public boolean pipeline = false;

   public static Logger accessLogger() {
      return logger;
   }

   public static void callLoadPipelined(ThreadDownloadImageData threadDownloadImageData) {
      threadDownloadImageData.loadPipelined();
   }

   public void loadTextureFromServer() {
      this.imageThread = new ThreadDownloadImageData.WorkerTask(this, "Texture Downloader #" + threadDownloadCounter.incrementAndGet());
      this.imageThread.setDaemon(true);
      this.imageThread.start();
   }

   public static boolean callShouldPipeline(ThreadDownloadImageData threadDownloadImageData) {
      return threadDownloadImageData.shouldPipeline();
   }

   public void setBufferedImage(BufferedImage image) {
      this.bufferedImage = image;
      if (this.imageBuffer != null) {
         this.imageBuffer.skinAvailable();
      }

      this.imageFound = this.bufferedImage != null;
   }

   public IImageBuffer getImageBuffer() {
      return this.imageBuffer;
   }

   public static File accessCacheFile(ThreadDownloadImageData threadDownloadImageData) {
      return threadDownloadImageData.cacheFile;
   }

   public static void callLoadingFinished(ThreadDownloadImageData threadDownloadImageData) {
      threadDownloadImageData.loadingFinished();
   }

   private boolean shouldPipeline() {
      if (!this.pipeline) {
         return false;
      } else {
         Proxy proxy = Minecraft.getMinecraft().getProxy();
         return proxy.type() != Type.DIRECT && proxy.type() != Type.SOCKS ? false : this.imageUrl.startsWith("http://");
      }
   }

   private void loadPipelined() {
      try {
         HttpResponse httpResponse = HttpPipeline.executeRequest(HttpPipeline.makeRequest(this.imageUrl, Minecraft.getMinecraft().getProxy()));
         if (httpResponse.getStatus() / 100 == 2) {
            byte[] body = httpResponse.getBody();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
            BufferedImage image;
            if (this.cacheFile != null) {
               FileUtils.copyInputStreamToFile(byteArrayInputStream, this.cacheFile);
               image = ImageIO.read(this.cacheFile);
            } else {
               image = TextureUtil.readBufferedImage(byteArrayInputStream);
            }

            if (this.imageBuffer != null) {
               image = this.imageBuffer.parseUserSkin(image);
            }

            this.setBufferedImage(image);
            return;
         }
      } catch (Exception e) {
         logger.error("Couldn't download http texture: " + e.getClass().getName() + ": " + e.getMessage());
         return;
      } finally {
         this.loadingFinished();
      }
   }

   public static String accessImageUrl(ThreadDownloadImageData threadDownloadImageData) {
      return threadDownloadImageData.imageUrl;
   }

   public ThreadDownloadImageData(File file, String str, ResourceLocation location, IImageBuffer imageBuffer2) {
      super(location, true);
      this.cacheFile = file;
      this.imageUrl = str;
      this.imageBuffer = imageBuffer2;
   }

   public static IImageBuffer accessImageBuffer(ThreadDownloadImageData threadDownloadImageData) {
      return threadDownloadImageData.imageBuffer;
   }

   @Override
   public int getGlTextureId() {
      this.checkTextureUploaded();
      return super.getGlTextureId();
   }

   @Override
   public void loadTexture(IResourceManager resourceManager) throws IOException {
      if (this.bufferedImage == null && this.textureLocation != null) {
         super.loadTexture(resourceManager);
      }

      if (this.imageThread == null) {
         if (this.cacheFile != null && this.cacheFile.isFile()) {
            logger.debug("Loading http texture from local cache ({})", new Object[]{this.cacheFile});

            try {
               this.bufferedImage = ImageIO.read(this.cacheFile);
               if (this.imageBuffer != null) {
                  this.setBufferedImage(this.imageBuffer.parseUserSkin(this.bufferedImage));
               }

               this.loadingFinished();
            } catch (IOException e) {
               logger.error("Couldn't load skin " + this.cacheFile, e);
               this.loadTextureFromServer();
            }
         } else {
            this.loadTextureFromServer();
         }
      }
   }

   private void checkTextureUploaded() {
      if (!this.textureUploaded && this.bufferedImage != null) {
         this.textureUploaded = true;
         if (this.textureLocation != null) {
            this.deleteGlTexture();
         }

         if (Config.isShaderPackLoaded()) {
            ShadersTex.loadSimpleTexture(super.getGlTextureId(), this.bufferedImage, false, false, Config.getResourceManager(), this.textureLocation, this.getMultiTexID());
         } else {
            TextureUtil.uploadTextureImage(super.getGlTextureId(), this.bufferedImage);
         }
      }
   }

   private void loadingFinished() {
      this.imageFound = this.bufferedImage != null;
      if (this.imageBuffer instanceof CapeImageBuffer) {
         ((CapeImageBuffer)this.imageBuffer).cleanup();
      }
   }


   public static class WorkerTask extends Thread {
      public final ThreadDownloadImageData imageData;

      public WorkerTask(ThreadDownloadImageData threadDownloadImageData, String str) {
         super(str);
         this.imageData = threadDownloadImageData;
      }

      @Override
      public void run() {
         HttpURLConnection conn = null;
         ThreadDownloadImageData.accessLogger()
            .debug(
               "Downloading http texture from {} to {}",
               new Object[]{ThreadDownloadImageData.accessImageUrl(this.imageData), ThreadDownloadImageData.accessCacheFile(this.imageData)}
            );
         if (ThreadDownloadImageData.callShouldPipeline(this.imageData)) {
            ThreadDownloadImageData.callLoadPipelined(this.imageData);
         } else {
            try {
               conn = (HttpURLConnection)new URL(ThreadDownloadImageData.accessImageUrl(this.imageData)).openConnection(Minecraft.getMinecraft().getProxy());
               conn.setDoInput(true);
               conn.setDoOutput(false);
               conn.connect();
               if (conn.getResponseCode() / 100 == 2) {
                  BufferedImage image;
                  if (ThreadDownloadImageData.accessCacheFile(this.imageData) != null) {
                     FileUtils.copyInputStreamToFile(conn.getInputStream(), ThreadDownloadImageData.accessCacheFile(this.imageData));
                     image = ImageIO.read(ThreadDownloadImageData.accessCacheFile(this.imageData));
                  } else {
                     image = TextureUtil.readBufferedImage(conn.getInputStream());
                  }

                  if (ThreadDownloadImageData.accessImageBuffer(this.imageData) != null) {
                     image = ThreadDownloadImageData.accessImageBuffer(this.imageData).parseUserSkin(image);
                  }

                  this.imageData.setBufferedImage(image);
                  return;
               }

               if (conn.getErrorStream() != null) {
                  Config.readAll(conn.getErrorStream());
               }

               return;
            } catch (Exception e) {
               ThreadDownloadImageData.accessLogger().error("Couldn't download http texture: " + e.getClass().getName() + ": " + e.getMessage());
            } finally {
               if (conn != null) {
                  conn.disconnect();
               }

               ThreadDownloadImageData.callLoadingFinished(this.imageData);
            }
         }
      }
   }
}
