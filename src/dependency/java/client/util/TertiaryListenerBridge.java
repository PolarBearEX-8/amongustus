package client.util;

import client.event.PreMotionDispatcher;
import client.setting.ItemPhysicsNoneType;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class TertiaryListenerBridge implements PreMotionDispatcher {
   public final RendererLivingEntity rendererLivingEntity;

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      ItemStack stack = livingEntity.getItemStack();
      if (stack != null) {
         GlStateManager.pushMatrix();
         if (this.rendererLivingEntity.getPassiveFloatEntry().enabled2) {
            float factor = 0.5F;
            GlStateManager.translate(0.0F, 0.625F, 0.0F);
            GlStateManager.rotate(-20.0F, -1.0F, 0.0F, 0.0F);
            GlStateManager.translate3f3(factor, factor, factor);
         }

         ((FloatEntryRegistry)this.rendererLivingEntity.getPassiveFloatEntry()).applyFloat(0.0625F);
         GlStateManager.translate(-0.0625F, 0.4375F, 0.0625F);
         if (livingEntity instanceof EntityPlayer && ((EntityPlayer)livingEntity).internalEntityAdapter != null) {
            stack = new ItemStack(Items.fishingRod, 0);
         }

         Item item = stack.getItem();
         Minecraft mc = Minecraft.getMinecraft();
         if (item instanceof ItemBlock && Block.resolveBlock(item).processAction13() == 2) {
            GlStateManager.translate(0.0F, 0.1875F, -0.3125F);
            GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
            float factor2 = 0.375F;
            GlStateManager.translate3f3(-factor2, -factor2, factor2);
         }

         if (livingEntity.isSprinting()) {
            GlStateManager.translate(0.0F, 0.203125F, 0.0F);
         }

         mc.getItemRenderer().applyEntityLivingBaseByEntityLivingBase(livingEntity, stack, ItemPhysicsNoneType.THIRD_PERSON);
         GlStateManager.popMatrix();
      }
   }

   @Override
   public boolean isActive() {
      return false;
   }

   public TertiaryListenerBridge(RendererLivingEntity rendererLivingEntity2) {
      this.rendererLivingEntity = rendererLivingEntity2;
   }
}
