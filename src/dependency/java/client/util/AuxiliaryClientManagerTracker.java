package client.util;

import client.event.HandlerListener;
import client.event.PreMotionListener;
import net.minecraft.entity.EntityArrow;
import net.minecraft.world.World;

public final class AuxiliaryClientManagerTracker extends CurveCoordinator {
   @Override
   public PreMotionListener resolvePreMotionListener(World world, HandlerListener handlerListener) {
      EntityArrow entityArrow = new EntityArrow(world, handlerListener.getValue(), handlerListener.getValue2(), handlerListener.getValue3());
      entityArrow.count12 = 1;
      return entityArrow;
   }
}
