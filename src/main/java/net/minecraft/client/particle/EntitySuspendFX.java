package net.minecraft.client.particle;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntitySuspendFX extends EntityFX {
   @Override
   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      if (this.entityWorld.getBlockState(new BlockPos(this)).getBlock().getMaterial() != Material.water) {
         this.setDead();
      }

      if (this.particleMaxAge-- <= 0) {
         this.setDead();
      }
   }

   public EntitySuspendFX(World world, double d, double d2, double d3, double d4, double d5, double d6) {
      super(world, d, d2 - 0.125, d3, d4, d5, d6);
      this.particleRed = 0.4F;
      this.particleGreen = 0.4F;
      this.particleBlue = 0.7F;
      this.setParticleTextureIndex(0);
      this.setSize(0.01F, 0.01F);
      this.particleScale = this.particleScale * (this.rand.nextFloat() * 0.6F + 0.2F);
      this.motionX = d4 * 0.0;
      this.motionY = d5 * 0.0;
      this.motionZ = d6 * 0.0;
      this.particleMaxAge = (int)(16.0 / (Math.random() * 0.8 + 0.2));
   }


   public static class Factory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntitySuspendFX(world, d, d2, d3, d4, d5, d6);
      }
   }
}
