package net.optifine.entity.model;

import net.minecraft.util.ResourceLocation;

public class CustomEntityRenderer {
   private String name = null;
   private String basePath = null;
   private ResourceLocation textureLocation = null;
   private CustomModelRenderer[] customModelRenderers = null;
   private float shadowSize = 0.0F;

   public String getBasePath() {
      return this.basePath;
   }

   public String getName() {
      return this.name;
   }

   public CustomModelRenderer[] getCustomModelRenderers() {
      return this.customModelRenderers;
   }

   public ResourceLocation getTextureLocation() {
      return this.textureLocation;
   }

   public float getShadowSize() {
      return this.shadowSize;
   }

   public CustomEntityRenderer(String str, String str2, ResourceLocation location, CustomModelRenderer[] customModelRenderers2, float f) {
      this.name = str;
      this.basePath = str2;
      this.textureLocation = location;
      this.customModelRenderers = customModelRenderers2;
      this.shadowSize = f;
   }
}
