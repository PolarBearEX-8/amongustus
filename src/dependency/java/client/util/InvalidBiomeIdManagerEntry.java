package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.item.GoldenTalisman;

public class InvalidBiomeIdManagerEntry extends GoldenTalisman {
   public InvalidBiomeIdManager invalidBiomeIdManager;
   public boolean enabled2;
   public int count4;
   public ClassControllerManager classControllerManager;
   public List list3;
   public List list2 = Lists.newArrayList();
   public List list = Lists.newArrayList();

   public InvalidBiomeIdManagerEntry(InvalidBiomeIdManager invalidBiomeIdManager2, int count, Random random, int count2, int count3, List list4, int count5) {
      super((InvalidBiomeIdManagerEntry)null, 0, random, count2, count3);
      this.invalidBiomeIdManager = invalidBiomeIdManager2;
      this.list3 = list4;
      this.count4 = count5;
      BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = invalidBiomeIdManager2.resolveBiomeIDIsOutOfBoundsManager(count2, count3, BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager10);
      this.enabled2 = biomeIDIsOutOfBoundsManager == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17 || biomeIDIsOutOfBoundsManager == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager27;
      this.setEnabled(this.enabled2);
   }

   public InvalidBiomeIdManager getInvalidBiomeIdManager() {
      return this.invalidBiomeIdManager;
   }

   public InvalidBiomeIdManagerEntry() {
   }
}
