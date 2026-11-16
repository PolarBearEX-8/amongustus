package client.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public final class InterpolationFormatter implements JsonPropertyGetter {
   @Override
   public void applyText(String text, Object entry, JsonObject jsonObject) {
      JsonArray jsonArray = (JsonArray)entry;
      this.add(text, jsonArray, jsonObject);
   }

   @Override
   public Object getEntry(String text, Object entry, JsonObject jsonObject) {
      JsonArray jsonArray = (JsonArray)entry;
      return this.resolveJsonArray(text, jsonArray, jsonObject);
   }

   public void add(String text, JsonArray jsonArray, JsonObject jsonObject) {
      if (jsonArray != null) {
         jsonObject.add(text, jsonArray);
      }
   }

   public JsonArray resolveJsonArray(String text, JsonArray jsonArray, JsonObject jsonObject) {
      JsonElement jsonElement = jsonObject.get(text);
      return jsonElement != null && jsonElement.isJsonArray() ? jsonElement.getAsJsonArray() : jsonArray;
   }
}
