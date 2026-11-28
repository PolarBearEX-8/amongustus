package net.minecraft.block;

import net.minecraft.util.BlockPos;

public class BlockEventData {
   private BlockPos position;
   private Block blockType;
   private int eventID;
   private int eventParameter;

   public Block getBlock() {
      return this.blockType;
   }

   public BlockPos getPosition() {
      return this.position;
   }

   public BlockEventData(BlockPos pos, Block block, int n, int n2) {
      this.position = pos;
      this.eventID = n;
      this.eventParameter = n2;
      this.blockType = block;
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof BlockEventData)) {
         return false;
      } else {
         BlockEventData blockEventData = (BlockEventData)obj;
         return this.position.equals(blockEventData.position)
            && this.eventID == blockEventData.eventID
            && this.eventParameter == blockEventData.eventParameter
            && this.blockType == blockEventData.blockType;
      }
   }

   @Override
   public String toString() {
      return "TE(" + this.position + ")," + this.eventID + "," + this.eventParameter + "," + this.blockType;
   }

   public int getEventParameter() {
      return this.eventParameter;
   }

   public int getEventID() {
      return this.eventID;
   }
}
