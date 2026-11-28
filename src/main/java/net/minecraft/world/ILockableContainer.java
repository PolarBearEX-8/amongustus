package net.minecraft.world;

import net.minecraft.inventory.IInventory;

public interface ILockableContainer extends IInventory, IInteractionObject {
   void setLockCode(LockCode lockCode);

   boolean isLocked();

   LockCode getLockCode();
}
