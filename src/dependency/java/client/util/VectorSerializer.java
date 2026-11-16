package client.util;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityMob;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class VectorSerializer extends EntityMob {
   @Override
   public void processFloat2(float factor, float factor2) {
   }

   @Override
   public void applyFloat2(float factor, float factor2) {
      if (this.isInWater()) {
         this.applyFloat(factor, factor2, 0.02F);
         this.applyDouble(this.motionX, this.motionY, this.motionZ);
         this.motionX *= 0.8F;
         this.motionY *= 0.8F;
         this.motionZ *= 0.8F;
      } else if (this.isWet()) {
         this.applyFloat(factor, factor2, 0.02F);
         this.applyDouble(this.motionX, this.motionY, this.motionZ);
         this.motionX *= 0.5;
         this.motionY *= 0.5;
         this.motionZ *= 0.5;
      } else {
         float factor3 = 0.91F;
         if (this.onGround) {
            factor3 = this.worldObj
                  .getBlockState(
                     new BlockPos(
                        MathHelper.ceiling_double_int(this.posX),
                        MathHelper.ceiling_double_int(this.getBoundingBox2().minY) - 1,
                        MathHelper.ceiling_double_int(this.posZ)
                     )
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         float factor4 = 0.16277136F / (factor3 * factor3 * factor3);
         this.applyFloat(factor, factor2, this.onGround ? 0.1F * factor4 : 0.02F);
         float factor5 = 0.91F;
         if (this.onGround) {
            factor5 = this.worldObj
                  .getBlockState(
                     new BlockPos(
                        MathHelper.ceiling_double_int(this.posX),
                        MathHelper.ceiling_double_int(this.getBoundingBox2().minY) - 1,
                        MathHelper.ceiling_double_int(this.posZ)
                     )
                  )
                  .getBlock()
                  .scaleFactor
               * 0.91F;
         }

         this.applyDouble(this.motionX, this.motionY, this.motionZ);
         this.motionX *= (double)factor5;
         this.motionY *= (double)factor5;
         this.motionZ *= (double)factor5;
      }

      this.factor9 = this.factor11;
      double offset = this.posX - this.prevPosX;
      double offset2 = this.posZ - this.prevPosZ;
      float factor6 = MathHelper.sqrt_double(offset * offset + offset2 * offset2) * 4.0F;
      if (factor6 > 1.0F) {
         factor6 = 1.0F;
      }

      this.factor11 = this.factor11 + (factor6 - this.factor11) * 0.4F;
      this.factor14 = this.factor14 + this.factor11;
   }

   @Override
   public void applyBlock(double offset, boolean flag, Block block, BlockPos blockPos) {
   }

   @Override
   public boolean canExecute9() {
      return false;
   }

   public VectorSerializer(World world) {
      super(world);
   }
}
