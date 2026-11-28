package net.minecraft.client.resources.model;

import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.IRegistry;

public class ModelManager implements IResourceManagerReloadListener {
   private IRegistry modelRegistry;
   private final TextureMap texMap;
   private final BlockModelShapes modelProvider;
   private IBakedModel defaultModel;

   @Override
   public void onResourceManagerReload(IResourceManager resourceManager) {
      this.modelRegistry = new ModelBakery(resourceManager, this.texMap, this.modelProvider).setupModelRegistry();
      this.defaultModel = (IBakedModel)this.modelRegistry.getObject(ModelBakery.MODEL_MISSING);
      this.modelProvider.reloadModels();
   }

   public BlockModelShapes getBlockModelShapes() {
      return this.modelProvider;
   }

   public IBakedModel getModel(ModelResourceLocation modelResourceLocation) {
      if (modelResourceLocation == null) {
         return this.defaultModel;
      } else {
         IBakedModel object = (IBakedModel)this.modelRegistry.getObject(modelResourceLocation);
         return object == null ? this.defaultModel : object;
      }
   }

   public IBakedModel getMissingModel() {
      return this.defaultModel;
   }

   public TextureMap getTextureMap() {
      return this.texMap;
   }

   public ModelManager(TextureMap textureMap) {
      this.texMap = textureMap;
      this.modelProvider = new BlockModelShapes(this);
   }
}
