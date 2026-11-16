package client.render;

import client.util.SmoothDispatcher;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.item.DiamondHammer;
import net.minecraft.util.ResourceLocation;

public class EffectPipeline extends InternalRendererLivingEntityAdapter {
   public static final Map map = Maps.newHashMap();
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/horse/horse_white.png");
   public static final ResourceLocation resourceLocation7 = new ResourceLocation("textures/entity/horse/mule.png");
   public static final ResourceLocation resourceLocation6 = new ResourceLocation("textures/entity/horse/donkey.png");
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/entity/horse/horse_zombie.png");
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/entity/horse/horse_skeleton.png");

   private ResourceLocation resolveResourceLocationByEntityHorse(EntityHorse entityHorse) {
      String textAsText = entityHorse.getTextAsText();
      if (!entityHorse.isEnabled28()) {
         return null;
      } else {
         ResourceLocation location = (ResourceLocation)map.get(textAsText);
         if (location == null) {
            location = new ResourceLocation(textAsText);
            Minecraft.getMinecraft().getTextureManager().matchesResourceLocation(location, new SmoothDispatcher(entityHorse.getTextArray()));
            map.put(textAsText, location);
         }

         return location;
      }
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      this.applyEntityHorse((EntityHorse)livingEntity, factor);
   }

   public ResourceLocation resolveResourceLocation3(EntityHorse entityHorse) {
      if (!entityHorse.isActive35()) {
         switch (entityHorse.getCount12()) {
            case 0:
            default:
               return resourceLocation3;
            case 1:
               return resourceLocation6;
            case 2:
               return resourceLocation7;
            case 3:
               return resourceLocation4;
            case 4:
               return resourceLocation5;
         }
      } else {
         return this.resolveResourceLocationByEntityHorse(entityHorse);
      }
   }

   public void applyEntityHorse(EntityHorse entityHorse, float factor) {
      float factor2 = 1.0F;
      int count = entityHorse.getCount12();
      if (count == 1) {
         factor2 *= 0.87F;
      } else if (count == 2) {
         factor2 *= 0.92F;
      }

      GlStateManager.translate3f3(factor2, factor2, factor2);
      super.applyEntityLivingBase(entityHorse, factor);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation3((EntityHorse)entity);
   }

   public EffectPipeline(RenderManager renderManager, DiamondHammer diamondHammer, float factor) {
      super(renderManager, diamondHammer, factor);
   }
}
