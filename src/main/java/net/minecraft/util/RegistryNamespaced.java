package net.minecraft.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.Iterator;
import java.util.Map;

public class RegistryNamespaced extends RegistrySimple implements IObjectIntIterable {
   public final ObjectIntIdentityMap underlyingIntegerMap = new ObjectIntIdentityMap();
   public final Map inverseObjectRegistry = ((BiMap)this.registryObjects).inverse();

   public Object getNameForObject(Object obj) {
      return this.inverseObjectRegistry.get(obj);
   }

   @Override
   public Object getObject(Object obj) {
      return super.getObject(obj);
   }

   @Override
   public boolean containsKey(Object obj) {
      return super.containsKey(obj);
   }

   public Object getObjectById(int n) {
      return this.underlyingIntegerMap.getByValue(n);
   }

   public void register(int n, Object obj, Object obj2) {
      this.underlyingIntegerMap.put(obj2, n);
      this.putObject(obj, obj2);
   }

   @Override
   public Map createUnderlyingMap() {
      return HashBiMap.create();
   }

   @Override
   public Iterator iterator() {
      return this.underlyingIntegerMap.iterator();
   }

   public int getIDForObject(Object obj) {
      return this.underlyingIntegerMap.get(obj);
   }
}
