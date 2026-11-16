package client.util;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.entity.EntityGuardian;
import net.minecraft.util.MathHelper;

public class StaticListEntry extends InternalRandomEntryAdapter {
   public int count2 = 32;
   public int count3 = 5;
   public static final List list = Arrays.asList(
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager28,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager39,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager37,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager26,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager12
   );
   public static final List list2 = Lists.newArrayList();

   static {
      list2.add(new ClassHolder(EntityGuardian.class, 1, 2, 4));
   }

   @Override
   public AuxiliaryLinkedListEntry resolveAuxiliaryLinkedListEntry(int count, int count4) {
      return new AuxiliarySetEntry(this.world, this.random, count, count4);
   }

   public StaticListEntry() {
   }

   @Override
   public String getText() {
      return "Monument";
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
      Random random = this.world.resolveRandom(count7, count8, 10387313);
      int count9 = count7 * this.count2;
      int count10 = count8 * this.count2;
      int count11 = count9 + (random.nextInt(this.count2 - this.count3) + random.nextInt(this.count2 - this.count3)) / 2;
      int count12 = count10 + (random.nextInt(this.count2 - this.count3) + random.nextInt(this.count2 - this.count3)) / 2;
      if (count == count11 && count4 == count12) {
         if (this.world.getInvalidBiomeIdManager().resolveBiomeIDIsOutOfBoundsManager(count * 16 + 8, count4 * 16 + 8, (BiomeIDIsOutOfBoundsManager)null)
            != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager39) {
            return false;
         }

         if (this.world.getInvalidBiomeIdManager().contains(count * 16 + 8, count4 * 16 + 8, 29, list)) {
            return true;
         }
      }

      return false;
   }

   public List getList2() {
      return list2;
   }

   public StaticListEntry(Map map) {
      this();

      for (Object entryObj : map.entrySet()) { Entry entry = (Entry)entryObj;
         if (((String)entry.getKey()).equals("spacing")) {
            this.count2 = MathHelper.processAction((String)entry.getValue(), this.count2, 1);
         } else if (((String)entry.getKey()).equals("separation")) {
            this.count3 = MathHelper.processAction((String)entry.getValue(), this.count3, 1);
         }
      }
   }
}
