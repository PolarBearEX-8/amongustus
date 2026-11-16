package client.util;

import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.world.World;

public class GeometryRegistry extends TertiaryEntityAdapter {
   @Override
   public void updateState() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.applyDouble(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.99;
      this.motionY *= 0.99;
      this.motionZ *= 0.99;
      if (this.count14-- <= 0) {
         this.updateState9();
      }
   }

   public GeometryRegistry(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world, posX, posX2, posX3, posX4, posY, posZ);
      float factor = this.random.nextFloat() * 0.1F + 0.2F;
      this.factor8 = factor;
      this.factor11 = factor;
      this.factor5 = factor;
      this.applyIndex5(0);
      this.applyFloat(0.02F, 0.02F);
      this.factor12 = this.factor12 * (this.random.nextFloat() * 0.6F + 0.5F);
      this.motionX *= 0.02F;
      this.motionY *= 0.02F;
      this.motionZ *= 0.02F;
      this.count14 = (int)(20.0 / (Math.random() * 0.8 + 0.2));
      this.noClip = true;
   }
}
