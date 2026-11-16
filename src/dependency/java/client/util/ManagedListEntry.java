package client.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Blocks;

public class ManagedListEntry {
   public List list3 = new ArrayList();
   public List list2 = new ArrayList();
   public List list = Arrays.asList();

   public void applyLocalIntArrayEntry(LocalIntArrayEntry localIntArrayEntry, IBlockState blockState) {
      if (localIntArrayEntry != null) {
         this.list3.add(localIntArrayEntry);
         this.list2.add(blockState);
      }
   }

   public int size() {
      return this.list3.size();
   }

   public LocalIntArrayEntry get(int count) {
      return (LocalIntArrayEntry)this.list3.get(count);
   }

   public void updateState() {
      this.list3.clear();
      this.list2.clear();
   }

   public IBlockState resolveIBlockState(int count) {
      return count >= 0 && count < this.list2.size() ? (IBlockState)this.list2.get(count) : Blocks.air.getDefaultState();
   }

   public List set(LocalIntArrayEntry localIntArrayEntry) {
      this.list.set(0, localIntArrayEntry);
      return this.list;
   }
}
