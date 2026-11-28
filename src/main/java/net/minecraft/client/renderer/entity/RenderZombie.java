package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerVillagerArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

public class RenderZombie extends RenderBiped {
   private static final ResourceLocation zombieTextures = new ResourceLocation("textures/entity/zombie/zombie.png");
   private static final ResourceLocation zombieVillagerTextures = new ResourceLocation("textures/entity/zombie/zombie_villager.png");
   private final ModelBiped defaultModel;
   private final ModelZombieVillager zombieVillagerModel;
   private final List villagerLayerRenderers;
   private final List defaultLayerRenderers;

   public ResourceLocation getEntityTexture(EntityZombie entityZombie) {
      return entityZombie.isVillager() ? zombieVillagerTextures : zombieTextures;
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityZombie)entity);
   }

   @Override
   public ResourceLocation getEntityTexture(EntityLiving entityLiving) {
      return this.getEntityTexture((EntityZombie)entityLiving);
   }

   @Override
   public void doRender(EntityLivingBase entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityZombie)entity, d, d2, d3, f, f2);
   }

   @Override
   public void rotateCorpse(EntityLivingBase entity, float f, float f2, float f3) {
      this.rotateCorpse((EntityZombie)entity, f, f2, f3);
   }

   public void rotateCorpse(EntityZombie entityZombie, float f, float f2, float f3) {
      if (entityZombie.isConverting()) {
         f2 += (float)(Math.cos((double)entityZombie.ticksExisted * 3.25) * Math.PI * 0.25);
      }

      super.rotateCorpse(entityZombie, f, f2, f3);
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityZombie)entity, d, d2, d3, f, f2);
   }

   public RenderZombie(RenderManager renderManager2) {
      super(renderManager2, new ModelZombie(), 0.5F, 1.0F);
      LayerRenderer layerRenderer = (LayerRenderer)this.layerRenderers.get(0);
      this.defaultModel = this.modelBipedMain;
      this.zombieVillagerModel = new ModelZombieVillager();
      this.addLayer(new LayerHeldItem(this));
      RenderZombie.DefaultLayerBipedArmor obj = new RenderZombie.DefaultLayerBipedArmor(this, this);
      this.addLayer(obj);
      this.defaultLayerRenderers = Lists.newArrayList(this.layerRenderers);
      if (layerRenderer instanceof LayerCustomHead) {
         this.removeLayer(layerRenderer);
         this.addLayer(new LayerCustomHead(this.zombieVillagerModel.bipedHead));
      }

      this.removeLayer(obj);
      this.addLayer(new LayerVillagerArmor(this));
      this.villagerLayerRenderers = Lists.newArrayList(this.layerRenderers);
   }

   @Override
   public void doRender(EntityLiving entityLiving, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityZombie)entityLiving, d, d2, d3, f, f2);
   }

   private void selectZombieModel(EntityZombie entityZombie) {
      if (entityZombie.isVillager()) {
         this.mainModel = this.zombieVillagerModel;
         this.layerRenderers = this.villagerLayerRenderers;
      } else {
         this.mainModel = this.defaultModel;
         this.layerRenderers = this.defaultLayerRenderers;
      }

      this.modelBipedMain = (ModelBiped)this.mainModel;
   }

   public void doRender(EntityZombie entityZombie, double d, double d2, double d3, float f, float f2) {
      this.selectZombieModel(entityZombie);
      super.doRender(entityZombie, d, d2, d3, f, f2);
   }


   public static class DefaultLayerBipedArmor extends LayerBipedArmor {
      public final RenderZombie renderZombie;

      @Override
      public void initArmor() {
         this.modelLeggings = new ModelZombie(0.5F, true);
         this.modelArmor = new ModelZombie(1.0F, true);
      }

      public DefaultLayerBipedArmor(RenderZombie renderZombie2, RendererLivingEntity rendererLivingEntity) {
         super(rendererLivingEntity);
         this.renderZombie = renderZombie2;
      }
   }
}
