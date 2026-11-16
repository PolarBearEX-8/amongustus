package client.event;

import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.world.World;

public interface PostMotionBroadcaster {
   TertiaryEntityAdapter resolveTertiaryEntityAdapter(
      int count, World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   );
}
