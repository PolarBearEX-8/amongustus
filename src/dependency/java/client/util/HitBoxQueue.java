package client.util;

import net.minecraft.entity.EntityBoggedAdapter;
import net.minecraft.world.World;

public class HitBoxQueue extends EntityBoggedAdapter {
   public HitBoxQueue(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, IBlockState blockState) {
      super(world, posX, posX2, posX3, posX4, posY, posZ, blockState);
      this.motionX = posX4;
      this.motionY = posY;
      this.motionZ = posZ;
   }
}
