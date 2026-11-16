package client.util;

import client.event.PostMotionBroadcaster;
import net.minecraft.entity.EntityHoglinAdapter;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.world.World;

public class VectorAdapter implements PostMotionBroadcaster {
   @Override
   public TertiaryEntityAdapter resolveTertiaryEntityAdapter(
      int count, World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   ) {
      EntityHoglinAdapter entityHoglinAdapter = new EntityHoglinAdapter(world, posX, posX2, posX3, posX4, posY, posZ);
      entityHoglinAdapter.setCount15(144);
      float factor = world.random.nextFloat() * 0.5F + 0.35F;
      entityHoglinAdapter.applyFloatByFloat(1.0F * factor, 0.0F * factor, 1.0F * factor);
      return entityHoglinAdapter;
   }
}
