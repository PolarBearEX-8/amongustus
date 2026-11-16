package net.minecraft.item;

import client.event.PreMotionDispatcher;
import client.util.ListenerAdapter27;
import client.util.SpecialResourceLocationEntry;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityVillagergolem;

public class GoldenGem implements PreMotionDispatcher {
   public final SpecialResourceLocationEntry specialResourceLocationEntry;

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyEntityVillagergolem((EntityVillagergolem)livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   @Override
   public boolean isActive() {
      return false;
   }

   public void applyEntityVillagergolem(EntityVillagergolem entityVillagergolem, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      if (entityVillagergolem.getCount28() != 0) {
         GoldenCrystal goldenCrystal = Minecraft.getMinecraft().getGoldenCrystal();
         GlStateManager.disableLighting();
         GlStateManager.pushMatrix();
         GlStateManager.rotate(
            5.0F + 180.0F * ((DiamondScythe)this.specialResourceLocationEntry.getPassiveFloatEntry()).boxesManager3.factor / (float) Math.PI, 1.0F, 0.0F, 0.0F
         );
         GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.translate(-0.9375F, -0.625F, -0.9375F);
         float factor = 0.5F;
         GlStateManager.translate3f3(factor, -factor, factor);
         int count = entityVillagergolem.computeCount(posX3);
         int count2 = count % 65536;
         int count3 = count / 65536;
         OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, (float)count2 / 1.0F, (float)count3 / 1.0F);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         this.specialResourceLocationEntry.applyResourceLocation2(ListenerAdapter27.resourceLocation);
         goldenCrystal.applyIBlockState(Blocks.immutableSetEntry2Entry2.getDefaultState(), 1.0F);
         GlStateManager.popMatrix();
         GlStateManager.applyState8();
      }
   }

   public GoldenGem(SpecialResourceLocationEntry specialResourceLocationEntry2) {
      this.specialResourceLocationEntry = specialResourceLocationEntry2;
   }
}
