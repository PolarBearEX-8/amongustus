package baritone.utils.schematic;

import baritone.api.schematic.IStaticSchematic;
import baritone.api.schematic.MaskSchematic;
import java.util.OptionalInt;
import java.util.function.Predicate;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;

public class MapArtSchematic extends MaskSchematic {
   private final int[][] heightMap;

   private static int[][] computeHeightMap(IStaticSchematic staticSchematic) {
      int[][] intsArr = new int[staticSchematic.widthX()][staticSchematic.lengthZ()];

      for (int i = 0; i < staticSchematic.widthX(); i++) {
         for (int j = 0; j < staticSchematic.lengthZ(); j++) {
            OptionalInt optionalInt = lastIndexMatching(staticSchematic.getColumn(i, j), obj -> !(((IBlockState)obj).getBlock() instanceof BlockAir));
            if (optionalInt.isPresent()) {
               intsArr[i][j] = optionalInt.getAsInt();
            } else {
               System.out.println("Column " + i + "," + j + " has no blocks, but it's apparently map art? wtf");
               System.out.println("Letting it be whatever");
               intsArr[i][j] = 256;
            }
         }
      }

      return intsArr;
   }

   @Override
   public boolean partOfMask(int n, int n2, int n3, IBlockState state) {
      return n2 >= this.heightMap[n][n3];
   }

   private static OptionalInt lastIndexMatching(Object[] array, Predicate predicate) {
      for (int i = array.length - 1; i >= 0; i--) {
         if (predicate.test(array[i])) {
            return OptionalInt.of(i);
         }
      }

      return OptionalInt.empty();
   }

   public MapArtSchematic(IStaticSchematic staticSchematic) {
      super(staticSchematic);
      this.heightMap = computeHeightMap(staticSchematic);
   }

}
