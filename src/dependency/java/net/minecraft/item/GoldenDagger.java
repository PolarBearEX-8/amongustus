package net.minecraft.item;

import client.util.ClassEntry3ArrayEntry;
import client.util.ClassEntry3Entry;
import client.util.DistanceAggregator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobspawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenDagger extends ExtendedItemEntryAdapter {
   public boolean enabled;

   public GoldenDagger(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
   }

   public static GoldenDagger resolveGoldenDagger(List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -4, -1, 0, 11, 8, 16, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new GoldenDagger(count4, random, distanceAggregator, enumFacing) : null;
   }

   public GoldenDagger() {
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.enabled = tagCompound.matchesText("Mob");
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("Mob", this.enabled);
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      this.applyWorld(world, distanceAggregator, 0, 0, 0, 10, 7, 15, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
      this.applyWorld(world, random, distanceAggregator, IRONDOOREnum.GRATES, 4, 1, 0);
      this.applyWorld(world, distanceAggregator, 1, 6, 1, 1, 6, 14, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
      this.applyWorld(world, distanceAggregator, 9, 6, 1, 9, 6, 14, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
      this.applyWorld(world, distanceAggregator, 2, 6, 1, 8, 6, 2, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
      this.applyWorld(world, distanceAggregator, 2, 6, 14, 8, 6, 14, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
      this.applyWorld(world, distanceAggregator, 1, 1, 1, 2, 1, 4, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
      this.applyWorld(world, distanceAggregator, 8, 1, 1, 9, 1, 4, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
      this.applyWorld(world, distanceAggregator, 1, 1, 1, 1, 1, 3, Blocks.blockFlowingLava2.getDefaultState(), Blocks.blockFlowingLava2.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 9, 1, 1, 9, 1, 3, Blocks.blockFlowingLava2.getDefaultState(), Blocks.blockFlowingLava2.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 3, 1, 8, 7, 1, 12, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
      this.applyWorld(world, distanceAggregator, 4, 1, 9, 6, 1, 11, Blocks.blockFlowingLava2.getDefaultState(), Blocks.blockFlowingLava2.getDefaultState(), false);

      for (int byteValue = 3; byteValue < 14; byteValue += 2) {
         this.applyWorld(world, distanceAggregator, 0, 3, byteValue, 0, 4, byteValue, Blocks.block127.getDefaultState(), Blocks.block127.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 10, 3, byteValue, 10, 4, byteValue, Blocks.block127.getDefaultState(), Blocks.block127.getDefaultState(), false);
      }

      for (int byteValue2 = 2; byteValue2 < 9; byteValue2 += 2) {
         this.applyWorld(world, distanceAggregator, byteValue2, 3, 15, byteValue2, 4, 15, Blocks.block127.getDefaultState(), Blocks.block127.getDefaultState(), false);
      }

      int count = this.computeCount(Blocks.block130, 3);
      this.applyWorld(world, distanceAggregator, 4, 1, 5, 6, 1, 7, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
      this.applyWorld(world, distanceAggregator, 4, 2, 6, 6, 2, 7, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
      this.applyWorld(world, distanceAggregator, 4, 3, 7, 6, 3, 7, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());

      for (int index = 4; index <= 6; index++) {
         this.applyWorld(world, Blocks.block130.processAction(count), index, 1, 4, distanceAggregator);
         this.applyWorld(world, Blocks.block130.processAction(count), index, 2, 5, distanceAggregator);
         this.applyWorld(world, Blocks.block130.processAction(count), index, 3, 6, distanceAggregator);
      }

      int indexOffset = EnumFacing.NORTH.getIndexOffset();
      int indexOffset2 = EnumFacing.SOUTH.getIndexOffset();
      int indexOffset3 = EnumFacing.EAST.getIndexOffset();
      int indexOffset4 = EnumFacing.WEST.getIndexOffset();
      if (this.enumFacing != null) {
         switch (ExtendedItemEntryAdapterIRONDOOREnumSwitchMap.countArray[this.enumFacing.ordinal()]) {
            case 1:
               indexOffset = EnumFacing.SOUTH.getIndexOffset();
               indexOffset2 = EnumFacing.NORTH.getIndexOffset();
               break;
            case 2:
               indexOffset = EnumFacing.WEST.getIndexOffset();
               indexOffset2 = EnumFacing.EAST.getIndexOffset();
               indexOffset3 = EnumFacing.SOUTH.getIndexOffset();
               indexOffset4 = EnumFacing.NORTH.getIndexOffset();
               break;
            case 3:
               indexOffset = EnumFacing.EAST.getIndexOffset();
               indexOffset2 = EnumFacing.WEST.getIndexOffset();
               indexOffset3 = EnumFacing.SOUTH.getIndexOffset();
               indexOffset4 = EnumFacing.NORTH.getIndexOffset();
         }
      }

      this.applyWorld(
         world, Blocks.block52.processAction(indexOffset).resolveIBlockState(BlockEndPortalFrame.internalImmutableSetEntry, random.nextFloat() > 0.9F), 4, 3, 8, distanceAggregator
      );
      this.applyWorld(
         world, Blocks.block52.processAction(indexOffset).resolveIBlockState(BlockEndPortalFrame.internalImmutableSetEntry, random.nextFloat() > 0.9F), 5, 3, 8, distanceAggregator
      );
      this.applyWorld(
         world, Blocks.block52.processAction(indexOffset).resolveIBlockState(BlockEndPortalFrame.internalImmutableSetEntry, random.nextFloat() > 0.9F), 6, 3, 8, distanceAggregator
      );
      this.applyWorld(
         world, Blocks.block52.processAction(indexOffset2).resolveIBlockState(BlockEndPortalFrame.internalImmutableSetEntry, random.nextFloat() > 0.9F), 4, 3, 12, distanceAggregator
      );
      this.applyWorld(
         world, Blocks.block52.processAction(indexOffset2).resolveIBlockState(BlockEndPortalFrame.internalImmutableSetEntry, random.nextFloat() > 0.9F), 5, 3, 12, distanceAggregator
      );
      this.applyWorld(
         world, Blocks.block52.processAction(indexOffset2).resolveIBlockState(BlockEndPortalFrame.internalImmutableSetEntry, random.nextFloat() > 0.9F), 6, 3, 12, distanceAggregator
      );
      this.applyWorld(
         world, Blocks.block52.processAction(indexOffset3).resolveIBlockState(BlockEndPortalFrame.internalImmutableSetEntry, random.nextFloat() > 0.9F), 3, 3, 9, distanceAggregator
      );
      this.applyWorld(
         world, Blocks.block52.processAction(indexOffset3).resolveIBlockState(BlockEndPortalFrame.internalImmutableSetEntry, random.nextFloat() > 0.9F), 3, 3, 10, distanceAggregator
      );
      this.applyWorld(
         world, Blocks.block52.processAction(indexOffset3).resolveIBlockState(BlockEndPortalFrame.internalImmutableSetEntry, random.nextFloat() > 0.9F), 3, 3, 11, distanceAggregator
      );
      this.applyWorld(
         world, Blocks.block52.processAction(indexOffset4).resolveIBlockState(BlockEndPortalFrame.internalImmutableSetEntry, random.nextFloat() > 0.9F), 7, 3, 9, distanceAggregator
      );
      this.applyWorld(
         world, Blocks.block52.processAction(indexOffset4).resolveIBlockState(BlockEndPortalFrame.internalImmutableSetEntry, random.nextFloat() > 0.9F), 7, 3, 10, distanceAggregator
      );
      this.applyWorld(
         world, Blocks.block52.processAction(indexOffset4).resolveIBlockState(BlockEndPortalFrame.internalImmutableSetEntry, random.nextFloat() > 0.9F), 7, 3, 11, distanceAggregator
      );
      if (!this.enabled) {
         int count2 = this.computeCount(3);
         BlockPos blockPos = new BlockPos(this.computeCountByIndex(5, 6), count2, this.computeCount(5, 6));
         if (distanceAggregator.matchesVec3i(blockPos)) {
            this.enabled = true;
            world.matchesBlockPos(blockPos, Blocks.block29.getDefaultState(), 2);
            TileEntity tileEntity = world.resolveTileEntity(blockPos);
            if (tileEntity instanceof TileEntityMobspawner) {
               ((TileEntityMobspawner)tileEntity).getPassiveStringEntry().setLabelText("Silverfish");
            }
         }
      }

      return true;
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      if (goldenIngot != null) {
         ((ClassEntry3Entry)goldenIngot).goldenDagger = this;
      }
   }
}
