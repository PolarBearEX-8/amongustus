package net.augustus.utils;

import java.util.ArrayList;
import net.minecraft.util.MathHelper;

public class BoundedHistoryList {
   private int maxSize;
   private ArrayList entries;

   public boolean isFull() {
      return this.entries.size() == this.maxSize;
   }

   public Object getFromEnd(int n) {
      return this.get(this.entries.size() - n - 1);
   }

   public int size() {
      return this.entries.size();
   }

   public void add(Object obj) {
      this.entries.add(obj);
      if (this.entries.size() > this.maxSize) {
         this.entries.remove(0);
      }
   }

   public void clear() {
      this.entries.clear();
   }

   public int getMaxSize() {
      return this.maxSize;
   }

   public ArrayList getEntries() {
      return this.entries;
   }

   public Object get(int n) {
      return this.entries.get(MathHelper.clamp_int(n, 0, this.entries.size() - 1));
   }

   public Object getOldest() {
      return this.entries.get(0);
   }

   public BoundedHistoryList(int n) {
      this.entries = new ArrayList(n);
      this.maxSize = n;
   }
}
