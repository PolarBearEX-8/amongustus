package client.util;

import client.event.PreMotionNotifier;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.Set;

public final class InternalListenerResolver implements PreMotionNotifier {
   public void add(JsonArray jsonArray, Integer count) {
      jsonArray.add(count);
   }

   @Override
   public void applySetBySet(Set set, JsonElement jsonElement) {
      set.add(jsonElement.getAsInt());
   }

   @Override
   public void applyJsonArray(JsonArray jsonArray, Object entry) {
      Integer count = (Integer)entry;
      this.add(jsonArray, count);
   }
}
