package net.minecraft.entity;

import client.render.WorldRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityZombieVillagerAdapter extends TertiaryEntityAdapter {
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
      this.motionY -= 0.03;
      this.applyDouble(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.99F;
      this.motionY *= 0.99F;
      this.motionZ *= 0.99F;
      if (this.onGround) {
         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }
   }

   public EntityZombieVillagerAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      this(world, posX, posX2, posX3, posX4, posY, posZ, 1.0F);
   }

   public EntityZombieVillagerAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, float factor) {
      super(world, posX, posX2, posX3, posX4, posY, posZ);
      this.motionX *= 0.1F;
      this.motionY *= 0.1F;
      this.motionZ *= 0.1F;
      this.motionX += posX4;
      this.motionY += posY;
      this.motionZ += posZ;
      this.factor8 = this.factor11 = this.factor5 = 1.0F - (float)(Math.random() * 0.3F);
      this.factor12 *= 0.75F;
      this.factor12 *= factor;
      this.factor14 = this.factor12;
      this.count14 = (int)(8.0 / (Math.random() * 0.8 + 0.2));
      this.count14 = (int)((float)this.count14 * factor);
      this.noClip = false;
   }
}
