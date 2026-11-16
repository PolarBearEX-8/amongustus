package net.minecraft.item;

import client.setting.CombatFactorProperty;
import client.util.CurveParser;
import client.util.IBlockState;
import client.util.ImmutableSetEntry2Entry;
import java.util.Random;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class IndirectItemEntry extends CurveParser {
   public ImmutableSetEntry2Entry immutableSetEntry2Entry;
   public IBlockState iBlockState;

   public void applyImmutableSetEntry2Entry(ImmutableSetEntry2Entry immutableSetEntry2Entry2, CombatFactorProperty combatFactorProperty) {
      this.immutableSetEntry2Entry = immutableSetEntry2Entry2;
      this.iBlockState = immutableSetEntry2Entry2.getDefaultState().resolveIBlockState(immutableSetEntry2Entry2.getIProperty(), combatFactorProperty);
   }

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      for (int index = 0; index < 64; index++) {
         BlockPos blockPos2 = blockPos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
         if (world.isAirBlock(blockPos2)
            && (!world.secondaryFloatArrayEntry.isEnabled() || blockPos2.getY() < 255)
            && this.immutableSetEntry2Entry.matchesWorld(world, blockPos2, this.iBlockState)) {
            world.matchesBlockPos(blockPos2, this.iBlockState, 2);
         }
      }

      return true;
   }

   public IndirectItemEntry(ImmutableSetEntry2Entry immutableSetEntry2Entry2, CombatFactorProperty combatFactorProperty) {
      this.applyImmutableSetEntry2Entry(immutableSetEntry2Entry2, combatFactorProperty);
   }
}
