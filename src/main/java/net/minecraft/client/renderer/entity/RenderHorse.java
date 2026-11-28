package net.minecraft.client.renderer.entity;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.ResourceLocation;

public class RenderHorse extends RenderLiving {
   private static final Map HORSE_TEXTURE_CACHE = Maps.newHashMap();
   private static final ResourceLocation whiteHorseTextures = new ResourceLocation("textures/entity/horse/horse_white.png");
   private static final ResourceLocation muleTextures = new ResourceLocation("textures/entity/horse/mule.png");
   private static final ResourceLocation donkeyTextures = new ResourceLocation("textures/entity/horse/donkey.png");
   private static final ResourceLocation zombieHorseTextures = new ResourceLocation("textures/entity/horse/horse_zombie.png");
   private static final ResourceLocation skeletonHorseTextures = new ResourceLocation("textures/entity/horse/horse_skeleton.png");

   @Override
   public void preRenderCallback(EntityLivingBase entity, float f) {
      this.preRenderCallback((EntityHorse)entity, f);
   }

   public ResourceLocation getEntityTexture2(EntityHorse entityHorse) {
      if (!entityHorse.usesLayeredTexture()) {
         switch (entityHorse.getHorseType()) {
            case 0:
            default:
               return whiteHorseTextures;
            case 1:
               return donkeyTextures;
            case 2:
               return muleTextures;
            case 3:
               return zombieHorseTextures;
            case 4:
               return skeletonHorseTextures;
         }
      } else {
         return this.getOrCreateLayeredTexture(entityHorse);
      }
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture2((EntityHorse)entity);
   }

   public void preRenderCallback(EntityHorse entityHorse, float f) {
      float f2 = 1.0F;
      int horseType = entityHorse.getHorseType();
      if (horseType == 1) {
         f2 *= 0.87F;
      } else if (horseType == 2) {
         f2 *= 0.92F;
      }

      GlStateManager.scale(f2, f2, f2);
      super.preRenderCallback(entityHorse, f);
   }

   private ResourceLocation getOrCreateLayeredTexture(EntityHorse entityHorse) {
      String horseTexture = entityHorse.getHorseTexture();
      if (!entityHorse.hasUniqueTexture()) {
         return null;
      } else {
         ResourceLocation location = (ResourceLocation)HORSE_TEXTURE_CACHE.get(horseTexture);
         if (location == null) {
            location = new ResourceLocation(horseTexture);
            Minecraft.getMinecraft().getTextureManager().loadTexture(location, new LayeredTexture(entityHorse.getVariantTexturePaths()));
            HORSE_TEXTURE_CACHE.put(horseTexture, location);
         }

         return location;
      }
   }

   public RenderHorse(RenderManager renderManager2, ModelHorse modelHorse, float f) {
      super(renderManager2, modelHorse, f);
   }
}
