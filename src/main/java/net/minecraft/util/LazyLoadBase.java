package net.minecraft.util;

public abstract class LazyLoadBase {
   private Object value;
   private boolean isLoaded = false;

   public abstract Object load();

   public Object getValue() {
      if (!this.isLoaded) {
         this.isLoaded = true;
         this.value = this.load();
      }

      return this.value;
   }
}
