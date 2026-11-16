package client.util;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddingDuplicateKeyManager implements AuxiliaryIterableExtension {
   public static final Logger logger = LogManager.getLogger();
   public final Map map = this.create();

   public Set getSet() {
      return Collections.unmodifiableSet(this.map.keySet());
   }

   public Map create() {
      return Maps.newHashMap();
   }

   public boolean containsKey(Object entry) {
      return this.map.containsKey(entry);
   }

   @Override
   public void applyTarget(Object entry, Object entry2) {
      Validate.notNull(entry);
      Validate.notNull(entry2);
      if (this.map.containsKey(entry)) {
         logger.debug("Adding duplicate key '" + entry + "' to registry");
      }

      this.map.put(entry, entry2);
   }

   @Override
   public Object getObject(Object entry) {
      return this.map.get(entry);
   }

   @Override
   public Iterator iterator() {
      return this.map.values().iterator();
   }
}
