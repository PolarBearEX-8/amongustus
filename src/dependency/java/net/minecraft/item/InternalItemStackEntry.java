package net.minecraft.item;

import client.event.PostRenderNotifier;
import client.util.AuxiliaryArrayListAdapter;
import client.util.ClientManagerHandler;
import client.util.DistanceBuffer;
import client.util.LvlManager;
import java.util.Random;

public class InternalItemStackEntry implements PostRenderNotifier {
   public ItemStack itemStack;
   public ClientManagerHandler clientManagerHandler;

   @Override
   public void applyAuxiliaryArrayListAdapter(AuxiliaryArrayListAdapter auxiliaryArrayListAdapter, Random random) {
      int count = 1;
      if (this.clientManagerHandler != null) {
         count = this.clientManagerHandler.computeCount(random);
      }

      ItemStack stack = new ItemStack(Items.emerald, count, 0);
      ItemStack stack2 = new ItemStack(this.itemStack.getItem(), 1, this.itemStack.getIndexOffset());
      ItemStack stack3 = LvlManager.resolveItemStack(random, stack2, 5 + random.nextInt(15));
      auxiliaryArrayListAdapter.add(new DistanceBuffer(stack, stack3));
   }

   public InternalItemStackEntry(Item item, ClientManagerHandler clientManagerHandler2) {
      this.itemStack = new ItemStack(item);
      this.clientManagerHandler = clientManagerHandler2;
   }
}
