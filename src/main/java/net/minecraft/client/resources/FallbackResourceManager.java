package net.minecraft.client.resources;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import com.google.common.collect.Lists;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FallbackResourceManager implements IResourceManager {
   private static final Logger logger = LogManager.getLogger();
   public final List resourcePacks = Lists.newArrayList();
   private final IMetadataSerializer frmMetadataSerializer;

   public InputStream getInputStream(ResourceLocation location, IResourcePack resourcePack) throws IOException {
      InputStream inputStream = resourcePack.getInputStream(location);
      return (InputStream)(logger.isDebugEnabled() ? new FallbackResourceManager.InputStreamLeakedResourceLogger(inputStream, location, resourcePack.getPackName()) : inputStream);
   }

   public void addResourcePack(IResourcePack resourcePack) {
      this.resourcePacks.add(resourcePack);
   }

   public static ResourceLocation getLocationMcmeta(ResourceLocation location) {
      return new ResourceLocation(location.getResourceDomain(), location.getResourcePath() + ".mcmeta");
   }

   public FallbackResourceManager(IMetadataSerializer metadataSerializer) {
      this.frmMetadataSerializer = metadataSerializer;
   }

   @Override
   public Set getResourceDomains() {
      return null;
   }

   public static Logger accessLogger() {
      return logger;
   }

   @Override
   public List getAllResources(ResourceLocation location) throws IOException {
      ArrayList list = Lists.newArrayList();
      ResourceLocation locationMcmeta = getLocationMcmeta(location);

      for (IResourcePack resourcePack : (Iterable<IResourcePack>)(this.resourcePacks)) {
         if (resourcePack.resourceExists(location)) {
            InputStream in = resourcePack.resourceExists(locationMcmeta) ? this.getInputStream(locationMcmeta, resourcePack) : null;
            list.add(new SimpleResource(resourcePack.getPackName(), location, this.getInputStream(location, resourcePack), in, this.frmMetadataSerializer));
         }
      }

      if (list.isEmpty()) {
         throw new FileNotFoundException(location.toString());
      } else {
         return list;
      }
   }

   @Override
   public IResource getResource(ResourceLocation location) throws IOException {
      IResourcePack resourcePack = null;
      ResourceLocation locationMcmeta = getLocationMcmeta(location);

      for (int i = this.resourcePacks.size() - 1; i >= 0; i--) {
         IResourcePack resourcePack2 = (IResourcePack)this.resourcePacks.get(i);
         if (resourcePack == null && resourcePack2.resourceExists(locationMcmeta)) {
            resourcePack = resourcePack2;
         }

         if (resourcePack2.resourceExists(location)) {
            InputStream in = null;
            if (resourcePack != null) {
               in = this.getInputStream(locationMcmeta, resourcePack);
            }

            return new SimpleResource(resourcePack2.getPackName(), location, this.getInputStream(location, resourcePack2), in, this.frmMetadataSerializer);
         }
      }

      throw new FileNotFoundException(location.toString());
   }


   public static class InputStreamLeakedResourceLogger extends InputStream {
      private final InputStream inputStream;
      private final String message;
      private boolean isClosed = false;

      public InputStreamLeakedResourceLogger(InputStream in, ResourceLocation location, String str) {
         this.inputStream = in;
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         new Exception().printStackTrace(new PrintStream(byteArrayOutputStream));
         this.message = "Leaked resource: '" + location + "' loaded from pack: '" + str + "'\n" + byteArrayOutputStream;
      }

      @Override
      public void finalize() throws Throwable {
         if (!this.isClosed) {
            FallbackResourceManager.accessLogger().warn(this.message);
         }

         super.finalize();
      }

      @Override
      public int read() throws IOException {
         return this.inputStream.read();
      }

      @Override
      public void close() throws IOException {
         this.inputStream.close();
         this.isClosed = true;
      }
   }
}
