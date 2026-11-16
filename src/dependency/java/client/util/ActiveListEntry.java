package client.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.util.MathHelper;

public class ActiveListEntry extends InternalRandomEntryAdapter {
   public static final List list = Arrays.asList(
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17,
      BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager7
   );
   public int count2;
   public int count3 = 32;
   public int count4 = 8;

   public ActiveListEntry(Map map) {
      this();

      for (Object entryObj : map.entrySet()) {
         Entry entry = (Entry)entryObj;
         if (((String)entry.getKey()).equals("size")) {
            this.count2 = MathHelper.processAction((String)entry.getValue(), this.count2, 0);
         } else if (((String)entry.getKey()).equals("distance")) {
            this.count3 = MathHelper.processAction((String)entry.getValue(), this.count3, this.count4 + 1);
         }
      }
   }

   @Override
   public String getText() {
      return "Village";
   }

   @Override
   public AuxiliaryLinkedListEntry resolveAuxiliaryLinkedListEntry(int count, int count5) {
      return new TargetFactory(this.world, this.random, count, count5, this.count2);
   }

   @Override
   public boolean checkIndex(int count, int count5) {
      int count6 = count;
      int count7 = count5;
      if (count < 0) {
         count6 = count - (this.count3 - 1);
      }

      if (count5 < 0) {
         count7 = count5 - (this.count3 - 1);
      }

      int count8 = count6 / this.count3;
      int count9 = count7 / this.count3;
      Random random = this.world.resolveRandom(count8, count9, 10387312);
      int count10 = count8 * this.count3;
      int count11 = count9 * this.count3;
      int count12 = count10 + random.nextInt(this.count3 - this.count4);
      int count13 = count11 + random.nextInt(this.count3 - this.count4);
      return count == count12 && count5 == count13 && this.world.getInvalidBiomeIdManager().contains(count * 16 + 8, count5 * 16 + 8, 0, list);
   }

   public ActiveListEntry() {
   }
}
