package client.event;

import java.util.List;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface CoreListener {
   void applyContainer(Container container, List list);

   void applyContainer(Container container, int count, ItemStack stack);

   void applyContainer(Container container, IInventory iInventory);

   void applyContainer(Container container, int count, int count2);
}
