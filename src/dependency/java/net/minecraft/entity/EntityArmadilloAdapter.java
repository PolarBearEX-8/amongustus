package net.minecraft.entity;

import client.util.IBlockState;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityArmadilloAdapter extends TertiaryEntityAdapter {
   @Override
   public void updateState() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.motionY = this.motionY - (double)this.factor6;
      this.applyDouble(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.98F;
      this.motionY *= 0.98F;
      this.motionZ *= 0.98F;
      if (this.count14-- <= 0) {
         this.updateState9();
      }

      if (this.onGround) {
         if (Math.random() < 0.5) {
            this.updateState9();
         }

         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }

      BlockPos blockPos = new BlockPos(this);
      IBlockState blockState = this.worldObj.getBlockState(blockPos);
      Block block = blockState.getBlock();
      block.processAction(this.worldObj, blockPos.getX(), blockPos.getY(), blockPos.getZ());
      Material material = blockState.getBlock().getMaterial();
      if (material.isActive2() || material.canExecute2()) {
         double offset;
         if (blockState.getBlock() instanceof BlockLiquid) {
            offset = (double)(1.0F - BlockLiquid.computeFactor((Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry)));
         } else {
            offset = block.getCoordOffset6();
         }

         double offset2 = (double)MathHelper.ceiling_double_int(this.posY) + offset;
         if (this.posY < offset2) {
            this.updateState9();
         }
      }
   }

   public EntityArmadilloAdapter(World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ, 0.0, 0.0, 0.0);
      this.motionX *= 0.3F;
      this.motionY = Math.random() * 0.2F + 0.1F;
      this.motionZ *= 0.3F;
      this.factor8 = 1.0F;
      this.factor11 = 1.0F;
      this.factor5 = 1.0F;
      this.applyIndex5(19 + this.random.nextInt(4));
      this.applyFloat(0.01F, 0.01F);
      this.factor6 = 0.06F;
      this.count14 = (int)(8.0 / (Math.random() * 0.8 + 0.2));
   }
}
