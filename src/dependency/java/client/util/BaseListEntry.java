package client.util;

import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.data.IMetadataSection;

public class BaseListEntry implements IMetadataSection {
   public final List list;
   public final int count2;
   public final int count3;
   public final int count;
   public final boolean enabled;

   private SplineReader get(int count4) {
      return (SplineReader)this.list.get(count4);
   }

   public boolean checkIndex(int count4) {
      return !((SplineReader)this.list.get(count4)).isActive();
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public int size() {
      return this.list.size();
   }

   public int getCount3() {
      return this.count3;
   }

   public Set getSet() {
      HashSet set = Sets.newHashSet();

      for (Object splineReaderObj : this.list) { SplineReader splineReader = (SplineReader)splineReaderObj;
         set.add(splineReader.getCount2());
      }

      return set;
   }

   public int computeCount(int count4) {
      return ((SplineReader)this.list.get(count4)).getCount2();
   }

   public int getCount2() {
      return this.count2;
   }

   public int getCount() {
      return this.count;
   }

   public int computeCountByIndex(int count4) {
      SplineReader splineReader = this.get(count4);
      return splineReader.isActive() ? this.count : splineReader.getCount();
   }

   public BaseListEntry(List list2, int count4, int count5, int count6, boolean flag) {
      this.list = list2;
      this.count2 = count4;
      this.count3 = count5;
      this.count = count6;
      this.enabled = flag;
   }
}
