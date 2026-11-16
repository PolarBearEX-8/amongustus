package client.event;

import client.util.AutoReportCache;
import client.util.JsonPropertyGetter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Set;

public interface PreMotionNotifier extends JsonPropertyGetter {
   @Override
   default void applyText(String key, Object value, JsonObject jsonObject) {
      Set set = (Set)value;
      this.applyText(key, set, jsonObject);
   }

   @Override
   default Object getEntry(String key, Object value, JsonObject jsonObject) {
      Set set = (Set)value;
      return this.resolveSet(key, set, jsonObject);
   }

   default void applyJsonArrayByJsonArray(JsonArray jsonArray, Object value) {
      this.applyJsonArray(jsonArray, value);
   }

   void applySetBySet(Set set, JsonElement element);

   default void applyText(String key, Set set, JsonObject jsonObject) {
      JsonArray jsonArray = new JsonArray();
      set.forEach(item -> this.applyJsonArrayByJsonArray(jsonArray, item));
      jsonObject.add(key, jsonArray);
   }

   default Set resolveSet(String key, Set set, JsonObject jsonObject) {
      AutoReportCache.getStringArray();
      JsonElement element = jsonObject.get(key);
      if (element != null && element.isJsonArray()) {
         set.clear();
         element.getAsJsonArray().asList().forEach(item -> this.applySet(set, (JsonElement)item));
         return set;
      } else {
         return set;
      }
   }

   void applyJsonArray(JsonArray jsonArray, Object value);

   default void applySet(Set set, JsonElement element) {
      this.applySetBySet(set, element);
   }
}
