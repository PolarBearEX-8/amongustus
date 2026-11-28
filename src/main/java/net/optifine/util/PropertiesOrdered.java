package net.optifine.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

public class PropertiesOrdered extends Properties {
   private Set keysOrdered = new LinkedHashSet();

   @Override
   public Set keySet() {
      Set keys = super.keySet();
      this.keysOrdered.retainAll(keys);
      return Collections.unmodifiableSet(this.keysOrdered);
   }

   @Override
   public synchronized Enumeration keys() {
      return Collections.enumeration(this.keySet());
   }

   @Override
   public synchronized Object put(Object obj, Object obj2) {
      this.keysOrdered.add(obj);
      return super.put(obj, obj2);
   }
}
