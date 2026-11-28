package net.minecraft.client.resources.model;

import java.util.List;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

public class BuiltInModel implements IBakedModel {
   private ItemCameraTransforms cameraTransforms;

   @Override
   public List getFaceQuads(EnumFacing facing) {
      return null;
   }

   @Override
   public ItemCameraTransforms getItemCameraTransforms() {
      return this.cameraTransforms;
   }

   @Override
   public boolean isAmbientOcclusion() {
      return false;
   }

   @Override
   public boolean isBuiltInRenderer() {
      return true;
   }

   @Override
   public TextureAtlasSprite getParticleTexture() {
      return null;
   }

   @Override
   public boolean isGui3d() {
      return true;
   }

   @Override
   public List getGeneralQuads() {
      return null;
   }

   public BuiltInModel(ItemCameraTransforms itemCameraTransforms) {
      this.cameraTransforms = itemCameraTransforms;
   }
}
