package net.minecraft.block;

import client.event.EventBlockCollision;
import client.event.EventBusManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public interface BlockCollisionListener {
   EventBlockCollision eventBlockCollision = new EventBlockCollision();

   static EventBlockCollision resolveEventBlockCollision(AxisAlignedBB boundingBox, Block block, BlockPos blockPos) {
      EventBlockCollision.onBlockCollision(eventBlockCollision, boundingBox, block, blockPos);
      EventBusManager.getDietrichEvents2().post(3, eventBlockCollision);
      return eventBlockCollision;
   }

   void onBlockCollision(EventBlockCollision event);
}
