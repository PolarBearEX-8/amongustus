package net.minecraft.entity;

import client.render.WorldRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAllayAdapter extends TertiaryEntityAdapter {
   public float factor14;

   @Override
   public int computeCount(float factor) {
      float factor2 = MathHelper.clamp_float(((float)this.count13 + factor) / (float)this.count14, 0.0F, 1.0F);
      int count = super.computeCount(factor);
      int count2 = count & 0xFF;
      int count3 = count >> 16 & 0xFF;
      int count4 = count2 + (int)(factor2 * 15.0F * 16.0F);
      if (count4 > 240) {
         count4 = 240;
      }

      return count4 | count3 << 16;
   }

   @Override
   public float computeFactor(float factor) {
      float factor2 = MathHelper.clamp_float(((float)this.count13 + factor) / (float)this.count14, 0.0F, 1.0F);
      return super.computeFactor(factor) * factor2 + (1.0F - factor2);
   }

   @Override
   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      float factor = ((float)this.count13 + posX) / (float)this.count14;
      this.factor12 = this.factor14 * (1.0F - factor * factor * 0.5F);
      super.applyWorldRendererByWorldRenderer(worldRenderer, entity, posX, posX2, posX3, posX4, posY, posZ);
   }

   @Override
   public void updateState() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.count13++ >= this.count14) {
         this.updateState9();
      }

      this.applyDouble(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.96F;
      this.motionY *= 0.96F;
      this.motionZ *= 0.96F;
      if (this.onGround) {
         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }
   }

   public EntityAllayAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world, posX, posX2, posX3, posX4, posY, posZ);
      this.motionX = this.motionX * 0.01F + posX4;
      this.motionY = this.motionY * 0.01F + posY;
      this.motionZ = this.motionZ * 0.01F + posZ;
      this.posX = this.posX + (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.05F);
      this.posY = this.posY + (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.05F);
      this.posZ = this.posZ + (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.05F);
      this.factor14 = this.factor12;
      this.factor8 = this.factor11 = this.factor5 = 1.0F;
      this.count14 = (int)(8.0 / (Math.random() * 0.8 + 0.2)) + 4;
      this.noClip = true;
      this.applyIndex5(48);
   }
}
