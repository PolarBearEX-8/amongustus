package client.util;

import client.event.PreMotionNotifier;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.Set;

public final class AuxiliaryListenerCoordinator implements PreMotionNotifier {
   public void add(JsonArray jsonArray, String text) {
      jsonArray.add(text);
   }

   @Override
   public void applySetBySet(Set set, JsonElement jsonElement) {
      set.add(jsonElement.getAsString());
   }

   @Override
   public void applyJsonArray(JsonArray jsonArray, Object entry) {
      String text = (String)entry;
      this.add(jsonArray, text);
   }
}
