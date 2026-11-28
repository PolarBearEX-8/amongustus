package net.minecraft.client.renderer;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomItems;
import net.optifine.config.Config;

public class ItemModelMesher {
   private final Map simpleShapes = Maps.newHashMap();
   private final Map simpleShapesCache = Maps.newHashMap();
   private final Map shapers = Maps.newHashMap();
   private final ModelManager modelManager;

   public void register(Item item, ItemMeshDefinition itemMeshDefinition) {
      this.shapers.put(item, itemMeshDefinition);
   }

   public TextureAtlasSprite getParticleIcon(Item item, int n) {
      return this.getItemModel(new ItemStack(item, 1, n)).getParticleTexture();
   }

   public IBakedModel getItemModel(Item item, int n) {
      return (IBakedModel)this.simpleShapesCache.get(this.getIndex(item, n));
   }

   public void rebuildCache() {
      this.simpleShapesCache.clear();

      for (Entry entry : (Iterable<Entry>)(this.simpleShapes.entrySet())) {
         this.simpleShapesCache.put(entry.getKey(), this.modelManager.getModel((ModelResourceLocation)entry.getValue()));
      }
   }

   public TextureAtlasSprite getParticleIcon(Item item) {
      return this.getParticleIcon(item, 0);
   }

   public int getMetadata(ItemStack stack) {
      return stack.isItemStackDamageable() ? 0 : stack.getMetadata();
   }

   public IBakedModel getItemModel(ItemStack stack) {
      Item item = stack.getItem();
      IBakedModel itemModel = this.getItemModel(item, this.getMetadata(stack));
      if (itemModel == null) {
         ItemMeshDefinition itemMeshDefinition = (ItemMeshDefinition)this.shapers.get(item);
         if (itemMeshDefinition != null) {
            itemModel = this.modelManager.getModel(itemMeshDefinition.getModelLocation(stack));
         }
      }

      if (itemModel == null) {
         itemModel = this.modelManager.getMissingModel();
      }

      if (Config.isCustomItems()) {
         itemModel = CustomItems.getCustomItemModel(stack, itemModel, (ResourceLocation)null, true);
      }

      return itemModel;
   }

   public ModelManager getModelManager() {
      return this.modelManager;
   }

   public void register(Item item, int n, ModelResourceLocation modelResourceLocation) {
      this.simpleShapes.put(this.getIndex(item, n), modelResourceLocation);
      this.simpleShapesCache.put(this.getIndex(item, n), this.modelManager.getModel(modelResourceLocation));
   }

   private int getIndex(Item item, int n) {
      return Item.getIdFromItem(item) << 16 | n;
   }

   public ItemModelMesher(ModelManager modelManager2) {
      this.modelManager = modelManager2;
   }
}
