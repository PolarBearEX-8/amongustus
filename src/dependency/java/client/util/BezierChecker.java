package client.util;

import client.event.PostMotionBroadcaster;
import net.minecraft.entity.EntityArmadilloAdapter;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.world.World;

public class BezierChecker implements PostMotionBroadcaster {
   @Override
   public TertiaryEntityAdapter resolveTertiaryEntityAdapter(
      int count, World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   ) {
      return new EntityArmadilloAdapter(world, posX, posX2, posX3);
   }
}
