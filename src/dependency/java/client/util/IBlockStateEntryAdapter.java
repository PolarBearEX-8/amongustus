package client.util;

import client.setting.DefaultOptionType;
import java.util.Random;
import net.minecraft.block.BlockStonebrick;
import net.minecraft.block.Blocks;

public class IBlockStateEntryAdapter extends CustomIBlockStateEntry {
   @Override
   public void applyRandom(Random random, int count, int count2, int count3, boolean flag) {
      if (flag) {
         float factor = random.nextFloat();
         if (factor < 0.2F) {
            this.iBlockState = Blocks.block42.processAction(BlockStonebrick.count2);
         } else if (factor < 0.5F) {
            this.iBlockState = Blocks.block42.processAction(BlockStonebrick.count3);
         } else if (factor < 0.55F) {
            this.iBlockState = Blocks.block22.processAction(DefaultOptionType.STONEBRICK.getCount());
         } else {
            this.iBlockState = Blocks.block42.getDefaultState();
         }
      } else {
         this.iBlockState = Blocks.air.getDefaultState();
      }
   }

   public IBlockStateEntryAdapter(AuxiliaryClassEntryAdapter auxiliaryClassEntryAdapter) {
      this();
   }

   private IBlockStateEntryAdapter() {
   }
}
