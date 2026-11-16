package client.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class AuxiliaryLoggerEntryBridge extends DefaultLoggerEntry {
   public boolean enabled7;
   public BufferedImage bufferedImage;
   public int count2;
   public float factor;
   public int count3;

   public void applyBufferedImage(BufferedImage bufferedImage2) {
      this.bufferedImage = bufferedImage2;
      this.count3 = bufferedImage2.getWidth();
      this.count2 = bufferedImage2.getHeight();
      this.factor = (float)this.count3 / (float)this.count2;
   }

   public int getCount2() {
      return this.count2;
   }

   public void updateState2() {
      this.updateState3();
   }

   public float getFactor() {
      return this.factor;
   }

   public int getCount3() {
      return this.count3;
   }

   public void setFactor(float factor2) {
      this.factor = factor2;
   }

   public BufferedImage getBufferedImage() {
      return this.bufferedImage;
   }

   @Override
   public int getCount() {
      int count = super.getCount();
      if (!this.enabled7 && this.bufferedImage != null) {
         this.enabled7 = true;
         SpecialLoggerEntry.computeCount(count, this.bufferedImage);
      }

      return count;
   }

   public ResourceLocation getResourceLocation3() {
      return this.resourceLocation2;
   }

   @Override
   public void updateState3() {
      super.updateState3();
      this.bufferedImage = null;
   }

   public boolean isEnabled7() {
      return this.enabled7;
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) throws IOException {
   }

   public AuxiliaryLoggerEntryBridge(ResourceLocation location, BufferedImage bufferedImage2) {
      super(location, true);
      if (bufferedImage2 != null) {
         this.applyBufferedImage(bufferedImage2);
      }
   }
}
