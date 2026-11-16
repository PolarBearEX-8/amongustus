package net.minecraft.entity;

import client.render.WorldRenderer;
import java.util.Random;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityHoglinAdapter extends TertiaryEntityAdapter {
   public static final Random random3 = new Random();
   public int count15 = 128;

   @Override
   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      MathHelper.clamp_float(((float)this.count13 + posX) / (float)this.count14 * 32.0F, 0.0F, 1.0F);
      super.applyWorldRendererByWorldRenderer(worldRenderer, entity, posX, posX2, posX3, posX4, posY, posZ);
   }

   public void setCount15(int count) {
      this.count15 = count;
   }

   @Override
   public void updateState() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.count13++ >= this.count14) {
         this.updateState9();
      }

      this.applyIndex5(this.count15 + (7 - this.count13 * 8 / this.count14));
      this.motionY += 0.004;
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

   public EntityHoglinAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world, posX, posX2, posX3, 0.5 - random3.nextDouble(), posY, 0.5 - random3.nextDouble());
      this.motionY *= 0.2F;
      if (posX4 == 0.0 && posZ == 0.0) {
         this.motionX *= 0.1F;
         this.motionZ *= 0.1F;
      }

      this.factor12 *= 0.75F;
      this.count14 = (int)(8.0 / (Math.random() * 0.8 + 0.2));
      this.noClip = false;
   }
}
