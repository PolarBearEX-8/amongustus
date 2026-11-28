package baritone.process;

import baritone.api.utils.Rotation;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class BuilderPlacement {
   private final int hotbarSelection;
   private final BlockPos placeAgainst;
   private final EnumFacing side;
   private final Rotation rot;

   public static int getHotbarSelection(BuilderPlacement builderPlacement) {
      return builderPlacement.hotbarSelection;
   }

   public static Rotation getRot(BuilderPlacement builderPlacement) {
      return builderPlacement.rot;
   }

   public static BlockPos getPlaceAgainst(BuilderPlacement builderPlacement) {
      return builderPlacement.placeAgainst;
   }

   public static EnumFacing getSide(BuilderPlacement builderPlacement) {
      return builderPlacement.side;
   }

   public BuilderPlacement(int n, BlockPos pos, EnumFacing facing, Rotation rotation) {
      this.hotbarSelection = n;
      this.placeAgainst = pos;
      this.side = facing;
      this.rot = rotation;
   }
}
