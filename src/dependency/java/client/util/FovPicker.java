package client.util;

import client.event.PostMotionBroadcaster;
import net.minecraft.entity.EntityWardenAdapter;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.world.World;

public class FovPicker implements PostMotionBroadcaster {
   @Override
   public TertiaryEntityAdapter resolveTertiaryEntityAdapter(
      int count, World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   ) {
      return new EntityWardenAdapter(world, posX, posX2, posX3, posX4, posY, posZ);
   }
}
