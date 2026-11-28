package net.minecraft.client.particle;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityDiggingFX extends EntityFX {
   private IBlockState sourceState;
   private BlockPos sourcePos;

   public EntityDiggingFX init() {
      this.sourcePos = new BlockPos(this.posX, this.posY, this.posZ);
      Block block = this.sourceState.getBlock();
      if (block == Blocks.grass) {
         return this;
      } else {
         int renderColor = block.getRenderColor(this.sourceState);
         this.particleRed *= (float)(renderColor >> 16 & 0xFF) / 255.0F;
         this.particleGreen *= (float)(renderColor >> 8 & 0xFF) / 255.0F;
         this.particleBlue *= (float)(renderColor & 0xFF) / 255.0F;
         return this;
      }
   }

   public EntityDiggingFX setBlockPos(BlockPos pos) {
      this.sourcePos = pos;
      if (this.sourceState.getBlock() == Blocks.grass) {
         return this;
      } else {
         int n = this.sourceState.getBlock().colorMultiplier(this.entityWorld, pos);
         this.particleRed *= (float)(n >> 16 & 0xFF) / 255.0F;
         this.particleGreen *= (float)(n >> 8 & 0xFF) / 255.0F;
         this.particleBlue *= (float)(n & 0xFF) / 255.0F;
         return this;
      }
   }

   public EntityDiggingFX(World world, double d, double d2, double d3, double d4, double d5, double d6, IBlockState state) {
      super(world, d, d2, d3, d4, d5, d6);
      this.sourceState = state;
      this.setParticleIcon(Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(state));
      this.particleGravity = state.getBlock().blockParticleGravity;
      this.particleRed = this.particleGreen = this.particleBlue = 0.6F;
      this.particleScale /= 2.0F;
   }

   @Override
   public int getBrightnessForRender(float f) {
      int brightnessForRender = super.getBrightnessForRender(f);
      int n = 0;
      if (this.entityWorld.isBlockLoaded(this.sourcePos)) {
         n = this.entityWorld.getCombinedLight(this.sourcePos.getX(), this.sourcePos.getY(), this.sourcePos.getZ(), 0);
      }

      return brightnessForRender == 0 ? n : brightnessForRender;
   }

   @Override
   public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      float f7 = ((float)this.particleTextureIndexX + this.particleTextureJitterX / 4.0F) / 16.0F;
      float f8 = f7 + 0.015609375F;
      float f9 = ((float)this.particleTextureIndexY + this.particleTextureJitterY / 4.0F) / 16.0F;
      float f10 = f9 + 0.015609375F;
      float f11 = 0.1F * this.particleScale;
      if (this.particleIcon != null) {
         f7 = this.particleIcon.getInterpolatedU((double)(this.particleTextureJitterX / 4.0F * 16.0F));
         f8 = this.particleIcon.getInterpolatedU((double)((this.particleTextureJitterX + 1.0F) / 4.0F * 16.0F));
         f9 = this.particleIcon.getInterpolatedV((double)(this.particleTextureJitterY / 4.0F * 16.0F));
         f10 = this.particleIcon.getInterpolatedV((double)((this.particleTextureJitterY + 1.0F) / 4.0F * 16.0F));
      }

      float f12 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)f - interpPosX);
      float f13 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)f - interpPosY);
      float f14 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)f - interpPosZ);
      int brightnessForRender = this.getBrightnessForRender(f);
      int n = brightnessForRender >> 16 & 65535;
      int n2 = brightnessForRender & 65535;
      worldRenderer.pos((double)(f12 - f2 * f11 - f5 * f11), (double)(f13 - f3 * f11), (double)(f14 - f4 * f11 - f6 * f11))
         .tex((double)f7, (double)f10)
         .color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
         .lightmap(n, n2)
         .endVertex();
      worldRenderer.pos((double)(f12 - f2 * f11 + f5 * f11), (double)(f13 + f3 * f11), (double)(f14 - f4 * f11 + f6 * f11))
         .tex((double)f7, (double)f9)
         .color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
         .lightmap(n, n2)
         .endVertex();
      worldRenderer.pos((double)(f12 + f2 * f11 + f5 * f11), (double)(f13 + f3 * f11), (double)(f14 + f4 * f11 + f6 * f11))
         .tex((double)f8, (double)f9)
         .color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
         .lightmap(n, n2)
         .endVertex();
      worldRenderer.pos((double)(f12 + f2 * f11 - f5 * f11), (double)(f13 - f3 * f11), (double)(f14 + f4 * f11 - f6 * f11))
         .tex((double)f8, (double)f10)
         .color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
         .lightmap(n, n2)
         .endVertex();
   }

   @Override
   public int getFXLayer() {
      return 1;
   }


   public static class Factory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntityDiggingFX(world, d, d2, d3, d4, d5, d6, Block.getStateById(ints[0])).init();
      }
   }
}
