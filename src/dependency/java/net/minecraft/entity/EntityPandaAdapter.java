package net.minecraft.entity;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityPandaAdapter extends TertiaryEntityAdapter {
   @Override
   public void updateState() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.motionY += 0.002;
      this.applyDouble(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.85F;
      this.motionY *= 0.85F;
      this.motionZ *= 0.85F;
      if (this.worldObj.getBlockState(new BlockPos(this)).getBlock().getMaterial() != Material.coreItemEntry1) {
         this.updateState9();
      }

      if (this.count14-- <= 0) {
         this.updateState9();
      }
   }

   public EntityPandaAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world, posX, posX2, posX3, posX4, posY, posZ);
      this.factor8 = 1.0F;
      this.factor11 = 1.0F;
      this.factor5 = 1.0F;
      this.applyIndex5(32);
      this.applyFloat(0.02F, 0.02F);
      this.factor12 = this.factor12 * (this.random.nextFloat() * 0.6F + 0.2F);
      this.motionX = posX4 * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
      this.motionY = posY * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
      this.motionZ = posZ * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
      this.count14 = (int)(8.0 / (Math.random() * 0.8 + 0.2));
   }
}
