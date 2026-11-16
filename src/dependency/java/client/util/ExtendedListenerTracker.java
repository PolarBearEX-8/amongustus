package client.util;

import client.event.PreMotionNotifier;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.Set;

public final class ExtendedListenerTracker implements PreMotionNotifier {
   @Override
   public void applySetBySet(Set set, JsonElement jsonElement) {
      set.add(jsonElement.getAsFloat());
   }

   public void add(JsonArray jsonArray, Float factor) {
      jsonArray.add(factor);
   }

   @Override
   public void applyJsonArray(JsonArray jsonArray, Object entry) {
      Float factor = (Float)entry;
      this.add(jsonArray, factor);
   }
}
