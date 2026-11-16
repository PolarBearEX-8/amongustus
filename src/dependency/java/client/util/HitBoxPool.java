package client.util;

import net.minecraft.entity.EntityArmadilloAdapter;
import net.minecraft.world.World;

public class HitBoxPool extends EntityArmadilloAdapter {
   public HitBoxPool(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world, posX, posX2, posX3);
      this.factor6 = 0.04F;
      this.updateState2();
      if (posY == 0.0 && (posX4 != 0.0 || posZ != 0.0)) {
         this.motionX = posX4;
         this.motionY = posY + 0.1;
         this.motionZ = posZ;
      }
   }
}
