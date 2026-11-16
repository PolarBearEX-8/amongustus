package client.util;

import java.util.Comparator;

public class InternalComparatorAdapter implements Comparator {
   public final JsonManager jsonManager;

   public int computeCount(ResourceLocationAdapter resourceLocationAdapter, ResourceLocationAdapter resourceLocationAdapter2) {
      return resourceLocationAdapter.toString().compareTo(resourceLocationAdapter2.toString());
   }

   public InternalComparatorAdapter(JsonManager jsonManager2) {
      this.jsonManager = jsonManager2;
   }

   @Override
   public int compare(Object entry, Object entry2) {
      return this.computeCount((ResourceLocationAdapter)entry, (ResourceLocationAdapter)entry2);
   }
}
