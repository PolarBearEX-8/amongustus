package client.util;

import client.setting.DefaultOptionType;
import java.util.Random;
import net.minecraft.block.BlockMonsterEgg;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntitySilverfish;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class HitBoxWatcher extends RotationPool {
   public EntitySilverfish entitySilverfish;
   public int count2;

   public void updateState4() {
      if (this.count2 == 0) {
         this.count2 = 20;
      }
   }

   @Override
   public void updateState2() {
      this.count2--;
      if (this.count2 <= 0) {
         World world = this.entitySilverfish.worldObj;
         Random random = this.entitySilverfish.getRandom();
         BlockPos blockPos = new BlockPos(this.entitySilverfish);

         for (int index = 0; index <= 5 && index >= -5; index = index <= 0 ? 1 - index : 0 - index) {
            for (int index2 = 0; index2 <= 10 && index2 >= -10; index2 = index2 <= 0 ? 1 - index2 : 0 - index2) {
               for (int index3 = 0; index3 <= 10 && index3 >= -10; index3 = index3 <= 0 ? 1 - index3 : 0 - index3) {
                  BlockPos blockPos2 = blockPos.add(index2, index, index3);
                  IBlockState blockState = world.getBlockState(blockPos2);
                  if (blockState.getBlock() == Blocks.block22) {
                     if (world.getDoFireTickManager().matchesText("mobGriefing")) {
                        world.matchesBlockPos3(blockPos2, true);
                     } else {
                        world.matchesBlockPos(blockPos2, ((DefaultOptionType)blockState.getComparable(BlockMonsterEgg.immutableSetEntry)).getIBlockState(), 3);
                     }

                     if (random.nextBoolean()) {
                        return;
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public boolean isActive() {
      return this.count2 > 0;
   }

   public HitBoxWatcher(EntitySilverfish entitySilverfish2) {
      this.entitySilverfish = entitySilverfish2;
   }
}
