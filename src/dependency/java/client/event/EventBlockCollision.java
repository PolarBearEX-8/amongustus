package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCollisionListener;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class EventBlockCollision extends AbstractEvent {
   public static final int count2 = 3;
   public Block block;
   public AxisAlignedBB axisAlignedBB;
   public BlockPos blockPos;

   public void applyBlockCollisionListener(BlockCollisionListener blockCollisionListener) {
      blockCollisionListener.onBlockCollision(this);
   }

   public void setBoundingBox(AxisAlignedBB boundingBox) {
      this.axisAlignedBB = boundingBox;
   }

   public void call(Object entry) {
      BlockCollisionListener blockCollisionListener = (BlockCollisionListener)entry;
      this.applyBlockCollisionListener(blockCollisionListener);
   }

   public void setBlock(Block block2) {
      this.block = block2;
   }

   public AxisAlignedBB getAxisAlignedBB() {
      return this.axisAlignedBB;
   }

   private void applyAxisAlignedBB(AxisAlignedBB boundingBox, Block block2, BlockPos blockPos2) {
      this.axisAlignedBB = boundingBox;
      this.block = block2;
      this.blockPos = blockPos2;
   }

   public void setBlockPos(BlockPos blockPos2) {
      this.blockPos = blockPos2;
   }

   public Block getBlock() {
      return this.block;
   }

   public static void onBlockCollision(EventBlockCollision event, AxisAlignedBB boundingBox, Block block2, BlockPos blockPos2) {
      event.applyAxisAlignedBB(boundingBox, block2, blockPos2);
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }
}
