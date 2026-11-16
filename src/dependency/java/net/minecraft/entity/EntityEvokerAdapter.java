package net.minecraft.entity;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityEvokerAdapter extends TertiaryEntityAdapter {
   @Override
   public void updateState() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.applyDouble(this.motionX, this.motionY, this.motionZ);
      if (this.worldObj.getBlockState(new BlockPos(this)).getBlock().getMaterial() != Material.coreItemEntry1) {
         this.updateState9();
      }

      if (this.count14-- <= 0) {
         this.updateState9();
      }
   }

   public EntityEvokerAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world, posX, posX2 - 0.125, posX3, posX4, posY, posZ);
      this.factor8 = 0.4F;
      this.factor11 = 0.4F;
      this.factor5 = 0.7F;
      this.applyIndex5(0);
      this.applyFloat(0.01F, 0.01F);
      this.factor12 = this.factor12 * (this.random.nextFloat() * 0.6F + 0.2F);
      this.motionX = posX4 * 0.0;
      this.motionY = posY * 0.0;
      this.motionZ = posZ * 0.0;
      this.count14 = (int)(16.0 / (Math.random() * 0.8 + 0.2));
   }
}
