package baritone.api.selection;

import baritone.api.utils.BetterBlockPos;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;

public interface ISelection {
   Vec3i size();

   BetterBlockPos max();

   BetterBlockPos min();

   ISelection expand(EnumFacing facing, int n);

   BetterBlockPos pos1();

   BetterBlockPos pos2();

   ISelection shift(EnumFacing facing, int n);

   AxisAlignedBB aabb();

   ISelection contract(EnumFacing facing, int n);
}
