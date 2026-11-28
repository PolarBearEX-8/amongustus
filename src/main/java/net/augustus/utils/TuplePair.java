package net.augustus.utils;

public class TuplePair {
   private final Object second;
   private final Object first;

   public TuplePair(Object obj, Object obj2) {
      this.first = obj;
      this.second = obj2;
   }

   public Object getSecond() {
      return this.second;
   }

   public Object getFirst() {
      return this.first;
   }
}
