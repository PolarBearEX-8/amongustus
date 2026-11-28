package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;

public class RenderVillager extends RenderLiving {
   private static final ResourceLocation villagerTextures = new ResourceLocation("textures/entity/villager/villager.png");
   private static final ResourceLocation farmerVillagerTextures = new ResourceLocation("textures/entity/villager/farmer.png");
   private static final ResourceLocation librarianVillagerTextures = new ResourceLocation("textures/entity/villager/librarian.png");
   private static final ResourceLocation priestVillagerTextures = new ResourceLocation("textures/entity/villager/priest.png");
   private static final ResourceLocation smithVillagerTextures = new ResourceLocation("textures/entity/villager/smith.png");
   private static final ResourceLocation butcherVillagerTextures = new ResourceLocation("textures/entity/villager/butcher.png");

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityVillager)entity);
   }

   public void preRenderCallback(EntityVillager entityVillager, float f) {
      float f2 = 0.9375F;
      if (entityVillager.getGrowingAge() < 0) {
         f2 = (float)((double)f2 * 0.5);
         this.shadowSize = 0.25F;
      } else {
         this.shadowSize = 0.5F;
      }

      GlStateManager.scale(f2, f2, f2);
   }

   public ModelVillager getVillagerModel() {
      return (ModelVillager)super.getMainModel();
   }

   public RenderVillager(RenderManager renderManager2) {
      super(renderManager2, new ModelVillager(0.0F), 0.5F);
      this.addLayer(new LayerCustomHead(this.getVillagerModel().villagerHead));
   }

   @Override
   public ModelBase getMainModel() {
      return this.getVillagerModel();
   }

   public ResourceLocation getEntityTexture(EntityVillager entityVillager) {
      switch (entityVillager.getProfession()) {
         case 0:
            return farmerVillagerTextures;
         case 1:
            return librarianVillagerTextures;
         case 2:
            return priestVillagerTextures;
         case 3:
            return smithVillagerTextures;
         case 4:
            return butcherVillagerTextures;
         default:
            return villagerTextures;
      }
   }

   @Override
   public void preRenderCallback(EntityLivingBase entity, float f) {
      this.preRenderCallback((EntityVillager)entity, f);
   }
}
