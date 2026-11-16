package client.util;

import java.util.Random;
import net.minecraft.block.Blocks;

public class RandomCustomIBlockStateEntry extends CustomIBlockStateEntry {
   private RandomCustomIBlockStateEntry() {
   }

   @Override
   public void applyRandom(Random random, int count, int count2, int count3, boolean flag) {
      if (random.nextFloat() < 0.4F) {
         this.iBlockState = Blocks.block65.getDefaultState();
      } else {
         this.iBlockState = Blocks.block131.getDefaultState();
      }
   }

   public RandomCustomIBlockStateEntry(DistanceDeserializerEnumFacingSwitchMap distanceDeserializerEnumFacingSwitchMap) {
      this();
   }
}
