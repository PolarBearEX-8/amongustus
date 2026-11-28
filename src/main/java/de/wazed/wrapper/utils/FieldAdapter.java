package de.wazed.wrapper.utils;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Optional;

public class FieldAdapter {
   private final HashMap<String, MethodHandle> fields = new HashMap<>();
   private static final Lookup LOOKUP;
   private static Field MODIFIERS;

   public FieldAdapter(String parent) {
      try {
         Class cls = Class.forName(parent);
         Field modifiers = MODIFIERS;

         for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            int accessFlags = field.getModifiers();
            if (Modifier.isFinal(accessFlags)) {
               modifiers.setInt(field, accessFlags & -17);
            }

            MethodHandle handler = LOOKUP.unreflectSetter(field);
            handler = handler.asType(handler.type().generic().changeReturnType(void.class));
            this.fields.put(field.getName(), handler);
         }
      } catch (ClassNotFoundException e) {
         throw new RuntimeException("Couldn't load/find the specified class");
      } catch (Exception e2) {
         throw new RuntimeException("Couldn't create a method handler for the field");
      }
   }

   public void updateFieldIfPresent(String name, Object newValue) {
      Optional.ofNullable(this.fields.get(name)).ifPresent(setter -> {
         try {
            setter.invokeExact((Object)newValue);
         } catch (Throwable e) {
            e.printStackTrace();
         }
      });
   }

   static {
      try {
         MODIFIERS = Field.class.getDeclaredField("modifiers");
         MODIFIERS.setAccessible(true);
      } catch (NoSuchFieldException e) {
         e.printStackTrace();
      }

      Lookup lookupObject;
      try {
         Field lookupImplField = Lookup.class.getDeclaredField("IMPL_LOOKUP");
         lookupImplField.setAccessible(true);
         lookupObject = (Lookup)lookupImplField.get(null);
      } catch (ReflectiveOperationException e2) {
         lookupObject = MethodHandles.lookup();
      }

      LOOKUP = lookupObject;
   }
}
