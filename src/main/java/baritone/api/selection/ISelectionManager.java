package baritone.api.selection;

import baritone.api.utils.BetterBlockPos;
import net.minecraft.util.EnumFacing;

public interface ISelectionManager {
   ISelection expand(ISelection selection, EnumFacing facing, int n);

   ISelection addSelection(BetterBlockPos betterBlockPos, BetterBlockPos betterBlockPos2);

   ISelection shift(ISelection selection, EnumFacing facing, int n);

   ISelection addSelection(ISelection selection);

   ISelection removeSelection(ISelection selection);

   ISelection getLastSelection();

   ISelection[] getSelections();

   ISelection[] removeAllSelections();

   ISelection getOnlySelection();

   ISelection contract(ISelection selection, EnumFacing facing, int n);
}
