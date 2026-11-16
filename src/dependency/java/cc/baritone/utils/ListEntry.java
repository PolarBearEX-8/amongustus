package cc.baritone.utils;

import cc.baritone.api.ItemStackEntry;
import client.util.IBlockState;
import java.util.List;
import net.minecraft.block.Blocks;

public class ListEntry extends ItemStackEntry {
   public final List list3;
   public final InternalListener internalListener;
   public final int count6;
   public final int count4;
   public final int count5;
   public final BuilderPausedSchematicsHandler builderPausedSchematicsHandler;

   private IBlockState resolveIBlockState(int count, int count2, int count3, IBlockState blockState) {
      return this.internalListener.matchesIBlockState(count - this.count6, count2 - this.count4, count3 - this.count5, blockState)
         ? this.internalListener
            .resolveIBlockState(
               count - this.count6,
               count2 - this.count4,
               count3 - this.count5,
               blockState,
               BuilderPausedSchematicsHandler.resolveList(this.builderPausedSchematicsHandler)
            )
         : null;
   }

   @Override
   public double computeValueByIBlockState(int count, int count2, int count3, IBlockState blockState) {
      if (!this.checkIndex(count, count2, count3) && this.directCustom.checkIndex(count, count3)) {
         IBlockState blockState2 = this.resolveIBlockState(count, count2, count3, blockState);
         if (blockState2 == null || ((List)BaritoneManager.getSettings().buildSkipBlocks.dataRef).contains(blockState2.getBlock())) {
            return this.enabled4 ? this.value4 : 1000000.0;
         } else if (blockState2.getBlock() == Blocks.air) {
            return this.value4 * 2.0;
         } else if (this.list3.contains(blockState2)) {
            return 0.0;
         } else {
            return !this.enabled4 ? 1000000.0 : this.value4 * 3.0;
         }
      } else {
         return 1000000.0;
      }
   }

   public static IBlockState resolveIBlockState(ListEntry listEntry, int count, int count2, int count3, IBlockState blockState) {
      return listEntry.resolveIBlockState(count, count2, count3, blockState);
   }

   @Override
   public double computeValue(int count, int count2, int count3, IBlockState blockState) {
      if ((this.enabled6 || this.list.contains(blockState.getBlock())) && !this.checkIndex(count, count2, count3)) {
         IBlockState blockState2 = this.resolveIBlockState(count, count2, count3, blockState);
         if (blockState2 == null || ((List)BaritoneManager.getSettings().buildSkipBlocks.dataRef).contains(blockState2.getBlock())) {
            return 1.0;
         } else if (blockState2.getBlock() == Blocks.air) {
            return 1.0;
         } else {
            return BuilderPausedSchematicsHandler.matchesBuilderPausedSchematicsHan(
                  this.builderPausedSchematicsHandler, this.heuristicManager.resolveIBlockState(count, count2, count3), blockState2, false
               )
               ? (Double)BaritoneManager.getSettings().breakCorrectBlockPenaltyMultiplier.dataRef
               : 1.0;
         }
      } else {
         return 1000000.0;
      }
   }

   public ListEntry(BuilderPausedSchematicsHandler builderPausedSchematicsHandler2) {
      super(BuilderPausedSchematicsHandler.resolveBaritoneManager(builderPausedSchematicsHandler2), true);
      this.builderPausedSchematicsHandler = builderPausedSchematicsHandler2;
      this.list3 = BuilderPausedSchematicsHandler.resolveList(builderPausedSchematicsHandler2, 9);
      this.internalListener = BuilderPausedSchematicsHandler.resolveInternalListener(builderPausedSchematicsHandler2);
      this.count6 = BuilderPausedSchematicsHandler.resolveVec3i(builderPausedSchematicsHandler2).getX();
      this.count4 = BuilderPausedSchematicsHandler.resolveVec3i(builderPausedSchematicsHandler2).getY();
      this.count5 = BuilderPausedSchematicsHandler.resolveVec3i(builderPausedSchematicsHandler2).getZ();
      this.value += 10.0;
      this.value3 = 1.0;
   }
}
