package net.minecraft.entity;

import client.render.WorldRenderer;
import client.setting.AttackEffectsExplosionNormalType;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityVindicatorAdapter extends TertiaryEntityAdapter {
   public float factor14;

   @Override
   public int computeCount(float factor) {
      MathHelper.clamp_float(((float)this.count13 + factor) / (float)this.count14, 0.0F, 1.0F);
      int count = super.computeCount(factor) >> 16 & 0xFF;
      return 240 | count << 16;
   }

   @Override
   public float computeFactor(float factor) {
      return 1.0F;
   }

   @Override
   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      float factor = ((float)this.count13 + posX) / (float)this.count14;
      this.factor12 = this.factor14 * (1.0F - factor * factor);
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

      float factor = (float)this.count13 / (float)this.count14;
      if (this.random.nextFloat() > factor) {
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.SMOKE_NORMAL, this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ, new int[0]
            );
      }

      this.motionY -= 0.03;
      this.applyDouble(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.999F;
      this.motionY *= 0.999F;
      this.motionZ *= 0.999F;
      if (this.onGround) {
         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }
   }

   public EntityVindicatorAdapter(World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ, 0.0, 0.0, 0.0);
      this.motionX *= 0.8F;
      this.motionY *= 0.8F;
      this.motionZ *= 0.8F;
      this.motionY = (double)(this.random.nextFloat() * 0.4F + 0.05F);
      this.factor8 = this.factor11 = this.factor5 = 1.0F;
      this.factor12 = this.factor12 * (this.random.nextFloat() * 2.0F + 0.2F);
      this.factor14 = this.factor12;
      this.count14 = (int)(16.0 / (Math.random() * 0.8 + 0.2));
      this.noClip = false;
      this.applyIndex5(49);
   }
}
