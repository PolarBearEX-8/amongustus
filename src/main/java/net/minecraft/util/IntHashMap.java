package net.minecraft.util;

public class IntHashMap {
   private transient IntHashMap.Entry[] slots = new IntHashMap.Entry[16];
   private transient int count;
   private int threshold = 12;
   private final float growFactor = 0.75F;

   private static int getSlotIndex(int n, int n2) {
      return n & n2 - 1;
   }

   public static int accessComputeHash(int n) {
      return computeHash(n);
   }

   private void grow(int n) {
      if (this.slots.length == 1073741824) {
         this.threshold = Integer.MAX_VALUE;
      } else {
         IntHashMap.Entry[] entrys = new IntHashMap.Entry[n];
         this.copyTo(entrys);
         this.slots = entrys;
         this.threshold = (int)((float)n * 0.75F);
      }
   }

   public Object lookup(int n) {
      int n2 = computeHash(n);

      for (IntHashMap.Entry i = this.slots[getSlotIndex(n2, this.slots.length)]; i != null; i = i.nextEntry) {
         if (i.hashEntry == n) {
            return i.valueEntry;
         }
      }

      return null;
   }

   public void clearMap() {
      IntHashMap.Entry[] slots2 = this.slots;

      for (int i = 0; i < slots2.length; i++) {
         slots2[i] = null;
      }

      this.count = 0;
   }

   public final IntHashMap.Entry lookupEntry(int n) {
      int n2 = computeHash(n);

      for (IntHashMap.Entry i = this.slots[getSlotIndex(n2, this.slots.length)]; i != null; i = i.nextEntry) {
         if (i.hashEntry == n) {
            return i;
         }
      }

      return null;
   }

   private void insert(int n, int n2, Object obj, int n3) {
      IntHashMap.Entry entry = this.slots[n3];
      this.slots[n3] = new IntHashMap.Entry(n, n2, obj, entry);
      if (this.count++ >= this.threshold) {
         this.grow(2 * this.slots.length);
      }
   }

   public boolean containsItem(int n) {
      return this.lookupEntry(n) != null;
   }

   public Object removeObject(int n) {
      IntHashMap.Entry entry = this.removeEntry(n);
      return entry == null ? null : entry.valueEntry;
   }

   public final IntHashMap.Entry removeEntry(int n) {
      int slotIndex = getSlotIndex(computeHash(n), this.slots.length);
      IntHashMap.Entry entry = this.slots[slotIndex];
      IntHashMap.Entry entry2 = entry;

      while (entry2 != null) {
         IntHashMap.Entry nextEntry = entry2.nextEntry;
         if (entry2.hashEntry == n) {
            this.count--;
            if (entry == entry2) {
               this.slots[slotIndex] = nextEntry;
            } else {
               entry.nextEntry = nextEntry;
            }

            return entry2;
         }

         entry = entry2;
         entry2 = nextEntry;
      }

      return entry2;
   }

   private static int computeHash(int n) {
      n = n ^ n >>> 20 ^ n >>> 12;
      return n ^ n >>> 7 ^ n >>> 4;
   }

   private void copyTo(IntHashMap.Entry[] entrys) {
      IntHashMap.Entry[] slots2 = this.slots;
      int length = entrys.length;

      for (int i = 0; i < slots2.length; i++) {
         IntHashMap.Entry entry = slots2[i];
         if (entry != null) {
            slots2[i] = null;

            while (true) {
               IntHashMap.Entry nextEntry = entry.nextEntry;
               int slotIndex = getSlotIndex(entry.slotHash, length);
               entry.nextEntry = entrys[slotIndex];
               entrys[slotIndex] = entry;
               entry = nextEntry;
               if (nextEntry == null) {
                  break;
               }
            }
         }
      }
   }

   public void addKey(int n, Object obj) {
      int n2 = computeHash(n);
      int slotIndex = getSlotIndex(n2, this.slots.length);

      for (IntHashMap.Entry i = this.slots[slotIndex]; i != null; i = i.nextEntry) {
         if (i.hashEntry == n) {
            i.valueEntry = obj;
            return;
         }
      }

      this.insert(n2, n, obj, slotIndex);
   }


   public static class Entry {
      public final int hashEntry;
      public Object valueEntry;
      public Entry nextEntry;
      public final int slotHash;

      @Override
      public final boolean equals(Object obj) {
         if (!(obj instanceof Entry)) {
            return false;
         } else {
            Entry entry = (Entry)obj;
            Integer hash = this.getHash();
            Integer hash2 = entry.getHash();
            if (hash == hash2 || hash != null && hash.equals(hash2)) {
               Object value = this.getValue();
               Object value2 = entry.getValue();
               if (value == value2 || value != null && value.equals(value2)) {
                  return true;
               }
            }

            return false;
         }
      }

      public final Object getValue() {
         return this.valueEntry;
      }

      @Override
      public final int hashCode() {
         return IntHashMap.accessComputeHash(this.hashEntry);
      }

      public Entry(int n, int n2, Object obj, Entry entry) {
         this.valueEntry = obj;
         this.nextEntry = entry;
         this.hashEntry = n2;
         this.slotHash = n;
      }

      public final int getHash() {
         return this.hashEntry;
      }

      @Override
      public final String toString() {
         return this.getHash() + "=" + this.getValue();
      }
   }
}
