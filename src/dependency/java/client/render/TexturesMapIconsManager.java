package client.render;

import client.util.DimensionStringEntry;
import client.util.MapManager;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class TexturesMapIconsManager {
   public static final ResourceLocation resourceLocation;
   public final TextureManager textureManager;
   public final Map map = Maps.newHashMap();

   public void renderElement() {
      for (MapManager mapManager : (Iterable<MapManager>) this.map.values()) {
         this.textureManager.applyResourceLocation2(MapManager.resolveResourceLocation(mapManager));
      }

      this.map.clear();
   }

   public static TextureManager resolveTextureManagerByTexturesMapIcons(TexturesMapIconsManager texturesMapIconsManager) {
      return texturesMapIconsManager.textureManager;
   }

   public void applyDimensionStringEntry(DimensionStringEntry dimensionStringEntry, boolean flag) {
      MapManager.applyMapManager(this.resolveMapManager(dimensionStringEntry), flag);
   }

   private MapManager resolveMapManager(DimensionStringEntry dimensionStringEntry) {
      MapManager mapManager = (MapManager)this.map.get(dimensionStringEntry.labelText);
      if (mapManager == null) {
         mapManager = new MapManager(this, dimensionStringEntry, null);
         this.map.put(dimensionStringEntry.labelText, mapManager);
      }

      return mapManager;
   }

   public static ResourceLocation getResourceLocation() {
      return resourceLocation;
   }

   public void applyDimensionStringEntry(DimensionStringEntry dimensionStringEntry) {
      MapManager.applyMapManager(this.resolveMapManager(dimensionStringEntry));
   }

   public TexturesMapIconsManager(TextureManager textureManager2) {
      this.textureManager = textureManager2;
   }

   static {
      String text = "textures/map/map_icons.png";
      resourceLocation = new ResourceLocation(text);
   }
}
