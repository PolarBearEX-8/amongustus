package client.util;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.item.GoldenIngot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class InternalRandomEntryAdapter extends TertiaryRandomEntry {
   public Map map = Maps.newHashMap();
   public StringEntryDispatcher stringEntryDispatcher;

   public boolean matchesWorld(World world, BlockPos blockPos) {
      this.applyWorld(world);

      for (AuxiliaryLinkedListEntry auxiliaryLinkedListEntry : (Iterable<AuxiliaryLinkedListEntry>)(Iterable)this.map.values()) {
         if (auxiliaryLinkedListEntry.isEnabled() && auxiliaryLinkedListEntry.getDistanceAggregator().matchesVec3i(blockPos)) {
            return true;
         }
      }

      return false;
   }

   public boolean matchesBlockPos(BlockPos blockPos) {
      this.applyWorld(this.world);
      return this.resolveAuxiliaryLinkedListEntry(blockPos) != null;
   }

   @Override
   public final void applyWorld(World world, int count, int count2, int count3, int count4, ShortArrayEntry shortArrayEntry) {
      this.applyWorld(world);
      if (!this.map.containsKey(InterpolationCollector.computeTimestamp(count, count2))) {
         this.random.nextInt();

         try {
            if (this.checkIndex(count, count2)) {
               AuxiliaryLinkedListEntry auxiliaryLinkedListEntry = this.resolveAuxiliaryLinkedListEntry(count, count2);
               this.map.put(InterpolationCollector.computeTimestamp(count, count2), auxiliaryLinkedListEntry);
               this.applyAuxiliaryLinkedListEntry(count, count2, auxiliaryLinkedListEntry);
            }
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Exception preparing structure feature");
            CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Feature being prepared");
            crashReportCategory.applyText("Is feature chunk", () -> this.resolveTextByIndex(count, count2));
            crashReportCategory.add("Chunk location", String.format("%d,%d", count, count2));
            crashReportCategory.applyText("Chunk pos hash", () -> InternalRandomEntryAdapter.resolveText(count, count2));
            crashReportCategory.applyText("Structure type", this::getTextAsText);
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }
      }
   }

   private void applyWorld(World world) {
      if (this.stringEntryDispatcher == null) {
         this.stringEntryDispatcher = (StringEntryDispatcher)world.resolveAdapterStringEntry(StringEntryDispatcher.class, this.getText());
         if (this.stringEntryDispatcher == null) {
            this.stringEntryDispatcher = new StringEntryDispatcher(this.getText());
            world.applyText(this.getText(), this.stringEntryDispatcher);
         } else {
            NBTTagCompound nBTTagCompound = this.stringEntryDispatcher.getNBTTagCompound();

            for (String text : (Iterable<String>)(Iterable)nBTTagCompound.keySet()) {
               LISTManager lISTManager = nBTTagCompound.get(text);
               if (lISTManager.getByteCode() == 10) {
                  NBTTagCompound tagCompound = (NBTTagCompound)lISTManager;
                  if (tagCompound.containsKey("ChunkX") && tagCompound.containsKey("ChunkZ")) {
                     int count = tagCompound.computeCount("ChunkX");
                     int count2 = tagCompound.computeCount("ChunkZ");
                     AuxiliaryLinkedListEntry auxiliaryLinkedListEntry = EnhancedLoggerEntry.resolveAuxiliaryLinkedListEntry(tagCompound, world);
                     if (auxiliaryLinkedListEntry != null) {
                        this.map.put(InterpolationCollector.computeTimestamp(count, count2), auxiliaryLinkedListEntry);
                     }
                  }
               }
            }
         }
      }
   }

   private static String resolveText(int count, int count2) throws Exception {
      return String.valueOf(InterpolationCollector.computeTimestamp(count, count2));
   }

   private String resolveTextByIndex(int count, int count2) throws Exception {
      return this.checkIndex(count, count2) ? "True" : "False";
   }

   public AuxiliaryLinkedListEntry resolveAuxiliaryLinkedListEntry(BlockPos blockPos) {
      for (AuxiliaryLinkedListEntry auxiliaryLinkedListEntry : (Iterable<AuxiliaryLinkedListEntry>)(Iterable)this.map.values()) {
         if (auxiliaryLinkedListEntry.isEnabled() && auxiliaryLinkedListEntry.getDistanceAggregator().matchesVec3i(blockPos)) {
            Iterator iterator = auxiliaryLinkedListEntry.getLinkedList().iterator();

            while (iterator.hasNext()) {
               if (((GoldenIngot)iterator.next()).getDistanceAggregator().matchesVec3i(blockPos)) {
                  return auxiliaryLinkedListEntry;
               }
            }
         }
      }

      return null;
   }

   private String getTextAsText() throws Exception {
      return this.getClass().getCanonicalName();
   }

   public abstract String getText();

   public BlockPos resolveBlockPos(World world, BlockPos blockPos) {
      this.world = world;
      this.applyWorld(world);
      this.random.setSeed(world.getTimestamp());
      long value = this.random.nextLong();
      long value2 = this.random.nextLong();
      value = (long)(blockPos.getX() >> 4) * value;
      value2 = (long)(blockPos.getZ() >> 4) * value2;
      this.random.setSeed(value ^ value2 ^ world.getTimestamp());
      this.applyWorld(world, blockPos.getX() >> 4, blockPos.getZ() >> 4, 0, 0, null);
      double offset = Double.MAX_VALUE;
      BlockPos blockPos2 = null;

      for (AuxiliaryLinkedListEntry auxiliaryLinkedListEntry : (Iterable<AuxiliaryLinkedListEntry>)(Iterable)this.map.values()) {
         if (auxiliaryLinkedListEntry.isEnabled()) {
            GoldenIngot goldenIngot = (GoldenIngot)auxiliaryLinkedListEntry.getLinkedList().get(0);
            BlockPos blockPos3 = goldenIngot.getBlockPos();
            double coordinate = blockPos3.getCoordinate(blockPos);
            if (coordinate < offset) {
               offset = coordinate;
               blockPos2 = blockPos3;
            }
         }
      }

      if (blockPos2 != null) {
         return blockPos2;
      } else {
         List list = this.getList();
         if (list != null) {
            BlockPos blockPos4 = null;

            for (BlockPos blockPos5 : (Iterable<BlockPos>)(Iterable)list) {
               double coordinate2 = blockPos5.getCoordinate(blockPos);
               if (coordinate2 < offset) {
                  offset = coordinate2;
                  blockPos4 = blockPos5;
               }
            }

            return blockPos4;
         } else {
            return null;
         }
      }
   }

   public boolean matchesWorld(World world, Random random, InterpolationCollector interpolationCollector) {
      this.applyWorld(world);
      int count = (interpolationCollector.count3 << 4) + 8;
      int count2 = (interpolationCollector.count2 << 4) + 8;
      boolean flag = false;

      for (AuxiliaryLinkedListEntry auxiliaryLinkedListEntry : (Iterable<AuxiliaryLinkedListEntry>)(Iterable)this.map.values()) {
         if (auxiliaryLinkedListEntry.isEnabled() && auxiliaryLinkedListEntry.matchesInterpolationCollector(interpolationCollector) && auxiliaryLinkedListEntry.getDistanceAggregator().checkIndex(count, count2, count + 15, count2 + 15)) {
            auxiliaryLinkedListEntry.applyWorld(world, random, new DistanceAggregator(count, count2, count + 15, count2 + 15));
            auxiliaryLinkedListEntry.applyInterpolationCollector(interpolationCollector);
            flag = true;
            this.applyAuxiliaryLinkedListEntry(auxiliaryLinkedListEntry.getCount(), auxiliaryLinkedListEntry.getCount2(), auxiliaryLinkedListEntry);
         }
      }

      return flag;
   }

   public List getList() {
      return null;
   }

   public abstract AuxiliaryLinkedListEntry resolveAuxiliaryLinkedListEntry(int count, int count2);

   public abstract boolean checkIndex(int count, int count2);

   private void applyAuxiliaryLinkedListEntry(int count, int count2, AuxiliaryLinkedListEntry auxiliaryLinkedListEntry) {
      this.stringEntryDispatcher.applyNBTTagCompound(auxiliaryLinkedListEntry.resolveNBTTagCompound(count, count2), count, count2);
      this.stringEntryDispatcher.updateState();
   }
}
