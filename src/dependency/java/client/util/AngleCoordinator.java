package client.util;

import client.event.PostMotionBroadcaster;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityPolarBearAdapter;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class AngleCoordinator implements PostMotionBroadcaster {
   @Override
   public TertiaryEntityAdapter resolveTertiaryEntityAdapter(
      int count, World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   ) {
      return new EntityPolarBearAdapter(world, posX, posX2, posX3, Item.processAction2(Blocks.block145));
   }
}
