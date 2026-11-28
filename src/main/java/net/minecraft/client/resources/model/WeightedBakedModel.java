package net.minecraft.client.resources.model;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandom;

public class WeightedBakedModel implements IBakedModel {
   private final int totalWeight;
   private final List models;
   private final IBakedModel baseModel;

   @Override
   public boolean isGui3d() {
      return this.baseModel.isGui3d();
   }

   @Override
   public boolean isBuiltInRenderer() {
      return this.baseModel.isBuiltInRenderer();
   }

   @Override
   public List getGeneralQuads() {
      return this.baseModel.getGeneralQuads();
   }

   @Override
   public TextureAtlasSprite getParticleTexture() {
      return this.baseModel.getParticleTexture();
   }

   @Override
   public ItemCameraTransforms getItemCameraTransforms() {
      return this.baseModel.getItemCameraTransforms();
   }

   @Override
   public boolean isAmbientOcclusion() {
      return this.baseModel.isAmbientOcclusion();
   }

   public IBakedModel getAlternativeModel(long l) {
      return ((WeightedBakedModel.MyWeighedRandomItem)WeightedRandom.getRandomItem(this.models, Math.abs((int)l >> 16) % this.totalWeight)).model;
   }

   public WeightedBakedModel(List list) {
      this.models = list;
      this.totalWeight = WeightedRandom.getTotalWeight(list);
      this.baseModel = ((WeightedBakedModel.MyWeighedRandomItem)list.get(0)).model;
   }

   @Override
   public List getFaceQuads(EnumFacing facing) {
      return this.baseModel.getFaceQuads(facing);
   }


   public static class Builder {
      private List listItems = Lists.newArrayList();

      public Builder add(IBakedModel bakedModel, int n) {
         this.listItems.add(new WeightedBakedModel.MyWeighedRandomItem(bakedModel, n));
         return this;
      }

      public WeightedBakedModel build() {
         Collections.sort(this.listItems);
         return new WeightedBakedModel(this.listItems);
      }

      public IBakedModel first() {
         return ((WeightedBakedModel.MyWeighedRandomItem)this.listItems.get(0)).model;
      }
   }

   public static class MyWeighedRandomItem extends WeightedRandom.Item implements Comparable {
      public final IBakedModel model;

      public MyWeighedRandomItem(IBakedModel bakedModel, int n) {
         super(n);
         this.model = bakedModel;
      }

      public int compareTo(MyWeighedRandomItem other) {
         return ComparisonChain.start().compare(other.itemWeight, this.itemWeight).compare(this.getCountQuads(), other.getCountQuads()).result();
      }

      @Override
      public int compareTo(Object other) {
         return this.compareTo((MyWeighedRandomItem)other);
      }

      @Override
      public String toString() {
         return "MyWeighedRandomItem{weight=" + this.itemWeight + ", model=" + this.model + '}';
      }

      public int getCountQuads() {
         int size = this.model.getGeneralQuads().size();

         for (EnumFacing facing : EnumFacing.getValues()) {
            size += this.model.getFaceQuads(facing).size();
         }

         return size;
      }
   }
}
