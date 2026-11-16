package client.util;

import client.Client;
import client.render.TexturesMapIconsManager;
import client.render.WorldRenderer;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public class MapManager {
   public final DimensionStringEntry dimensionStringEntry;
   public final DynamicTexture dynamicTexture;
   public final ResourceLocation resourceLocation;
   public final int[] countArray;
   public final TexturesMapIconsManager texturesMapIconsManager;

   public static void applyMapManager(MapManager mapManager) {
      mapManager.updateState();
   }

   public static ResourceLocation resolveResourceLocation(MapManager mapManager) {
      return mapManager.resourceLocation;
   }

   public static void applyMapManager(MapManager mapManager, boolean flag) {
      mapManager.setEnabled(flag);
   }

   private void setEnabled(boolean enabled) {
      if (!Client.getClient().getModuleManager().antiStrike.canExecute8()) {
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         float factor = 0.0F;
         TexturesMapIconsManager.resolveTextureManagerByTexturesMapIcons(this.texturesMapIconsManager).applyResourceLocation3(this.resourceLocation);
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(1, 771, 0, 1);
         GlStateManager.applyState10();
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
         worldRenderer.getWorldRenderer((double)((float)0 + factor), (double)((float)128 - factor), -0.01F).getWorldRenderer6(0.0, 1.0).updateState();
         worldRenderer.getWorldRenderer((double)((float)128 - factor), (double)((float)128 - factor), -0.01F).getWorldRenderer6(1.0, 1.0).updateState();
         worldRenderer.getWorldRenderer((double)((float)128 - factor), (double)((float)0 + factor), -0.01F).getWorldRenderer6(1.0, 0.0).updateState();
         worldRenderer.getWorldRenderer((double)((float)0 + factor), (double)((float)0 + factor), -0.01F).getWorldRenderer6(0.0, 0.0).updateState();
         tessellator.updateState();
         GlStateManager.applyState14();
         GlStateManager.enableBlend();
         TexturesMapIconsManager.resolveTextureManagerByTexturesMapIcons(this.texturesMapIconsManager)
            .applyResourceLocation3(TexturesMapIconsManager.getResourceLocation());
         int count = 0;

         for (ByteEntry byteEntry : (Iterable<ByteEntry>)(Iterable<?>)this.dimensionStringEntry.map.values()) {
            if (!enabled || byteEntry.getByteFlag() == 1) {
               GlStateManager.pushMatrix();
               GlStateManager.translate((float)0 + (float)byteEntry.getByteFlag4() / 2.0F + 64.0F, (float)0 + (float)byteEntry.getByteFlag3() / 2.0F + 64.0F, -0.02F);
               GlStateManager.rotate((float)(byteEntry.getByteFlag2() * 360) / 16.0F, 0.0F, 0.0F, 1.0F);
               GlStateManager.translate3f3(4.0F, 4.0F, 3.0F);
               GlStateManager.translate(-0.125F, 0.125F, 0.0F);
               byte byteFlag = byteEntry.getByteFlag();
               float factor2 = (float)(byteFlag % 4 + 0) / 4.0F;
               float factor3 = (float)(byteFlag / 4 + 0) / 4.0F;
               float factor4 = (float)(byteFlag % 4 + 1) / 4.0F;
               float factor5 = (float)(byteFlag / 4 + 1) / 4.0F;
               worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
               worldRenderer.getWorldRenderer(-1.0, 1.0, (double)((float)count * -0.001F)).getWorldRenderer6((double)factor2, (double)factor3).updateState();
               worldRenderer.getWorldRenderer(1.0, 1.0, (double)((float)count * -0.001F)).getWorldRenderer6((double)factor4, (double)factor3).updateState();
               worldRenderer.getWorldRenderer(1.0, -1.0, (double)((float)count * -0.001F)).getWorldRenderer6((double)factor4, (double)factor5).updateState();
               worldRenderer.getWorldRenderer(-1.0, -1.0, (double)((float)count * -0.001F)).getWorldRenderer6((double)factor2, (double)factor5).updateState();
               tessellator.updateState();
               GlStateManager.popMatrix();
               count++;
            }
         }

         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, 0.0F, -0.04F);
         GlStateManager.translate3f3(1.0F, 1.0F, 1.0F);
         GlStateManager.popMatrix();
      }
   }

   private void updateState() {
      for (int index = 0; index < 16384; index++) {
         int count = this.dimensionStringEntry.byteFlagArray[index] & 255;
         if (count / 4 == 0) {
            this.countArray[index] = (index + index / 128 & 1) * 8 + 16 << 24;
         } else {
            this.countArray[index] = MapColor.mapColorArray[count / 4].computeCount(count & 3);
         }
      }

      this.dynamicTexture.updateState2();
   }

   private MapManager(TexturesMapIconsManager texturesMapIconsManager2, DimensionStringEntry dimensionStringEntry2) {
      this.texturesMapIconsManager = texturesMapIconsManager2;
      this.dimensionStringEntry = dimensionStringEntry2;
      this.dynamicTexture = new DynamicTexture(128, 128);
      this.countArray = this.dynamicTexture.getCountArray();
      this.resourceLocation = TexturesMapIconsManager.resolveTextureManagerByTexturesMapIcons(texturesMapIconsManager2)
         .resolveResourceLocationByText("map/" + dimensionStringEntry2.labelText, this.dynamicTexture);

      for (int index = 0; index < this.countArray.length; index++) {
         this.countArray[index] = 0;
      }
   }

   public MapManager(TexturesMapIconsManager texturesMapIconsManager2, DimensionStringEntry dimensionStringEntry2, GeometryCollector geometryCollector) {
      this(texturesMapIconsManager2, dimensionStringEntry2);
   }
}
