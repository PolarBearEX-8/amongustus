package net.minecraft.entity;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityFlying extends EntityLiving {
   @Override
   public void fall(float f, float f2) {
   }

   @Override
   public void moveEntityWithHeading(float f, float f2) {
      if (this.isInWater()) {
         this.moveFlying(f, f2, 0.02F);
         this.moveEntity(this.motionX, this.motionY, this.motionZ);
         this.motionX *= 0.8F;
         this.motionY *= 0.8F;
         this.motionZ *= 0.8F;
      } else if (this.isInLava()) {
         this.moveFlying(f, f2, 0.02F);
         this.moveEntity(this.motionX, this.motionY, this.motionZ);
         this.motionX *= 0.5;
         this.motionY *= 0.5;
         this.motionZ *= 0.5;
      } else {
         float f3 = 0.91F;
         if (this.onGround) {
            f3 = this.entityWorld
                  .getBlockState(
                     new BlockPos(
                        MathHelper.floor_double(this.posX), MathHelper.floor_double(this.getEntityBoundingBox().minY) - 1, MathHelper.floor_double(this.posZ)
                     )
                  )
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f4 = 0.16277136F / (f3 * f3 * f3);
         this.moveFlying(f, f2, this.onGround ? 0.1F * f4 : 0.02F);
         f3 = 0.91F;
         if (this.onGround) {
            f3 = this.entityWorld
                  .getBlockState(
                     new BlockPos(
                        MathHelper.floor_double(this.posX), MathHelper.floor_double(this.getEntityBoundingBox().minY) - 1, MathHelper.floor_double(this.posZ)
                     )
                  )
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         this.moveEntity(this.motionX, this.motionY, this.motionZ);
         this.motionX *= (double)f3;
         this.motionY *= (double)f3;
         this.motionZ *= (double)f3;
      }

      this.prevLimbSwingAmount = this.limbSwingAmount;
      double d = this.posX - this.prevPosX;
      double d2 = this.posZ - this.prevPosZ;
      float f5 = MathHelper.sqrt_double(d * d + d2 * d2) * 4.0F;
      if (f5 > 1.0F) {
         f5 = 1.0F;
      }

      this.limbSwingAmount = this.limbSwingAmount + (f5 - this.limbSwingAmount) * 0.4F;
      this.limbSwing = this.limbSwing + this.limbSwingAmount;
   }

   @Override
   public void updateFallState(double d, boolean flag, Block block, BlockPos pos) {
   }

   public EntityFlying(World world) {
      super(world);
   }

   @Override
   public boolean isOnLadder() {
      return false;
   }
}
