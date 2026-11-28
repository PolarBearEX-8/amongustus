package net.augustus.events.listeners;

import net.augustus.events.EventBlockBoundingBox;
import net.augustus.events.EventManager;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public interface BlockBoundingBoxListener {
   EventBlockBoundingBox BLOCK_BOUNDING_BOX_EVENT = new EventBlockBoundingBox();

   void onBlockBoundingBox(EventBlockBoundingBox event);

   static EventBlockBoundingBox onBlockBoundingBox(AxisAlignedBB box, Block block, BlockPos pos) {
      EventBlockBoundingBox event = new EventBlockBoundingBox(box, block, pos);
      EventManager.getEventBus().post(3, event);
      return event;
   }
}
