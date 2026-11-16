package client.util;

import client.event.HandlerListener;
import client.event.PreMotionListener;
import net.minecraft.entity.EntityThrownegg;
import net.minecraft.world.World;

public final class InternalClientManagerProcessor extends CurveCoordinator {
   @Override
   public PreMotionListener resolvePreMotionListener(World world, HandlerListener handlerListener) {
      return new EntityThrownegg(world, handlerListener.getValue(), handlerListener.getValue2(), handlerListener.getValue3());
   }
}
