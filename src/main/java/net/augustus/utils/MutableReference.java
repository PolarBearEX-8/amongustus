package net.augustus.utils;

public class MutableReference {
   private Object value;

   public MutableReference(Object obj) {
      this.value = obj;
   }

   public Object getValue() {
      return this.value;
   }

   public MutableReference() {
   }

   public void setValue(Object obj) {
      this.value = obj;
   }
}
