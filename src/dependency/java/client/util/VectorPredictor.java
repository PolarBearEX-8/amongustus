package client.util;

import client.event.PostMotionBroadcaster;
import net.minecraft.entity.EntityBreezeAdapter;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.world.World;

public class VectorPredictor implements PostMotionBroadcaster {
   @Override
   public TertiaryEntityAdapter resolveTertiaryEntityAdapter(
      int count, World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   ) {
      return new EntityBreezeAdapter(world, posX, posX2, posX3, (float)posX4, (float)posY, (float)posZ);
   }
}
