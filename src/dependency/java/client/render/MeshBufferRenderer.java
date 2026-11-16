package client.render;

import client.util.AdvancedListenerHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.EntityWitch;
import net.minecraft.item.DiamondStaff;
import net.minecraft.util.ResourceLocation;

public class MeshBufferRenderer extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/witch.png");

   public void applyEntityWitch(EntityWitch entityWitch, float factor) {
      float factor2 = 0.9375F;
      GlStateManager.translate3f3(factor2, factor2, factor2);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityWitch((EntityWitch)livingEntity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      this.applyEntityWitch((EntityWitch)livingEntity, factor);
   }

   @Override
   public void applyEntityMob(EntityMob entityMob, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityWitch((EntityWitch)entityMob, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void updateState() {
      GlStateManager.translate(0.0F, 0.1875F, 0.0F);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityWitch((EntityWitch)entity, posX, posY, posZ, factor, factor2);
   }

   public void applyEntityWitch(EntityWitch entityWitch, double posX, double posY, double posZ, float factor, float factor2) {
      ((DiamondStaff)this.passiveFloatEntry).enabled3 = entityWitch.getItemStack() != null;
      super.applyEntityMob(entityWitch, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityWitch((EntityWitch)entity);
   }

   public ResourceLocation resolveResourceLocationByEntityWitch(EntityWitch entityWitch) {
      return resourceLocation3;
   }

   public MeshBufferRenderer(RenderManager renderManager) {
      super(renderManager, new DiamondStaff(0.0F), 0.5F);
      this.add(new AdvancedListenerHandler(this));
   }
}
