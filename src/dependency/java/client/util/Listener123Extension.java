package client.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;

public interface Listener123Extension extends JsonPropertyGetter {
   @Override
   default void applyText(String key, Object value, JsonObject jsonObject) {
      List list = (List)value;
      this.applyText(key, list, jsonObject);
   }

   default List resolveList(String key, List list, JsonObject jsonObject) {
      AutoReportCache.getStringArray();
      JsonElement jsonElement = jsonObject.get(key);
      if (jsonElement != null && jsonElement.isJsonArray()) {
         list.clear();
         jsonElement.getAsJsonArray().asList().forEach(element -> this.applyListByList(list, element));
         return list;
      } else {
         return list;
      }
   }

   @Override
   default Object getEntry(String key, Object value, JsonObject jsonObject) {
      List list = (List)value;
      return this.resolveList(key, list, jsonObject);
   }

   default void applyJsonArrayByJsonArray(JsonArray jsonArray, Object entry) {
      this.applyJsonArray(jsonArray, entry);
   }

   void applyList(List list, JsonElement element);

   void applyJsonArray(JsonArray jsonArray, Object entry);

   default void applyListByList(List list, JsonElement element) {
      this.applyList(list, element);
   }

   default void applyText(String key, List list, JsonObject jsonObject) {
      JsonArray jsonArray = new JsonArray();
      list.forEach(entry -> this.applyJsonArrayByJsonArray(jsonArray, entry));
      jsonObject.add(key, jsonArray);
   }
}
