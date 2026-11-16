package client.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class ExtendedHashMapHolder {
   public HashMap hashMap = new HashMap();

   public SupplementalStringEntry get(String text) {
      return (SupplementalStringEntry)this.hashMap.get(text);
   }

   public Collection values() {
      return this.hashMap.values();
   }

   public String getText() {
      Optional optional = this.hashMap.keySet().stream().findFirst();
      return optional.isPresent() ? (String)optional.get() : null;
   }

   public void put(String text, SupplementalStringEntry supplementalStringEntry) {
      this.hashMap.put(text, supplementalStringEntry);
   }
}
