package client.util;

import com.google.common.base.Predicate;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class IBlockStateEntry extends CurveParser {
   public final IBlockState iBlockState;
   public final int count;
   public final Predicate predicate;

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      float factor = random.nextFloat() * (float) Math.PI;
      double offset = (double)((float)(blockPos.getX() + 8) + MathHelper.abs(factor) * (float)this.count / 8.0F);
      double offset2 = (double)((float)(blockPos.getX() + 8) - MathHelper.abs(factor) * (float)this.count / 8.0F);
      double offset3 = (double)((float)(blockPos.getZ() + 8) + MathHelper.cos(factor) * (float)this.count / 8.0F);
      double offset4 = (double)((float)(blockPos.getZ() + 8) - MathHelper.cos(factor) * (float)this.count / 8.0F);
      double offset5 = (double)(blockPos.getY() + random.nextInt(3) - 2);
      double offset6 = (double)(blockPos.getY() + random.nextInt(3) - 2);

      for (int index = 0; index < this.count; index++) {
         float factor2 = (float)index / (float)this.count;
         double offset7 = offset + (offset2 - offset) * (double)factor2;
         double offset8 = offset5 + (offset6 - offset5) * (double)factor2;
         double offset9 = offset3 + (offset4 - offset3) * (double)factor2;
         double offset10 = random.nextDouble() * (double)this.count / 16.0;
         double offset11 = (double)(MathHelper.abs((float) Math.PI * factor2) + 1.0F) * offset10 + 1.0;
         double offset12 = (double)(MathHelper.abs((float) Math.PI * factor2) + 1.0F) * offset10 + 1.0;
         int count2 = MathHelper.ceiling_double_int(offset7 - offset11 / 2.0);
         int count3 = MathHelper.ceiling_double_int(offset8 - offset12 / 2.0);
         int count4 = MathHelper.ceiling_double_int(offset9 - offset11 / 2.0);
         int count5 = MathHelper.ceiling_double_int(offset7 + offset11 / 2.0);
         int count6 = MathHelper.ceiling_double_int(offset8 + offset12 / 2.0);

         for (int index2 = MathHelper.ceiling_double_int(offset9 + offset11 / 2.0); count2 <= count5; count2++) {
            double offset13 = ((double)count2 + 0.5 - offset7) / (offset11 / 2.0);
            if (offset13 * offset13 < 1.0) {
               for (int index3 = count3; index3 <= count6; index3++) {
                  double offset14 = ((double)index3 + 0.5 - offset8) / (offset12 / 2.0);
                  if (offset13 * offset13 + offset14 * offset14 < 1.0) {
                     for (int index4 = count4; index4 <= index2; index4++) {
                        double offset15 = ((double)index4 + 0.5 - offset9) / (offset11 / 2.0);
                        if (offset13 * offset13 + offset14 * offset14 + offset15 * offset15 < 1.0) {
                           BlockPos blockPos2 = new BlockPos(count2, index3, index4);
                           if (this.predicate.apply(world.getBlockState(blockPos2))) {
                              world.matchesBlockPos(blockPos2, this.iBlockState, 2);
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return true;
   }

   public IBlockStateEntry(IBlockState blockState, int count2) {
      this(blockState, count2, SpecialPredicateAdapter.resolveSpecialPredicateAdapter(Blocks.stone));
   }

   public IBlockStateEntry(IBlockState blockState, int count2, Predicate predicate2) {
      this.iBlockState = blockState;
      this.count = count2;
      this.predicate = predicate2;
   }
}
