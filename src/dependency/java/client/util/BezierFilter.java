package client.util;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.entity.EntityWitch;
import net.minecraft.item.GoldenIngot;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class BezierFilter extends InternalRandomEntryAdapter {
   public static final List list = Arrays.asList(
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager27,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager23,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager40,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager16
   );
   public List list2 = Lists.newArrayList();
   public int count2 = 32;
   public int count3 = 8;

   public List getList2() {
      return this.list2;
   }

   public BezierFilter() {
      this.list2.add(new ClassHolder(EntityWitch.class, 1, 1, 1));
   }

   @Override
   public boolean checkIndex(int count, int count4) {
      int count5 = count;
      int count6 = count4;
      if (count < 0) {
         count5 = count - (this.count2 - 1);
      }

      if (count4 < 0) {
         count6 = count4 - (this.count2 - 1);
      }

      int count7 = count5 / this.count2;
      int count8 = count6 / this.count2;
      Random random = this.world.resolveRandom(count7, count8, 14357617);
      int count9 = count7 * this.count2;
      int count10 = count8 * this.count2;
      int count11 = count9 + random.nextInt(this.count2 - this.count3);
      int count12 = count10 + random.nextInt(this.count2 - this.count3);
      if (count == count11 && count4 == count12) {
         BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = this.world
            .getInvalidBiomeIdManager()
            .resolveBiomeIDIsOutOfBoundsManager(new BlockPos(count * 16 + 8, 0, count4 * 16 + 8));
         if (biomeIDIsOutOfBoundsManager == null) {
            return false;
         }

         for (BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager2 : (Iterable<BiomeIDIsOutOfBoundsManager>)(Iterable)(list)) {
            if (biomeIDIsOutOfBoundsManager == biomeIDIsOutOfBoundsManager2) {
               return true;
            }
         }
      }

      return false;
   }

   public BezierFilter(Map map) {
      this();

      for (Entry entry : (Iterable<Entry>)(Iterable)(map.entrySet())) {
         if (((String)entry.getKey()).equals("distance")) {
            this.count2 = MathHelper.processAction((String)entry.getValue(), this.count2, this.count3 + 1);
         }
      }
   }

   public boolean matchesBlockPosByBlockPos(BlockPos blockPos) {
      AuxiliaryLinkedListEntry auxiliaryLinkedListEntry = this.resolveAuxiliaryLinkedListEntry(blockPos);
      return auxiliaryLinkedListEntry != null && auxiliaryLinkedListEntry instanceof TargetPool && !auxiliaryLinkedListEntry.linkedList.isEmpty()
         ? (GoldenIngot)auxiliaryLinkedListEntry.linkedList.getFirst() instanceof DistanceEvaluator
         : false;
   }

   @Override
   public AuxiliaryLinkedListEntry resolveAuxiliaryLinkedListEntry(int count, int count4) {
      return new TargetPool(this.world, this.random, count, count4);
   }

   @Override
   public String getText() {
      return "Temple";
   }
}
