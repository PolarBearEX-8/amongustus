package net.minecraft.item;

import client.event.PreMotionDispatcher;
import client.util.BoxesManager;
import client.util.BuildManager;
import client.util.ListenerAdapter27;
import client.util.SharedResourceLocationEntry;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMushroomcow;
import net.minecraft.util.ResourceLocation;

public class ActiveItemEntry implements PreMotionDispatcher {
   public final SharedResourceLocationEntry sharedResourceLocationEntry;
   public BoxesManager boxesManager;
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/entity/cow/mushroom_red.png");
   public static boolean enabled = false;

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyEntityMushroomcow((EntityMushroomcow)livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   public void applyEntityMushroomcow(EntityMushroomcow entityMushroomcow, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      if (!entityMushroomcow.isActive16() && !entityMushroomcow.isActive12()) {
         GoldenCrystal goldenCrystal = Minecraft.getMinecraft().getGoldenCrystal();
         if (enabled) {
            this.sharedResourceLocationEntry.applyResourceLocation2(resourceLocation);
         } else {
            this.sharedResourceLocationEntry.applyResourceLocation2(ListenerAdapter27.resourceLocation);
         }

         GlStateManager.enableLighting();
         GlStateManager.glCullFace(1028);
         GlStateManager.pushMatrix();
         GlStateManager.translate3f3(1.0F, -1.0F, 1.0F);
         GlStateManager.translate(0.2F, 0.35F, 0.5F);
         GlStateManager.rotate(42.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.pushMatrix();
         GlStateManager.translate(-0.5F, -0.5F, 0.5F);
         if (enabled) {
            this.boxesManager.applyFloat3(0.0625F);
         } else {
            goldenCrystal.applyIBlockState(Blocks.secondaryBlockAdapter.getDefaultState(), 1.0F);
         }

         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.translate(0.1F, 0.0F, -0.6F);
         GlStateManager.rotate(42.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(-0.5F, -0.5F, 0.5F);
         if (enabled) {
            this.boxesManager.applyFloat3(0.0625F);
         } else {
            goldenCrystal.applyIBlockState(Blocks.secondaryBlockAdapter.getDefaultState(), 1.0F);
         }

         GlStateManager.popMatrix();
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         ((DiamondRing)this.sharedResourceLocationEntry.getPassiveFloatEntry()).boxesManager6.applyFloatByFloat(0.0625F);
         GlStateManager.translate3f3(1.0F, -1.0F, 1.0F);
         GlStateManager.translate(0.0F, 0.7F, -0.2F);
         GlStateManager.rotate(12.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(-0.5F, -0.5F, 0.5F);
         if (enabled) {
            this.boxesManager.applyFloat3(0.0625F);
         } else {
            goldenCrystal.applyIBlockState(Blocks.secondaryBlockAdapter.getDefaultState(), 1.0F);
         }

         GlStateManager.popMatrix();
         GlStateManager.glCullFace(1029);
         GlStateManager.disableAlpha();
      }
   }

   @Override
   public boolean isActive() {
      return true;
   }

   public static void updateState() {
      enabled = BuildManager.matchesResourceLocation3(resourceLocation);
   }

   public ActiveItemEntry(SharedResourceLocationEntry sharedResourceLocationEntry2) {
      this.sharedResourceLocationEntry = sharedResourceLocationEntry2;
      this.boxesManager = new BoxesManager(this.sharedResourceLocationEntry.passiveFloatEntry);
      this.boxesManager.resolveBoxesManager(16, 16);
      this.boxesManager.factor5 = -6.0F;
      this.boxesManager.factor10 = -8.0F;
      this.boxesManager.factor9 = (float) (Math.PI / 4);
      int[][] countMatrix = new int[][]{null, null, {16, 16, 0, 0}, {16, 16, 0, 0}, null, null};
      this.boxesManager.applyIndexArray(countMatrix, 0.0F, 0.0F, 10.0F, 20.0F, 16.0F, 0.0F, 0.0F);
      int[][] countMatrix2 = new int[][]{null, null, null, null, {16, 16, 0, 0}, {16, 16, 0, 0}};
      this.boxesManager.applyIndexArray(countMatrix2, 10.0F, 0.0F, 0.0F, 0.0F, 16.0F, 20.0F, 0.0F);
   }
}
