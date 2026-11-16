package cc.baritone.utils;

import net.minecraft.util.EnumFacing;

public interface Listener {
   ManagedListener[] getManagedListenerArrayAsC44OArray();

   ManagedListener resolveManagedListener2(ManagedListener managedListener, EnumFacing enumFacing, int count);

   ManagedListener resolveManagedListener(BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2);

   ManagedListener resolveManagedListener(ManagedListener managedListener);

   ManagedListener[] getManagedListenerArray();

   ManagedListener resolveManagedListenerByManagedListener(ManagedListener managedListener, EnumFacing enumFacing, int count);

   ManagedListener getManagedListener();

   ManagedListener resolveManagedListenerByManagedListener(ManagedListener managedListener);

   ManagedListener peekLast();

   ManagedListener resolveManagedListener(ManagedListener managedListener, EnumFacing enumFacing, int count);
}
