package client.util;

import client.setting.MovementFactorProperty;
import com.google.common.collect.Sets;
import java.util.Random;
import java.util.Set;
import net.minecraft.item.GoldenStaff;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class AuxiliarySetEntry extends AuxiliaryLinkedListEntry {
   public Set set = Sets.newHashSet();
   public boolean enabled;

   private void applyWorldByWorld(World world, Random random, int count, int count2) {
      random.setSeed(world.getTimestamp());
      long value = random.nextLong();
      long value2 = random.nextLong();
      long value3 = (long)count * value;
      long value4 = (long)count2 * value2;
      random.setSeed(value3 ^ value4 ^ world.getTimestamp());
      int count3 = count * 16 + 8 - 29;
      int count4 = count2 * 16 + 8 - 29;
      EnumFacing enumFacing = MovementFactorProperty.HORIZONTAL.resolveEnumFacing(random);
      this.linkedList.add(new GoldenStaff(random, count3, count4, enumFacing));
      this.updateState();
      this.enabled = true;
   }

   @Override
   public void applyInterpolationCollector(InterpolationCollector interpolationCollector) {
      super.applyInterpolationCollector(interpolationCollector);
      this.set.add(interpolationCollector);
   }

   @Override
   public boolean matchesInterpolationCollector(InterpolationCollector interpolationCollector) {
      return this.set.contains(interpolationCollector) ? false : super.matchesInterpolationCollector(interpolationCollector);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      if (tagCompound.matchesText("Processed", 9)) {
         NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Processed", 10);

         for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
            NBTTagCompound nBTTagCompound = extendedLoggerEntry.getNBTTagCompound(index);
            this.set.add(new InterpolationCollector(nBTTagCompound.computeCount("X"), nBTTagCompound.computeCount("Z")));
         }
      }
   }

   public AuxiliarySetEntry() {
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      NBTTagList nBTTagList = new NBTTagList();

      for (InterpolationCollector interpolationCollector : (java.lang.Iterable<InterpolationCollector>) (this.set)) {
         NBTTagCompound tagCompound2 = new NBTTagCompound();
         tagCompound2.put("X", interpolationCollector.count3);
         tagCompound2.put("Z", interpolationCollector.count2);
         nBTTagList.applyLISTManager(tagCompound2);
      }

      tagCompound.applyText("Processed", nBTTagList);
   }

   public AuxiliarySetEntry(World world, Random random, int count, int count2) {
      super(count, count2);
      this.applyWorldByWorld(world, random, count, count2);
   }

   @Override
   public void applyWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (!this.enabled) {
         this.linkedList.clear();
         this.applyWorldByWorld(world, random, this.getCount(), this.getCount2());
      }

      super.applyWorld(world, random, distanceAggregator);
   }
}
