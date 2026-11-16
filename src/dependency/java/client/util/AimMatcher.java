package client.util;

import client.event.PostMotionBroadcaster;
import net.minecraft.block.Block;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.world.World;

public class AimMatcher implements PostMotionBroadcaster {
   @Override
   public TertiaryEntityAdapter resolveTertiaryEntityAdapter(
      int count, World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   ) {
      IBlockState blockState = Block.processAction2(countArray[0]);
      return blockState.getBlock().processAction13() == -1 ? null : new HitBoxQueue(world, posX, posX2, posX3, posX4, posY, posZ, blockState).getEntityBoggedAdapter();
   }
}
