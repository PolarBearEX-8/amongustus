package client.util;

import client.event.PostMotionBroadcaster;
import net.minecraft.entity.EntityFrogAdapter;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.world.World;

public class TertiaryListenerHandler implements PostMotionBroadcaster {
   @Override
   public TertiaryEntityAdapter resolveTertiaryEntityAdapter(
      int count, World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   ) {
      EntityFrogAdapter entityFrogAdapter = new EntityFrogAdapter(world, posX, posX2 + 0.5, posX3, posX4, posY, posZ);
      entityFrogAdapter.applyIndex5(81);
      entityFrogAdapter.applyFloatByFloat(1.0F, 1.0F, 1.0F);
      return entityFrogAdapter;
   }
}
