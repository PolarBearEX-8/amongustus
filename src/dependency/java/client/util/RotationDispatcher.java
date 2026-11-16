package client.util;

import client.setting.CannotSupportPropertyPredicate;
import client.setting.CombatAlphaProperty;
import client.setting.IndirectOptionType;
import client.setting.MovementFactorProperty;
import client.setting.MovementModeProperty;
import com.google.common.base.Predicates;
import java.util.Random;
import net.minecraft.block.BlockSand;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExtraBlockAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class RotationDispatcher extends CurveParser {
   public static final CannotSupportPropertyPredicate cannotSupportPropertyPredicate = CannotSupportPropertyPredicate.resolveCannotSupportPropertyPredica(
         Blocks.blockSand
      )
      .resolveCannotSupportPropertyPredica(BlockSand.immutableSetEntry, Predicates.equalTo(CombatAlphaProperty.SAND));
   public final IBlockState iBlockState = Blocks.extraBlockAdapter6
      .getDefaultState()
      .resolveIBlockState(ImmutableSetWrapper.immutableSetEntry3, IndirectOptionType.SAND)
      .resolveIBlockState(ExtraBlockAdapter.immutableSetEntry, MovementModeProperty.BOTTOM);
   public final IBlockState iBlockState2 = Blocks.block136.getDefaultState();
   public final IBlockState iBlockState3 = Blocks.blockFlowingLava.getDefaultState();

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      BlockPos blockPos2 = blockPos;

      while (world.isAirBlock(blockPos2) && blockPos2.getY() > 2) {
         blockPos2 = blockPos2.down();
      }

      if (!cannotSupportPropertyPredicate.matchesIBlockState(world.getBlockState(blockPos2))) {
         return false;
      } else {
         for (int index = -2; index <= 2; index++) {
            for (int index2 = -2; index2 <= 2; index2++) {
               if (world.isAirBlock(blockPos2.add(index, -1, index2)) && world.isAirBlock(blockPos2.add(index, -2, index2))) {
                  return false;
               }
            }
         }

         for (int index3 = -1; index3 <= 0; index3++) {
            for (int index4 = -2; index4 <= 2; index4++) {
               for (int index5 = -2; index5 <= 2; index5++) {
                  world.matchesBlockPos(blockPos2.add(index4, index3, index5), this.iBlockState2, 2);
               }
            }
         }

         world.matchesBlockPos(blockPos2, this.iBlockState3, 2);

         for (Object element : MovementFactorProperty.HORIZONTAL) {
            world.matchesBlockPos(blockPos2.offset((EnumFacing)element), this.iBlockState3, 2);
         }

         for (int index6 = -2; index6 <= 2; index6++) {
            for (int index7 = -2; index7 <= 2; index7++) {
               if (index6 == -2 || index6 == 2 || index7 == -2 || index7 == 2) {
                  world.matchesBlockPos(blockPos2.add(index6, 1, index7), this.iBlockState2, 2);
               }
            }
         }

         world.matchesBlockPos(blockPos2.add(2, 1, 0), this.iBlockState, 2);
         world.matchesBlockPos(blockPos2.add(-2, 1, 0), this.iBlockState, 2);
         world.matchesBlockPos(blockPos2.add(0, 1, 2), this.iBlockState, 2);
         world.matchesBlockPos(blockPos2.add(0, 1, -2), this.iBlockState, 2);

         for (int index8 = -1; index8 <= 1; index8++) {
            for (int index9 = -1; index9 <= 1; index9++) {
               if (index8 == 0 && index9 == 0) {
                  world.matchesBlockPos(blockPos2.add(index8, 4, index9), this.iBlockState2, 2);
               } else {
                  world.matchesBlockPos(blockPos2.add(index8, 4, index9), this.iBlockState, 2);
               }
            }
         }

         for (int index10 = 1; index10 <= 3; index10++) {
            world.matchesBlockPos(blockPos2.add(-1, index10, -1), this.iBlockState2, 2);
            world.matchesBlockPos(blockPos2.add(-1, index10, 1), this.iBlockState2, 2);
            world.matchesBlockPos(blockPos2.add(1, index10, -1), this.iBlockState2, 2);
            world.matchesBlockPos(blockPos2.add(1, index10, 1), this.iBlockState2, 2);
         }

         return true;
      }
   }
}
