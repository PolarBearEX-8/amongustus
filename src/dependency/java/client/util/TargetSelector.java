package client.util;

import client.render.EntitySkinLocation;
import client.render.RenderManager;
import client.render.WorldRenderer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFallingsand;
import net.minecraft.item.GoldenCrystal;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TargetSelector extends EntitySkinLocation {
   public void applyEntityFallingsand(EntityFallingsand entityFallingsand, double posX, double posY, double posZ, float factor, float factor2) {
      if (entityFallingsand.getIBlockState() != null) {
         this.applyResourceLocation2(ListenerAdapter27.resourceLocation);
         IBlockState iBlockState = entityFallingsand.getIBlockState();
         Block block = iBlockState.getBlock();
         BlockPos blockPos = new BlockPos(entityFallingsand);
         World worldAsWorld = entityFallingsand.getWorldAsWorld();
         if (iBlockState != worldAsWorld.getBlockState(blockPos) && block.processAction13() != -1 && block.processAction13() == 3) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)posX, (float)posY, (float)posZ);
            GlStateManager.applyState3();
            Tessellator tessellator = Tessellator.getTessellator();
            WorldRenderer worldRenderer = tessellator.getWorldRenderer();
            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry5);
            int coordX = blockPos.getX();
            int coordY = blockPos.getY();
            int coordZ = blockPos.getZ();
            worldRenderer.applyDouble((double)((float)(-coordX) - 0.5F), (double)(-coordY), (double)((float)(-coordZ) - 0.5F));
            GoldenCrystal goldenCrystal = Minecraft.getMinecraft().getGoldenCrystal();
            IBakedModel iBakedModel = goldenCrystal.resolveIBakedModel(iBlockState, worldAsWorld, (BlockPos)null);
            goldenCrystal.getSharedFloatEntry().matchesIBlockAccessByIBlockAccess(worldAsWorld, iBakedModel, iBlockState, blockPos, worldRenderer, false);
            worldRenderer.applyDouble(0.0, 0.0, 0.0);
            tessellator.updateState();
            GlStateManager.applyState13();
            GlStateManager.popMatrix();
            super.applyEntity(entityFallingsand, posX, posY, posZ, factor, factor2);
         }
      }
   }

   public ResourceLocation resolveResourceLocationByEntityFallingsan(EntityFallingsand entityFallingsand) {
      return ListenerAdapter27.resourceLocation;
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityFallingsand((EntityFallingsand)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityFallingsan((EntityFallingsand)entity);
   }

   public TargetSelector(RenderManager renderManager) {
      super(renderManager);
      this.factor = 0.5F;
   }
}
