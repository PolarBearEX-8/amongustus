package client.util;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class DynamicListEntry extends InternalRandomEntryAdapter {
   public List list;
   public boolean enabled;
   public InterpolationCollector[] interpolationCollectorArray = new InterpolationCollector[3];
   public double value = 32.0;
   public int count2 = 3;

   @Override
   public AuxiliaryLinkedListEntry resolveAuxiliaryLinkedListEntry(int count, int count3) {
      TargetDispatcher targetDispatcher = new TargetDispatcher(this.world, this.random, count, count3);

      while (targetDispatcher.getLinkedList().isEmpty() || ((ClassEntry3Entry)targetDispatcher.getLinkedList().get(0)).goldenDagger == null) {
         targetDispatcher = new TargetDispatcher(this.world, this.random, count, count3);
      }

      return targetDispatcher;
   }

   public DynamicListEntry(Map map) {
      this();

      for (Entry entry : (java.lang.Iterable<Entry>) (map.entrySet())) {
         if (((String)entry.getKey()).equals("distance")) {
            this.value = MathHelper.processAction((String)entry.getValue(), this.value, 1.0);
         } else if (((String)entry.getKey()).equals("count")) {
            this.interpolationCollectorArray = new InterpolationCollector[MathHelper.processAction(
               (String)entry.getValue(), this.interpolationCollectorArray.length, 1
            )];
         } else if (((String)entry.getKey()).equals("spread")) {
            this.count2 = MathHelper.processAction((String)entry.getValue(), this.count2, 1);
         }
      }
   }

   public DynamicListEntry() {
      this.list = Lists.newArrayList();

      for (BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager : BiomeIDIsOutOfBoundsManager.getBiomeIDIsOutOfBoundsManagerArray()) {
         if (biomeIDIsOutOfBoundsManager != null && biomeIDIsOutOfBoundsManager.factor2 > 0.0F) {
            this.list.add(biomeIDIsOutOfBoundsManager);
         }
      }
   }

   @Override
   public String getText() {
      return "Stronghold";
   }

   @Override
   public List getList() {
      ArrayList list2 = Lists.newArrayList();

      for (InterpolationCollector interpolationCollector : this.interpolationCollectorArray) {
         if (interpolationCollector != null) {
            list2.add(interpolationCollector.resolveBlockPos(64));
         }
      }

      return list2;
   }

   @Override
   public boolean checkIndex(int count, int count3) {
      if (!this.enabled) {
         Random random = new Random();
         random.setSeed(this.world.getTimestamp());
         double offset = random.nextDouble() * Math.PI * 2.0;
         int count4 = 1;

         for (int index = 0; index < this.interpolationCollectorArray.length; index++) {
            double offset2 = (1.25 * (double)count4 + random.nextDouble()) * this.value * (double)count4;
            int count5 = (int)Math.round(Math.cos(offset) * offset2);
            int count6 = (int)Math.round(Math.sin(offset) * offset2);
            BlockPos blockPos = this.world.getInvalidBiomeIdManager().resolveBlockPos((count5 << 4) + 8, (count6 << 4) + 8, 112, this.list, random);
            if (blockPos != null) {
               count5 = blockPos.getX() >> 4;
               count6 = blockPos.getZ() >> 4;
            }

            this.interpolationCollectorArray[index] = new InterpolationCollector(count5, count6);
            offset += (Math.PI * 2) * (double)count4 / (double)this.count2;
            if (index == this.count2) {
               count4 += 2 + random.nextInt(5);
               this.count2 = this.count2 + 1 + random.nextInt(2);
            }
         }

         this.enabled = true;
      }

      for (InterpolationCollector interpolationCollector : this.interpolationCollectorArray) {
         if (count == interpolationCollector.count3 && count3 == interpolationCollector.count2) {
            return true;
         }
      }

      return false;
   }
}
