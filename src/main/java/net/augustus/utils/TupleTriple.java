package net.augustus.utils;

public class TupleTriple {
   public final Object second;
   public final Object first;
   public final Object third;

   public Object getThird() {
      return this.third;
   }

   public Object getFirst() {
      return this.first;
   }

   public Object getSecond() {
      return this.second;
   }

   public TupleTriple(Object obj, Object obj2, Object obj3) {
      this.first = obj;
      this.second = obj2;
      this.third = obj3;
   }
}
