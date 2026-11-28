package net.minecraft.client.renderer.entity;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.entity.layers.LayerEndermanEyes;
import net.minecraft.client.renderer.entity.layers.LayerHeldBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;

public class RenderEnderman extends RenderLiving {
   private static final ResourceLocation endermanTextures = new ResourceLocation("textures/entity/enderman/enderman.png");
   private ModelEnderman endermanModel;
   private Random rnd = new Random();

   public ResourceLocation getEntityTexture(EntityEnderman entityEnderman) {
      return endermanTextures;
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityEnderman)entity, d, d2, d3, f, f2);
   }

   @Override
   public void doRender(EntityLivingBase entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityEnderman)entity, d, d2, d3, f, f2);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityEnderman)entity);
   }

   @Override
   public void doRender(EntityLiving entityLiving, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityEnderman)entityLiving, d, d2, d3, f, f2);
   }

   public void doRender(EntityEnderman entityEnderman, double d, double d2, double d3, float f, float f2) {
      this.endermanModel.isCarrying = entityEnderman.getHeldBlockState().getBlock().getMaterial() != Material.air;
      this.endermanModel.isAttacking = entityEnderman.isScreaming();
      if (entityEnderman.isScreaming()) {
         double d4 = 0.02;
         d += this.rnd.nextGaussian() * d4;
         d3 += this.rnd.nextGaussian() * d4;
      }

      super.doRender((EntityLiving)entityEnderman, d, d2, d3, f, f2);
   }

   public RenderEnderman(RenderManager renderManager2) {
      super(renderManager2, new ModelEnderman(0.0F), 0.5F);
      this.endermanModel = (ModelEnderman)super.mainModel;
      this.addLayer(new LayerEndermanEyes(this));
      this.addLayer(new LayerHeldBlock(this));
   }
}
