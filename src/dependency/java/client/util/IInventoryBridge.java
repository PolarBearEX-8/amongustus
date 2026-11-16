package client.util;

import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.util.IChatComponent;

public class IInventoryBridge extends InventoryLargeChest {
   public IInventoryBridge(String text, int count) {
      super(text, false, count);
   }

   public IInventoryBridge(IChatComponent iChatComponent, int count) {
      super(iChatComponent, count);
   }
}
