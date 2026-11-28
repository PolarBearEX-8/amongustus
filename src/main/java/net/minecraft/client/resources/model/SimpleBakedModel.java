package net.minecraft.client.resources.model;

import com.google.common.collect.Lists;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BreakingFour;
import net.minecraft.client.renderer.block.model.ModelBlock;
import java.util.List;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

public class SimpleBakedModel implements IBakedModel {
   public final List generalQuads;
   public final List faceQuads;
   public final boolean ambientOcclusion;
   public final boolean gui3d;
   public final TextureAtlasSprite texture;
   public final ItemCameraTransforms cameraTransforms;

   @Override
   public List getGeneralQuads() {
      return this.generalQuads;
   }

   @Override
   public List getFaceQuads(EnumFacing facing) {
      return (List)this.faceQuads.get(facing.ordinal());
   }

   @Override
   public ItemCameraTransforms getItemCameraTransforms() {
      return this.cameraTransforms;
   }

   @Override
   public boolean isGui3d() {
      return this.gui3d;
   }

   @Override
   public boolean isBuiltInRenderer() {
      return false;
   }

   @Override
   public boolean isAmbientOcclusion() {
      return this.ambientOcclusion;
   }

   public SimpleBakedModel(List list, List list2, boolean flag, boolean flag2, TextureAtlasSprite textureAtlasSprite, ItemCameraTransforms itemCameraTransforms) {
      this.generalQuads = list;
      this.faceQuads = list2;
      this.ambientOcclusion = flag;
      this.gui3d = flag2;
      this.texture = textureAtlasSprite;
      this.cameraTransforms = itemCameraTransforms;
   }

   @Override
   public TextureAtlasSprite getParticleTexture() {
      return this.texture;
   }


   public static class Builder {
      private final List builderGeneralQuads = Lists.newArrayList();
      private final List builderFaceQuads = Lists.newArrayListWithCapacity(6);
      private final boolean builderAmbientOcclusion;
      private TextureAtlasSprite builderTexture;
      private final boolean builderGui3d;
      private final ItemCameraTransforms builderCameraTransforms;

      public Builder(ModelBlock modelBlock) {
         this(modelBlock.isAmbientOcclusion(), modelBlock.isGui3d(), modelBlock.getAllTransforms());
      }

      public Builder(IBakedModel bakedModel, TextureAtlasSprite textureAtlasSprite) {
         this(bakedModel.isAmbientOcclusion(), bakedModel.isGui3d(), bakedModel.getItemCameraTransforms());
         this.builderTexture = bakedModel.getParticleTexture();

         for (EnumFacing facing : EnumFacing.getValues()) {
            this.addFaceBreakingFours(bakedModel, textureAtlasSprite, facing);
         }

         this.addGeneralBreakingFours(bakedModel, textureAtlasSprite);
      }

      public Builder addFaceQuad(EnumFacing facing, BakedQuad bakedQuad) {
         ((List)this.builderFaceQuads.get(facing.ordinal())).add(bakedQuad);
         return this;
      }

      public Builder addGeneralQuad(BakedQuad bakedQuad) {
         this.builderGeneralQuads.add(bakedQuad);
         return this;
      }

      private Builder(boolean flag, boolean flag2, ItemCameraTransforms itemCameraTransforms) {
         for (EnumFacing facing : EnumFacing.getValues()) {
            this.builderFaceQuads.add(Lists.newArrayList());
         }

         this.builderAmbientOcclusion = flag;
         this.builderGui3d = flag2;
         this.builderCameraTransforms = itemCameraTransforms;
      }

      public IBakedModel makeBakedModel() {
         if (this.builderTexture == null) {
            throw new RuntimeException("Missing particle!");
         } else {
            return new SimpleBakedModel(
               this.builderGeneralQuads, this.builderFaceQuads, this.builderAmbientOcclusion, this.builderGui3d, this.builderTexture, this.builderCameraTransforms
            );
         }
      }

      private void addFaceBreakingFours(IBakedModel bakedModel, TextureAtlasSprite textureAtlasSprite, EnumFacing facing) {
         for (BakedQuad bakedQuad : (Iterable<BakedQuad>)(bakedModel.getFaceQuads(facing))) {
            this.addFaceQuad(facing, new BreakingFour(bakedQuad, textureAtlasSprite));
         }
      }

      public Builder setTexture(TextureAtlasSprite textureAtlasSprite) {
         this.builderTexture = textureAtlasSprite;
         return this;
      }

      private void addGeneralBreakingFours(IBakedModel bakedModel, TextureAtlasSprite textureAtlasSprite) {
         for (BakedQuad bakedQuad : (Iterable<BakedQuad>)(bakedModel.getGeneralQuads())) {
            this.addGeneralQuad(new BreakingFour(bakedQuad, textureAtlasSprite));
         }
      }
   }
}
