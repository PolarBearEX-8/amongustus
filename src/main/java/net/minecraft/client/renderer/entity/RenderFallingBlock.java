package net.minecraft.client.renderer.entity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RenderFallingBlock extends Render {
   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityFallingBlock)entity);
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityFallingBlock)entity, d, d2, d3, f, f2);
   }

   public ResourceLocation getEntityTexture(EntityFallingBlock entityFallingBlock) {
      return TextureMap.locationBlocksTexture;
   }

   public void doRender(EntityFallingBlock entityFallingBlock, double d, double d2, double d3, float f, float f2) {
      if (entityFallingBlock.getBlock() != null) {
         this.bindTexture(TextureMap.locationBlocksTexture);
         IBlockState block = entityFallingBlock.getBlock();
         Block block2 = block.getBlock();
         BlockPos pos = new BlockPos(entityFallingBlock);
         World worldObj = entityFallingBlock.getWorldObj();
         if (block != worldObj.getBlockState(pos) && block2.getRenderType() != -1 && block2.getRenderType() == 3) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)d, (float)d2, (float)d3);
            GlStateManager.disableLighting();
            Tessellator instance = Tessellator.getInstance();
            WorldRenderer worldRenderer = instance.getWorldRenderer();
            worldRenderer.begin(7, DefaultVertexFormats.BLOCK);
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            worldRenderer.setTranslation((double)((float)(-x) - 0.5F), (double)(-y), (double)((float)(-z) - 0.5F));
            BlockRendererDispatcher blockRendererDispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
            IBakedModel modelFromBlockState = blockRendererDispatcher.getModelFromBlockState(block, worldObj, (BlockPos)null);
            blockRendererDispatcher.getBlockModelRenderer().renderModel(worldObj, modelFromBlockState, block, pos, worldRenderer, false);
            worldRenderer.setTranslation(0.0, 0.0, 0.0);
            instance.draw();
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
            super.doRender(entityFallingBlock, d, d2, d3, f, f2);
         }
      }
   }

   public RenderFallingBlock(RenderManager renderManager2) {
      super(renderManager2);
      this.shadowSize = 0.5F;
   }
}
