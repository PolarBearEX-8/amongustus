package net.minecraft.entity;

import client.render.WorldRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityStrayAdapter extends TertiaryEntityAdapter {
   public float factor14;

   @Override
   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      float factor = MathHelper.clamp_float(((float)this.count13 + posX) / (float)this.count14 * 32.0F, 0.0F, 1.0F);
      this.factor12 = this.factor14 * factor;
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
      if (this.posY == this.prevPosY) {
         this.motionX *= 1.1;
         this.motionZ *= 1.1;
      }

      this.motionX *= 0.66F;
      this.motionY *= 0.66F;
      this.motionZ *= 0.66F;
      if (this.onGround) {
         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }
   }

   public EntityStrayAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, float factor) {
      super(world, posX, posX2, posX3, 0.0, 0.0, 0.0);
      this.motionX *= 0.01F;
      this.motionY *= 0.01F;
      this.motionZ *= 0.01F;
      this.motionY += 0.2;
      this.factor8 = MathHelper.abs(((float)posX4 + 0.0F) * (float) Math.PI * 2.0F) * 0.65F + 0.35F;
      this.factor11 = MathHelper.abs(((float)posX4 + 0.33333334F) * (float) Math.PI * 2.0F) * 0.65F + 0.35F;
      this.factor5 = MathHelper.abs(((float)posX4 + 0.6666667F) * (float) Math.PI * 2.0F) * 0.65F + 0.35F;
      this.factor12 *= 0.75F;
      this.factor12 *= factor;
      this.factor14 = this.factor12;
      this.count14 = 6;
      this.noClip = false;
      this.applyIndex5(64);
   }

   public EntityStrayAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      this(world, posX, posX2, posX3, posX4, posY, posZ, 2.0F);
   }
}
