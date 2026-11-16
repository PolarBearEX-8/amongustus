package client.util;

import client.event.PostMotionBroadcaster;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityZoglinAdapter;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.world.World;

public class VirtualClientManager implements PostMotionBroadcaster {
   @Override
   public TertiaryEntityAdapter resolveTertiaryEntityAdapter(
      int count, World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   ) {
      return new EntityZoglinAdapter(world, posX, posX2, posX3, Material.coreItemEntry1);
   }
}
