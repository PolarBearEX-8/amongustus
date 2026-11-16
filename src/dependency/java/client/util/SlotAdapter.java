package client.util;

import net.minecraft.block.Blocks;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class SlotAdapter extends Slot {
   public final int count;
   public final ContainerPlayer containerPlayer;

   @Override
   public String processAction() {
      return ItemGoldenBoots.stringArray[this.count];
   }

   @Override
   public int processAction2() {
      return 1;
   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      return stack == null
         ? false
         : (
            stack.getItem() instanceof ItemGoldenBoots
               ? ((ItemGoldenBoots)stack.getItem()).indexOffset3 == this.count
               : (stack.getItem() != Item.processAction2(Blocks.block146) && stack.getItem() != Items.skull ? false : this.count == 0)
         );
   }

   public SlotAdapter(ContainerPlayer containerPlayer2, IInventory iInventory, int count2, int count3, int count4, int count5) {
      super(iInventory, count2, count3, count4);
      this.containerPlayer = containerPlayer2;
      this.count = count5;
   }
}
