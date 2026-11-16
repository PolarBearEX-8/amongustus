package net.minecraft.entity;

import net.minecraft.world.World;

public class EntityStriderAdapter extends TertiaryEntityAdapter {
   public float factor14;
   public double value28;
   public double value26;
   public double value21;

   @Override
   public int computeCount(float factor) {
      int count = super.computeCount(factor);
      float factor2 = (float)this.count13 / (float)this.count14;
      float factor3 = factor2 * factor2;
      float factor4 = factor3 * factor3;
      int count2 = count & 0xFF;
      int count3 = (count >> 16 & 0xFF) + (int)(factor4 * 15.0F * 16.0F);
      if (count3 > 240) {
         count3 = 240;
      }

      return count2 | count3 << 16;
   }

   @Override
   public float computeFactor(float factor) {
      float factor2 = super.computeFactor(factor);
      float factor3 = (float)this.count13 / (float)this.count14;
      float factor4 = factor3 * factor3;
      float factor5 = factor4 * factor4;
      return factor2 * (1.0F - factor5) + factor5;
   }

   @Override
   public void updateState() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      float factor = (float)this.count13 / (float)this.count14;
      float factor2 = 1.0F - factor;
      float factor3 = 1.0F - factor2;
      float factor4 = factor3 * factor3;
      float factor5 = factor4 * factor4;
      this.posX = this.value28 + this.motionX * (double)factor2;
      this.posY = this.value26 + this.motionY * (double)factor2 - (double)(factor5 * 1.2F);
      this.posZ = this.value21 + this.motionZ * (double)factor2;
      if (this.count13++ >= this.count14) {
         this.updateState9();
      }
   }

   public EntityStriderAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world, posX, posX2, posX3, posX4, posY, posZ);
      this.motionX = posX4;
      this.motionY = posY;
      this.motionZ = posZ;
      this.value28 = posX;
      this.value26 = posX2;
      this.value21 = posX3;
      this.posX = this.prevPosX = posX + posX4;
      this.posY = this.prevPosY = posX2 + posY;
      this.posZ = this.prevPosZ = posX3 + posZ;
      float factor = this.random.nextFloat() * 0.6F + 0.4F;
      this.factor14 = this.factor12 = this.random.nextFloat() * 0.5F + 0.2F;
      this.factor8 = this.factor11 = this.factor5 = 1.0F * factor;
      this.factor11 *= 0.9F;
      this.factor8 *= 0.9F;
      this.count14 = (int)(Math.random() * 10.0) + 30;
      this.noClip = true;
      this.applyIndex5((int)(Math.random() * 26.0 + 1.0 + 224.0));
   }
}
