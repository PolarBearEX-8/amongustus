package net.minecraft.item;

import client.util.ClientManagerAdapter2;
import client.util.CustomIBlockStateEntry;
import client.util.DistanceAggregator;
import client.util.EnhancedIntArrayEntry;
import client.util.EnhancedLoggerEntry;
import client.util.IBlockState;
import java.util.List;
import java.util.Random;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.BaseBlockAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDarkOakDoor;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityTrap;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class GoldenIngot {
   public DistanceAggregator distanceAggregator;
   public EnumFacing enumFacing;
   public int count;

   public void applyWorld(World world, DistanceAggregator distanceAggregator2, Random random, float factor, int count2, int count3, int count4, IBlockState blockState) {
      if (random.nextFloat() < factor) {
         this.applyWorld(world, blockState, count2, count3, count4, distanceAggregator2);
      }
   }

   public void applyWorld(World world, IBlockState blockState, int count2, int count3, int count4, DistanceAggregator distanceAggregator2) {
      BlockPos blockPos = new BlockPos(this.computeCountByIndex(count2, count4), this.computeCount(count3), this.computeCount(count2, count4));
      if (distanceAggregator2.matchesVec3i(blockPos)) {
         world.matchesBlockPos(blockPos, blockState, 2);
      }
   }

   public int getCount() {
      return this.count;
   }

   public void applyIndex(int count2, int count3, int count4) {
      this.distanceAggregator.applyIndex(count2, count3, count4);
   }

   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
   }

   public IBlockState resolveIBlockState(World world, int count2, int count3, int count4, DistanceAggregator distanceAggregator2) {
      int count5 = this.computeCountByIndex(count2, count4);
      int count6 = this.computeCount(count3);
      int count7 = this.computeCount(count2, count4);
      BlockPos blockPos = new BlockPos(count5, count6, count7);
      return !distanceAggregator2.matchesVec3i(blockPos) ? Blocks.air.getDefaultState() : world.getBlockState(blockPos);
   }

   public int computeCount(int count2, int count3) {
      if (this.enumFacing == null) {
         return count3;
      } else {
         switch (EnhancedIntArrayEntry.countArray[this.enumFacing.ordinal()]) {
            case 1:
               return this.distanceAggregator.count - count3;
            case 2:
               return this.distanceAggregator.count4 + count3;
            case 3:
            case 4:
               return this.distanceAggregator.count4 + count2;
            default:
               return count3;
         }
      }
   }

   public boolean matchesWorld(World world, DistanceAggregator distanceAggregator2) {
      int count2 = Math.max(this.distanceAggregator.count3 - 1, distanceAggregator2.count3);
      int count3 = Math.max(this.distanceAggregator.count6 - 1, distanceAggregator2.count6);
      int count4 = Math.max(this.distanceAggregator.count4 - 1, distanceAggregator2.count4);
      int count5 = Math.min(this.distanceAggregator.count5 + 1, distanceAggregator2.count5);
      int count6 = Math.min(this.distanceAggregator.count2 + 1, distanceAggregator2.count2);
      int count7 = Math.min(this.distanceAggregator.count + 1, distanceAggregator2.count);
      AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

      for (int index = count2; index <= count5; index++) {
         for (int index2 = count4; index2 <= count7; index2++) {
            if (world.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index, count3, index2)).getBlock().getMaterial().isActive2()) {
               return true;
            }

            if (world.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index, count6, index2)).getBlock().getMaterial().isActive2()) {
               return true;
            }
         }
      }

      for (int index3 = count2; index3 <= count5; index3++) {
         for (int index4 = count3; index4 <= count6; index4++) {
            if (world.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index3, index4, count4)).getBlock().getMaterial().isActive2()) {
               return true;
            }

            if (world.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index3, index4, count7)).getBlock().getMaterial().isActive2()) {
               return true;
            }
         }
      }

      for (int index5 = count4; index5 <= count7; index5++) {
         for (int index6 = count3; index6 <= count6; index6++) {
            if (world.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count2, index6, index5)).getBlock().getMaterial().isActive2()) {
               return true;
            }

            if (world.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count5, index6, index5)).getBlock().getMaterial().isActive2()) {
               return true;
            }
         }
      }

      return false;
   }

   public void applyWorld(World world, NBTTagCompound tagCompound) {
      if (tagCompound.containsKey("BB")) {
         this.distanceAggregator = new DistanceAggregator(tagCompound.resolveIndexArray("BB"));
      }

      int count2 = tagCompound.computeCount("O");
      this.enumFacing = count2 == -1 ? null : EnumFacing.resolveEnumFacing(count2);
      this.count = tagCompound.computeCount("GD");
      this.applyNBTTagCompound(tagCompound);
   }

   public void applyWorld(
      World world, DistanceAggregator distanceAggregator2, int count2, int count3, int count4, int count5, int count6, int count7, IBlockState blockState, IBlockState blockState2, boolean flag
   ) {
      for (int index = count3; index <= count6; index++) {
         for (int index2 = count2; index2 <= count5; index2++) {
            for (int index3 = count4; index3 <= count7; index3++) {
               if (!flag || this.resolveIBlockState(world, index2, index, index3, distanceAggregator2).getBlock().getMaterial() != Material.coreItemEntry) {
                  if (index != count3 && index != count6 && index2 != count2 && index2 != count5 && index3 != count4 && index3 != count7) {
                     this.applyWorld(world, blockState2, index2, index, index3, distanceAggregator2);
                  } else {
                     this.applyWorld(world, blockState, index2, index, index3, distanceAggregator2);
                  }
               }
            }
         }
      }
   }

   public GoldenIngot() {
   }

   public DistanceAggregator getDistanceAggregator() {
      return this.distanceAggregator;
   }

   public abstract boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator2);

   public void applyWorld(World world, DistanceAggregator distanceAggregator2, int count2, int count3, int count4, int count5, int count6, int count7, IBlockState blockState, boolean flag) {
      float factor = (float)(count5 - count2 + 1);
      float factor2 = (float)(count6 - count3 + 1);
      float factor3 = (float)(count7 - count4 + 1);
      float factor4 = (float)count2 + factor / 2.0F;
      float factor5 = (float)count4 + factor3 / 2.0F;

      for (int index = count3; index <= count6; index++) {
         float factor6 = (float)(index - count3) / factor2;

         for (int index2 = count2; index2 <= count5; index2++) {
            float factor7 = ((float)index2 - factor4) / (factor * 0.5F);

            for (int index3 = count4; index3 <= count7; index3++) {
               float factor8 = ((float)index3 - factor5) / (factor3 * 0.5F);
               if ((!flag || this.resolveIBlockState(world, index2, index, index3, distanceAggregator2).getBlock().getMaterial() != Material.coreItemEntry)
                  && factor7 * factor7 + factor6 * factor6 + factor8 * factor8 <= 1.05F) {
                  this.applyWorld(world, blockState, index2, index, index3, distanceAggregator2);
               }
            }
         }
      }
   }

   public void applyWorld(
      World world, DistanceAggregator distanceAggregator2, int count2, int count3, int count4, int count5, int count6, int count7, boolean flag, Random random, CustomIBlockStateEntry customIBlockStateEntry
   ) {
      for (int index = count3; index <= count6; index++) {
         for (int index2 = count2; index2 <= count5; index2++) {
            for (int index3 = count4; index3 <= count7; index3++) {
               if (!flag || this.resolveIBlockState(world, index2, index, index3, distanceAggregator2).getBlock().getMaterial() != Material.coreItemEntry) {
                  customIBlockStateEntry.applyRandom(
                     random, index2, index, index3, index == count3 || index == count6 || index2 == count2 || index2 == count5 || index3 == count4 || index3 == count7
                  );
                  this.applyWorld(world, customIBlockStateEntry.getIBlockState(), index2, index, index3, distanceAggregator2);
               }
            }
         }
      }
   }

   public void applyWorld(World world, int count2, int count3, int count4, DistanceAggregator distanceAggregator2) {
      BlockPos blockPos = new BlockPos(this.computeCountByIndex(count2, count4), this.computeCount(count3), this.computeCount(count2, count4));
      if (distanceAggregator2.matchesVec3i(blockPos)) {
         while (!world.isAirBlock(blockPos) && blockPos.getY() < 255) {
            world.matchesBlockPos(blockPos, Blocks.air.getDefaultState(), 2);
            blockPos = blockPos.up();
         }
      }
   }

   public abstract void applyNBTTagCompound(NBTTagCompound tagCompound);

   public void applyWorldByWorld(World world, IBlockState blockState, int count2, int count3, int count4, DistanceAggregator distanceAggregator2) {
      int count5 = this.computeCountByIndex(count2, count4);
      int count6 = this.computeCount(count3);
      int count7 = this.computeCount(count2, count4);
      if (distanceAggregator2.matchesVec3i(new BlockPos(count5, count6, count7))) {
         while (
            (world.isAirBlock(new BlockPos(count5, count6, count7)) || world.getBlockState(new BlockPos(count5, count6, count7)).getBlock().getMaterial().isActive2())
               && count6 > 1
         ) {
            world.matchesBlockPos(new BlockPos(count5, count6, count7), blockState, 2);
            count6--;
         }
      }
   }

   public BlockPos getBlockPos() {
      return new BlockPos(this.distanceAggregator.getVec3iAsVec3i());
   }

   public GoldenIngot(int count2) {
      this.count = count2;
   }

   public void applyWorld(World world, DistanceAggregator distanceAggregator2, Random random, int count2, int count3, int count4, EnumFacing enumFacing2) {
      BlockPos blockPos = new BlockPos(this.computeCountByIndex(count2, count4), this.computeCount(count3), this.computeCount(count2, count4));
      if (distanceAggregator2.matchesVec3i(blockPos)) {
         ItemDarkOakDoor.applyWorldByWorld(world, blockPos, enumFacing2.getEnumFacing2(), Blocks.block110);
      }
   }

   public NBTTagCompound getNBTTagCompound() {
      NBTTagCompound tagCompound = new NBTTagCompound();
      tagCompound.put("id", EnhancedLoggerEntry.resolveText(this));
      tagCompound.applyText("BB", this.distanceAggregator.getProviderIntArrayEntry());
      tagCompound.put("O", this.enumFacing == null ? -1 : this.enumFacing.getIndexOffset());
      tagCompound.put("GD", this.count);
      this.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      return tagCompound;
   }

   public void applyWorld(
      World world,
      DistanceAggregator distanceAggregator2,
      Random random,
      float factor,
      int count2,
      int count3,
      int count4,
      int count5,
      int count6,
      int count7,
      IBlockState blockState,
      IBlockState blockState2,
      boolean flag
   ) {
      for (int index = count3; index <= count6; index++) {
         for (int index2 = count2; index2 <= count5; index2++) {
            for (int index3 = count4; index3 <= count7; index3++) {
               if (random.nextFloat() <= factor
                  && (!flag || this.resolveIBlockState(world, index2, index, index3, distanceAggregator2).getBlock().getMaterial() != Material.coreItemEntry)) {
                  if (index != count3 && index != count6 && index2 != count2 && index2 != count5 && index3 != count4 && index3 != count7) {
                     this.applyWorld(world, blockState2, index2, index, index3, distanceAggregator2);
                  } else {
                     this.applyWorld(world, blockState, index2, index, index3, distanceAggregator2);
                  }
               }
            }
         }
      }
   }

   public int computeCountByIndex(int count2, int count3) {
      if (this.enumFacing == null) {
         return count2;
      } else {
         switch (EnhancedIntArrayEntry.countArray[this.enumFacing.ordinal()]) {
            case 1:
            case 2:
               return this.distanceAggregator.count3 + count2;
            case 3:
               return this.distanceAggregator.count5 - count3;
            case 4:
               return this.distanceAggregator.count3 + count3;
            default:
               return count2;
         }
      }
   }

   public abstract void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound);

   public int computeCount(int count2) {
      return this.enumFacing == null ? count2 : count2 + this.distanceAggregator.count6;
   }

   public int computeCount(Block block, int count2) {
      if (block == Blocks.block13) {
         if (this.enumFacing == EnumFacing.WEST || this.enumFacing == EnumFacing.EAST) {
            if (count2 == 1) {
               return 0;
            }

            return 1;
         }
      } else if (block instanceof BlockDarkOakDoor) {
         if (this.enumFacing == EnumFacing.SOUTH) {
            if (count2 == 0) {
               return 2;
            }

            if (count2 == 2) {
               return 0;
            }
         } else {
            if (this.enumFacing == EnumFacing.WEST) {
               return count2 + 1 & 3;
            }

            if (this.enumFacing == EnumFacing.EAST) {
               return count2 + 3 & 3;
            }
         }
      } else if (block != Blocks.block125 && block != Blocks.block3 && block != Blocks.block24 && block != Blocks.block130 && block != Blocks.block122) {
         if (block == Blocks.block81) {
            if (this.enumFacing == EnumFacing.SOUTH) {
               if (count2 == EnumFacing.NORTH.getIndexOffset1()) {
                  return EnumFacing.SOUTH.getIndexOffset1();
               }

               if (count2 == EnumFacing.SOUTH.getIndexOffset1()) {
                  return EnumFacing.NORTH.getIndexOffset1();
               }
            } else if (this.enumFacing == EnumFacing.WEST) {
               if (count2 == EnumFacing.NORTH.getIndexOffset1()) {
                  return EnumFacing.WEST.getIndexOffset1();
               }

               if (count2 == EnumFacing.SOUTH.getIndexOffset1()) {
                  return EnumFacing.EAST.getIndexOffset1();
               }

               if (count2 == EnumFacing.WEST.getIndexOffset1()) {
                  return EnumFacing.NORTH.getIndexOffset1();
               }

               if (count2 == EnumFacing.EAST.getIndexOffset1()) {
                  return EnumFacing.SOUTH.getIndexOffset1();
               }
            } else if (this.enumFacing == EnumFacing.EAST) {
               if (count2 == EnumFacing.NORTH.getIndexOffset1()) {
                  return EnumFacing.EAST.getIndexOffset1();
               }

               if (count2 == EnumFacing.SOUTH.getIndexOffset1()) {
                  return EnumFacing.WEST.getIndexOffset1();
               }

               if (count2 == EnumFacing.WEST.getIndexOffset1()) {
                  return EnumFacing.NORTH.getIndexOffset1();
               }

               if (count2 == EnumFacing.EAST.getIndexOffset1()) {
                  return EnumFacing.SOUTH.getIndexOffset1();
               }
            }
         } else if (block == Blocks.block75) {
            if (this.enumFacing == EnumFacing.SOUTH) {
               if (count2 == 3) {
                  return 4;
               }

               if (count2 == 4) {
                  return 3;
               }
            } else if (this.enumFacing == EnumFacing.WEST) {
               if (count2 == 3) {
                  return 1;
               }

               if (count2 == 4) {
                  return 2;
               }

               if (count2 == 2) {
                  return 3;
               }

               if (count2 == 1) {
                  return 4;
               }
            } else if (this.enumFacing == EnumFacing.EAST) {
               if (count2 == 3) {
                  return 2;
               }

               if (count2 == 4) {
                  return 1;
               }

               if (count2 == 2) {
                  return 3;
               }

               if (count2 == 1) {
                  return 4;
               }
            }
         } else if (block == Blocks.blockTripwireHook || block instanceof BaseBlockAdapter) {
            EnumFacing enumFacing2 = EnumFacing.resolveEnumFacing(count2);
            if (this.enumFacing == EnumFacing.SOUTH) {
               if (enumFacing2 == EnumFacing.SOUTH || enumFacing2 == EnumFacing.NORTH) {
                  return enumFacing2.getEnumFacing5().getIndexOffset();
               }
            } else if (this.enumFacing == EnumFacing.WEST) {
               if (enumFacing2 == EnumFacing.NORTH) {
                  return EnumFacing.WEST.getIndexOffset();
               }

               if (enumFacing2 == EnumFacing.SOUTH) {
                  return EnumFacing.EAST.getIndexOffset();
               }

               if (enumFacing2 == EnumFacing.WEST) {
                  return EnumFacing.NORTH.getIndexOffset();
               }

               if (enumFacing2 == EnumFacing.EAST) {
                  return EnumFacing.SOUTH.getIndexOffset();
               }
            } else if (this.enumFacing == EnumFacing.EAST) {
               if (enumFacing2 == EnumFacing.NORTH) {
                  return EnumFacing.EAST.getIndexOffset();
               }

               if (enumFacing2 == EnumFacing.SOUTH) {
                  return EnumFacing.WEST.getIndexOffset();
               }

               if (enumFacing2 == EnumFacing.WEST) {
                  return EnumFacing.NORTH.getIndexOffset();
               }

               if (enumFacing2 == EnumFacing.EAST) {
                  return EnumFacing.SOUTH.getIndexOffset();
               }
            }
         } else if (block == Blocks.blockPiston2 || block == Blocks.blockPiston || block == Blocks.block48 || block == Blocks.block129) {
            if (this.enumFacing == EnumFacing.SOUTH) {
               if (count2 == EnumFacing.NORTH.getIndexOffset1() || count2 == EnumFacing.SOUTH.getIndexOffset1()) {
                  return EnumFacing.getEnumFacing6(count2).getEnumFacing5().getIndexOffset1();
               }
            } else if (this.enumFacing == EnumFacing.WEST) {
               if (count2 == EnumFacing.NORTH.getIndexOffset1()) {
                  return EnumFacing.WEST.getIndexOffset1();
               }

               if (count2 == EnumFacing.SOUTH.getIndexOffset1()) {
                  return EnumFacing.EAST.getIndexOffset1();
               }

               if (count2 == EnumFacing.WEST.getIndexOffset1()) {
                  return EnumFacing.NORTH.getIndexOffset1();
               }

               if (count2 == EnumFacing.EAST.getIndexOffset1()) {
                  return EnumFacing.SOUTH.getIndexOffset1();
               }
            } else if (this.enumFacing == EnumFacing.EAST) {
               if (count2 == EnumFacing.NORTH.getIndexOffset1()) {
                  return EnumFacing.EAST.getIndexOffset1();
               }

               if (count2 == EnumFacing.SOUTH.getIndexOffset1()) {
                  return EnumFacing.WEST.getIndexOffset1();
               }

               if (count2 == EnumFacing.WEST.getIndexOffset1()) {
                  return EnumFacing.NORTH.getIndexOffset1();
               }

               if (count2 == EnumFacing.EAST.getIndexOffset1()) {
                  return EnumFacing.SOUTH.getIndexOffset1();
               }
            }
         }
      } else if (this.enumFacing == EnumFacing.SOUTH) {
         if (count2 == 2) {
            return 3;
         }

         if (count2 == 3) {
            return 2;
         }
      } else if (this.enumFacing == EnumFacing.WEST) {
         if (count2 == 0) {
            return 2;
         }

         if (count2 == 1) {
            return 3;
         }

         if (count2 == 2) {
            return 0;
         }

         if (count2 == 3) {
            return 1;
         }
      } else if (this.enumFacing == EnumFacing.EAST) {
         if (count2 == 0) {
            return 2;
         }

         if (count2 == 1) {
            return 3;
         }

         if (count2 == 2) {
            return 1;
         }

         if (count2 == 3) {
            return 0;
         }
      }

      return count2;
   }

   public boolean matchesWorld(World world, DistanceAggregator distanceAggregator2, Random random, int count2, int count3, int count4, int count5, List list, int count6) {
      BlockPos blockPos = new BlockPos(this.computeCountByIndex(count2, count4), this.computeCount(count3), this.computeCount(count2, count4));
      if (distanceAggregator2.matchesVec3i(blockPos) && world.getBlockState(blockPos).getBlock() != Blocks.block129) {
         world.matchesBlockPos(blockPos, Blocks.block129.processAction(this.computeCount(Blocks.block129, count5)), 2);
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityTrap) {
            ClientManagerAdapter2.applyRandom(random, list, (TileEntityTrap)tileEntity, count6);
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean matchesWorld(World world, DistanceAggregator distanceAggregator2, Random random, int count2, int count3, int count4, List list, int count5) {
      BlockPos blockPos = new BlockPos(this.computeCountByIndex(count2, count4), this.computeCount(count3), this.computeCount(count2, count4));
      if (distanceAggregator2.matchesVec3i(blockPos) && world.getBlockState(blockPos).getBlock() != Blocks.blockTrappedChest) {
         IBlockState defaultState = Blocks.blockTrappedChest.getDefaultState();
         world.matchesBlockPos(blockPos, Blocks.blockTrappedChest.resolveIBlockState3(world, blockPos, defaultState), 2);
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityChest) {
            ClientManagerAdapter2.applyRandom(random, list, (TileEntityChest)tileEntity, count5);
         }

         return true;
      } else {
         return false;
      }
   }

   public static GoldenIngot resolveGoldenIngot(List list, DistanceAggregator distanceAggregator2) {
      for (GoldenIngot goldenIngot : (Iterable<GoldenIngot>)(list)) {
         if (goldenIngot.getDistanceAggregator() != null && goldenIngot.getDistanceAggregator().matchesDistanceAggregator(distanceAggregator2)) {
            return goldenIngot;
         }
      }

      return null;
   }

   public void applyWorld(World world, DistanceAggregator distanceAggregator2, int count2, int count3, int count4, int count5, int count6, int count7) {
      for (int index = count3; index <= count6; index++) {
         for (int index2 = count2; index2 <= count5; index2++) {
            for (int index3 = count4; index3 <= count7; index3++) {
               this.applyWorld(world, Blocks.air.getDefaultState(), index2, index, index3, distanceAggregator2);
            }
         }
      }
   }
}
