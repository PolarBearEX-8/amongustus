package net.minecraft.util;

import client.util.AddingDuplicateKeyManager;
import client.util.IterableExtension;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.Iterator;
import java.util.Map;

public class RegistryNamespaced extends AddingDuplicateKeyManager implements IterableExtension {
   public final RegistryNamespacedDefaultedByKey registryNamespacedDefaultedByKey = new RegistryNamespacedDefaultedByKey();
   public final Map map3 = ((BiMap)this.map).inverse();

   public Object getObject2(Object entry) {
      return this.map3.get(entry);
   }

   public int getIndex(Object entry) {
      return this.registryNamespacedDefaultedByKey.computeCount(entry);
   }

   public Object getObject3(int count) {
      return this.registryNamespacedDefaultedByKey.resolveTarget(count);
   }

   public void applyObject2(int count, Object entry, Object entry2) {
      this.registryNamespacedDefaultedByKey.applyTarget(entry2, count);
      this.applyTarget(entry, entry2);
   }

   @Override
   public Map create() {
      return HashBiMap.create();
   }

   @Override
   public boolean containsKey(Object entry) {
      return super.containsKey(entry);
   }

   @Override
   public Object getObject(Object entry) {
      return super.getObject(entry);
   }

   @Override
   public Iterator iterator() {
      return this.registryNamespacedDefaultedByKey.iterator();
   }
}
