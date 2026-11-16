package client.util;

import client.render.AllocateTextureMapManager;
import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SmoothDispatcher extends ListenerAdapter26 {
   public static final Logger logger = LogManager.getLogger();
   public final List list;
   public ResourceLocation resourceLocation;

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) throws IOException {
      this.updateState3();
      BufferedImage bufferedImage = null;

      try {
         for (Object textObj : this.list) { String text = (String)textObj;
            if (text != null) {
               BufferedImage bufferedImage2 = SpecialLoggerEntry.resolveBufferedImage(
                  iResourceManager.resolveIResourceByResourceLocation(new ResourceLocation(text)).getInputStream()
               );
               if (bufferedImage == null) {
                  bufferedImage = new BufferedImage(bufferedImage2.getWidth(), bufferedImage2.getHeight(), 2);
               }

               bufferedImage.getGraphics().drawImage(bufferedImage2, 0, 0, (ImageObserver)null);
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

   public SmoothDispatcher(String[] textArray) {
      this.list = Lists.newArrayList(textArray);
      if (textArray.length > 0 && textArray[0] != null) {
         this.resourceLocation = new ResourceLocation(textArray[0]);
      }
   }
}
