package net.minecraft.item;

import client.event.PostRenderConsumer;
import client.setting.InternalOptionType;
import client.util.AngleService;
import client.util.DistanceAggregator;
import client.util.HitBoxTransformerEnumFacingSwitchMap;
import client.util.IBlockState;
import client.util.InvalidBiomeIdManagerEntry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.BlockRedSandstoneStairs;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityVillager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class InternalItemEntryAdapter extends GoldenIngot {
   public int count2 = -1;
   public int count3;
   public boolean enabled;

   public int computeCount2(int count, int count4) {
      return count4;
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   @Override
   public void applyWorld(World world, IBlockState blockState, int count, int count4, int count5, DistanceAggregator distanceAggregator) {
      IBlockState blockState2 = this.resolveIBlockState(blockState);
      super.applyWorld(world, blockState2, count, count4, count5, distanceAggregator);
   }

   public static boolean matchesDistanceAggregator(DistanceAggregator distanceAggregator) {
      return distanceAggregator != null && distanceAggregator.count6 > 10;
   }

   public GoldenIngot resolveGoldenIngotByInvalidBiomeIdMa(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list, Random random, int count, int count4) {
      if (this.enumFacing != null) {
         switch (HitBoxTransformerEnumFacingSwitchMap.countArray[this.enumFacing.ordinal()]) {
            case 1:
               return AngleService.resolveGoldenIngot3(
                  invalidBiomeIdManagerEntry,
                  list,
                  random,
                  this.distanceAggregator.count3 - 1,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 + count4,
                  EnumFacing.WEST,
                  this.getCount()
               );
            case 2:
               return AngleService.resolveGoldenIngot3(
                  invalidBiomeIdManagerEntry,
                  list,
                  random,
                  this.distanceAggregator.count3 - 1,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 + count4,
                  EnumFacing.WEST,
                  this.getCount()
               );
            case 3:
               return AngleService.resolveGoldenIngot3(
                  invalidBiomeIdManagerEntry,
                  list,
                  random,
                  this.distanceAggregator.count3 + count4,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 - 1,
                  EnumFacing.NORTH,
                  this.getCount()
               );
            case 4:
               return AngleService.resolveGoldenIngot3(
                  invalidBiomeIdManagerEntry,
                  list,
                  random,
                  this.distanceAggregator.count3 + count4,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 - 1,
                  EnumFacing.NORTH,
                  this.getCount()
               );
         }
      }

      return null;
   }

   public IBlockState resolveIBlockState(IBlockState blockState) {
      if (this.enabled) {
         if (blockState.getBlock() == Blocks.block140 || blockState.getBlock() == Blocks.block104) {
            return Blocks.block136.getDefaultState();
         }

         if (blockState.getBlock() == Blocks.block65) {
            return Blocks.block136.processAction(InternalOptionType.DEFAULT.getCount());
         }

         if (blockState.getBlock() == Blocks.block142) {
            return Blocks.block136.processAction(InternalOptionType.SMOOTH.getCount());
         }

         if (blockState.getBlock() == Blocks.block3) {
            return Blocks.block122
               .getDefaultState()
               .resolveIBlockState(BlockRedSandstoneStairs.autoWalkPath, blockState.getComparable(BlockRedSandstoneStairs.autoWalkPath));
         }

         if (blockState.getBlock() == Blocks.block125) {
            return Blocks.block122
               .getDefaultState()
               .resolveIBlockState(BlockRedSandstoneStairs.autoWalkPath, blockState.getComparable(BlockRedSandstoneStairs.autoWalkPath));
         }

         if (blockState.getBlock() == Blocks.block45) {
            return Blocks.block136.getDefaultState();
         }
      }

      return blockState;
   }

   public InternalItemEntryAdapter(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, int count) {
      super(count);
      if (invalidBiomeIdManagerEntry != null) {
         this.enabled = invalidBiomeIdManagerEntry.enabled2;
      }
   }

   public InternalItemEntryAdapter() {
   }

   @Override
   public void applyWorld(
      World world, DistanceAggregator distanceAggregator, int count, int count4, int count5, int count6, int count7, int count8, IBlockState blockState, IBlockState blockState2, boolean flag
   ) {
      IBlockState blockState3 = this.resolveIBlockState(blockState);
      IBlockState blockState4 = this.resolveIBlockState(blockState2);
      super.applyWorld(world, distanceAggregator, count, count4, count5, count6, count7, count8, blockState3, blockState4, flag);
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      this.count2 = tagCompound.computeCount("HPos");
      this.count3 = tagCompound.computeCount("VCount");
      this.enabled = tagCompound.matchesText("Desert");
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("HPos", this.count2);
      tagCompound.put("VCount", this.count3);
      tagCompound.applyText("Desert", this.enabled);
   }

   public int computeCount(World world, DistanceAggregator distanceAggregator) {
      int count = 0;
      int count4 = 0;
      AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

      for (int index = this.distanceAggregator.count4; index <= this.distanceAggregator.count; index++) {
         for (int index2 = this.distanceAggregator.count3; index2 <= this.distanceAggregator.count5; index2++) {
            auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index2, 64, index);
            if (distanceAggregator.matchesVec3i(auxiliaryBlockPosAdapter)) {
               count += Math.max(world.resolveBlockPos(auxiliaryBlockPosAdapter).getY(), world.secondaryFloatArrayEntry.getCount());
               count4++;
            }
         }
      }

      return count4 == 0 ? -1 : count / count4;
   }

   @Override
   public void applyWorldByWorld(World world, IBlockState blockState, int count, int count4, int count5, DistanceAggregator distanceAggregator) {
      IBlockState blockState2 = this.resolveIBlockState(blockState);
      super.applyWorldByWorld(world, blockState2, count, count4, count5, distanceAggregator);
   }

   public void applyWorld(World world, DistanceAggregator distanceAggregator, int count, int count4, int count5, int count6) {
      if (this.count3 < count6) {
         for (int index = this.count3; index < count6; index++) {
            int count7 = this.computeCountByIndex(count + index, count5);
            int count8 = this.computeCount(count4);
            int count9 = this.computeCount(count + index, count5);
            if (!distanceAggregator.matchesVec3i(new BlockPos(count7, count8, count9))) {
               break;
            }

            this.count3++;
            EntityVillager entityVillager = new EntityVillager(world);
            entityVillager.applyDoubleByDouble((double)count7 + 0.5, (double)count8, (double)count9 + 0.5, 0.0F, 0.0F);
            entityVillager.resolvePostRenderConsumer(world.resolveGoldenOre(new BlockPos(entityVillager)), (PostRenderConsumer)null);
            entityVillager.applyIndex10(this.computeCount2(index, entityVillager.getCount10()));
            world.matchesEntity(entityVillager);
         }
      }
   }

   public GoldenIngot resolveGoldenIngot(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list, Random random, int count, int count4) {
      if (this.enumFacing != null) {
         switch (HitBoxTransformerEnumFacingSwitchMap.countArray[this.enumFacing.ordinal()]) {
            case 1:
               return AngleService.resolveGoldenIngot3(
                  invalidBiomeIdManagerEntry,
                  list,
                  random,
                  this.distanceAggregator.count5 + 1,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 + count4,
                  EnumFacing.EAST,
                  this.getCount()
               );
            case 2:
               return AngleService.resolveGoldenIngot3(
                  invalidBiomeIdManagerEntry,
                  list,
                  random,
                  this.distanceAggregator.count5 + 1,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 + count4,
                  EnumFacing.EAST,
                  this.getCount()
               );
            case 3:
               return AngleService.resolveGoldenIngot3(
                  invalidBiomeIdManagerEntry,
                  list,
                  random,
                  this.distanceAggregator.count3 + count4,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count + 1,
                  EnumFacing.SOUTH,
                  this.getCount()
               );
            case 4:
               return AngleService.resolveGoldenIngot3(
                  invalidBiomeIdManagerEntry,
                  list,
                  random,
                  this.distanceAggregator.count3 + count4,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count + 1,
                  EnumFacing.SOUTH,
                  this.getCount()
               );
         }
      }

      return null;
   }
}
