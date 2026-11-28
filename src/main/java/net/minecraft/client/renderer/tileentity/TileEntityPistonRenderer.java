package net.minecraft.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class TileEntityPistonRenderer extends TileEntitySpecialRenderer {
   private final BlockRendererDispatcher blockRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher();

   public void renderTileEntityAt(TileEntityPiston tileEntityPiston, double d, double d2, double d3, float f, int n) {
      BlockPos pos = tileEntityPiston.getPos();
      IBlockState pistonState = tileEntityPiston.getPistonState();
      Block block = pistonState.getBlock();
      if (block.getMaterial() != Material.air && tileEntityPiston.getProgress(f) < 1.0F) {
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         this.bindTexture(TextureMap.locationBlocksTexture);
         RenderHelper.disableStandardItemLighting();
         GlStateManager.blendFunc(770, 771);
         GlStateManager.enableBlend();
         GlStateManager.disableCull();
         if (Minecraft.isAmbientOcclusionEnabled()) {
            GlStateManager.shadeModel(7425);
         } else {
            GlStateManager.shadeModel(7424);
         }

         worldRenderer.begin(7, DefaultVertexFormats.BLOCK);
         worldRenderer.setTranslation(
            (double)((float)d - (float)pos.getX() + tileEntityPiston.getOffsetX(f)),
            (double)((float)d2 - (float)pos.getY() + tileEntityPiston.getOffsetY(f)),
            (double)((float)d3 - (float)pos.getZ() + tileEntityPiston.getOffsetZ(f))
         );
         World world = this.getWorld();
         if (block == Blocks.piston_head && tileEntityPiston.getProgress(f) < 0.5F) {
            pistonState = pistonState.withProperty(BlockPistonExtension.SHORT, Boolean.TRUE);
            this.blockRenderer
               .getBlockModelRenderer()
               .renderModel(world, this.blockRenderer.getModelFromBlockState(pistonState, world, pos), pistonState, pos, worldRenderer, true);
         } else if (tileEntityPiston.shouldPistonHeadBeRendered() && !tileEntityPiston.isExtending()) {
            BlockPistonExtension.EnumPistonType enumPistonType = block == Blocks.sticky_piston
               ? BlockPistonExtension.EnumPistonType.STICKY
               : BlockPistonExtension.EnumPistonType.DEFAULT;
            IBlockState state = Blocks.piston_head
               .getDefaultState()
               .withProperty(BlockPistonExtension.TYPE, enumPistonType)
               .withProperty(BlockPistonExtension.FACING, pistonState.getValue(BlockPistonBase.FACING));
            state = state.withProperty(BlockPistonExtension.SHORT, tileEntityPiston.getProgress(f) >= 0.5F);
            this.blockRenderer
               .getBlockModelRenderer()
               .renderModel(world, this.blockRenderer.getModelFromBlockState(state, world, pos), state, pos, worldRenderer, true);
            worldRenderer.setTranslation(
               (double)((float)d - (float)pos.getX()), (double)((float)d2 - (float)pos.getY()), (double)((float)d3 - (float)pos.getZ())
            );
            pistonState.withProperty(BlockPistonBase.EXTENDED, Boolean.TRUE);
            this.blockRenderer
               .getBlockModelRenderer()
               .renderModel(world, this.blockRenderer.getModelFromBlockState(pistonState, world, pos), pistonState, pos, worldRenderer, true);
         } else {
            this.blockRenderer
               .getBlockModelRenderer()
               .renderModel(world, this.blockRenderer.getModelFromBlockState(pistonState, world, pos), pistonState, pos, worldRenderer, false);
         }

         worldRenderer.setTranslation(0.0, 0.0, 0.0);
         instance.draw();
         RenderHelper.enableStandardItemLighting();
      }
   }

   @Override
   public void renderTileEntityAt(TileEntity tileEntity, double d, double d2, double d3, float f, int n) {
      this.renderTileEntityAt((TileEntityPiston)tileEntity, d, d2, d3, f, n);
   }
}
