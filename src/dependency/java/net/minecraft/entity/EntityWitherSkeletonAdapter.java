package net.minecraft.entity;

import net.minecraft.world.World;

public class EntityWitherSkeletonAdapter extends TertiaryEntityAdapter {
   @Override
   public void updateState() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.count13++ >= this.count14) {
         this.updateState9();
      }

      this.applyIndex5(7 - this.count13 * 8 / this.count14);
      this.motionY += 0.004;
      this.applyDouble(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.9F;
      this.motionY *= 0.9F;
      this.motionZ *= 0.9F;
      if (this.onGround) {
         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }
   }

   public EntityWitherSkeletonAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world, posX, posX2, posX3, posX4, posY, posZ);
      this.motionX = posX4 + (Math.random() * 2.0 - 1.0) * 0.05F;
      this.motionY = posY + (Math.random() * 2.0 - 1.0) * 0.05F;
      this.motionZ = posZ + (Math.random() * 2.0 - 1.0) * 0.05F;
      this.factor8 = this.factor11 = this.factor5 = this.random.nextFloat() * 0.3F + 0.7F;
      this.factor12 = this.random.nextFloat() * this.random.nextFloat() * 6.0F + 1.0F;
      this.count14 = (int)(16.0 / ((double)this.random.nextFloat() * 0.8 + 0.2)) + 2;
   }
}
