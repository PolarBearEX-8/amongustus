package cc.baritone.utils;

import cc.baritone.api.ItemStackEntry;

public class Listener5EntryAdapter extends Listener5Entry {
   public final ItemStackEntry itemStackEntry;

   public Listener5EntryAdapter(SecondaryListener secondaryListener, HeuristicCoordinator heuristicCoordinator, ItemStackEntry itemStackEntry2) {
      super(secondaryListener, heuristicCoordinator);
      this.itemStackEntry = itemStackEntry2;
   }
}
