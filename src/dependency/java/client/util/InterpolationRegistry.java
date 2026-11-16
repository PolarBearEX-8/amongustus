package client.util;

import client.event.PostMotionBroadcaster;
import net.minecraft.entity.EntitySnifferAdapter;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.world.World;

public class InterpolationRegistry implements PostMotionBroadcaster {
   @Override
   public TertiaryEntityAdapter resolveTertiaryEntityAdapter(
      int count, World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   ) {
      EntitySnifferAdapter entitySnifferAdapter = new EntitySnifferAdapter(world, posX, posX2, posX3, posX4, posY, posZ);
      entitySnifferAdapter.applyFloatByFloat(entitySnifferAdapter.getFactor8() * 0.3F, entitySnifferAdapter.getFactor11() * 0.8F, entitySnifferAdapter.getFactor5());
      entitySnifferAdapter.updateState2();
      return entitySnifferAdapter;
   }
}
