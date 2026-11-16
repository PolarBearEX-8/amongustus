package client.util;

import client.event.HandlerListener;
import client.event.PreMotionListener;
import net.minecraft.entity.EntitySnowball;
import net.minecraft.world.World;

public final class CurveDispatcher extends CurveCoordinator {
   @Override
   public PreMotionListener resolvePreMotionListener(World world, HandlerListener handlerListener) {
      return new EntitySnowball(world, handlerListener.getValue(), handlerListener.getValue2(), handlerListener.getValue3());
   }
}
