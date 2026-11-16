package client.util;

import client.render.AllocateTextureMapManager;
import client.render.EmissiveTexturesManager;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultLoggerEntry extends ListenerAdapter26 {
   public static final Logger logger = LogManager.getLogger();
   public final ResourceLocation resourceLocation2;
   public ResourceLocation resourceLocation;
   public boolean enabled5;
   public final boolean enabled6;

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) throws IOException {
      this.updateState3();
      InputStream inputStream = null;

      try {
         IResource iResource = this.enabled6 ? iResourceManager.resolveIResourceByResourceLocation(this.resourceLocation2) : null;
         inputStream = this.enabled6
            ? iResource.getInputStream()
            : this.getClass().getResourceAsStream("/assets/" + this.resourceLocation2.getText() + "/" + this.resourceLocation2.getText1());
         BufferedImage bufferedImage = SpecialLoggerEntry.resolveBufferedImage(inputStream);
         if (bufferedImage != null) {
            boolean flag = false;
            boolean flag2 = false;
            if (iResource != null && iResource.isActive()) {
               try {
                  SupplementalListEntry supplementalListEntry = (SupplementalListEntry)iResource.resolveIMetadataSection("texture");
                  if (supplementalListEntry != null) {
                     flag = supplementalListEntry.isEnabled();
                     flag2 = supplementalListEntry.isEnabled2();
                  }
               } catch (RuntimeException exception) {
                  logger.warn("Failed reading metadata of: " + this.resourceLocation2, exception);
               }
            }

            if (BuildManager.canExecute()) {
               AllocateTextureMapManager.computeCountByBufferedImage(
                  this.getCount(), bufferedImage, flag, flag2, iResourceManager, this.resourceLocation2, this.getDistanceTransformer()
               );
            } else {
               SpecialLoggerEntry.computeCount(this.getCount(), bufferedImage, flag, flag2);
            }

            if (EmissiveTexturesManager.isEnabled2()) {
               EmissiveTexturesManager.applyResourceLocationByResourceLocation(this.resourceLocation2, this);
            }

            return;
         }

         System.err.println("Failed to load texture: " + this.resourceLocation2);
         if (inputStream != null) {
            inputStream.close();
         }
      } finally {
         if (inputStream != null) {
            inputStream.close();
         }
      }
   }

   public DefaultLoggerEntry(ResourceLocation location, boolean flag) {
      this.resourceLocation2 = location;
      this.enabled6 = flag;
   }
}
