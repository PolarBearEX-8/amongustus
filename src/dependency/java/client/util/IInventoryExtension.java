package client.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.world.LockCode;

public interface IInventoryExtension extends IInventory, Listener63Extension {
   LockCode getLockCode();

   void setLockCode(LockCode lockCode);

   boolean isActive16();
}
