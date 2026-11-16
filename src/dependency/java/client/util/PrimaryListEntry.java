package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.item.ExtendedItemEntryAdapter;
import net.minecraft.item.GoldenIngot;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class PrimaryListEntry extends ExtendedItemEntryAdapter {
   public static final List list = Lists.newArrayList(
      new ClientManagerAdapter2[]{
         new ClientManagerAdapter2(Items.ironIngot, 0, 1, 5, 10),
         new ClientManagerAdapter2(Items.goldIngot, 0, 1, 3, 5),
         new ClientManagerAdapter2(Items.redstone, 0, 4, 9, 5),
         new ClientManagerAdapter2(Items.coal, 0, 3, 8, 10),
         new ClientManagerAdapter2(Items.bread, 0, 1, 3, 15),
         new ClientManagerAdapter2(Items.apple, 0, 1, 3, 15),
         new ClientManagerAdapter2(Items.ironPickaxe, 0, 1, 1, 1)
      }
   );
   public int count2;

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("Type", this.count2);
   }

   public PrimaryListEntry(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.iRONDOOREnum = this.nextInt(random);
      this.distanceAggregator = distanceAggregator;
      this.count2 = random.nextInt(5);
   }

   public PrimaryListEntry() {
   }

   public static PrimaryListEntry resolvePrimaryListEntry(List list2, Random random, int count, int count3, int count4, EnumFacing enumFacing, int count5) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count3, count4, -4, -1, 0, 11, 7, 11, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list2, distanceAggregator) == null ? new PrimaryListEntry(count5, random, distanceAggregator, enumFacing) : null;
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesWorld(world, distanceAggregator)) {
         return false;
      } else {
         this.applyWorld(world, distanceAggregator, 0, 0, 0, 10, 6, 10, true, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, random, distanceAggregator, this.iRONDOOREnum, 4, 1, 0);
         this.applyWorld(world, distanceAggregator, 4, 1, 10, 6, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 0, 1, 4, 0, 3, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 10, 1, 4, 10, 3, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         switch (this.count2) {
            case 0:
               this.applyWorld(world, Blocks.block42.getDefaultState(), 5, 1, 5, distanceAggregator);
               this.applyWorld(world, Blocks.block42.getDefaultState(), 5, 2, 5, distanceAggregator);
               this.applyWorld(world, Blocks.block42.getDefaultState(), 5, 3, 5, distanceAggregator);
               this.applyWorld(world, Blocks.block21.getDefaultState(), 4, 3, 5, distanceAggregator);
               this.applyWorld(world, Blocks.block21.getDefaultState(), 6, 3, 5, distanceAggregator);
               this.applyWorld(world, Blocks.block21.getDefaultState(), 5, 3, 4, distanceAggregator);
               this.applyWorld(world, Blocks.block21.getDefaultState(), 5, 3, 6, distanceAggregator);
               this.applyWorld(world, Blocks.extraBlockAdapter6.getDefaultState(), 4, 1, 4, distanceAggregator);
               this.applyWorld(world, Blocks.extraBlockAdapter6.getDefaultState(), 4, 1, 5, distanceAggregator);
               this.applyWorld(world, Blocks.extraBlockAdapter6.getDefaultState(), 4, 1, 6, distanceAggregator);
               this.applyWorld(world, Blocks.extraBlockAdapter6.getDefaultState(), 6, 1, 4, distanceAggregator);
               this.applyWorld(world, Blocks.extraBlockAdapter6.getDefaultState(), 6, 1, 5, distanceAggregator);
               this.applyWorld(world, Blocks.extraBlockAdapter6.getDefaultState(), 6, 1, 6, distanceAggregator);
               this.applyWorld(world, Blocks.extraBlockAdapter6.getDefaultState(), 5, 1, 4, distanceAggregator);
               this.applyWorld(world, Blocks.extraBlockAdapter6.getDefaultState(), 5, 1, 6, distanceAggregator);
               break;
            case 1:
               for (int index = 0; index < 5; index++) {
                  this.applyWorld(world, Blocks.block42.getDefaultState(), 3, 1, 3 + index, distanceAggregator);
                  this.applyWorld(world, Blocks.block42.getDefaultState(), 7, 1, 3 + index, distanceAggregator);
                  this.applyWorld(world, Blocks.block42.getDefaultState(), 3 + index, 1, 3, distanceAggregator);
                  this.applyWorld(world, Blocks.block42.getDefaultState(), 3 + index, 1, 7, distanceAggregator);
               }

               this.applyWorld(world, Blocks.block42.getDefaultState(), 5, 1, 5, distanceAggregator);
               this.applyWorld(world, Blocks.block42.getDefaultState(), 5, 2, 5, distanceAggregator);
               this.applyWorld(world, Blocks.block42.getDefaultState(), 5, 3, 5, distanceAggregator);
               this.applyWorld(world, Blocks.blockFlowingLava.getDefaultState(), 5, 4, 5, distanceAggregator);
               break;
            case 2:
               for (int index2 = 1; index2 <= 9; index2++) {
                  this.applyWorld(world, Blocks.block65.getDefaultState(), 1, 3, index2, distanceAggregator);
                  this.applyWorld(world, Blocks.block65.getDefaultState(), 9, 3, index2, distanceAggregator);
               }

               for (int index3 = 1; index3 <= 9; index3++) {
                  this.applyWorld(world, Blocks.block65.getDefaultState(), index3, 3, 1, distanceAggregator);
                  this.applyWorld(world, Blocks.block65.getDefaultState(), index3, 3, 9, distanceAggregator);
               }

               this.applyWorld(world, Blocks.block65.getDefaultState(), 5, 1, 4, distanceAggregator);
               this.applyWorld(world, Blocks.block65.getDefaultState(), 5, 1, 6, distanceAggregator);
               this.applyWorld(world, Blocks.block65.getDefaultState(), 5, 3, 4, distanceAggregator);
               this.applyWorld(world, Blocks.block65.getDefaultState(), 5, 3, 6, distanceAggregator);
               this.applyWorld(world, Blocks.block65.getDefaultState(), 4, 1, 5, distanceAggregator);
               this.applyWorld(world, Blocks.block65.getDefaultState(), 6, 1, 5, distanceAggregator);
               this.applyWorld(world, Blocks.block65.getDefaultState(), 4, 3, 5, distanceAggregator);
               this.applyWorld(world, Blocks.block65.getDefaultState(), 6, 3, 5, distanceAggregator);

               for (int index4 = 1; index4 <= 3; index4++) {
                  this.applyWorld(world, Blocks.block65.getDefaultState(), 4, index4, 4, distanceAggregator);
                  this.applyWorld(world, Blocks.block65.getDefaultState(), 6, index4, 4, distanceAggregator);
                  this.applyWorld(world, Blocks.block65.getDefaultState(), 4, index4, 6, distanceAggregator);
                  this.applyWorld(world, Blocks.block65.getDefaultState(), 6, index4, 6, distanceAggregator);
               }

               this.applyWorld(world, Blocks.block21.getDefaultState(), 5, 3, 5, distanceAggregator);

               for (int index5 = 2; index5 <= 8; index5++) {
                  this.applyWorld(world, Blocks.block142.getDefaultState(), 2, 3, index5, distanceAggregator);
                  this.applyWorld(world, Blocks.block142.getDefaultState(), 3, 3, index5, distanceAggregator);
                  if (index5 <= 3 || index5 >= 7) {
                     this.applyWorld(world, Blocks.block142.getDefaultState(), 4, 3, index5, distanceAggregator);
                     this.applyWorld(world, Blocks.block142.getDefaultState(), 5, 3, index5, distanceAggregator);
                     this.applyWorld(world, Blocks.block142.getDefaultState(), 6, 3, index5, distanceAggregator);
                  }

                  this.applyWorld(world, Blocks.block142.getDefaultState(), 7, 3, index5, distanceAggregator);
                  this.applyWorld(world, Blocks.block142.getDefaultState(), 8, 3, index5, distanceAggregator);
               }

               this.applyWorld(world, Blocks.block81.processAction(this.computeCount(Blocks.block81, EnumFacing.WEST.getIndexOffset1())), 9, 1, 3, distanceAggregator);
               this.applyWorld(world, Blocks.block81.processAction(this.computeCount(Blocks.block81, EnumFacing.WEST.getIndexOffset1())), 9, 2, 3, distanceAggregator);
               this.applyWorld(world, Blocks.block81.processAction(this.computeCount(Blocks.block81, EnumFacing.WEST.getIndexOffset1())), 9, 3, 3, distanceAggregator);
               this.matchesWorld(
                  world,
                  distanceAggregator,
                  random,
                  3,
                  4,
                  8,
                  ClientManagerAdapter2.resolveList(list, new ClientManagerAdapter2[]{Items.enchantedBook.resolveClientManagerAdapter2(random)}),
                  1 + random.nextInt(4)
               );
         }

         return true;
      }
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list2, Random random) {
      this.resolveGoldenIngotByClassEntry3Entry((ClassEntry3Entry)goldenIngot, list2, random, 4, 1);
      this.resolveGoldenIngot((ClassEntry3Entry)goldenIngot, list2, random, 1, 4);
      this.resolveGoldenIngot2((ClassEntry3Entry)goldenIngot, list2, random, 1, 4);
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.count2 = tagCompound.computeCount("Type");
   }
}
