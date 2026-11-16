package client.util;

import client.event.PreMotionDispatcher;
import client.render.MeshBufferRenderer;
import client.setting.ItemPhysicsNoneType;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityWitch;
import net.minecraft.item.DiamondStaff;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class AdvancedListenerHandler implements PreMotionDispatcher {
   public final MeshBufferRenderer meshBufferRenderer;

   public void applyEntityWitch(EntityWitch entityWitch, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      ItemStack stack = entityWitch.getItemStack();
      if (stack != null) {
         GlStateManager.translate3f2(1.0F, 1.0F, 1.0F);
         GlStateManager.pushMatrix();
         if (this.meshBufferRenderer.getPassiveFloatEntry().enabled2) {
            GlStateManager.translate(0.0F, 0.625F, 0.0F);
            GlStateManager.rotate(-20.0F, -1.0F, 0.0F, 0.0F);
            float factor = 0.5F;
            GlStateManager.translate3f3(factor, factor, factor);
         }

         ((DiamondStaff)this.meshBufferRenderer.getPassiveFloatEntry()).boxesManager5.applyFloatByFloat(0.0625F);
         GlStateManager.translate(-0.0625F, 0.53125F, 0.21875F);
         Item item = stack.getItem();
         Minecraft mc = Minecraft.getMinecraft();
         if (item instanceof ItemBlock && mc.getGoldenCrystal().matchesBlock(Block.resolveBlock(item), stack.getIndexOffset())) {
            GlStateManager.translate(0.0F, 0.0625F, -0.25F);
            GlStateManager.rotate(30.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(-5.0F, 0.0F, 1.0F, 0.0F);
            float factor2 = 0.375F;
            GlStateManager.translate3f3(factor2, -factor2, factor2);
         } else if (item == Items.bow) {
            GlStateManager.translate(0.0F, 0.125F, -0.125F);
            GlStateManager.rotate(-45.0F, 0.0F, 1.0F, 0.0F);
            float factor3 = 0.625F;
            GlStateManager.translate3f3(factor3, -factor3, factor3);
            GlStateManager.rotate(-100.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(-20.0F, 0.0F, 1.0F, 0.0F);
         } else if (item.isState4()) {
            if (item.processAction3()) {
               GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
               GlStateManager.translate(0.0F, -0.0625F, 0.0F);
            }

            this.meshBufferRenderer.updateState();
            GlStateManager.translate(0.0625F, -0.125F, 0.0F);
            float factor4 = 0.625F;
            GlStateManager.translate3f3(factor4, -factor4, factor4);
            GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F);
         } else {
            GlStateManager.translate(0.1875F, 0.1875F, 0.0F);
            float factor5 = 0.875F;
            GlStateManager.translate3f3(factor5, factor5, factor5);
            GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(-60.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(-30.0F, 0.0F, 0.0F, 1.0F);
         }

         GlStateManager.rotate(-15.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(40.0F, 0.0F, 0.0F, 1.0F);
         mc.getItemRenderer().applyEntityLivingBaseByEntityLivingBase(entityWitch, stack, ItemPhysicsNoneType.THIRD_PERSON);
         GlStateManager.popMatrix();
      }
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyEntityWitch((EntityWitch)livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   @Override
   public boolean isActive() {
      return false;
   }

   public AdvancedListenerHandler(MeshBufferRenderer meshBufferRenderer2) {
      this.meshBufferRenderer = meshBufferRenderer2;
   }
}
