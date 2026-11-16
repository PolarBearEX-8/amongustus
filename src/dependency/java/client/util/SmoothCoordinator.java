package client.util;

import client.render.AllocateTextureMapManager;
import client.setting.LocalOptionType;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SmoothCoordinator extends ListenerAdapter26 {
   public static final Logger logger = LogManager.getLogger();
   public final ResourceLocation resourceLocation;
   public final List list;
   public final List list2;

   public SmoothCoordinator(ResourceLocation location, List list3, List list4) {
      this.resourceLocation = location;
      this.list = list3;
      this.list2 = list4;
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) throws IOException {
      this.updateState3();

      BufferedImage bufferedImage;
      try {
         BufferedImage bufferedImage2 = SpecialLoggerEntry.resolveBufferedImage(iResourceManager.resolveIResourceByResourceLocation(this.resourceLocation).getInputStream());
         int type = bufferedImage2.getType();
         if (type == 0) {
            type = 6;
         }

         bufferedImage = new BufferedImage(bufferedImage2.getWidth(), bufferedImage2.getHeight(), type);
         bufferedImage.getGraphics().drawImage(bufferedImage2, 0, 0, (ImageObserver)null);

         for (int index = 0; index < 17 && index < this.list.size() && index < this.list2.size(); index++) {
            String text = (String)this.list.get(index);
            MapColor mapColor = ((LocalOptionType)this.list2.get(index)).getMapColor();
            if (text != null) {
               BufferedImage bufferedImage3 = SpecialLoggerEntry.resolveBufferedImage(
                  iResourceManager.resolveIResourceByResourceLocation(new ResourceLocation(text)).getInputStream()
               );
               if (bufferedImage3.getWidth() == bufferedImage.getWidth() && bufferedImage3.getHeight() == bufferedImage.getHeight() && bufferedImage3.getType() == 6) {
                  for (int index2 = 0; index2 < bufferedImage3.getHeight(); index2++) {
                     for (int index3 = 0; index3 < bufferedImage3.getWidth(); index3++) {
                        int rgb = bufferedImage3.getRGB(index3, index2);
                        if ((rgb & 0xFF000000) != 0) {
                           rgb = (rgb & 0xFF0000) << 8 & 0xFF000000;
                           int count = MathHelper.processAction(bufferedImage2.getRGB(index3, index2), mapColor.indexOffset) & 16777215;
                           bufferedImage3.setRGB(index3, index2, rgb | count);
                        }
                     }
                  }

                  bufferedImage.getGraphics().drawImage(bufferedImage3, 0, 0, (ImageObserver)null);
               }
            }
         }
      } catch (IOException ioException) {
         logger.error("Couldn't load layered image", ioException);
         return;
      }

      if (BuildManager.canExecute()) {
         AllocateTextureMapManager.computeCountByBufferedImage(this.getCount(), bufferedImage, false, false, iResourceManager, this.resourceLocation, this.getDistanceTransformer());
      } else {
         SpecialLoggerEntry.computeCount(this.getCount(), bufferedImage);
      }
   }
}
