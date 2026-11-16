package cc.baritone.utils;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;

public interface ManagedListener {
   ManagedListener resolveManagedListener2(EnumFacing enumFacing, int count);

   BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos3();

   BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos4();

   BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos();

   ManagedListener resolveManagedListener(EnumFacing enumFacing, int count);

   BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos2();

   Vec3i getVec3i();

   AxisAlignedBB getAxisAlignedBB();

   ManagedListener resolveManagedListenerByEnumFacing(EnumFacing enumFacing, int count);
}
