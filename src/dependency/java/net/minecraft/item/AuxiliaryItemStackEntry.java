package net.minecraft.item;

import client.event.PostRenderNotifier;
import client.util.AuxiliaryArrayListAdapter;
import client.util.ClientManagerHandler;
import client.util.DistanceBuffer;
import java.util.Random;

public class AuxiliaryItemStackEntry implements PostRenderNotifier {
   public ItemStack itemStack;
   public ClientManagerHandler clientManagerHandler;

   @Override
   public void applyAuxiliaryArrayListAdapter(AuxiliaryArrayListAdapter auxiliaryArrayListAdapter, Random random) {
      int count = 1;
      if (this.clientManagerHandler != null) {
         count = this.clientManagerHandler.computeCount(random);
      }

      ItemStack stack;
      ItemStack stack2;
      if (count < 0) {
         stack = new ItemStack(Items.emerald, 1, 0);
         stack2 = new ItemStack(this.itemStack.getItem(), -count, this.itemStack.getIndexOffset());
      } else {
         stack = new ItemStack(Items.emerald, count, 0);
         stack2 = new ItemStack(this.itemStack.getItem(), 1, this.itemStack.getIndexOffset());
      }

      auxiliaryArrayListAdapter.add(new DistanceBuffer(stack, stack2));
   }

   public AuxiliaryItemStackEntry(Item item, ClientManagerHandler clientManagerHandler2) {
      this.itemStack = new ItemStack(item);
      this.clientManagerHandler = clientManagerHandler2;
   }

   public AuxiliaryItemStackEntry(ItemStack stack, ClientManagerHandler clientManagerHandler2) {
      this.itemStack = stack;
      this.clientManagerHandler = clientManagerHandler2;
   }
}
