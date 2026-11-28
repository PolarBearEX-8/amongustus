package net.raphimc.minecraftauth.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonUtil {
   public static final Gson GSON = new Gson();

   public static JsonElement parseString(String json) {
      return (JsonElement)GSON.fromJson(json, JsonElement.class);
   }

   public static String getStringOr(JsonObject obj, String key, String defaultValue) {
      JsonElement element = obj.get(key);
      return element != null && !element.isJsonNull() ? element.getAsString() : defaultValue;
   }

   public static boolean getBooleanOr(JsonObject obj, String key, boolean defaultValue) {
      JsonElement element = obj.get(key);
      return element != null && !element.isJsonNull() ? element.getAsBoolean() : defaultValue;
   }

   public static int getIntOr(JsonObject obj, String key, int defaultValue) {
      JsonElement element = obj.get(key);
      return element != null && !element.isJsonNull() ? element.getAsInt() : defaultValue;
   }

   public static long getLongOr(JsonObject obj, String key, long defaultValue) {
      JsonElement element = obj.get(key);
      return element != null && !element.isJsonNull() ? element.getAsLong() : defaultValue;
   }

   public static JsonObject getObjectNonNull(JsonObject obj, String key) {
      JsonElement element = obj.get(key);
      return element != null && !element.isJsonNull() ? element.getAsJsonObject() : new JsonObject();
   }
}
