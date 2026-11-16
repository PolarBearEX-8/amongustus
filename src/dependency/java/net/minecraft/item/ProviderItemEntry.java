package net.minecraft.item;

import client.event.PreMotionDispatcher;
import client.render.PrimitiveBatchRenderer;
import client.setting.ItemPhysicsNoneType;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySnowman;

public class ProviderItemEntry implements PreMotionDispatcher {
   public final PrimitiveBatchRenderer primitiveBatchRenderer;

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyEntitySnowman((EntitySnowman)livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   @Override
   public boolean isActive() {
      return true;
   }

   public void applyEntitySnowman(EntitySnowman entitySnowman, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      if (!entitySnowman.isActive12()) {
         GlStateManager.pushMatrix();
         this.primitiveBatchRenderer.getDiamondSpear().boxesManager2.applyFloatByFloat(0.0625F);
         float factor = 0.625F;
         GlStateManager.translate(0.0F, -0.34375F, 0.0F);
         GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate3f3(factor, -factor, -factor);
         Minecraft.getMinecraft().getItemRenderer().applyEntityLivingBaseByEntityLivingBase(entitySnowman, new ItemStack(Blocks.block146, 1), ItemPhysicsNoneType.HEAD);
         GlStateManager.popMatrix();
      }
   }

   public ProviderItemEntry(PrimitiveBatchRenderer primitiveBatchRenderer2) {
      this.primitiveBatchRenderer = primitiveBatchRenderer2;
   }
}
