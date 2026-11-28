package net.minecraft.world.gen.feature;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;

public class WorldGeneratorBonusChest extends WorldGenerator {
   private final List chestItems;
   private final int itemsToGenerateInBonusChest;

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      Block block;
      while (((block = world.getBlockState(pos).getBlock()).getMaterial() == Material.air || block.getMaterial() == Material.leaves) && pos.getY() > 1) {
         pos = pos.down();
      }

      if (pos.getY() < 1) {
         return false;
      } else {
         pos = pos.up();

         for (int i = 0; i < 4; i++) {
            BlockPos pos2 = pos.add(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(4) - rand.nextInt(4));
            if (world.isAirBlock(pos2) && World.doesBlockHaveSolidTopSurface(world, pos2.getX(), pos2.getY() - 1, pos2.getZ())) {
               world.setBlockState(pos2, Blocks.chest.getDefaultState(), 2);
               TileEntity tileEntity = world.getTileEntity(pos2);
               if (tileEntity instanceof TileEntityChest) {
                  WeightedRandomChestContent.generateChestContents(rand, this.chestItems, (TileEntityChest)tileEntity, this.itemsToGenerateInBonusChest);
               }

               BlockPos pos3 = pos2.east();
               BlockPos pos4 = pos2.west();
               BlockPos pos5 = pos2.north();
               BlockPos pos6 = pos2.south();
               if (world.isAirBlock(pos4) && World.doesBlockHaveSolidTopSurface(world, pos4.getX(), pos4.getY() - 1, pos4.getZ())) {
                  world.setBlockState(pos4, Blocks.torch.getDefaultState(), 2);
               }

               if (world.isAirBlock(pos3) && World.doesBlockHaveSolidTopSurface(world, pos3.getX(), pos3.getY() - 1, pos3.getZ())) {
                  world.setBlockState(pos3, Blocks.torch.getDefaultState(), 2);
               }

               if (world.isAirBlock(pos5) && World.doesBlockHaveSolidTopSurface(world, pos5.getX(), pos5.getY() - 1, pos5.getZ())) {
                  world.setBlockState(pos5, Blocks.torch.getDefaultState(), 2);
               }

               if (world.isAirBlock(pos6) && World.doesBlockHaveSolidTopSurface(world, pos6.getX(), pos6.getY() - 1, pos6.getZ())) {
                  world.setBlockState(pos6, Blocks.torch.getDefaultState(), 2);
               }

               return true;
            }
         }

         return false;
      }
   }

   public WorldGeneratorBonusChest(List list, int n) {
      this.chestItems = list;
      this.itemsToGenerateInBonusChest = n;
   }
}
