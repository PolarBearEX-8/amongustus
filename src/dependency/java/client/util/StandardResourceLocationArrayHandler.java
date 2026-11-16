package client.util;

import client.render.WorldRenderer;
import client.setting.MovementToggleProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPiston;
import net.minecraft.block.BlockPistonHead;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.GoldenCrystal;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class StandardResourceLocationArrayHandler extends ResourceLocationArrayHolder {
   public final GoldenCrystal goldenCrystal = Minecraft.getMinecraft().getGoldenCrystal();

   public void applyTileEntityPiston(TileEntityPiston tileEntityPiston, double posX, double posY, double posZ, float factor, int count) {
      BlockPos blockPos = tileEntityPiston.getBlockPos();
      IBlockState iBlockState = tileEntityPiston.getIBlockState();
      Block block = iBlockState.getBlock();
      if (block.getMaterial() != Material.coreItemEntry && tileEntityPiston.computeFactor3(factor) < 1.0F) {
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         this.applyResourceLocation2(ListenerAdapter27.resourceLocation);
         RenderHelper.disableStandardItemLighting();
         GlStateManager.glStateAction3(770, 771);
         GlStateManager.disableBlend();
         GlStateManager.disableAlpha();
         if (Minecraft.isActive()) {
            GlStateManager.glShadeModel(7425);
         } else {
            GlStateManager.glShadeModel(7424);
         }

         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry5);
         worldRenderer.applyDouble(
            (double)((float)posX - (float)blockPos.getX() + tileEntityPiston.computeFactor2(factor)),
            (double)((float)posY - (float)blockPos.getY() + tileEntityPiston.computeFactorByFloat(factor)),
            (double)((float)posZ - (float)blockPos.getZ() + tileEntityPiston.computeFactor(factor))
         );
         World world = this.getWorld();
         if (block == Blocks.blockPistonHead && tileEntityPiston.computeFactor3(factor) < 0.5F) {
            IBlockState blockState = iBlockState.resolveIBlockState(BlockPistonHead.internalImmutableSetEntry, Boolean.TRUE);
            this.goldenCrystal
               .getSharedFloatEntry()
               .matchesIBlockAccessByIBlockAccess(world, this.goldenCrystal.resolveIBakedModel(blockState, world, blockPos), blockState, blockPos, worldRenderer, true);
         } else if (tileEntityPiston.isEnabled3() && !tileEntityPiston.isEnabled2()) {
            MovementToggleProperty movementToggleProperty = block == Blocks.blockPiston ? MovementToggleProperty.STICKY : MovementToggleProperty.DEFAULT;
            IBlockState blockState2 = Blocks.blockPistonHead
               .getDefaultState()
               .resolveIBlockState(BlockPistonHead.immutableSetEntry, movementToggleProperty)
               .resolveIBlockState(BlockPistonHead.autoWalkPath, iBlockState.getComparable(BlockPiston.autoWalkPath))
               .resolveIBlockState(BlockPistonHead.internalImmutableSetEntry, tileEntityPiston.computeFactor3(factor) >= 0.5F);
            this.goldenCrystal
               .getSharedFloatEntry()
               .matchesIBlockAccessByIBlockAccess(world, this.goldenCrystal.resolveIBakedModel(blockState2, world, blockPos), blockState2, blockPos, worldRenderer, true);
            worldRenderer.applyDouble(
               (double)((float)posX - (float)blockPos.getX()), (double)((float)posY - (float)blockPos.getY()), (double)((float)posZ - (float)blockPos.getZ())
            );
            iBlockState.resolveIBlockState(BlockPiston.internalImmutableSetEntry, Boolean.TRUE);
            this.goldenCrystal
               .getSharedFloatEntry()
               .matchesIBlockAccessByIBlockAccess(world, this.goldenCrystal.resolveIBakedModel(iBlockState, world, blockPos), iBlockState, blockPos, worldRenderer, true);
         } else {
            this.goldenCrystal
               .getSharedFloatEntry()
               .matchesIBlockAccessByIBlockAccess(world, this.goldenCrystal.resolveIBakedModel(iBlockState, world, blockPos), iBlockState, blockPos, worldRenderer, false);
         }

         worldRenderer.applyDouble(0.0, 0.0, 0.0);
         tessellator.updateState();
         RenderHelper.enableStandardItemLighting();
      }
   }

   @Override
   public void applyTileEntity(TileEntity tileEntity, double posX, double posY, double posZ, float factor, int count) {
      this.applyTileEntityPiston((TileEntityPiston)tileEntity, posX, posY, posZ, factor, count);
   }
}
