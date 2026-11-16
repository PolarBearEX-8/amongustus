package client.util;

import client.event.PostMotionBroadcaster;
import net.minecraft.entity.EntityHoglinAdapter;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.world.World;

public class InterpolationProvider implements PostMotionBroadcaster {
   @Override
   public TertiaryEntityAdapter resolveTertiaryEntityAdapter(
      int count, World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   ) {
      EntityHoglinAdapter entityHoglinAdapter = new EntityHoglinAdapter(world, posX, posX2, posX3, posX4, posY, posZ);
      entityHoglinAdapter.applyFloatByFloat((float)posX4, (float)posY, (float)posZ);
      return entityHoglinAdapter;
   }
}
