package net.minecraft.client.renderer;

import client.util.DampenerAccumulator;
import client.util.InventorySorter;

public class GlTexGenState {
   public InventorySorter inventorySorter = new InventorySorter(8192, 3168);
   public InventorySorter inventorySorter4 = new InventorySorter(8193, 3169);
   public InventorySorter inventorySorter3 = new InventorySorter(8194, 3170);
   public InventorySorter inventorySorter2 = new InventorySorter(8195, 3171);

   public GlTexGenState(DampenerAccumulator dampenerAccumulator) {
      this();
   }

   private GlTexGenState() {
   }
}
