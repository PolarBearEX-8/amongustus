package net.minecraft.entity;

import client.render.WorldRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySnifferAdapter extends TertiaryEntityAdapter {
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
      this.factor11 = (float)((double)this.factor11 * 0.96);
      this.factor5 = (float)((double)this.factor5 * 0.9);
      this.motionX *= 0.7F;
      this.motionY *= 0.7F;
      this.motionZ *= 0.7F;
      this.motionY -= 0.02F;
      if (this.onGround) {
         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }
   }

   public EntitySnifferAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, float factor) {
      super(world, posX, posX2, posX3, 0.0, 0.0, 0.0);
      this.motionX *= 0.1F;
      this.motionY *= 0.1F;
      this.motionZ *= 0.1F;
      this.motionX += posX4 * 0.4;
      this.motionY += posY * 0.4;
      this.motionZ += posZ * 0.4;
      this.factor8 = this.factor11 = this.factor5 = (float)(Math.random() * 0.3F + 0.6F);
      this.factor12 *= 0.75F;
      this.factor12 *= factor;
      this.factor14 = this.factor12;
      this.count14 = (int)(6.0 / (Math.random() * 0.8 + 0.6));
      this.count14 = (int)((float)this.count14 * factor);
      this.noClip = false;
      this.applyIndex5(65);
      this.updateState();
   }

   public EntitySnifferAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      this(world, posX, posX2, posX3, posX4, posY, posZ, 1.0F);
   }
}
