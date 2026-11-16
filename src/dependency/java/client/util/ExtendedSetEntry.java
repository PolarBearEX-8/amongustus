package client.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.util.MathHelper;

public class ExtendedSetEntry {
   public final int count4;
   public final Set set = Sets.newHashSetWithExpectedSize(256);
   public final List list = Lists.newArrayListWithCapacity(256);
   public int count;
   public int count3;
   public final int count2;
   public final int count5;
   public final boolean enabled;
   public final int count6;

   public static int computeCount(int count7, int count8) {
      return computeCountByIndex(count7, count8);
   }

   public int getCount() {
      return this.count;
   }

   public List getList() {
      ArrayList list2 = Lists.newArrayList();
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         ((SpecialListEntry)iterator.next()).applyList(list2);
      }

      ArrayList list3 = Lists.newArrayList();

      for (SpecialListEntry specialListEntry : (Iterable<SpecialListEntry>)(Iterable<?>)list2) {
         InternalComparableAdapter internalComparableAdapter = specialListEntry.getInternalComparableAdapter();
         ServiceStringEntry serviceStringEntry = internalComparableAdapter.getServiceStringEntry();
         serviceStringEntry.applyIndex(this.count, this.count3, specialListEntry.getCount2(), specialListEntry.getCount4(), internalComparableAdapter.isEnabled());
         list3.add(serviceStringEntry);
      }

      return list3;
   }

   public void updateState() {
      InternalComparableAdapter[] internalComparableAdapterArray = ((InternalComparableAdapter[])this.set.toArray(new InternalComparableAdapter[this.set.size()]));
      Arrays.sort(internalComparableAdapterArray);

      for (InternalComparableAdapter internalComparableAdapter : internalComparableAdapterArray) {
         if (!this.matchesInternalComparableAdapter(internalComparableAdapter)) {
            String text = String.format(
               "Unable to fit: %s, size: %dx%d, atlas: %dx%d, atlasMax: %dx%d - Maybe try a lower resolution resourcepack?",
               internalComparableAdapter.getServiceStringEntry().getLabelText3(),
               internalComparableAdapter.getServiceStringEntry().getCount(),
               internalComparableAdapter.getServiceStringEntry().getCount2(),
               this.count,
               this.count3,
               this.count2,
               this.count5
            );
            throw new ExtendedRuntimeExceptionAdapter(internalComparableAdapter, text);
         }
      }

      if (this.enabled) {
         this.count = MathHelper.processAction(this.count);
         this.count3 = MathHelper.processAction(this.count3);
      }
   }

   public int getCount3() {
      return this.count3;
   }

   private boolean matchesInternalComparableAdapterByInternalComparab(InternalComparableAdapter internalComparableAdapter) {
      int count7 = Math.min(internalComparableAdapter.getCountAsIndex(), internalComparableAdapter.getCount());
      boolean flag = this.count == 0 && this.count3 == 0;
      boolean flag2;
      if (this.enabled) {
         int count8 = MathHelper.processAction(this.count);
         int count9 = MathHelper.processAction(this.count3);
         int count10 = MathHelper.processAction(this.count + count7);
         int count11 = MathHelper.processAction(this.count3 + count7);
         boolean flag3 = count10 <= this.count2;
         boolean flag4 = count11 <= this.count5;
         if (!flag3 && !flag4) {
            return false;
         }

         boolean flag5 = count8 != count10;
         boolean flag6 = count9 != count11;
         if (flag5 ^ flag6) {
            flag2 = !flag5;
         } else {
            flag2 = flag3 && count8 <= count9;
         }
      } else {
         boolean flag7 = this.count + count7 <= this.count2;
         boolean flag8 = this.count3 + count7 <= this.count5;
         if (!flag7 && !flag8) {
            return false;
         }

         flag2 = flag7 && (flag || this.count <= this.count3);
      }

      int count12 = Math.max(internalComparableAdapter.getCountAsIndex(), internalComparableAdapter.getCount());
      if (MathHelper.processAction((!flag2 ? this.count3 : this.count) + count12) > (!flag2 ? this.count5 : this.count2)) {
         return false;
      } else {
         SpecialListEntry specialListEntry;
         if (flag2) {
            if (internalComparableAdapter.getCountAsIndex() > internalComparableAdapter.getCount()) {
               internalComparableAdapter.updateState();
            }

            if (this.count3 == 0) {
               this.count3 = internalComparableAdapter.getCount();
            }

            specialListEntry = new SpecialListEntry(this.count, 0, internalComparableAdapter.getCountAsIndex(), this.count3);
            this.count = this.count + internalComparableAdapter.getCountAsIndex();
         } else {
            specialListEntry = new SpecialListEntry(0, this.count3, this.count, internalComparableAdapter.getCount());
            this.count3 = this.count3 + internalComparableAdapter.getCount();
         }

         specialListEntry.matchesInternalComparableAdapter(internalComparableAdapter);
         this.list.add(specialListEntry);
         return true;
      }
   }

   public void applyServiceStringEntry(ServiceStringEntry serviceStringEntry) {
      InternalComparableAdapter internalComparableAdapter = new InternalComparableAdapter(serviceStringEntry, this.count4);
      if (this.count6 > 0) {
         internalComparableAdapter.applyIndex(this.count6);
      }

      this.set.add(internalComparableAdapter);
   }

   private boolean matchesInternalComparableAdapter(InternalComparableAdapter internalComparableAdapter) {
      for (int index = 0; index < this.list.size(); index++) {
         if (((SpecialListEntry)this.list.get(index)).matchesInternalComparableAdapter(internalComparableAdapter)) {
            return true;
         }

         internalComparableAdapter.updateState();
         if (((SpecialListEntry)this.list.get(index)).matchesInternalComparableAdapter(internalComparableAdapter)) {
            return true;
         }

         internalComparableAdapter.updateState();
      }

      return this.matchesInternalComparableAdapterByInternalComparab(internalComparableAdapter);
   }

   private static int computeCountByIndex(int count7, int count8) {
      return (count7 >> count8) + ((count7 & (1 << count8) - 1) == 0 ? 0 : 1) << count8;
   }

   public ExtendedSetEntry(int count7, int count8, boolean flag, int count9, int count10) {
      this.count4 = count10;
      this.count2 = count7;
      this.count5 = count8;
      this.enabled = flag;
      this.count6 = count9;
   }
}
