package cc.baritone.utils;

import client.util.IBlockState;

public class PassiveCustom extends HeuristicResolver {
   public final HeuristicSystem heuristicSystem;
   public final Boolean[][][] itemArray;

   public PassiveCustom(InternalListener internalListener, HeuristicSystem heuristicSystem2) {
      super(internalListener);
      this.heuristicSystem = heuristicSystem2;
      this.itemArray = new Boolean[this.getCount()][this.getCount2()][this.getCount3()];
   }

   @Override
   public boolean matchesIBlockStateByIBlockState(int count, int count2, int count3, IBlockState blockState) {
      if (this.itemArray[count][count2][count3] == null) {
         this.itemArray[count][count2][count3] = this.heuristicSystem.contains(blockState);
      }

      return this.itemArray[count][count2][count3];
   }

   @Override
   public void updateState() {
      for (int index = 0; index < this.itemArray.length; index++) {
         for (int index2 = 0; index2 < this.itemArray[0].length; index2++) {
            for (int index3 = 0; index3 < this.itemArray[0][0].length; index3++) {
               this.itemArray[index][index2][index3] = null;
            }
         }
      }
   }
}
