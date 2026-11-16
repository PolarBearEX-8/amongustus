package client.util;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityEnderman;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class AuxiliaryClientManagerHandler extends RotationPool {
   public EntityEnderman entityEnderman;

   public AuxiliaryClientManagerHandler(EntityEnderman entityEnderman2) {
      this.entityEnderman = entityEnderman2;
   }

   @Override
   public boolean isActive() {
      return !this.entityEnderman.worldObj.getDoFireTickManager().matchesText("mobGriefing")
         ? false
         : (this.entityEnderman.getIBlockState().getBlock().getMaterial() != Material.coreItemEntry ? false : this.entityEnderman.getRandom().nextInt(20) == 0);
   }

   @Override
   public void updateState2() {
      Random random = this.entityEnderman.getRandom();
      World world = this.entityEnderman.worldObj;
      int count = MathHelper.ceiling_double_int(this.entityEnderman.posX - 2.0 + random.nextDouble() * 4.0);
      int count2 = MathHelper.ceiling_double_int(this.entityEnderman.posY + random.nextDouble() * 3.0);
      int count3 = MathHelper.ceiling_double_int(this.entityEnderman.posZ - 2.0 + random.nextDouble() * 4.0);
      BlockPos blockPos = new BlockPos(count, count2, count3);
      IBlockState blockState = world.getBlockState(blockPos);
      Block block = blockState.getBlock();
      if (EntityEnderman.getSet().contains(block)) {
         this.entityEnderman.applyIBlockState(blockState);
         world.matchesBlockPos(blockPos, Blocks.air.getDefaultState());
      }
   }
}
