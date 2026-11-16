package net.minecraft.item;

import client.util.DistanceAggregator;
import client.util.GlobalListEntry;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemEntryProvider extends GoldenIngot {
   public List list = Lists.newLinkedList();

   @Override
   public void applyIndex(int count, int count2, int count3) {
      super.applyIndex(count, count2, count3);
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         ((DistanceAggregator)iterator.next()).applyIndex(count, count2, count3);
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      NBTTagList nBTTagList = new NBTTagList();

      for (Object distanceAggregator_o : this.list) {
         DistanceAggregator distanceAggregator = (DistanceAggregator)distanceAggregator_o;
         nBTTagList.applyLISTManager(distanceAggregator.getProviderIntArrayEntry());
      }

      tagCompound.applyText("Entrances", nBTTagList);
   }

   public ItemEntryProvider(int count, Random random, int count2, int count3) {
      super(count);
      this.distanceAggregator = new DistanceAggregator(count2, 50, count3, count2 + 7 + random.nextInt(6), 54 + random.nextInt(6), count3 + 7 + random.nextInt(6));
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesWorld(world, distanceAggregator)) {
         return false;
      } else {
         this.applyWorld(
            world,
            distanceAggregator,
            this.distanceAggregator.count3,
            this.distanceAggregator.count6,
            this.distanceAggregator.count4,
            this.distanceAggregator.count5,
            this.distanceAggregator.count6,
            this.distanceAggregator.count,
            Blocks.block115.getDefaultState(),
            Blocks.air.getDefaultState(),
            true
         );
         this.applyWorld(
            world,
            distanceAggregator,
            this.distanceAggregator.count3,
            this.distanceAggregator.count6 + 1,
            this.distanceAggregator.count4,
            this.distanceAggregator.count5,
            Math.min(this.distanceAggregator.count6 + 3, this.distanceAggregator.count2),
            this.distanceAggregator.count,
            Blocks.air.getDefaultState(),
            Blocks.air.getDefaultState(),
            false
         );

         for (Object distanceAggregator2_o : this.list) {
            DistanceAggregator distanceAggregator2 = (DistanceAggregator)distanceAggregator2_o;
            this.applyWorld(
               world,
               distanceAggregator,
               distanceAggregator2.count3,
               distanceAggregator2.count2 - 2,
               distanceAggregator2.count4,
               distanceAggregator2.count5,
               distanceAggregator2.count2,
               distanceAggregator2.count,
               Blocks.air.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );
         }

         this.applyWorld(
            world,
            distanceAggregator,
            this.distanceAggregator.count3,
            this.distanceAggregator.count6 + 4,
            this.distanceAggregator.count4,
            this.distanceAggregator.count5,
            this.distanceAggregator.count2,
            this.distanceAggregator.count,
            Blocks.air.getDefaultState(),
            false
         );
         return true;
      }
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list2, Random random) {
      int count = this.getCount();
      int count2 = this.distanceAggregator.getCount2() - 3 - 1;
      if (count2 <= 0) {
         count2 = 1;
      }

      int count3 = 0;

      while (count3 < this.distanceAggregator.getCountAsIndex()) {
         int count4 = count3 + random.nextInt(this.distanceAggregator.getCountAsIndex());
         if (count4 + 3 > this.distanceAggregator.getCountAsIndex()) {
            break;
         }

         GoldenIngot goldenIngot2 = GlobalListEntry.resolveGoldenIngotByGoldenIngot(
            goldenIngot,
            list2,
            random,
            this.distanceAggregator.count3 + count4,
            this.distanceAggregator.count6 + random.nextInt(count2) + 1,
            this.distanceAggregator.count4 - 1,
            EnumFacing.NORTH,
            count
         );
         if (goldenIngot2 != null) {
            DistanceAggregator distance = goldenIngot2.getDistanceAggregator();
            this.list
               .add(
                  new DistanceAggregator(
                     distance.count3, distance.count6, this.distanceAggregator.count4, distance.count5, distance.count2, this.distanceAggregator.count4 + 1
                  )
               );
         }

         count3 = count4 + 4;
      }

      int count5 = 0;

      while (count5 < this.distanceAggregator.getCountAsIndex()) {
         int count6 = count5 + random.nextInt(this.distanceAggregator.getCountAsIndex());
         if (count6 + 3 > this.distanceAggregator.getCountAsIndex()) {
            break;
         }

         GoldenIngot goldenIngot3 = GlobalListEntry.resolveGoldenIngotByGoldenIngot(
            goldenIngot,
            list2,
            random,
            this.distanceAggregator.count3 + count6,
            this.distanceAggregator.count6 + random.nextInt(count2) + 1,
            this.distanceAggregator.count + 1,
            EnumFacing.SOUTH,
            count
         );
         if (goldenIngot3 != null) {
            DistanceAggregator distance2 = goldenIngot3.getDistanceAggregator();
            this.list
               .add(
                  new DistanceAggregator(
                     distance2.count3, distance2.count6, this.distanceAggregator.count - 1, distance2.count5, distance2.count2, this.distanceAggregator.count
                  )
               );
         }

         count5 = count6 + 4;
      }

      int count7 = 0;

      while (count7 < this.distanceAggregator.getCount()) {
         int count8 = count7 + random.nextInt(this.distanceAggregator.getCount());
         if (count8 + 3 > this.distanceAggregator.getCount()) {
            break;
         }

         GoldenIngot goldenIngot4 = GlobalListEntry.resolveGoldenIngotByGoldenIngot(
            goldenIngot,
            list2,
            random,
            this.distanceAggregator.count3 - 1,
            this.distanceAggregator.count6 + random.nextInt(count2) + 1,
            this.distanceAggregator.count4 + count8,
            EnumFacing.WEST,
            count
         );
         if (goldenIngot4 != null) {
            DistanceAggregator distance3 = goldenIngot4.getDistanceAggregator();
            this.list
               .add(
                  new DistanceAggregator(
                     this.distanceAggregator.count3, distance3.count6, distance3.count4, this.distanceAggregator.count3 + 1, distance3.count2, distance3.count
                  )
               );
         }

         count7 = count8 + 4;
      }

      int count9 = 0;

      while (count9 < this.distanceAggregator.getCount()) {
         int count10 = count9 + random.nextInt(this.distanceAggregator.getCount());
         if (count10 + 3 > this.distanceAggregator.getCount()) {
            break;
         }

         GoldenIngot goldenIngot5 = GlobalListEntry.resolveGoldenIngotByGoldenIngot(
            goldenIngot,
            list2,
            random,
            this.distanceAggregator.count5 + 1,
            this.distanceAggregator.count6 + random.nextInt(count2) + 1,
            this.distanceAggregator.count4 + count10,
            EnumFacing.EAST,
            count
         );
         if (goldenIngot5 != null) {
            DistanceAggregator distance4 = goldenIngot5.getDistanceAggregator();
            this.list
               .add(
                  new DistanceAggregator(
                     this.distanceAggregator.count5 - 1, distance4.count6, distance4.count4, this.distanceAggregator.count5, distance4.count2, distance4.count
                  )
               );
         }

         count9 = count10 + 4;
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Entrances", 11);

      for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
         this.list.add(new DistanceAggregator(extendedLoggerEntry.resolveIndexArray(index)));
      }
   }

   public ItemEntryProvider() {
   }
}
