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

public class DefaultListEntry extends ExtendedItemEntryAdapter {
   public static final List list = Lists.newArrayList(
      new ClientManagerAdapter2[]{
         new ClientManagerAdapter2(Items.book, 0, 1, 3, 20),
         new ClientManagerAdapter2(Items.paper, 0, 2, 7, 20),
         new ClientManagerAdapter2(Items.map, 0, 1, 1, 1),
         new ClientManagerAdapter2(Items.compass, 0, 1, 1, 1)
      }
   );
   public boolean enabled;

   public static DefaultListEntry resolveDefaultListEntry(List list2, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -4, -1, 0, 14, 11, 15, enumFacing);
      if (!matchesDistanceAggregator(distanceAggregator) || GoldenIngot.resolveGoldenIngot(list2, distanceAggregator) != null) {
         distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -4, -1, 0, 14, 6, 15, enumFacing);
         if (!matchesDistanceAggregator(distanceAggregator) || GoldenIngot.resolveGoldenIngot(list2, distanceAggregator) != null) {
            return null;
         }
      }

      return new DefaultListEntry(count4, random, distanceAggregator, enumFacing);
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.enabled = tagCompound.matchesText("Tall");
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("Tall", this.enabled);
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesWorld(world, distanceAggregator)) {
         return false;
      } else {
         byte byteValue = 11;
         if (!this.enabled) {
            byteValue = 6;
         }

         this.applyWorld(world, distanceAggregator, 0, 0, 0, 13, byteValue - 1, 14, true, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, random, distanceAggregator, this.iRONDOOREnum, 4, 1, 0);
         this.applyWorld(world, distanceAggregator, random, 0.07F, 2, 1, 1, 11, 4, 13, Blocks.web.getDefaultState(), Blocks.web.getDefaultState(), false);

         for (int index = 1; index <= 13; index++) {
            if ((index - 1) % 4 == 0) {
               this.applyWorld(world, distanceAggregator, 1, 1, index, 1, 4, index, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
               this.applyWorld(world, distanceAggregator, 12, 1, index, 12, 4, index, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
               this.applyWorld(world, Blocks.block21.getDefaultState(), 2, 3, index, distanceAggregator);
               this.applyWorld(world, Blocks.block21.getDefaultState(), 11, 3, index, distanceAggregator);
               if (this.enabled) {
                  this.applyWorld(world, distanceAggregator, 1, 6, index, 1, 9, index, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
                  this.applyWorld(world, distanceAggregator, 12, 6, index, 12, 9, index, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
               }
            } else {
               this.applyWorld(world, distanceAggregator, 1, 1, index, 1, 4, index, Blocks.block8.getDefaultState(), Blocks.block8.getDefaultState(), false);
               this.applyWorld(world, distanceAggregator, 12, 1, index, 12, 4, index, Blocks.block8.getDefaultState(), Blocks.block8.getDefaultState(), false);
               if (this.enabled) {
                  this.applyWorld(world, distanceAggregator, 1, 6, index, 1, 9, index, Blocks.block8.getDefaultState(), Blocks.block8.getDefaultState(), false);
                  this.applyWorld(world, distanceAggregator, 12, 6, index, 12, 9, index, Blocks.block8.getDefaultState(), Blocks.block8.getDefaultState(), false);
               }
            }
         }

         for (int byteValue2 = 3; byteValue2 < 12; byteValue2 += 2) {
            this.applyWorld(world, distanceAggregator, 3, 1, byteValue2, 4, 3, byteValue2, Blocks.block8.getDefaultState(), Blocks.block8.getDefaultState(), false);
            this.applyWorld(world, distanceAggregator, 6, 1, byteValue2, 7, 3, byteValue2, Blocks.block8.getDefaultState(), Blocks.block8.getDefaultState(), false);
            this.applyWorld(world, distanceAggregator, 9, 1, byteValue2, 10, 3, byteValue2, Blocks.block8.getDefaultState(), Blocks.block8.getDefaultState(), false);
         }

         if (this.enabled) {
            this.applyWorld(world, distanceAggregator, 1, 5, 1, 3, 5, 13, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
            this.applyWorld(world, distanceAggregator, 10, 5, 1, 12, 5, 13, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
            this.applyWorld(world, distanceAggregator, 4, 5, 1, 9, 5, 2, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
            this.applyWorld(world, distanceAggregator, 4, 5, 12, 9, 5, 13, Blocks.block142.getDefaultState(), Blocks.block142.getDefaultState(), false);
            this.applyWorld(world, Blocks.block142.getDefaultState(), 9, 5, 11, distanceAggregator);
            this.applyWorld(world, Blocks.block142.getDefaultState(), 8, 5, 11, distanceAggregator);
            this.applyWorld(world, Blocks.block142.getDefaultState(), 9, 5, 10, distanceAggregator);
            this.applyWorld(world, distanceAggregator, 3, 6, 2, 3, 6, 12, Blocks.block111.getDefaultState(), Blocks.block111.getDefaultState(), false);
            this.applyWorld(world, distanceAggregator, 10, 6, 2, 10, 6, 10, Blocks.block111.getDefaultState(), Blocks.block111.getDefaultState(), false);
            this.applyWorld(world, distanceAggregator, 4, 6, 2, 9, 6, 2, Blocks.block111.getDefaultState(), Blocks.block111.getDefaultState(), false);
            this.applyWorld(world, distanceAggregator, 4, 6, 12, 8, 6, 12, Blocks.block111.getDefaultState(), Blocks.block111.getDefaultState(), false);
            this.applyWorld(world, Blocks.block111.getDefaultState(), 9, 6, 11, distanceAggregator);
            this.applyWorld(world, Blocks.block111.getDefaultState(), 8, 6, 11, distanceAggregator);
            this.applyWorld(world, Blocks.block111.getDefaultState(), 9, 6, 10, distanceAggregator);
            int count = this.computeCount(Blocks.block81, 3);
            this.applyWorld(world, Blocks.block81.processAction(count), 10, 1, 13, distanceAggregator);
            this.applyWorld(world, Blocks.block81.processAction(count), 10, 2, 13, distanceAggregator);
            this.applyWorld(world, Blocks.block81.processAction(count), 10, 3, 13, distanceAggregator);
            this.applyWorld(world, Blocks.block81.processAction(count), 10, 4, 13, distanceAggregator);
            this.applyWorld(world, Blocks.block81.processAction(count), 10, 5, 13, distanceAggregator);
            this.applyWorld(world, Blocks.block81.processAction(count), 10, 6, 13, distanceAggregator);
            this.applyWorld(world, Blocks.block81.processAction(count), 10, 7, 13, distanceAggregator);
            this.applyWorld(world, Blocks.block111.getDefaultState(), 6, 9, 7, distanceAggregator);
            this.applyWorld(world, Blocks.block111.getDefaultState(), 7, 9, 7, distanceAggregator);
            this.applyWorld(world, Blocks.block111.getDefaultState(), 6, 8, 7, distanceAggregator);
            this.applyWorld(world, Blocks.block111.getDefaultState(), 7, 8, 7, distanceAggregator);
            this.applyWorld(world, Blocks.block111.getDefaultState(), 6, 7, 7, distanceAggregator);
            this.applyWorld(world, Blocks.block111.getDefaultState(), 7, 7, 7, distanceAggregator);
            this.applyWorld(world, Blocks.block111.getDefaultState(), 5, 7, 7, distanceAggregator);
            this.applyWorld(world, Blocks.block111.getDefaultState(), 8, 7, 7, distanceAggregator);
            this.applyWorld(world, Blocks.block111.getDefaultState(), 6, 7, 6, distanceAggregator);
            this.applyWorld(world, Blocks.block111.getDefaultState(), 6, 7, 8, distanceAggregator);
            this.applyWorld(world, Blocks.block111.getDefaultState(), 7, 7, 6, distanceAggregator);
            this.applyWorld(world, Blocks.block111.getDefaultState(), 7, 7, 8, distanceAggregator);
            this.applyWorld(world, Blocks.block21.getDefaultState(), 5, 8, 7, distanceAggregator);
            this.applyWorld(world, Blocks.block21.getDefaultState(), 8, 8, 7, distanceAggregator);
            this.applyWorld(world, Blocks.block21.getDefaultState(), 6, 8, 6, distanceAggregator);
            this.applyWorld(world, Blocks.block21.getDefaultState(), 6, 8, 8, distanceAggregator);
            this.applyWorld(world, Blocks.block21.getDefaultState(), 7, 8, 6, distanceAggregator);
            this.applyWorld(world, Blocks.block21.getDefaultState(), 7, 8, 8, distanceAggregator);
         }

         this.matchesWorld(
            world,
            distanceAggregator,
            random,
            3,
            3,
            5,
            ClientManagerAdapter2.resolveList(list, new ClientManagerAdapter2[]{Items.enchantedBook.resolveClientManagerAdapter2(random, 1, 5, 2)}),
            1 + random.nextInt(4)
         );
         if (this.enabled) {
            this.applyWorld(world, Blocks.air.getDefaultState(), 12, 9, 1, distanceAggregator);
            this.matchesWorld(
               world,
               distanceAggregator,
               random,
               12,
               8,
               1,
               ClientManagerAdapter2.resolveList(list, new ClientManagerAdapter2[]{Items.enchantedBook.resolveClientManagerAdapter2(random, 1, 5, 2)}),
               1 + random.nextInt(4)
            );
         }

         return true;
      }
   }

   public DefaultListEntry(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.iRONDOOREnum = this.nextInt(random);
      this.distanceAggregator = distanceAggregator;
      this.enabled = distanceAggregator.getCount2() > 6;
   }

   public DefaultListEntry() {
   }
}
