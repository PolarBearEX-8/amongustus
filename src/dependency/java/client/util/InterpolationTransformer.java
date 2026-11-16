package client.util;

import client.event.PostMotionBroadcaster;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityBoggedAdapter;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.world.World;

public class InterpolationTransformer implements PostMotionBroadcaster {
   @Override
   public TertiaryEntityAdapter resolveTertiaryEntityAdapter(
      int count, World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   ) {
      return new EntityBoggedAdapter(world, posX, posX2, posX3, posX4, posY, posZ, Block.processAction2(countArray[0])).getEntityBoggedAdapter();
   }
}
