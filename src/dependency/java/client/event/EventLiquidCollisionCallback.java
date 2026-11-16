package client.event;

public interface EventLiquidCollisionCallback {
   EventLiquidCollision eventLiquidCollision = new EventLiquidCollision();

   void onLiquidCollision(EventLiquidCollision event);

   static void applyPreMotionConsumer(PreMotionConsumer preMotionConsumer, double posX, double posY, double posZ, float factor, boolean flag) {
      eventLiquidCollision.applyPreMotionConsumer(preMotionConsumer, posX, posY, posZ, factor, flag);
      EventBusManager.getDietrichEvents2().post(57, eventLiquidCollision);
   }
}
