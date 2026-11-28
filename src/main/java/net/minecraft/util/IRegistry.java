package net.minecraft.util;

public interface IRegistry extends Iterable {
   Object getObject(Object obj);

   void putObject(Object obj, Object obj2);
}
