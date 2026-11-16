package client.util;

import com.google.common.collect.Maps;
import java.util.Map;

public class AltMapEntry extends AddingDuplicateKeyManager {
   public Map map2;

   public void clear() {
      this.map2.clear();
   }

   public void applyCommonListEntry(CommonListEntry commonListEntry) {
      this.applyTarget(commonListEntry.getResourceLocation(), commonListEntry);
   }

   @Override
   public Map create() {
      this.map2 = Maps.newHashMap();
      return this.map2;
   }
}
