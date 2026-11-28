package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderBiped extends RenderLiving {
   private static final ResourceLocation DEFAULT_RES_LOC = new ResourceLocation("textures/entity/steve.png");
   public ModelBiped modelBipedMain;
   public float modelScale;

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityLiving)entity);
   }

   public RenderBiped(RenderManager renderManager2, ModelBiped modelBiped, float f, float f2) {
      super(renderManager2, modelBiped, f);
      this.modelBipedMain = modelBiped;
      this.modelScale = f2;
      this.addLayer(new LayerCustomHead(modelBiped.bipedHead));
   }

   public RenderBiped(RenderManager renderManager2, ModelBiped modelBiped, float f) {
      this(renderManager2, modelBiped, f, 1.0F);
      this.addLayer(new LayerHeldItem(this));
   }

   @Override
   public void transformHeldFull3DItemLayer() {
      GlStateManager.translate(0.0F, 0.1875F, 0.0F);
   }

   public ResourceLocation getEntityTexture(EntityLiving entityLiving) {
      return DEFAULT_RES_LOC;
   }
}
