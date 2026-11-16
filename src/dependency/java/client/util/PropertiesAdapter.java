package client.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

public class PropertiesAdapter extends Properties {
   public Set set = new LinkedHashSet();

   @Override
   public synchronized Object put(Object entry, Object entry2) {
      this.set.add(entry);
      return super.put(entry, entry2);
   }

   @Override
   public Set keySet() {
      Set keys = super.keySet();
      this.set.retainAll(keys);
      return Collections.unmodifiableSet(this.set);
   }

   @Override
   public synchronized Enumeration keys() {
      return Collections.enumeration(this.keySet());
   }
}
