package client.util;

import client.event.HandlerListener;
import client.event.PreMotionListener;
import net.minecraft.entity.EntityThrownexpbottle;
import net.minecraft.world.World;

public final class ClientManagerWrapper extends CurveCoordinator {
   @Override
   public PreMotionListener resolvePreMotionListener(World world, HandlerListener handlerListener) {
      return new EntityThrownexpbottle(world, handlerListener.getValue(), handlerListener.getValue2(), handlerListener.getValue3());
   }

   @Override
   public float getFactor() {
      return super.getFactor() * 0.5F;
   }

   @Override
   public float getFactorAsFloat() {
      return super.getFactorAsFloat() * 1.25F;
   }
}
