package client.util;

import java.util.concurrent.Callable;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class SecondaryCallableAdapter implements Callable {
   public final ItemStack itemStack;
   public final InventoryPlayer inventoryPlayer;

   public SecondaryCallableAdapter(InventoryPlayer inventoryPlayer2, ItemStack stack) {
      this.inventoryPlayer = inventoryPlayer2;
      this.itemStack = stack;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      return this.itemStack.getString();
   }
}
