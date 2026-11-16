package net.minecraft.item;

import client.util.DistanceAggregator;
import client.util.InvalidBiomeIdManagerEntry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class GoldenWand extends InternalItemEntryAdapter {
   public Block block;
   public Block block2;
   public Block block3;
   public Block block4;

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.count2 < 0) {
         this.count2 = this.computeCount(world, distanceAggregator);
         if (this.count2 < 0) {
            return true;
         }

         this.distanceAggregator.applyIndex(0, this.count2 - this.distanceAggregator.count2 + 4 - 1, 0);
      }

      this.applyWorld(world, distanceAggregator, 0, 1, 0, 12, 4, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 0, 1, 2, 0, 7, Blocks.block7.getDefaultState(), Blocks.block7.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 4, 0, 1, 5, 0, 7, Blocks.block7.getDefaultState(), Blocks.block7.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 7, 0, 1, 8, 0, 7, Blocks.block7.getDefaultState(), Blocks.block7.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 10, 0, 1, 11, 0, 7, Blocks.block7.getDefaultState(), Blocks.block7.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 0, 0, 0, 0, 0, 8, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 6, 0, 0, 6, 0, 8, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 12, 0, 0, 12, 0, 8, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 0, 0, 11, 0, 0, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 1, 0, 8, 11, 0, 8, Blocks.block140.getDefaultState(), Blocks.block140.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 3, 0, 1, 3, 0, 7, Blocks.blockLava2.getDefaultState(), Blocks.blockLava2.getDefaultState(), false);
      this.applyWorld(world, distanceAggregator, 9, 0, 1, 9, 0, 7, Blocks.blockLava2.getDefaultState(), Blocks.blockLava2.getDefaultState(), false);

      for (int index = 1; index <= 7; index++) {
         this.applyWorld(world, this.block.processAction(MathHelper.nextInt(random, 2, 7)), 1, 1, index, distanceAggregator);
         this.applyWorld(world, this.block.processAction(MathHelper.nextInt(random, 2, 7)), 2, 1, index, distanceAggregator);
         this.applyWorld(world, this.block2.processAction(MathHelper.nextInt(random, 2, 7)), 4, 1, index, distanceAggregator);
         this.applyWorld(world, this.block2.processAction(MathHelper.nextInt(random, 2, 7)), 5, 1, index, distanceAggregator);
         this.applyWorld(world, this.block3.processAction(MathHelper.nextInt(random, 2, 7)), 7, 1, index, distanceAggregator);
         this.applyWorld(world, this.block3.processAction(MathHelper.nextInt(random, 2, 7)), 8, 1, index, distanceAggregator);
         this.applyWorld(world, this.block4.processAction(MathHelper.nextInt(random, 2, 7)), 10, 1, index, distanceAggregator);
         this.applyWorld(world, this.block4.processAction(MathHelper.nextInt(random, 2, 7)), 11, 1, index, distanceAggregator);
      }

      for (int index2 = 0; index2 < 9; index2++) {
         for (int index3 = 0; index3 < 13; index3++) {
            this.applyWorld(world, index3, 4, index2, distanceAggregator);
            this.applyWorldByWorld(world, Blocks.block115.getDefaultState(), index3, -1, index2, distanceAggregator);
         }
      }

      return true;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.block = Block.processAction3(tagCompound.computeCount("CA"));
      this.block2 = Block.processAction3(tagCompound.computeCount("CB"));
      this.block3 = Block.processAction3(tagCompound.computeCount("CC"));
      this.block4 = Block.processAction3(tagCompound.computeCount("CD"));
   }

   public GoldenWand() {
   }

   public static GoldenWand resolveGoldenWand(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, 0, 0, 0, 13, 4, 9, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new GoldenWand(invalidBiomeIdManagerEntry, count4, random, distanceAggregator, enumFacing) : null;
   }

   private Block nextInt(Random random) {
      switch (random.nextInt(5)) {
         case 0:
            return Blocks.block66;
         case 1:
            return Blocks.block20;
         default:
            return Blocks.block92;
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("CA", Block.iterableExtensionBridge.getIndex(this.block));
      tagCompound.put("CB", Block.iterableExtensionBridge.getIndex(this.block2));
      tagCompound.put("CC", Block.iterableExtensionBridge.getIndex(this.block3));
      tagCompound.put("CD", Block.iterableExtensionBridge.getIndex(this.block4));
   }

   public GoldenWand(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(invalidBiomeIdManagerEntry, count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
      this.block = this.nextInt(random);
      this.block2 = this.nextInt(random);
      this.block3 = this.nextInt(random);
      this.block4 = this.nextInt(random);
   }
}
