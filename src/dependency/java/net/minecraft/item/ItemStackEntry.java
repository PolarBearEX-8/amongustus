package net.minecraft.item;

import client.event.PostRenderNotifier;
import client.util.AuxiliaryArrayListAdapter;
import client.util.ClientManagerHandler;
import client.util.DistanceBuffer;
import java.util.Random;

public class ItemStackEntry implements PostRenderNotifier {
   public ItemStack itemStack2;
   public ClientManagerHandler clientManagerHandler;
   public ItemStack itemStack;
   public ClientManagerHandler clientManagerHandler2;

   @Override
   public void applyAuxiliaryArrayListAdapter(AuxiliaryArrayListAdapter auxiliaryArrayListAdapter, Random random) {
      int count = 1;
      if (this.clientManagerHandler != null) {
         count = this.clientManagerHandler.computeCount(random);
      }

      int count2 = 1;
      if (this.clientManagerHandler2 != null) {
         count2 = this.clientManagerHandler2.computeCount(random);
      }

      auxiliaryArrayListAdapter.add(
         new DistanceBuffer(
            new ItemStack(this.itemStack2.getItem(), count, this.itemStack2.getIndexOffset()),
            new ItemStack(Items.emerald),
            new ItemStack(this.itemStack.getItem(), count2, this.itemStack.getIndexOffset())
         )
      );
   }

   public ItemStackEntry(Item item, ClientManagerHandler clientManagerHandler3, Item item2, ClientManagerHandler clientManagerHandler4) {
      this.itemStack2 = new ItemStack(item);
      this.clientManagerHandler = clientManagerHandler3;
      this.itemStack = new ItemStack(item2);
      this.clientManagerHandler2 = clientManagerHandler4;
   }
}
