package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelArmorStandArmor;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.model.ModelArmorStand;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.ResourceLocation;

public class ArmorStandRenderer extends RendererLivingEntity {
   public static final ResourceLocation TEXTURE_ARMOR_STAND = new ResourceLocation("textures/entity/armorstand/wood.png");

   @Override
   public boolean canRenderName(Entity entity) {
      return this.canRenderName((EntityArmorStand)entity);
   }

   public ResourceLocation getEntityTexture(EntityArmorStand entityArmorStand) {
      return TEXTURE_ARMOR_STAND;
   }

   public ArmorStandRenderer(RenderManager renderManager2) {
      super(renderManager2, new ModelArmorStand(), 0.0F);
      this.addLayer(new ArmorStandRenderer.DefaultLayerBipedArmor(this, this));
      this.addLayer(new LayerHeldItem(this));
      this.addLayer(new LayerCustomHead(this.getArmorStandModel().bipedHead));
   }

   public ModelArmorStand getArmorStandModel() {
      return (ModelArmorStand)super.getMainModel();
   }

   public boolean canRenderName(EntityArmorStand entityArmorStand) {
      return entityArmorStand.getAlwaysRenderNameTag();
   }

   public void rotateCorpse(EntityArmorStand entityArmorStand, float f, float f2, float f3) {
      GlStateManager.rotate(180.0F - f2, 0.0F, 1.0F, 0.0F);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityArmorStand)entity);
   }

   @Override
   public ModelBase getMainModel() {
      return this.getArmorStandModel();
   }

   @Override
   public void rotateCorpse(EntityLivingBase entity, float f, float f2, float f3) {
      this.rotateCorpse((EntityArmorStand)entity, f, f2, f3);
   }

   @Override
   public boolean canRenderName(EntityLivingBase entity) {
      return this.canRenderName((EntityArmorStand)entity);
   }


   public static class DefaultLayerBipedArmor extends LayerBipedArmor {
      public final ArmorStandRenderer armorStandRenderer;

      public DefaultLayerBipedArmor(ArmorStandRenderer armorStandRenderer2, RendererLivingEntity rendererLivingEntity) {
         super(rendererLivingEntity);
         this.armorStandRenderer = armorStandRenderer2;
      }

      @Override
      public void initArmor() {
         this.modelLeggings = new ModelArmorStandArmor(0.5F);
         this.modelArmor = new ModelArmorStandArmor(1.0F);
      }
   }
}
