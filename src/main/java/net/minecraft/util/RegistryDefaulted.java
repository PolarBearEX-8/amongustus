package net.minecraft.util;

public class RegistryDefaulted extends RegistrySimple {
   private final Object defaultObject;

   @Override
   public Object getObject(Object obj) {
      Object object = super.getObject(obj);
      return object == null ? this.defaultObject : object;
   }

   public RegistryDefaulted(Object obj) {
      this.defaultObject = obj;
   }
}
