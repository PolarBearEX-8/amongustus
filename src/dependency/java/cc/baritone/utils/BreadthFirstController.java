package cc.baritone.utils;

import client.util.IBlockState;
import java.util.OptionalInt;
import java.util.function.Predicate;
import net.minecraft.block.BlockAir;

public class BreadthFirstController extends HeuristicResolver {
   public final int[][] countArray;

   private static int[][] resolveIndexArray(AStarRegistry aStarRegistry) {
      int[][] countMatrix = new int[aStarRegistry.getCount()][aStarRegistry.getCount3()];

      for (int index = 0; index < aStarRegistry.getCount(); index++) {
         for (int index2 = 0; index2 < aStarRegistry.getCount3(); index2++) {
            OptionalInt optionalInt = resolveOptionalInt(aStarRegistry.resolveIBlockStateArray(index, index2), (item -> BreadthFirstController.matchesIBlockState((IBlockState)item)));
            if (optionalInt.isPresent()) {
               countMatrix[index][index2] = optionalInt.getAsInt();
            } else {
               System.out.println("Column " + index + "," + index2 + " has no blocks, but it's apparently map art? wtf");
               System.out.println("Letting it be whatever");
               countMatrix[index][index2] = 256;
            }
         }
      }

      return countMatrix;
   }

   private static boolean matchesIBlockState(IBlockState blockState) {
      return !(blockState.getBlock() instanceof BlockAir);
   }

   @Override
   public boolean matchesIBlockStateByIBlockState(int count, int count2, int count3, IBlockState blockState) {
      return count2 >= this.countArray[count][count3];
   }

   public BreadthFirstController(AStarRegistry aStarRegistry) {
      super(aStarRegistry);
      this.countArray = resolveIndexArray(aStarRegistry);
   }

   private static OptionalInt resolveOptionalInt(Object[] entryArray, Predicate predicate) {
      for (int index = entryArray.length - 1; index >= 0; index--) {
         if (predicate.test(entryArray[index])) {
            return OptionalInt.of(index);
         }
      }

      return OptionalInt.empty();
   }
}
