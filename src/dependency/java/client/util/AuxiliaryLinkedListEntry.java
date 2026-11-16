package client.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import net.minecraft.item.GoldenIngot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public abstract class AuxiliaryLinkedListEntry {
   public LinkedList linkedList = new LinkedList();
   public DistanceAggregator distanceAggregator;
   public int count;
   public int count2;

   public NBTTagCompound resolveNBTTagCompound(int count3, int count4) {
      NBTTagCompound tagCompound = new NBTTagCompound();
      tagCompound.put("id", EnhancedLoggerEntry.resolveText(this));
      tagCompound.put("ChunkX", count3);
      tagCompound.put("ChunkZ", count4);
      tagCompound.applyText("BB", this.distanceAggregator.getProviderIntArrayEntry());
      NBTTagList nBTTagList = new NBTTagList();

      for (GoldenIngot goldenIngot : (Iterable<GoldenIngot>) this.linkedList) {
         nBTTagList.applyLISTManager(goldenIngot.getNBTTagCompound());
      }

      tagCompound.applyText("Children", nBTTagList);
      this.applyNBTTagCompound(tagCompound);
      return tagCompound;
   }

   public AuxiliaryLinkedListEntry(int count3, int count4) {
      this.count = count3;
      this.count2 = count4;
   }

   public boolean matchesInterpolationCollector(InterpolationCollector interpolationCollector) {
      return true;
   }

   public int getCount2() {
      return this.count2;
   }

   public void applyInterpolationCollector(InterpolationCollector interpolationCollector) {
   }

   public void applyWorld(World world, NBTTagCompound tagCompound) {
      this.count = tagCompound.computeCount("ChunkX");
      this.count2 = tagCompound.computeCount("ChunkZ");
      if (tagCompound.containsKey("BB")) {
         this.distanceAggregator = new DistanceAggregator(tagCompound.resolveIndexArray("BB"));
      }

      NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Children", 10);

      for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
         this.linkedList.add(EnhancedLoggerEntry.resolveGoldenIngot(extendedLoggerEntry.getNBTTagCompound(index), world));
      }

      this.applyNBTTagCompoundByNBTTagCompound(tagCompound);
   }

   public boolean isEnabled() {
      return true;
   }

   public void updateState() {
      this.distanceAggregator = DistanceAggregator.getDistanceAggregator();

      for (GoldenIngot goldenIngot : (Iterable<GoldenIngot>) this.linkedList) {
         this.distanceAggregator.applyDistanceAggregator(goldenIngot.getDistanceAggregator());
      }
   }

   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
   }

   public void applyWorld(World world, Random random, int count3, int count4) {
      int count5 = count4 - count3 + 1 - this.distanceAggregator.getCount2();
      int count6;
      if (count5 > 1) {
         count6 = count3 + random.nextInt(count5);
      } else {
         count6 = count3;
      }

      int count7 = count6 - this.distanceAggregator.count6;
      this.distanceAggregator.applyIndex(0, count7, 0);
      Iterator iterator = this.linkedList.iterator();

      while (iterator.hasNext()) {
         ((GoldenIngot)iterator.next()).applyIndex(0, count7, 0);
      }
   }

   public void applyWorld(World world, Random random, int count3) {
      int count4 = world.getIndexOffset1() - count3;
      int count5 = this.distanceAggregator.getCount2() + 1;
      if (count5 < count4) {
         count5 += random.nextInt(count4 - count5);
      }

      int count6 = count5 - this.distanceAggregator.count2;
      this.distanceAggregator.applyIndex(0, count6, 0);
      Iterator iterator = this.linkedList.iterator();

      while (iterator.hasNext()) {
         ((GoldenIngot)iterator.next()).applyIndex(0, count6, 0);
      }
   }

   public void applyWorld(World world, Random random, DistanceAggregator distanceAggregator2) {
      Iterator iterator = this.linkedList.iterator();

      while (iterator.hasNext()) {
         GoldenIngot goldenIngot = (GoldenIngot)iterator.next();
         if (goldenIngot.getDistanceAggregator().matchesDistanceAggregator(distanceAggregator2) && !goldenIngot.matchesWorld(world, random, distanceAggregator2)) {
            iterator.remove();
         }
      }
   }

   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
   }

   public DistanceAggregator getDistanceAggregator() {
      return this.distanceAggregator;
   }

   public LinkedList getLinkedList() {
      return this.linkedList;
   }

   public AuxiliaryLinkedListEntry() {
   }

   public int getCount() {
      return this.count;
   }
}
