package net.augustus.settings.property.serializer;

import net.augustus.settings.property.codec.JsonPropertyCodec;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public final class JsonArrayPropertySerializer implements JsonPropertyCodec {
   @Override
   public void write(String str, Object obj, JsonObject json) {
      this.writeJsonArray(str, (JsonArray)obj, json);
   }

   public JsonArray readJsonArray(String str, JsonArray array, JsonObject json) {
      JsonElement elem = json.get(str);
      return elem != null && elem.isJsonArray() ? elem.getAsJsonArray() : array;
   }

   @Override
   public Object read(String str, Object obj, JsonObject json) {
      return this.readJsonArray(str, (JsonArray)obj, json);
   }

   public void writeJsonArray(String str, JsonArray array, JsonObject json) {
      if (array != null) {
         json.add(str, array);
      }
   }
}
