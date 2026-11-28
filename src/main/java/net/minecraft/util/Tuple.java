package net.minecraft.util;

public class Tuple {
   private Object a;
   private Object second;

   public Tuple(Object obj, Object obj2) {
      this.a = obj;
      this.second = obj2;
   }

   public Object getFirst() {
      return this.a;
   }

   public Object getSecond() {
      return this.second;
   }
}
