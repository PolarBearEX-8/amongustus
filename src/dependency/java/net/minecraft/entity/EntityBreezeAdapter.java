package net.minecraft.entity;

import client.render.WorldRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBreezeAdapter extends TertiaryEntityAdapter {
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

      this.applyIndex5(7 - this.count13 * 8 / this.count14);
      this.applyDouble(this.motionX, this.motionY, this.motionZ);
      if (this.posY == this.prevPosY) {
         this.motionX *= 1.1;
         this.motionZ *= 1.1;
      }

      this.motionX *= 0.96F;
      this.motionY *= 0.96F;
      this.motionZ *= 0.96F;
      if (this.onGround) {
         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }
   }

   public EntityBreezeAdapter(World world, double posX, double posY, double posZ, float posX2, float posX3, float posY2, float posZ2) {
      super(world, posX, posY, posZ, 0.0, 0.0, 0.0);
      this.motionX *= 0.1F;
      this.motionY *= 0.1F;
      this.motionZ *= 0.1F;
      if (posX3 == 0.0F) {
         posX3 = 1.0F;
      }

      float factor = (float)Math.random() * 0.4F + 0.6F;
      this.factor8 = ((float)(Math.random() * 0.2F) + 0.8F) * posX3 * factor;
      this.factor11 = ((float)(Math.random() * 0.2F) + 0.8F) * posY2 * factor;
      this.factor5 = ((float)(Math.random() * 0.2F) + 0.8F) * posZ2 * factor;
      this.factor12 *= 0.75F;
      this.factor12 *= posX2;
      this.factor14 = this.factor12;
      this.count14 = (int)(8.0 / (Math.random() * 0.8 + 0.2));
      this.count14 = (int)((float)this.count14 * posX2);
      this.noClip = false;
   }

   public EntityBreezeAdapter(World world, double posX, double posY, double posZ, float posX2, float posY2, float posZ2) {
      this(world, posX, posY, posZ, 1.0F, posX2, posY2, posZ2);
   }
}
