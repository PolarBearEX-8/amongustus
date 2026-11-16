package client.util;

import client.event.PostMotionBroadcaster;
import net.minecraft.entity.EntityBeeAdapter;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class InterpolationWriter implements PostMotionBroadcaster {
   @Override
   public TertiaryEntityAdapter resolveTertiaryEntityAdapter(
      int count, World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   ) {
      return new EntityBeeAdapter(world, posX, posX2, posX3, Items.snowball);
   }
}
