package client.util;

import java.util.Random;
import net.minecraft.block.SecondaryBlockAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class AuxiliaryClientManagerController extends CurveParser {
   public SecondaryBlockAdapter secondaryBlockAdapter;

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      for (int index = 0; index < 64; index++) {
         BlockPos blockPos2 = blockPos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
         if (world.isAirBlock(blockPos2)
            && (!world.secondaryFloatArrayEntry.isEnabled() || blockPos2.getY() < 255)
            && this.secondaryBlockAdapter.matchesWorld(world, blockPos2, this.secondaryBlockAdapter.getDefaultState())) {
            world.matchesBlockPos(blockPos2, this.secondaryBlockAdapter.getDefaultState(), 2);
         }
      }

      return true;
   }

   public AuxiliaryClientManagerController(SecondaryBlockAdapter secondaryBlockAdapter2) {
      this.secondaryBlockAdapter = secondaryBlockAdapter2;
   }
}
