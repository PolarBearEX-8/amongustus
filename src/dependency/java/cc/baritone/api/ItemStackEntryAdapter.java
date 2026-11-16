package cc.baritone.api;

import cc.baritone.utils.ExploringRandomlyToFindManager;
import client.util.IBlockState;

public class ItemStackEntryAdapter extends ItemStackEntry {
   public final ExploringRandomlyToFindManager exploringRandomlyToFindManager;

   @Override
   public double computeValue(int count, int count2, int count3, IBlockState blockState) {
      return 1.0;
   }

   public ItemStackEntryAdapter(ExploringRandomlyToFindManager exploringRandomlyToFindManager2, boolean flag) {
      super(ExploringRandomlyToFindManager.resolveBaritoneManager(exploringRandomlyToFindManager2), flag);
      this.exploringRandomlyToFindManager = exploringRandomlyToFindManager2;
   }
}
