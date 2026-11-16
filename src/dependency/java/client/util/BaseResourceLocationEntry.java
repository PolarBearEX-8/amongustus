package client.util;

import client.render.InternalRendererLivingEntityAdapter;
import client.render.RenderManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityVillager;
import net.minecraft.item.DiamondAmulet;
import net.minecraft.util.ResourceLocation;

public class BaseResourceLocationEntry extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/entity/villager/villager.png");
   public static final ResourceLocation resourceLocation7 = new ResourceLocation("textures/entity/villager/farmer.png");
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/entity/villager/librarian.png");
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/villager/priest.png");
   public static final ResourceLocation resourceLocation6 = new ResourceLocation("textures/entity/villager/smith.png");
   public static final ResourceLocation resourceLocation8 = new ResourceLocation("textures/entity/villager/butcher.png");

   public void applyEntityVillager(EntityVillager entityVillager, float factor) {
      float factor2 = 0.9375F;
      if (entityVillager.getCount9() < 0) {
         factor2 = (float)((double)factor2 * 0.5);
         this.factor = 0.25F;
      } else {
         this.factor = 0.5F;
      }

      GlStateManager.translate3f3(factor2, factor2, factor2);
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return this.getDiamondAmulet();
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      this.applyEntityVillager((EntityVillager)livingEntity, factor);
   }

   public DiamondAmulet getDiamondAmulet() {
      return (DiamondAmulet)super.getPassiveFloatEntry();
   }

   public ResourceLocation resolveResourceLocation(EntityVillager entityVillager) {
      switch (entityVillager.getCount10()) {
         case 0:
            return resourceLocation7;
         case 1:
            return resourceLocation5;
         case 2:
            return resourceLocation3;
         case 3:
            return resourceLocation6;
         case 4:
            return resourceLocation8;
         default:
            return resourceLocation4;
      }
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityVillager)entity);
   }

   public BaseResourceLocationEntry(RenderManager renderManager) {
      super(renderManager, new DiamondAmulet(0.0F), 0.5F);
      this.add(new ListenerAdapter5(this.getDiamondAmulet().boxesManager2));
   }
}
