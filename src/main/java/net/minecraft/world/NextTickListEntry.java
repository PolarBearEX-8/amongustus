package net.minecraft.world;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;

public class NextTickListEntry implements Comparable {
   private static long nextTickEntryID;
   private final Block block;
   public final BlockPos position;
   public long scheduledTime;
   public int priority;
   private long tickEntryID;

   @Override
   public String toString() {
      return Block.getIdFromBlock(this.block) + ": " + this.position + ", " + this.scheduledTime + ", " + this.priority + ", " + this.tickEntryID;
   }

   @Override
   public int hashCode() {
      return this.position.hashCode();
   }

   public int compareTo(NextTickListEntry other) {
      return this.scheduledTime < other.scheduledTime
         ? -1
         : (
            this.scheduledTime > other.scheduledTime
               ? 1
               : (
                  this.priority != other.priority
                     ? this.priority - other.priority
                     : (this.tickEntryID < other.tickEntryID ? -1 : (this.tickEntryID > other.tickEntryID ? 1 : 0))
               )
         );
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof NextTickListEntry)) {
         return false;
      } else {
         NextTickListEntry nextTickListEntry = (NextTickListEntry)obj;
         return this.position.equals(nextTickListEntry.position) && Block.isEqualTo(this.block, nextTickListEntry.block);
      }
   }

   @Override
   public int compareTo(Object other) {
      return this.compareTo((NextTickListEntry)other);
   }

   public NextTickListEntry setScheduledTime(long l) {
      this.scheduledTime = l;
      return this;
   }

   public Block getBlock() {
      return this.block;
   }

   public void setPriority(int n) {
      this.priority = n;
   }

   public NextTickListEntry(BlockPos pos, Block block2) {
      this.tickEntryID = nextTickEntryID++;
      this.position = pos;
      this.block = block2;
   }
}
