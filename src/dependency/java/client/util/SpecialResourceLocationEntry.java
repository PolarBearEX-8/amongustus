package client.util;

import client.render.InternalRendererLivingEntityAdapter;
import client.render.RenderManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityVillagergolem;
import net.minecraft.item.DiamondScythe;
import net.minecraft.item.GoldenGem;
import net.minecraft.util.ResourceLocation;

public class SpecialResourceLocationEntry extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/iron_golem.png");

   public ResourceLocation resolveResourceLocation(EntityVillagergolem entityVillagergolem) {
      return resourceLocation3;
   }

   public void applyEntityVillagergolem(EntityVillagergolem entityVillagergolem, float posX, float posY, float posZ) {
      super.applyEntityLivingBase(entityVillagergolem, posX, posY, posZ);
      if ((double)entityVillagergolem.factor11 >= 0.01) {
         float factor = 13.0F;
         float factor2 = (Math.abs((entityVillagergolem.factor14 - entityVillagergolem.factor11 * (1.0F - posZ) + 6.0F) % factor - factor * 0.5F) - factor * 0.25F) / (factor * 0.25F);
         GlStateManager.rotate(6.5F * factor2, 0.0F, 0.0F, 1.0F);
      }
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      this.applyEntityVillagergolem((EntityVillagergolem)livingEntity, posX, posY, posZ);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityVillagergolem)entity);
   }

   public SpecialResourceLocationEntry(RenderManager renderManager) {
      super(renderManager, new DiamondScythe(), 0.5F);
      this.add(new GoldenGem(this));
   }
}
