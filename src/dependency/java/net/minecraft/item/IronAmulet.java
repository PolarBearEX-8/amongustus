package net.minecraft.item;

import client.event.PostRenderNotifier;
import client.util.AuxiliaryArrayListAdapter;
import client.util.ClientManagerHandler;
import client.util.DistanceBuffer;
import java.util.Random;

public class IronAmulet implements PostRenderNotifier {
   public Item item;
   public ClientManagerHandler clientManagerHandler;

   @Override
   public void applyAuxiliaryArrayListAdapter(AuxiliaryArrayListAdapter auxiliaryArrayListAdapter, Random random) {
      int count = 1;
      if (this.clientManagerHandler != null) {
         count = this.clientManagerHandler.computeCount(random);
      }

      auxiliaryArrayListAdapter.add(new DistanceBuffer(new ItemStack(this.item, count, 0), Items.emerald));
   }

   public IronAmulet(Item item2, ClientManagerHandler clientManagerHandler2) {
      this.item = item2;
      this.clientManagerHandler = clientManagerHandler2;
   }
}
