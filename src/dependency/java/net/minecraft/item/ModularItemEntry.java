package net.minecraft.item;

import client.setting.ExternalOptionType;
import client.util.CurveParser;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ModularItemEntry extends CurveParser {
   public ExternalOptionType externalOptionType;

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      boolean flag = false;

      for (int index = 0; index < 64; index++) {
         BlockPos blockPos2 = blockPos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
         if (world.isAirBlock(blockPos2) && (!world.secondaryFloatArrayEntry.isEnabled() || blockPos2.getY() < 254) && Blocks.blockDoublePlant.processAction7(world, blockPos2)) {
            Blocks.blockDoublePlant.applyWorldByWorld(world, blockPos2, this.externalOptionType, 2);
            flag = true;
         }
      }

      return flag;
   }

   public void setExternalOptionType(ExternalOptionType externalOptionType2) {
      this.externalOptionType = externalOptionType2;
   }
}
