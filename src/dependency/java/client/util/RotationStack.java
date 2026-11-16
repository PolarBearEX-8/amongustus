package client.util;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class RotationStack extends CurveParser {
   public final List list;
   public final int count;

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      BlockPos blockPos2 = blockPos;

      Block block;
      while (
         ((block = world.getBlockState(blockPos2).getBlock()).getMaterial() == Material.coreItemEntry || block.getMaterial() == Material.material9) && blockPos2.getY() > 1
      ) {
         blockPos2 = blockPos2.down();
      }

      if (blockPos2.getY() < 1) {
         return false;
      } else {
         blockPos2 = blockPos2.up();

         for (int index = 0; index < 4; index++) {
            BlockPos blockPos3 = blockPos2.add(random.nextInt(4) - random.nextInt(4), random.nextInt(3) - random.nextInt(3), random.nextInt(4) - random.nextInt(4));
            if (world.isAirBlock(blockPos3) && World.matchesIBlockAccess(world, blockPos3.getX(), blockPos3.getY() - 1, blockPos3.getZ())) {
               world.matchesBlockPos(blockPos3, Blocks.blockTrappedChest.getDefaultState(), 2);
               TileEntity tileEntity = world.resolveTileEntity(blockPos3);
               if (tileEntity instanceof TileEntityChest) {
                  ClientManagerAdapter2.applyRandom(random, this.list, (TileEntityChest)tileEntity, this.count);
               }

               BlockPos blockPos4 = blockPos3.getBlockPos3();
               BlockPos blockPos5 = blockPos3.getBlockPos();
               BlockPos blockPosAsBlockPos = blockPos3.getBlockPosAsBlockPos();
               BlockPos blockPos6 = blockPos3.getBlockPos2();
               if (world.isAirBlock(blockPos5) && World.matchesIBlockAccess(world, blockPos5.getX(), blockPos5.getY() - 1, blockPos5.getZ())) {
                  world.matchesBlockPos(blockPos5, Blocks.block21.getDefaultState(), 2);
               }

               if (world.isAirBlock(blockPos4) && World.matchesIBlockAccess(world, blockPos4.getX(), blockPos4.getY() - 1, blockPos4.getZ())) {
                  world.matchesBlockPos(blockPos4, Blocks.block21.getDefaultState(), 2);
               }

               if (world.isAirBlock(blockPosAsBlockPos) && World.matchesIBlockAccess(world, blockPosAsBlockPos.getX(), blockPosAsBlockPos.getY() - 1, blockPosAsBlockPos.getZ())) {
                  world.matchesBlockPos(blockPosAsBlockPos, Blocks.block21.getDefaultState(), 2);
               }

               if (world.isAirBlock(blockPos6) && World.matchesIBlockAccess(world, blockPos6.getX(), blockPos6.getY() - 1, blockPos6.getZ())) {
                  world.matchesBlockPos(blockPos6, Blocks.block21.getDefaultState(), 2);
               }

               return true;
            }
         }

         return false;
      }
   }

   public RotationStack(List list2, int count2) {
      this.list = list2;
      this.count = count2;
   }
}
