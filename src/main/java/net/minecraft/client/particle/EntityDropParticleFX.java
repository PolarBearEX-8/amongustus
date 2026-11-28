package net.minecraft.client.particle;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityDropParticleFX extends EntityFX {
   private Material materialType;
   private int bobTimer;

   @Override
   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.materialType == Material.water) {
         this.particleRed = 0.2F;
         this.particleGreen = 0.3F;
         this.particleBlue = 1.0F;
      } else {
         this.particleRed = 1.0F;
         this.particleGreen = 16.0F / (float)(40 - this.bobTimer + 16);
         this.particleBlue = 4.0F / (float)(40 - this.bobTimer + 8);
      }

      this.motionY = this.motionY - (double)this.particleGravity;
      if (this.bobTimer-- > 0) {
         this.motionX *= 0.02;
         this.motionY *= 0.02;
         this.motionZ *= 0.02;
         this.setParticleTextureIndex(113);
      } else {
         this.setParticleTextureIndex(112);
      }

      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.98F;
      this.motionY *= 0.98F;
      this.motionZ *= 0.98F;
      if (this.particleMaxAge-- <= 0) {
         this.setDead();
      }

      if (this.onGround) {
         if (this.materialType == Material.water) {
            this.setDead();
            this.entityWorld.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0, new int[0]);
         } else {
            this.setParticleTextureIndex(114);
         }

         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }

      BlockPos pos = new BlockPos(this);
      IBlockState blockState = this.entityWorld.getBlockState(pos);
      Material material = blockState.getBlock().getMaterial();
      if (material.isLiquid() || material.isSolid()) {
         double d = 0.0;
         if (blockState.getBlock() instanceof BlockLiquid) {
            d = (double)BlockLiquid.getLiquidHeightPercent((Integer)blockState.getValue(BlockLiquid.LEVEL));
         }

         double d2 = (double)(MathHelper.floor_double(this.posY) + 1) - d;
         if (this.posY < d2) {
            this.setDead();
         }
      }
   }

   public EntityDropParticleFX(World world, double d, double d2, double d3, Material material) {
      super(world, d, d2, d3, 0.0, 0.0, 0.0);
      this.motionX = this.motionY = this.motionZ = 0.0;
      if (material == Material.water) {
         this.particleRed = 0.0F;
         this.particleGreen = 0.0F;
         this.particleBlue = 1.0F;
      } else {
         this.particleRed = 1.0F;
         this.particleGreen = 0.0F;
         this.particleBlue = 0.0F;
      }

      this.setParticleTextureIndex(113);
      this.setSize(0.01F, 0.01F);
      this.particleGravity = 0.06F;
      this.materialType = material;
      this.bobTimer = 40;
      this.particleMaxAge = (int)(64.0 / (Math.random() * 0.8 + 0.2));
      this.motionX = this.motionY = this.motionZ = 0.0;
   }

   @Override
   public float getBrightness(float f) {
      return this.materialType == Material.water ? super.getBrightness(f) : 1.0F;
   }

   @Override
   public int getBrightnessForRender(float f) {
      return this.materialType == Material.water ? super.getBrightnessForRender(f) : 257;
   }


   public static class LavaFactory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntityDropParticleFX(world, d, d2, d3, Material.lava);
      }
   }

   public static class WaterFactory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntityDropParticleFX(world, d, d2, d3, Material.water);
      }
   }
}
