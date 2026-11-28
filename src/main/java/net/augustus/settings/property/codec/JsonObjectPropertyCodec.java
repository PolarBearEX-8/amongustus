package net.augustus.settings.property.codec;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public final class JsonObjectPropertyCodec implements JsonPropertyCodec {
   public void writeJsonObject(String str, JsonObject json, JsonObject json2) {
      if (json != null) {
         json2.add(str, json);
      }
   }

   @Override
   public Object read(String str, Object obj, JsonObject json) {
      return this.readJsonObject(str, (JsonObject)obj, json);
   }

   @Override
   public void write(String str, Object obj, JsonObject json) {
      this.writeJsonObject(str, (JsonObject)obj, json);
   }

   public JsonObject readJsonObject(String str, JsonObject json, JsonObject json2) {
      JsonElement elem = json2.get(str);
      return elem != null && elem.isJsonObject() ? elem.getAsJsonObject() : json;
   }
}
