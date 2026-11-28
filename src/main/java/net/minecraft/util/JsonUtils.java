package net.minecraft.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;

public class JsonUtils {

   public static boolean isString(JsonObject json, String str) {
      return !isJsonPrimitive(json, str) ? false : json.getAsJsonPrimitive(str).isString();
   }

   public static boolean getBoolean(JsonObject json, String str) {
      if (json.has(str)) {
         return getBoolean(json.get(str), str);
      } else {
         throw new JsonSyntaxException("Missing " + str + ", expected to find a Boolean");
      }
   }

   public static boolean getBoolean(JsonObject json, String str, boolean flag) {
      return json.has(str) ? getBoolean(json.get(str), str) : flag;
   }

   public static String toString(JsonElement elem) {
      String str = org.apache.commons.lang3.StringUtils.abbreviateMiddle(String.valueOf(elem), "...", 10);
      if (elem == null) {
         return "null (missing)";
      } else if (elem.isJsonNull()) {
         return "null (json)";
      } else if (elem.isJsonArray()) {
         return "an array (" + str + ")";
      } else if (elem.isJsonObject()) {
         return "an object (" + str + ")";
      } else {
         if (elem.isJsonPrimitive()) {
            JsonPrimitive asJsonPrimitive = elem.getAsJsonPrimitive();
            if (asJsonPrimitive.isNumber()) {
               return "a number (" + str + ")";
            }

            if (asJsonPrimitive.isBoolean()) {
               return "a boolean (" + str + ")";
            }
         }

         return str;
      }
   }

   public static float getFloat(JsonElement elem, String str) {
      if (elem.isJsonPrimitive() && elem.getAsJsonPrimitive().isNumber()) {
         return elem.getAsFloat();
      } else {
         throw new JsonSyntaxException("Expected " + str + " to be a Float, was " + toString(elem));
      }
   }

   public static boolean isString(JsonElement elem) {
      return !elem.isJsonPrimitive() ? false : elem.getAsJsonPrimitive().isString();
   }

   public static String getString(JsonObject json, String str, String str2) {
      return json.has(str) ? getString(json.get(str), str) : str2;
   }

   public static int getInt(JsonObject json, String str) {
      if (json.has(str)) {
         return getInt(json.get(str), str);
      } else {
         throw new JsonSyntaxException("Missing " + str + ", expected to find a Int");
      }
   }

   public static String getString(JsonElement elem, String str) {
      if (elem.isJsonPrimitive()) {
         return elem.getAsString();
      } else {
         throw new JsonSyntaxException("Expected " + str + " to be a string, was " + toString(elem));
      }
   }

   public static JsonArray getJsonArray(JsonObject json, String str) {
      if (json.has(str)) {
         return getJsonArray(json.get(str), str);
      } else {
         throw new JsonSyntaxException("Missing " + str + ", expected to find a JsonArray");
      }
   }

   public static boolean isJsonPrimitive(JsonObject json, String str) {
      return !hasField(json, str) ? false : json.get(str).isJsonPrimitive();
   }

   public static String getString(JsonObject json, String str) {
      if (json.has(str)) {
         return getString(json.get(str), str);
      } else {
         throw new JsonSyntaxException("Missing " + str + ", expected to find a string");
      }
   }

   public static boolean hasField(JsonObject json, String str) {
      return json == null ? false : json.get(str) != null;
   }

   public static JsonObject getJsonObject(JsonElement elem, String str) {
      if (elem.isJsonObject()) {
         return elem.getAsJsonObject();
      } else {
         throw new JsonSyntaxException("Expected " + str + " to be a JsonObject, was " + toString(elem));
      }
   }

   public static boolean isBoolean(JsonObject json, String str) {
      return !isJsonPrimitive(json, str) ? false : json.getAsJsonPrimitive(str).isBoolean();
   }

   public static JsonObject getJsonObject(JsonObject json, String str) {
      if (json.has(str)) {
         return getJsonObject(json.get(str), str);
      } else {
         throw new JsonSyntaxException("Missing " + str + ", expected to find a JsonObject");
      }
   }

   public static int getInt(JsonElement elem, String str) {
      if (elem.isJsonPrimitive() && elem.getAsJsonPrimitive().isNumber()) {
         return elem.getAsInt();
      } else {
         throw new JsonSyntaxException("Expected " + str + " to be a Int, was " + toString(elem));
      }
   }

   public static boolean isJsonArray(JsonObject json, String str) {
      return !hasField(json, str) ? false : json.get(str).isJsonArray();
   }

   public static float getFloat(JsonObject json, String str) {
      if (json.has(str)) {
         return getFloat(json.get(str), str);
      } else {
         throw new JsonSyntaxException("Missing " + str + ", expected to find a Float");
      }
   }

   public static float getFloat(JsonObject json, String str, float f) {
      return json.has(str) ? getFloat(json.get(str), str) : f;
   }

   public static int getInt(JsonObject json, String str, int n) {
      return json.has(str) ? getInt(json.get(str), str) : n;
   }

   public static JsonArray getJsonArray(JsonObject json, String str, JsonArray array) {
      return json.has(str) ? getJsonArray(json.get(str), str) : array;
   }

   public static boolean getBoolean(JsonElement elem, String str) {
      if (elem.isJsonPrimitive()) {
         return elem.getAsBoolean();
      } else {
         throw new JsonSyntaxException("Expected " + str + " to be a Boolean, was " + toString(elem));
      }
   }

   public static JsonArray getJsonArray(JsonElement elem, String str) {
      if (elem.isJsonArray()) {
         return elem.getAsJsonArray();
      } else {
         throw new JsonSyntaxException("Expected " + str + " to be a JsonArray, was " + toString(elem));
      }
   }

   public static JsonObject getJsonObject(JsonObject json, String str, JsonObject json2) {
      return json.has(str) ? getJsonObject(json.get(str), str) : json2;
   }
}
