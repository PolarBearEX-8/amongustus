package net.minecraft.util;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrySimple implements IRegistry {
   private static final Logger LOGGER = LogManager.getLogger();
   public final Map registryObjects = this.createUnderlyingMap();

   public boolean containsKey(Object obj) {
      return this.registryObjects.containsKey(obj);
   }

   public Map createUnderlyingMap() {
      return Maps.newHashMap();
   }

   @Override
   public Iterator iterator() {
      return this.registryObjects.values().iterator();
   }

   @Override
   public Object getObject(Object obj) {
      return this.registryObjects.get(obj);
   }

   @Override
   public void putObject(Object obj, Object obj2) {
      Validate.notNull(obj);
      Validate.notNull(obj2);
      if (this.registryObjects.containsKey(obj)) {
         LOGGER.debug("Adding duplicate key '" + obj + "' to registry");
      }

      this.registryObjects.put(obj, obj2);
   }

   public Set getKeys() {
      return Collections.unmodifiableSet(this.registryObjects.keySet());
   }
}
