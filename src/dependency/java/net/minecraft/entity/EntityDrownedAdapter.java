package net.minecraft.entity;

import net.minecraft.world.World;

public class EntityDrownedAdapter extends TertiaryEntityAdapter {
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
      int count = 60 - this.count14;
      float factor = (float)count * 0.001F;
      this.applyFloat(factor, factor);
      this.applyIndex5(19 + count % 4);
      if (this.count14-- <= 0) {
         this.updateState9();
      }
   }

   public EntityDrownedAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world, posX, posX2, posX3, 0.0, 0.0, 0.0);
      this.motionX *= 0.3F;
      this.motionY = Math.random() * 0.2F + 0.1F;
      this.motionZ *= 0.3F;
      this.factor8 = 1.0F;
      this.factor11 = 1.0F;
      this.factor5 = 1.0F;
      this.applyIndex5(19);
      this.applyFloat(0.01F, 0.01F);
      this.count14 = (int)(8.0 / (Math.random() * 0.8 + 0.2));
      this.factor6 = 0.0F;
      this.motionX = posX4;
      this.motionY = posY;
      this.motionZ = posZ;
   }
}
