package net.lenni0451.commons.httpclient.utils;

public class ResettableStorage<T> {
   private boolean set = false;
   private T value;

   public ResettableStorage() {
   }

   public ResettableStorage(T defaultValue) {
      this.set(defaultValue);
   }

   public boolean isSet() {
      return this.set;
   }

   public void unset() {
      this.set = false;
      this.value = null;
   }

   public T get() {
      if (!this.set) {
         throw new IllegalStateException("Value is not set");
      } else {
         return this.value;
      }
   }

   public void set(T value) {
      this.value = value;
      this.set = true;
   }
}
