package net.minecraft.entity;

import client.render.WorldRenderer;
import net.minecraft.world.World;

public class EntityPiglinAdapter extends TertiaryEntityAdapter {
   public float factor14;
   public double value28;
   public double value21;
   public double value26;

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
      float factor4 = factor3 * factor3 * factor3 * factor3;
      return factor2 * (1.0F - factor4) + factor4;
   }

   @Override
   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      float factor = ((float)this.count13 + posX) / (float)this.count14;
      float factor2 = 1.0F - factor;
      float factor3 = factor2 * factor2;
      float factor4 = 1.0F - factor3;
      this.factor12 = this.factor14 * factor4;
      super.applyWorldRendererByWorldRenderer(worldRenderer, entity, posX, posX2, posX3, posX4, posY, posZ);
   }

   @Override
   public void updateState() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      float factor = (float)this.count13 / (float)this.count14;
      float factor2 = -factor + factor * factor * 2.0F;
      float factor3 = 1.0F - factor2;
      this.posX = this.value28 + this.motionX * (double)factor3;
      this.posY = this.value21 + this.motionY * (double)factor3 + (double)(1.0F - factor3);
      this.posZ = this.value26 + this.motionZ * (double)factor3;
      if (this.count13++ >= this.count14) {
         this.updateState9();
      }
   }

   public EntityPiglinAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world, posX, posX2, posX3, posX4, posY, posZ);
      this.motionX = posX4;
      this.motionY = posY;
      this.motionZ = posZ;
      this.value28 = this.posX = posX;
      this.value21 = this.posY = posX2;
      this.value26 = this.posZ = posX3;
      float factor = this.random.nextFloat() * 0.6F + 0.4F;
      this.factor14 = this.factor12 = this.random.nextFloat() * 0.2F + 0.5F;
      this.factor8 = this.factor11 = this.factor5 = 1.0F * factor;
      this.factor11 *= 0.3F;
      this.factor8 *= 0.9F;
      this.count14 = (int)(Math.random() * 10.0) + 40;
      this.noClip = true;
      this.applyIndex5((int)(Math.random() * 8.0));
   }
}
