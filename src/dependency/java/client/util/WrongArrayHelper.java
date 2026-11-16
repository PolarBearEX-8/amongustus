package client.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class WrongArrayHelper {
   public static int[] resolveIndexArray(JsonElement jsonElement, int count) {
      return resolveIndexArray(jsonElement, count, (int[])null);
   }

   public static float[] resolveFloatArray(JsonElement jsonElement, int count, float[] factorArray) {
      if (jsonElement == null) {
         return factorArray;
      } else {
         JsonArray asJsonArray = jsonElement.getAsJsonArray();
         if (asJsonArray.size() != count) {
            throw new JsonParseException("Wrong array length: " + asJsonArray.size() + ", should be: " + count + ", array: " + asJsonArray);
         } else {
            float[] factorArray2 = new float[asJsonArray.size()];

            for (int index = 0; index < factorArray2.length; index++) {
               factorArray2[index] = asJsonArray.get(index).getAsFloat();
            }

            return factorArray2;
         }
      }
   }

   public static String resolveText(JsonObject jsonObject, String text, String text2) {
      JsonElement jsonElement = jsonObject.get(text);
      return jsonElement == null ? text2 : jsonElement.getAsString();
   }

   public static float[] resolveFloatArray(JsonElement jsonElement, int count) {
      return resolveFloatArray(jsonElement, count, (float[])null);
   }

   public static String resolveText(JsonObject jsonObject, String text) {
      return resolveText(jsonObject, text, (String)null);
   }

   public static boolean matchesJsonObject(JsonObject jsonObject, String text, boolean flag) {
      JsonElement jsonElement = jsonObject.get(text);
      return jsonElement == null ? flag : jsonElement.getAsBoolean();
   }

   public static int[] resolveIndexArray(JsonElement jsonElement, int count, int[] countArray) {
      if (jsonElement == null) {
         return countArray;
      } else {
         JsonArray asJsonArray = jsonElement.getAsJsonArray();
         if (asJsonArray.size() != count) {
            throw new JsonParseException("Wrong array length: " + asJsonArray.size() + ", should be: " + count + ", array: " + asJsonArray);
         } else {
            int[] countArray2 = new int[asJsonArray.size()];

            for (int index = 0; index < countArray2.length; index++) {
               countArray2[index] = asJsonArray.get(index).getAsInt();
            }

            return countArray2;
         }
      }
   }

   public static float computeFactor(JsonObject jsonObject, String text, float factor) {
      JsonElement jsonElement = jsonObject.get(text);
      return jsonElement == null ? factor : jsonElement.getAsFloat();
   }
}
