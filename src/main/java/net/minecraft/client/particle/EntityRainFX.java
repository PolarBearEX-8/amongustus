package net.minecraft.client.particle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityRainFX extends EntityFX {
   public EntityRainFX(World world, double d, double d2, double d3) {
      super(world, d, d2, d3, 0.0, 0.0, 0.0);
      this.motionX *= 0.3F;
      this.motionY = Math.random() * 0.2F + 0.1F;
      this.motionZ *= 0.3F;
      this.particleRed = 1.0F;
      this.particleGreen = 1.0F;
      this.particleBlue = 1.0F;
      this.setParticleTextureIndex(19 + this.rand.nextInt(4));
      this.setSize(0.01F, 0.01F);
      this.particleGravity = 0.06F;
      this.particleMaxAge = (int)(8.0 / (Math.random() * 0.8 + 0.2));
   }

   @Override
   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.motionY = this.motionY - (double)this.particleGravity;
      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.98F;
      this.motionY *= 0.98F;
      this.motionZ *= 0.98F;
      if (this.particleMaxAge-- <= 0) {
         this.setDead();
      }

      if (this.onGround) {
         if (Math.random() < 0.5) {
            this.setDead();
         }

         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }

      BlockPos pos = new BlockPos(this);
      IBlockState blockState = this.entityWorld.getBlockState(pos);
      Block block = blockState.getBlock();
      block.setBlockBoundsBasedOnState(this.entityWorld, pos.getX(), pos.getY(), pos.getZ());
      Material material = blockState.getBlock().getMaterial();
      if (material.isLiquid() || material.isSolid()) {
         double d = 0.0;
         if (blockState.getBlock() instanceof BlockLiquid) {
            d = (double)(1.0F - BlockLiquid.getLiquidHeightPercent((Integer)blockState.getValue(BlockLiquid.LEVEL)));
         } else {
            d = block.getBlockBoundsMaxY();
         }

         double d2 = (double)MathHelper.floor_double(this.posY) + d;
         if (this.posY < d2) {
            this.setDead();
         }
      }
   }


   public static class Factory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntityRainFX(world, d, d2, d3);
      }
   }
}
