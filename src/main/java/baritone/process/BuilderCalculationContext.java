package baritone.process;

import baritone.Baritone;
import baritone.api.schematic.ISchematic;
import baritone.pathing.movement.CalculationContext;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class BuilderCalculationContext extends CalculationContext {
   private final List placeable;
   private final ISchematic schematic;
   private final int originX;
   private final int originY;
   private final int originZ;
   public final BuilderProcess builderProcess;

   public static IBlockState getSchematicState(BuilderCalculationContext context, int n, int n2, int n3, IBlockState state) {
      return context.getSchematic(n, n2, n3, state);
   }

   public BuilderCalculationContext(BuilderProcess builderProcess2) {
      super(BuilderProcess.getBaritone(builderProcess2), true);
      this.builderProcess = builderProcess2;
      this.placeable = BuilderProcess.getApproxPlaceable(builderProcess2, 9);
      this.schematic = BuilderProcess.getSchematic(builderProcess2);
      this.originX = BuilderProcess.getOrigin(builderProcess2).getX();
      this.originY = BuilderProcess.getOrigin(builderProcess2).getY();
      this.originZ = BuilderProcess.getOrigin(builderProcess2).getZ();
      this.jumpPenalty += 10.0;
      this.backtrackCostFavoringCoefficient = 1.0;
   }

   @Override
   public double breakCostMultiplierAt(int n, int n2, int n3, IBlockState state) {
      if ((this.allowBreak || this.allowBreakAnyway.contains(state.getBlock())) && !this.isPossiblyProtected(n, n2, n3)) {
         IBlockState schematic2 = this.getSchematic(n, n2, n3, state);
         if (schematic2 == null || ((List)Baritone.settings().buildSkipBlocks.value).contains(schematic2.getBlock())) {
            return 1.0;
         } else if (schematic2.getBlock() == Blocks.air) {
            return 1.0;
         } else {
            return BuilderProcess.checkValidBlockState(this.builderProcess, this.bsi.get0(n, n2, n3), schematic2, false)
               ? (Double)Baritone.settings().breakCorrectBlockPenaltyMultiplier.value
               : 1.0;
         }
      } else {
         return 1000000.0;
      }
   }

   private IBlockState getSchematic(int n, int n2, int n3, IBlockState state) {
      return this.schematic.inSchematic(n - this.originX, n2 - this.originY, n3 - this.originZ, state)
         ? this.schematic.desiredState(n - this.originX, n2 - this.originY, n3 - this.originZ, state, BuilderProcess.getApproxPlaceableList(this.builderProcess))
         : null;
   }

   @Override
   public double costOfPlacingAt(int n, int n2, int n3, IBlockState state) {
      if (!this.isPossiblyProtected(n, n2, n3) && this.worldBorder.canPlaceAt(n, n3)) {
         IBlockState schematic2 = this.getSchematic(n, n2, n3, state);
         if (schematic2 == null || ((List)Baritone.settings().buildSkipBlocks.value).contains(schematic2.getBlock())) {
            return this.hasThrowaway ? this.placeBlockCost : 1000000.0;
         } else if (schematic2.getBlock() == Blocks.air) {
            return this.placeBlockCost * 2.0;
         } else if (this.placeable.contains(schematic2)) {
            return 0.0;
         } else {
            return !this.hasThrowaway ? 1000000.0 : this.placeBlockCost * 3.0;
         }
      } else {
         return 1000000.0;
      }
   }
}
