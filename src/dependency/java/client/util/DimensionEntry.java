package client.util;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class DimensionEntry {
   public Dimension dimension = null;
   public boolean enabled = false;
   public FovAccumulator[] fovAccumulatorArray = new FovAccumulator[0];
   public ResourceLocation resourceLocation2 = null;
   public BufferedImage bufferedImage = null;
   public DynamicTexture dynamicTexture = null;
   public ResourceLocation resourceLocation = new ResourceLocation("textures/blocks/wool_colored_red.png");
   public static final int count3 = 0;
   public static final int count5 = 1;
   public static final int count2 = 2;
   public static final int count7 = 3;
   public static final int count4 = 4;
   public static final int count6 = 5;
   public static final int count = 6;

   public ResourceLocation getResourceLocation2() {
      return this.resourceLocation2;
   }

   public static BoxesManager resolveBoxesManager(FloatEntryRegistry floatEntryRegistry, int count8) {
      switch (count8) {
         case 0:
            return floatEntryRegistry.boxesManager7;
         case 1:
            return floatEntryRegistry.boxesManager3;
         case 2:
            return floatEntryRegistry.boxesManager;
         case 3:
            return floatEntryRegistry.boxesManager2;
         case 4:
            return floatEntryRegistry.boxesManager6;
         case 5:
            return floatEntryRegistry.boxesManager5;
         default:
            return null;
      }
   }

   public DynamicTexture getDynamicTextureAsDynamicTexture() {
      return this.dynamicTexture;
   }

   public void setResourceLocation2(ResourceLocation resourceLocation3) {
      this.resourceLocation2 = resourceLocation3;
   }

   public BufferedImage getBufferedImage() {
      return this.bufferedImage;
   }

   public void setBufferedImage(BufferedImage bufferedImage2) {
      this.bufferedImage = bufferedImage2;
   }

   public void applyFloatEntryRegistryByFloatEntryRegist(FloatEntryRegistry floatEntryRegistry, AbstractClientPlayer player, float factor, float factor2) {
      TextureManager textureManagerAsTextureManager = BuildManager.getTextureManagerAsTextureManager();
      if (this.enabled) {
         textureManagerAsTextureManager.applyResourceLocation3(player.getResourceLocation2());
      } else if (this.resourceLocation2 != null) {
         if (this.dynamicTexture == null && this.bufferedImage != null) {
            this.dynamicTexture = new DynamicTexture(this.bufferedImage);
            Minecraft.getMinecraft().getTextureManager().matchesResourceLocation(this.resourceLocation2, this.dynamicTexture);
         }

         textureManagerAsTextureManager.applyResourceLocation3(this.resourceLocation2);
      } else {
         textureManagerAsTextureManager.applyResourceLocation3(this.resourceLocation);
      }

      for (int index = 0; index < this.fovAccumulatorArray.length; index++) {
         FovAccumulator fovAccumulator = this.fovAccumulatorArray[index];
         GlStateManager.pushMatrix();
         if (player.isSprinting()) {
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
         }

         fovAccumulator.applyFloatEntryRegistry(floatEntryRegistry, factor);
         GlStateManager.popMatrix();
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public DimensionEntry(Dimension dimension2, boolean flag, FovAccumulator[] fovAccumulatorArray2) {
      this.dimension = dimension2;
      this.enabled = flag;
      this.fovAccumulatorArray = fovAccumulatorArray2;
   }
}
