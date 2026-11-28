package net.minecraft.util;


import java.util.Objects;
public class LongHashMap {
   private final float percentUseable = 0.75F;
   public transient LongHashMap.Entry[] hashArray = new LongHashMap.Entry[4096];
   private transient int numHashElements;
   private int mask;
   private int capacity = 3072;
   private transient volatile int modCount;

   public long[] getKeys() {
      long[] longs = new long[this.numHashElements];
      int n = 0;

      for (LongHashMap.Entry entry : this.hashArray) {
         for (LongHashMap.Entry i = entry; i != null; i = i.nextEntry) {
            longs[n++] = i.key;
         }
      }

      return longs;
   }

   public Object remove(long l) {
      LongHashMap.Entry entry = this.removeKey(l);
      return entry == null ? null : entry.value;
   }

   public LongHashMap() {
      this.mask = this.hashArray.length - 1;
   }

   public int getNumHashElements() {
      return this.numHashElements;
   }

   private static int hash(int n) {
      n = n ^ n >>> 20 ^ n >>> 12;
      return n ^ n >>> 7 ^ n >>> 4;
   }

   public boolean containsItem(long l) {
      return this.getEntry(l) != null;
   }

   private void createKey(int n, long l, Object obj, int n2) {
      LongHashMap.Entry entry = this.hashArray[n2];
      this.hashArray[n2] = new LongHashMap.Entry(n, l, obj, entry);
      if (this.numHashElements++ >= this.capacity) {
         this.resizeTable(2 * this.hashArray.length);
      }
   }

   public final LongHashMap.Entry getEntry(long l) {
      int hashedKey = getHashedKey(l);

      for (LongHashMap.Entry i = this.hashArray[getHashIndex(hashedKey, this.mask)]; i != null; i = i.nextEntry) {
         if (i.key == l) {
            return i;
         }
      }

      return null;
   }

   private void copyHashTableTo(LongHashMap.Entry[] entrys) {
      LongHashMap.Entry[] hashArray2 = this.hashArray;
      int length = entrys.length;

      for (int i = 0; i < hashArray2.length; i++) {
         LongHashMap.Entry entry = hashArray2[i];
         if (entry != null) {
            hashArray2[i] = null;

            while (true) {
               LongHashMap.Entry nextEntry = entry.nextEntry;
               int hashIndex = getHashIndex(entry.hash, length - 1);
               entry.nextEntry = entrys[hashIndex];
               entrys[hashIndex] = entry;
               entry = nextEntry;
               if (nextEntry == null) {
                  break;
               }
            }
         }
      }
   }

   private static int getHashedKey(long l) {
      return (int)(l ^ l >>> 27);
   }

   public double getKeyDistribution() {
      int n = 0;

      for (int i = 0; i < this.hashArray.length; i++) {
         if (this.hashArray[i] != null) {
            n++;
         }
      }

      return (double)n / (double)this.numHashElements;
   }

   public Object getValueByKey(long l) {
      int hashedKey = getHashedKey(l);

      for (LongHashMap.Entry i = this.hashArray[getHashIndex(hashedKey, this.mask)]; i != null; i = i.nextEntry) {
         if (i.key == l) {
            return i.value;
         }
      }

      return null;
   }

   private static int getHashIndex(int n, int n2) {
      return n & n2;
   }

   public final LongHashMap.Entry removeKey(long l) {
      int hashIndex = getHashIndex(getHashedKey(l), this.mask);
      LongHashMap.Entry entry = this.hashArray[hashIndex];
      LongHashMap.Entry entry2 = entry;

      while (entry2 != null) {
         LongHashMap.Entry nextEntry = entry2.nextEntry;
         if (entry2.key == l) {
            this.modCount++;
            this.numHashElements--;
            if (entry == entry2) {
               this.hashArray[hashIndex] = nextEntry;
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

   public static int accessGetHashedKey(long l) {
      return getHashedKey(l);
   }

   public void add(long l, Object obj) {
      int hashedKey = getHashedKey(l);
      int hashIndex = getHashIndex(hashedKey, this.mask);

      for (LongHashMap.Entry i = this.hashArray[hashIndex]; i != null; i = i.nextEntry) {
         if (i.key == l) {
            i.value = obj;
            return;
         }
      }

      this.modCount++;
      this.createKey(hashedKey, l, obj, hashIndex);
   }

   private void resizeTable(int n) {
      if (this.hashArray.length == 1073741824) {
         this.capacity = Integer.MAX_VALUE;
      } else {
         LongHashMap.Entry[] entrys = new LongHashMap.Entry[n];
         this.copyHashTableTo(entrys);
         this.hashArray = entrys;
         this.mask = this.hashArray.length - 1;
         float f = (float)n;
         this.capacity = (int)(f * 0.75F);
      }
   }


   public static class Entry {
      public final long key;
      public final int hash;
      public Object value;
      public Entry nextEntry;

      public final Object getValue() {
         return this.value;
      }

      @Override
      public final int hashCode() {
         return LongHashMap.accessGetHashedKey(this.key);
      }

      @Override
      public final String toString() {
         return this.getKey() + "=" + this.getValue();
      }

      public Entry(int n, long l, Object obj, Entry entry) {
         this.value = obj;
         this.nextEntry = entry;
         this.key = l;
         this.hash = n;
      }

      @Override
      public final boolean equals(Object obj) {
         if (!(obj instanceof Entry)) {
            return false;
         } else {
            Entry entry = (Entry)obj;
            Long key2 = this.getKey();
            Long key3 = entry.getKey();
            if (Objects.equals(key2, key3)) {
               return Objects.equals(this.getValue(), entry.getValue());
            } else {
               return false;
            }
         }
      }

      public final long getKey() {
         return this.key;
      }
   }
}
