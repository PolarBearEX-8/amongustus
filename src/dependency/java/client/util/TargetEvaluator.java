package client.util;

import client.setting.CombatFactorProperty;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntitySlime;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class TargetEvaluator extends BiomeIDIsOutOfBoundsManager {
   @Override
   public int computeCount(BlockPos blockPos) {
      return 6975545;
   }

   @Override
   public CombatFactorProperty nextInt(Random random, BlockPos blockPos) {
      return CombatFactorProperty.BLUE_ORCHID;
   }

   @Override
   public void applyWorld(World world, Random random, ShortArrayEntry shortArrayEntry, int count, int count2, double offset) {
      double offset2 = auxiliaryClientManagerService.computeValue((double)count * 0.25, (double)count2 * 0.25);
      if (offset2 > 0.0) {
         int count3 = count & 15;
         int count4 = count2 & 15;

         for (int index = 255; index >= 0; index--) {
            if (shortArrayEntry.resolveIBlockState(count4, index, count3).getBlock().getMaterial() != Material.coreItemEntry) {
               if (index == 62 && shortArrayEntry.resolveIBlockState(count4, index, count3).getBlock() != Blocks.blockLava2) {
                  shortArrayEntry.applyIBlockState(count4, index, count3, Blocks.blockLava2.getDefaultState());
                  if (offset2 < 0.12) {
                     shortArrayEntry.applyIBlockState(count4, index + 1, count3, Blocks.block33.getDefaultState());
                  }
               }
               break;
            }
         }
      }

      this.applyWorld2(world, random, shortArrayEntry, count, count2, offset);
   }

   @Override
   public ClientManagerResolver nextInt(Random random) {
      return this.auxiliaryClientManagerProcessor;
   }

   @Override
   public int computeCountByBlockPos(BlockPos blockPos) {
      return auxiliaryClientManagerService.computeValue((double)blockPos.getX() * 0.0225, (double)blockPos.getZ() * 0.0225) < -0.1 ? 5011004 : 6975545;
   }

   public TargetEvaluator(int count) {
      super(count);
      this.alreadyDecoratingManager.count9 = 2;
      this.alreadyDecoratingManager.count8 = 1;
      this.alreadyDecoratingManager.count = 1;
      this.alreadyDecoratingManager.count2 = 8;
      this.alreadyDecoratingManager.count4 = 10;
      this.alreadyDecoratingManager.count12 = 1;
      this.alreadyDecoratingManager.count3 = 4;
      this.alreadyDecoratingManager.count6 = 0;
      this.alreadyDecoratingManager.count7 = 0;
      this.alreadyDecoratingManager.count5 = 5;
      this.count5 = 14745518;
      this.list3.add(new ClassHolder(EntitySlime.class, 1, 1, 1));
   }
}
