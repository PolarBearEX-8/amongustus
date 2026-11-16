package client.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang3.StringUtils;

public class MissingManager {
   public static float computeFactor(JsonObject jsonObject, String text, float factor) {
      return jsonObject.has(text) ? computeFactor(jsonObject.get(text), text) : factor;
   }

   public static boolean matchesJsonObject(JsonObject jsonObject, String text) {
      return !matchesJsonObjectByJsonObject(jsonObject, text) ? false : jsonObject.getAsJsonPrimitive(text).isBoolean();
   }

   public static String resolveText(JsonObject jsonObject, String text) {
      if (jsonObject.has(text)) {
         return resolveText(jsonObject.get(text), text);
      } else {
         throw new JsonSyntaxException("Missing " + text + ", expected to find a string");
      }
   }

   public static JsonArray resolveJsonArray(JsonObject jsonObject, String text) {
      if (jsonObject.has(text)) {
         return resolveJsonArray(jsonObject.get(text), text);
      } else {
         throw new JsonSyntaxException("Missing " + text + ", expected to find a JsonArray");
      }
   }

   public static int computeCount(JsonElement jsonElement, String text) {
      if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isNumber()) {
         return jsonElement.getAsInt();
      } else {
         throw new JsonSyntaxException("Expected " + text + " to be a Int, was " + resolveText(jsonElement));
      }
   }

   public static JsonObject resolveJsonObject(JsonObject jsonObject, String text) {
      if (jsonObject.has(text)) {
         return resolveJsonObject(jsonObject.get(text), text);
      } else {
         throw new JsonSyntaxException("Missing " + text + ", expected to find a JsonObject");
      }
   }

   public static boolean matchesJsonObject(JsonObject jsonObject, String text, boolean flag) {
      return jsonObject.has(text) ? matchesJsonElement(jsonObject.get(text), text) : flag;
   }

   public static JsonObject resolveJsonObject(JsonElement jsonElement, String text) {
      if (jsonElement.isJsonObject()) {
         return jsonElement.getAsJsonObject();
      } else {
         throw new JsonSyntaxException("Expected " + text + " to be a JsonObject, was " + resolveText(jsonElement));
      }
   }

   public static boolean matchesJsonElement(JsonElement jsonElement, String text) {
      if (jsonElement.isJsonPrimitive()) {
         return jsonElement.getAsBoolean();
      } else {
         throw new JsonSyntaxException("Expected " + text + " to be a Boolean, was " + resolveText(jsonElement));
      }
   }

   public static String resolveText(JsonElement jsonElement) {
      String text = StringUtils.abbreviateMiddle(String.valueOf(jsonElement), "...", 10);
      if (jsonElement == null) {
         return "null (missing)";
      } else if (jsonElement.isJsonNull()) {
         return "null (json)";
      } else if (jsonElement.isJsonArray()) {
         return "an array (" + text + ")";
      } else if (jsonElement.isJsonObject()) {
         return "an object (" + text + ")";
      } else {
         if (jsonElement.isJsonPrimitive()) {
            JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
            if (asJsonPrimitive.isNumber()) {
               return "a number (" + text + ")";
            }

            if (asJsonPrimitive.isBoolean()) {
               return "a boolean (" + text + ")";
            }
         }

         return text;
      }
   }

   public static int computeCount(JsonObject jsonObject, String text, int count) {
      return jsonObject.has(text) ? computeCount(jsonObject.get(text), text) : count;
   }

   public static boolean matchesJsonElement(JsonElement jsonElement) {
      return !jsonElement.isJsonPrimitive() ? false : jsonElement.getAsJsonPrimitive().isString();
   }

   public static boolean matchesJsonObject2(JsonObject jsonObject, String text) {
      return !get(jsonObject, text) ? false : jsonObject.get(text).isJsonArray();
   }

   public static JsonArray resolveJsonArray(JsonElement jsonElement, String text) {
      if (jsonElement.isJsonArray()) {
         return jsonElement.getAsJsonArray();
      } else {
         throw new JsonSyntaxException("Expected " + text + " to be a JsonArray, was " + resolveText(jsonElement));
      }
   }

   public static boolean matchesJsonObject3(JsonObject jsonObject, String text) {
      if (jsonObject.has(text)) {
         return matchesJsonElement(jsonObject.get(text), text);
      } else {
         throw new JsonSyntaxException("Missing " + text + ", expected to find a Boolean");
      }
   }

   public static JsonArray resolveJsonArray(JsonObject jsonObject, String text, JsonArray jsonArray) {
      return jsonObject.has(text) ? resolveJsonArray(jsonObject.get(text), text) : jsonArray;
   }

   public static int computeCount(JsonObject jsonObject, String text) {
      if (jsonObject.has(text)) {
         return computeCount(jsonObject.get(text), text);
      } else {
         throw new JsonSyntaxException("Missing " + text + ", expected to find a Int");
      }
   }

   public static String resolveText(JsonElement jsonElement, String text) {
      if (jsonElement.isJsonPrimitive()) {
         return jsonElement.getAsString();
      } else {
         throw new JsonSyntaxException("Expected " + text + " to be a string, was " + resolveText(jsonElement));
      }
   }

   public static boolean matchesJsonObject4(JsonObject jsonObject, String text) {
      return !matchesJsonObjectByJsonObject(jsonObject, text) ? false : jsonObject.getAsJsonPrimitive(text).isString();
   }

   public static JsonObject resolveJsonObject(JsonObject jsonObject, String text, JsonObject jsonObject2) {
      return jsonObject.has(text) ? resolveJsonObject(jsonObject.get(text), text) : jsonObject2;
   }

   public static boolean get(JsonObject jsonObject, String text) {
      return jsonObject == null ? false : jsonObject.get(text) != null;
   }

   public static float computeFactor(JsonElement jsonElement, String text) {
      if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isNumber()) {
         return jsonElement.getAsFloat();
      } else {
         throw new JsonSyntaxException("Expected " + text + " to be a Float, was " + resolveText(jsonElement));
      }
   }

   public static String resolveText(JsonObject jsonObject, String text, String text2) {
      return jsonObject.has(text) ? resolveText(jsonObject.get(text), text) : text2;
   }

   public static float computeFactor(JsonObject jsonObject, String text) {
      if (jsonObject.has(text)) {
         return computeFactor(jsonObject.get(text), text);
      } else {
         throw new JsonSyntaxException("Missing " + text + ", expected to find a Float");
      }
   }

   public static boolean matchesJsonObjectByJsonObject(JsonObject jsonObject, String text) {
      return !get(jsonObject, text) ? false : jsonObject.get(text).isJsonPrimitive();
   }
}
