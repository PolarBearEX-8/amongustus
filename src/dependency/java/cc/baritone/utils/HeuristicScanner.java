package cc.baritone.utils;

import client.util.IBlockState;
import java.util.List;

public class HeuristicScanner extends AbstractInternalListener {
   public final PrimaryCustom primaryCustom;

   public PrimaryCustom getPrimaryCustom() {
      return this.primaryCustom;
   }

   public HeuristicScanner(int count, int count2, int count3, PrimaryCustom primaryCustom2) {
      super(count, count2, count3);
      this.primaryCustom = primaryCustom2;
   }

   public HeuristicScanner(int count, int count2, int count3, IBlockState blockState) {
      this(count, count2, count3, new PrimaryCustom(blockState.getBlock(), blockState.getBlock().getIndex(blockState)));
   }

   @Override
   public IBlockState resolveIBlockState(int count, int count2, int count3, IBlockState blockState, List list) {
      if (this.primaryCustom.matchesIBlockState(blockState)) {
         return blockState;
      } else {
         for (Object blockState2Obj : list) {
            IBlockState blockState2 = (IBlockState)blockState2Obj;
            if (this.primaryCustom.matchesIBlockState(blockState2)) {
               return blockState2;
            }
         }

         return this.primaryCustom.getIBlockState();
      }
   }
}
