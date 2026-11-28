package net.augustus.events;

import net.augustus.events.listeners.BlockBoundingBoxListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class EventBlockBoundingBox extends AbstractEvent<BlockBoundingBoxListener> {
   public static final int ID = 3;
   private BlockPos blockPos;
   private Block block;
   private AxisAlignedBB boundingBox;

   public AxisAlignedBB getBoundingBox() {
      return this.boundingBox;
   }

   public void setBlock(Block block2) {
      this.block = block2;
   }

   public void setBlockPos(BlockPos pos) {
      this.blockPos = pos;
   }

   public EventBlockBoundingBox(AxisAlignedBB box, Block block2, BlockPos pos) {
      this.boundingBox = box;
      this.block = block2;
      this.blockPos = pos;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }


   private void reset(AxisAlignedBB box, Block block2, BlockPos pos) {
      this.boundingBox = box;
      this.block = block2;
      this.blockPos = pos;
   }

   public EventBlockBoundingBox() {
   }

   public void setBoundingBox(AxisAlignedBB box) {
      this.boundingBox = box;
   }

   @Override
   public void call(BlockBoundingBoxListener listener) {
      listener.onBlockBoundingBox(this);
   }

   public Block getBlock() {
      return this.block;
   }
}
