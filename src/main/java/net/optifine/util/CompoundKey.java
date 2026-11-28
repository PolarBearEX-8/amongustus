package net.optifine.util;

import net.optifine.config.Config;

public class CompoundKey {
   private Object[] keys;
   private int hashcode = 0;

   public CompoundKey(Object obj, Object obj2) {
      this(new Object[]{obj, obj2});
   }

   private static boolean compareKeys(Object obj, Object obj2) {
      return obj == obj2 ? true : (obj == null ? false : (obj2 == null ? false : obj.equals(obj2)));
   }

   @Override
   public int hashCode() {
      if (this.hashcode == 0) {
         this.hashcode = 7;

         for (int i = 0; i < this.keys.length; i++) {
            Object obj = this.keys[i];
            if (obj != null) {
               this.hashcode = 31 * this.hashcode + obj.hashCode();
            }
         }
      }

      return this.hashcode;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (obj == this) {
         return true;
      } else if (!(obj instanceof CompoundKey)) {
         return false;
      } else {
         Object[] keys2 = ((CompoundKey)obj).getKeys();
         if (keys2.length != this.keys.length) {
            return false;
         } else {
            for (int i = 0; i < this.keys.length; i++) {
               if (!compareKeys(this.keys[i], keys2[i])) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public Object[] getKeysCopy() {
      return (Object[])this.keys.clone();
   }

   public CompoundKey(Object[] array) {
      this.keys = (Object[])array.clone();
   }

   @Override
   public String toString() {
      return "[" + Config.arrayToString3(this.keys) + "]";
   }

   private Object[] getKeys() {
      return this.keys;
   }

   public CompoundKey(Object obj, Object obj2, Object obj3) {
      this(new Object[]{obj, obj2, obj3});
   }
}
