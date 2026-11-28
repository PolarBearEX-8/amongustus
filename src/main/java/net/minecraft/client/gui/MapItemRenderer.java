package net.minecraft.client.gui;

import net.augustus.Augustus;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.Vec4b;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;

public class MapItemRenderer {
   private static final ResourceLocation mapIcons;
   private final TextureManager textureManager;
   private final Map loadedMaps = Maps.newHashMap();

   static {
      String str = "textures/map/map_icons.png";
      mapIcons = new ResourceLocation(str);
   }

   public MapItemRenderer(TextureManager textureManager2) {
      this.textureManager = textureManager2;
   }

   public void updateMapTexture(MapData mapData) {
      MapItemRenderer.Instance.invokeUpdateMapTexture(this.getMapRendererInstance(mapData));
   }

   public void clearLoadedMaps() {
      for (MapItemRenderer.Instance instance : (Iterable<MapItemRenderer.Instance>)(this.loadedMaps.values())) {
         this.textureManager.deleteTexture(MapItemRenderer.Instance.accessLocation(instance));
      }

      this.loadedMaps.clear();
   }

   public static TextureManager accessTextureManager(MapItemRenderer mapItemRenderer) {
      return mapItemRenderer.textureManager;
   }

   private MapItemRenderer.Instance getMapRendererInstance(MapData mapData) {
      MapItemRenderer.Instance instance = (MapItemRenderer.Instance)this.loadedMaps.get(mapData.mapName);
      if (instance == null) {
         instance = new MapItemRenderer.Instance(this, mapData, null);
         this.loadedMaps.put(mapData.mapName, instance);
      }

      return instance;
   }

   public void renderMap(MapData mapData, boolean flag) {
      MapItemRenderer.Instance.invokeRender(this.getMapRendererInstance(mapData), flag);
   }

   public static ResourceLocation accessMapIcons() {
      return mapIcons;
   }



   public static class InnerEntry {
   }

   public static class Instance {
      private final MapData mapData;
      private final DynamicTexture mapTexture;
      private final ResourceLocation location;
      private final int[] mapTextureData;
      public final MapItemRenderer mapItemRenderer;

      private Instance(MapItemRenderer mapItemRenderer2, MapData mapData2) {
         this.mapItemRenderer = mapItemRenderer2;
         this.mapData = mapData2;
         this.mapTexture = new DynamicTexture(128, 128);
         this.mapTextureData = this.mapTexture.getTextureData();
         this.location = MapItemRenderer.accessTextureManager(mapItemRenderer2).getDynamicTextureLocation("map/" + mapData2.mapName, this.mapTexture);

         for (int i = 0; i < this.mapTextureData.length; i++) {
            this.mapTextureData[i] = 0;
         }
      }

      private void render(boolean flag) {
         if (!Augustus.getInstance().getModuleManager().antiStrike.shouldHideMaps()) {
            byte b = 0;
            byte b2 = 0;
            Tessellator instance = Tessellator.getInstance();
            WorldRenderer worldRenderer = instance.getWorldRenderer();
            float f = 0.0F;
            MapItemRenderer.accessTextureManager(this.mapItemRenderer).bindTexture(this.location);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(1, 771, 0, 1);
            GlStateManager.disableAlpha();
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
            worldRenderer.pos((double)((float)(b + 0) + f), (double)((float)(b2 + 128) - f), -0.01F).tex(0.0, 1.0).endVertex();
            worldRenderer.pos((double)((float)(b + 128) - f), (double)((float)(b2 + 128) - f), -0.01F).tex(1.0, 1.0).endVertex();
            worldRenderer.pos((double)((float)(b + 128) - f), (double)((float)(b2 + 0) + f), -0.01F).tex(1.0, 0.0).endVertex();
            worldRenderer.pos((double)((float)(b + 0) + f), (double)((float)(b2 + 0) + f), -0.01F).tex(0.0, 0.0).endVertex();
            instance.draw();
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
            MapItemRenderer.accessTextureManager(this.mapItemRenderer).bindTexture(MapItemRenderer.accessMapIcons());
            int n = 0;

            for (Vec4b vec4b : (Iterable<Vec4b>)(this.mapData.mapDecorations.values())) {
               if (!flag || vec4b.getType() == 1) {
                  GlStateManager.pushMatrix();
                  GlStateManager.translate(
                     (float)b + (float)vec4b.getX() / 2.0F + 64.0F, (float)b2 + (float)vec4b.getY() / 2.0F + 64.0F, -0.02F
                  );
                  GlStateManager.rotate((float)(vec4b.getRotation() * 360) / 16.0F, 0.0F, 0.0F, 1.0F);
                  GlStateManager.scale(4.0F, 4.0F, 3.0F);
                  GlStateManager.translate(-0.125F, 0.125F, 0.0F);
                  byte type = vec4b.getType();
                  float f2 = (float)(type % 4 + 0) / 4.0F;
                  float f3 = (float)(type / 4 + 0) / 4.0F;
                  float f4 = (float)(type % 4 + 1) / 4.0F;
                  float f5 = (float)(type / 4 + 1) / 4.0F;
                  worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
                  float f6 = -0.001F;
                  worldRenderer.pos(-1.0, 1.0, (double)((float)n * -0.001F)).tex((double)f2, (double)f3).endVertex();
                  worldRenderer.pos(1.0, 1.0, (double)((float)n * -0.001F)).tex((double)f4, (double)f3).endVertex();
                  worldRenderer.pos(1.0, -1.0, (double)((float)n * -0.001F)).tex((double)f4, (double)f5).endVertex();
                  worldRenderer.pos(-1.0, -1.0, (double)((float)n * -0.001F)).tex((double)f2, (double)f5).endVertex();
                  instance.draw();
                  GlStateManager.popMatrix();
                  n++;
               }
            }

            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 0.0F, -0.04F);
            GlStateManager.scale(1.0F, 1.0F, 1.0F);
            GlStateManager.popMatrix();
         }
      }

      public static void invokeRender(Instance instance, boolean flag) {
         instance.render(flag);
      }

      public Instance(MapItemRenderer mapItemRenderer2, MapData mapData2, MapItemRenderer.InnerEntry obj) {
         this(mapItemRenderer2, mapData2);
      }

      public static void invokeUpdateMapTexture(Instance instance) {
         instance.updateMapTexture();
      }

      public static ResourceLocation accessLocation(Instance instance) {
         return instance.location;
      }

      private void updateMapTexture() {
         for (int i = 0; i < 16384; i++) {
            int n = this.mapData.colors[i] & 255;
            if (n / 4 == 0) {
               this.mapTextureData[i] = (i + i / 128 & 1) * 8 + 16 << 24;
            } else {
               this.mapTextureData[i] = MapColor.mapColorArray[n / 4].getMapColor(n & 3);
            }
         }

         this.mapTexture.updateDynamicTexture();
      }


   }
}
