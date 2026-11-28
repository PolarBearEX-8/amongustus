package org.json.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ItemList {
   private String sp = ",";
   public List items = new ArrayList();

   public ItemList(String str) {
      this.split(str, this.sp, this.items);
   }

   public void add(String str) {
      if (str != null) {
         this.items.add(str.trim());
      }
   }

   public String[] getArray() {
      return (String[])this.items.toArray();
   }

   public void addAll(String str, String str2, boolean flag) {
      this.split(str, str2, this.items, flag);
   }

   public void reset() {
      this.sp = ",";
      this.items.clear();
   }

   public void clear() {
      this.items.clear();
   }

   public void addAll(String str, String str2) {
      this.split(str, str2, this.items);
   }

   public void split(String str, String str2, List list, boolean flag) {
      if (str != null && str2 != null) {
         if (flag) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, str2);

            while (stringTokenizer.hasMoreTokens()) {
               list.add(stringTokenizer.nextToken().trim());
            }
         } else {
            this.split(str, str2, list);
         }
      }
   }

   public String get(int n) {
      return (String)this.items.get(n);
   }

   public List getItems() {
      return this.items;
   }

   public void split(String str, String str2, List list) {
      if (str != null && str2 != null) {
         int n = 0;
         int n2 = 0;

         do {
            n2 = n;
            n = str.indexOf(str2, n);
            if (n == -1) {
               break;
            }

            list.add(str.substring(n, n).trim());
            n += str2.length();
         } while (n != -1);

         list.add(str.substring(n2).trim());
      }
   }

   public ItemList(String str, String str2) {
      this.sp = str;
      this.split(str, str2, this.items);
   }

   public void add(int n, String str) {
      if (str != null) {
         this.items.add(n, str.trim());
      }
   }

   public int size() {
      return this.items.size();
   }

   public ItemList() {
   }

   public void addAll2(String str) {
      this.split(str, this.sp, this.items);
   }

   public void addAll(ItemList itemList) {
      this.items.addAll(itemList.items);
   }

   public void setSp(String str) {
      this.sp = str;
   }

   @Override
   public String toString() {
      return this.toString(this.sp);
   }

   public ItemList(String str, String str2, boolean flag) {
      this.split(str, str2, this.items, flag);
   }

   public String toString(String str) {
      StringBuffer sb = new StringBuffer();

      for (int i = 0; i < this.items.size(); i++) {
         if (i == 0) {
            sb.append(this.items.get(i));
         } else {
            sb.append(str);
            sb.append(this.items.get(i));
         }
      }

      return sb.toString();
   }
}
