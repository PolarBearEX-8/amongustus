package client.event;

import net.minecraft.inventory.InventoryLargeChest;

public interface PostUpdateDispatcher {
   void applyInventoryLargeChest(InventoryLargeChest inventoryLargeChest);
}
