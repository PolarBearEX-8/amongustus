package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class LayerHeldItem implements LayerRenderer {
   private final RendererLivingEntity livingEntityRenderer;

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      ItemStack heldItem = entity.getHeldItem();
      if (heldItem != null) {
         GlStateManager.pushMatrix();
         if (this.livingEntityRenderer.getMainModel().isChild) {
            float f8 = 0.5F;
            GlStateManager.translate(0.0F, 0.625F, 0.0F);
            GlStateManager.rotate(-20.0F, -1.0F, 0.0F, 0.0F);
            GlStateManager.scale(f8, f8, f8);
         }

         ((ModelBiped)this.livingEntityRenderer.getMainModel()).postRenderArm(0.0625F);
         GlStateManager.translate(-0.0625F, 0.4375F, 0.0625F);
         if (entity instanceof EntityPlayer && ((EntityPlayer)entity).fishEntity != null) {
            heldItem = new ItemStack(Items.fishing_rod, 0);
         }

         Item item = heldItem.getItem();
         Minecraft minecraft = Minecraft.getMinecraft();
         if (item instanceof ItemBlock && Block.getBlockFromItem(item).getRenderType() == 2) {
            GlStateManager.translate(0.0F, 0.1875F, -0.3125F);
            GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
            float f9 = 0.375F;
            GlStateManager.scale(-f9, -f9, f9);
         }

         if (entity.isSneaking()) {
            GlStateManager.translate(0.0F, 0.203125F, 0.0F);
         }

         minecraft.getItemRenderer().renderItem(entity, heldItem, ItemCameraTransforms.TransformType.THIRD_PERSON);
         GlStateManager.popMatrix();
      }
   }

   public LayerHeldItem(RendererLivingEntity rendererLivingEntity) {
      this.livingEntityRenderer = rendererLivingEntity;
   }
}
