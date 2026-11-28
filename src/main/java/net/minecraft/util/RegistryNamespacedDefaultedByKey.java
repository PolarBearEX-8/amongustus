package net.minecraft.util;

import org.apache.commons.lang3.Validate;

public class RegistryNamespacedDefaultedByKey extends RegistryNamespaced {
   private final Object defaultValueKey;
   private Object defaultValue;

   @Override
   public Object getObjectById(int n) {
      Object objectById = super.getObjectById(n);
      return objectById == null ? this.defaultValue : objectById;
   }

   public RegistryNamespacedDefaultedByKey(Object obj) {
      this.defaultValueKey = obj;
   }

   @Override
   public void register(int n, Object obj, Object obj2) {
      if (this.defaultValueKey.equals(obj)) {
         this.defaultValue = obj2;
      }

      super.register(n, obj, obj2);
   }

   @Override
   public Object getObject(Object obj) {
      Object object = super.getObject(obj);
      return object == null ? this.defaultValue : object;
   }

   public void validateKey() {
      Validate.notNull(this.defaultValueKey);
   }
}
