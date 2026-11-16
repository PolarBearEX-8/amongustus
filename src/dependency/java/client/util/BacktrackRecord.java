package client.util;

import java.util.ArrayList;
import net.minecraft.util.MathHelper;

public class BacktrackRecord {
   public int count;
   public ArrayList arrayList;

   public Object getObject(int count2) {
      return this.arrayList.get(MathHelper.getInt12(count2, 0, this.arrayList.size() - 1));
   }

   public Object get() {
      return this.arrayList.get(0);
   }

   public ArrayList getItemList() {
      return this.arrayList;
   }

   public int getCount() {
      return this.count;
   }

   public int getInt() {
      return this.arrayList.size();
   }

   public Object getObject3(int count2) {
      int count3 = this.arrayList.size() - count2 - 1;
      return this.getObject(count3);
   }

   public boolean canExecute() {
      return this.arrayList.size() == this.count;
   }

   public void applyObject(Object entry) {
      this.arrayList.add(entry);
      if (this.arrayList.size() > this.count) {
         this.arrayList.remove(0);
      }
   }

   public void clear() {
      this.arrayList.clear();
   }

   public BacktrackRecord(int count2) {
      this.arrayList = new ArrayList(count2);
      this.count = count2;
   }
}
