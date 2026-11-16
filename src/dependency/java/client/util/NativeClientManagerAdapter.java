package client.util;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityEnderman;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class NativeClientManagerAdapter extends RotationPool {
   public EntityEnderman entityEnderman;

   @Override
   public boolean isActive() {
      return !this.entityEnderman.worldObj.getDoFireTickManager().matchesText("mobGriefing")
         ? false
         : (
            this.entityEnderman.getIBlockState().getBlock().getMaterial() == Material.coreItemEntry
               ? false
               : this.entityEnderman.getRandom().nextInt(2000) == 0
         );
   }

   public NativeClientManagerAdapter(EntityEnderman entityEnderman2) {
      this.entityEnderman = entityEnderman2;
   }

   @Override
   public void updateState2() {
      Random random = this.entityEnderman.getRandom();
      World world = this.entityEnderman.worldObj;
      int count = MathHelper.ceiling_double_int(this.entityEnderman.posX - 1.0 + random.nextDouble() * 2.0);
      int count2 = MathHelper.ceiling_double_int(this.entityEnderman.posY + random.nextDouble() * 2.0);
      int count3 = MathHelper.ceiling_double_int(this.entityEnderman.posZ - 1.0 + random.nextDouble() * 2.0);
      BlockPos blockPos = new BlockPos(count, count2, count3);
      Block block = world.getBlockState(blockPos).getBlock();
      Block block2 = world.getBlockState(blockPos.down()).getBlock();
      if (this.matchesWorld(world, blockPos, this.entityEnderman.getIBlockState().getBlock(), block, block2)) {
         world.matchesBlockPos(blockPos, this.entityEnderman.getIBlockState(), 3);
         this.entityEnderman.applyIBlockState(Blocks.air.getDefaultState());
      }
   }

   private boolean matchesWorld(World world, BlockPos blockPos, Block block, Block block2, Block block3) {
      return !block.processAction7(world, blockPos)
         ? false
         : (block2.getMaterial() != Material.coreItemEntry ? false : (block3.getMaterial() == Material.coreItemEntry ? false : block3.processAction14()));
   }
}
