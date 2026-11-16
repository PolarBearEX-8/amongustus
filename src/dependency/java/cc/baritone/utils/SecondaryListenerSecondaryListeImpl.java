package cc.baritone.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class SecondaryListenerSecondaryListeImpl extends AbstractSecondaryListenerCustom {
   public final List list;
   public final List list2;
   public final int count;
   public final SecondaryListener secondaryListener;

   public static Optional resolveOptional(Custom custom, Custom custom2, boolean flag) {
      if (custom2 != null && custom != null) {
         if (!custom.getBetterBlockPosXSYSZSBlockPos2().equals(custom2.getBetterBlockPosXSYSZSBlockPos())) {
            return Optional.empty();
         } else {
            HashSet set = new HashSet(custom2.unmodifiableList());
            int count2 = -1;

            for (int index = 0; index < custom.size() - 1; index++) {
               if (set.contains(custom.unmodifiableList().get(index))) {
                  count2 = index;
                  break;
               }
            }

            if (count2 != -1) {
               if (!flag) {
                  return Optional.empty();
               }
            } else {
               count2 = custom.size() - 1;
            }

            int count3 = custom2.unmodifiableList().indexOf(custom.unmodifiableList().get(count2));
            if (!flag && count3 != 0) {
               throw new IllegalStateException();
            } else {
               java.util.ArrayList list3 = new java.util.ArrayList();
               java.util.ArrayList list4 = new java.util.ArrayList();
               list3.addAll(custom.unmodifiableList().subList(0, count2 + 1));
               list4.addAll(custom.unmodifiableListAsList().subList(0, count2));
               list3.addAll(custom2.unmodifiableList().subList(count3 + 1, custom2.size()));
               list4.addAll(custom2.unmodifiableListAsList().subList(count3, custom2.size() - 1));
               return Optional.of(new SecondaryListenerSecondaryListeImpl(list3, list4, custom.getCount() + custom2.getCount(), custom.getSecondaryListener()));
            }
         }
      } else {
         return Optional.empty();
      }
   }

   @Override
   public SecondaryListener getSecondaryListener() {
      return this.secondaryListener;
   }

   @Override
   public int getCount() {
      return this.count;
   }

   @Override
   public List unmodifiableList() {
      return Collections.unmodifiableList(this.list);
   }

   @Override
   public List unmodifiableListAsList() {
      return Collections.unmodifiableList(this.list2);
   }

   @Override
   public int size() {
      return this.list.size();
   }

   private SecondaryListenerSecondaryListeImpl(List list3, List list4, int count2, SecondaryListener secondaryListener2) {
      this.list = list3;
      this.list2 = list4;
      this.count = count2;
      this.secondaryListener = secondaryListener2;
      this.updateState();
   }
}
