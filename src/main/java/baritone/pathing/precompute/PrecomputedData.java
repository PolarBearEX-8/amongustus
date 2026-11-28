package baritone.pathing.precompute;

import baritone.pathing.movement.MovementHelper;
import baritone.utils.BlockStateInterface;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public class PrecomputedData {
   private final int[] data = new int[Block.BLOCK_STATE_IDS.size()];
   private static final int COMPLETED_MASK = 1;
   private static final int CAN_WALK_ON_MASK = 2;
   private static final int CAN_WALK_ON_SPECIAL_MASK = 4;
   private static final int CAN_WALK_THROUGH_MASK = 8;
   private static final int CAN_WALK_THROUGH_SPECIAL_MASK = 16;
   private static final int FULLY_PASSABLE_MASK = 32;
   private static final int FULLY_PASSABLE_SPECIAL_MASK = 64;

   public boolean canWalkOn(BlockStateInterface blockStateInterface, int n, int n2, int n3, IBlockState state) {
      int n4 = Block.BLOCK_STATE_IDS.get(state);
      int n5 = this.data[n4];
      if ((n5 & 1) == 0) {
         n5 = this.fillData(n4, state);
      }

      return (n5 & 4) != 0 ? MovementHelper.canWalkOnPosition(blockStateInterface, n, n2, n3, state) : (n5 & 2) != 0;
   }

   private int fillData(int n, IBlockState state) {
      int n2 = 0;
      Ternary ternary = MovementHelper.canWalkOnState(state);
      if (ternary == Ternary.YES) {
         n2 |= 2;
      }

      if (ternary == Ternary.MAYBE) {
         n2 |= 4;
      }

      Ternary ternary2 = MovementHelper.canWalkThroughState(state);
      if (ternary2 == Ternary.YES) {
         n2 |= 8;
      }

      if (ternary2 == Ternary.MAYBE) {
         n2 |= 16;
      }

      Ternary ternary3 = MovementHelper.fullyPassableState(state);
      if (ternary3 == Ternary.YES) {
         n2 |= 32;
      }

      if (ternary3 == Ternary.MAYBE) {
         n2 |= 64;
      }

      n2 |= 1;
      this.data[n] = n2;
      return n2;
   }

   public boolean fullyPassable(BlockStateInterface blockStateInterface, int n, int n2, int n3, IBlockState state) {
      int n4 = Block.BLOCK_STATE_IDS.get(state);
      int n5 = this.data[n4];
      if ((n5 & 1) == 0) {
         n5 = this.fillData(n4, state);
      }

      return (n5 & 64) != 0 ? MovementHelper.fullyPassablePosition(blockStateInterface, n, n2, n3, state) : (n5 & 32) != 0;
   }

   public boolean canWalkThrough(BlockStateInterface blockStateInterface, int n, int n2, int n3, IBlockState state) {
      int n4 = Block.BLOCK_STATE_IDS.get(state);
      int n5 = this.data[n4];
      if ((n5 & 1) == 0) {
         n5 = this.fillData(n4, state);
      }

      return (n5 & 16) != 0 ? MovementHelper.canWalkThroughPosition(blockStateInterface, n, n2, n3, state) : (n5 & 8) != 0;
   }
}
