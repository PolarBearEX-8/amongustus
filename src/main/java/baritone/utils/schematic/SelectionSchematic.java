package baritone.utils.schematic;

import baritone.api.schematic.ISchematic;
import baritone.api.schematic.MaskSchematic;
import baritone.api.selection.ISelection;
import java.util.stream.Stream;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;

public class SelectionSchematic extends MaskSchematic {
   private final ISelection[] selections;

   @Override
   public boolean partOfMask(int n, int n2, int n3, IBlockState state) {
      for (ISelection selection : this.selections) {
         if (n >= selection.min().x2
            && n2 >= selection.min().y2
            && n3 >= selection.min().z2
            && n <= selection.max().x2
            && n2 <= selection.max().y2
            && n3 <= selection.max().z2) {
            return true;
         }
      }

      return false;
   }

   public SelectionSchematic(ISchematic schematic2, Vec3i vec, ISelection[] selections2) {
      super(schematic2);
      this.selections = Stream.of(selections2)
         .map(selection -> selection.shift(EnumFacing.WEST, vec.getX()).shift(EnumFacing.DOWN, vec.getY()).shift(EnumFacing.NORTH, vec.getZ()))
         .toArray(ISelection[]::new);
   }
}
