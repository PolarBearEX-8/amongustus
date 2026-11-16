package cc.baritone.utils;

import java.util.Collections;
import java.util.List;

public class SecondaryListenerSecondaryListe extends AbstractSecondaryListenerCustom {
   public final List list;
   public final List list2;
   public final int count;
   public final SecondaryListener secondaryListener;

   @Override
   public int getCount() {
      return this.count;
   }

   public SecondaryListenerSecondaryListe(Custom custom, int count2) {
      this(custom, 0, count2);
   }

   @Override
   public List unmodifiableListAsList() {
      return Collections.unmodifiableList(this.list2);
   }

   @Override
   public SecondaryListener getSecondaryListener() {
      return this.secondaryListener;
   }

   public SecondaryListenerSecondaryListe(Custom custom, int count2, int count3) {
      this.list = custom.unmodifiableList().subList(count2, count3 + 1);
      this.list2 = custom.unmodifiableListAsList().subList(count2, count3);
      this.count = custom.getCount();
      this.secondaryListener = custom.getSecondaryListener();
      this.updateState();
   }

   @Override
   public List unmodifiableList() {
      return Collections.unmodifiableList(this.list);
   }
}
