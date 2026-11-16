package client.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public final class VectorMonitor implements JsonPropertyGetter {
   @Override
   public void applyText(String text, Object entry, JsonObject jsonObject) {
      JsonObject jsonObject2 = (JsonObject)entry;
      this.add(text, jsonObject2, jsonObject);
   }

   public void add(String text, JsonObject jsonObject, JsonObject jsonObject2) {
      if (jsonObject != null) {
         jsonObject2.add(text, jsonObject);
      }
   }

   @Override
   public Object getEntry(String text, Object entry, JsonObject jsonObject) {
      JsonObject jsonObject2 = (JsonObject)entry;
      return this.resolveJsonObject(text, jsonObject2, jsonObject);
   }

   public JsonObject resolveJsonObject(String text, JsonObject jsonObject, JsonObject jsonObject2) {
      JsonElement jsonElement = jsonObject2.get(text);
      return jsonElement != null && jsonElement.isJsonObject() ? jsonElement.getAsJsonObject() : jsonObject;
   }
}
