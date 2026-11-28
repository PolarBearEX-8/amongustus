package net.optifine.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Json {

   public static int[] parseIntArray(JsonElement elem, int n, int[] ints) {
      if (elem == null) {
         return ints;
      } else {
         JsonArray asJsonArray = elem.getAsJsonArray();
         if (asJsonArray.size() != n) {
            throw new JsonParseException("Wrong array length: " + asJsonArray.size() + ", should be: " + n + ", array: " + asJsonArray);
         } else {
            int[] ints2 = new int[asJsonArray.size()];

            for (int i = 0; i < ints2.length; i++) {
               ints2[i] = asJsonArray.get(i).getAsInt();
            }

            return ints2;
         }
      }
   }

   public static int[] parseIntArray(JsonElement elem, int n) {
      return parseIntArray(elem, n, (int[])null);
   }

   public static float getFloat(JsonObject json, String str, float f) {
      JsonElement elem = json.get(str);
      return elem == null ? f : elem.getAsFloat();
   }

   public static float[] parseFloatArray(JsonElement elem, int n, float[] floats) {
      if (elem == null) {
         return floats;
      } else {
         JsonArray asJsonArray = elem.getAsJsonArray();
         if (asJsonArray.size() != n) {
            throw new JsonParseException("Wrong array length: " + asJsonArray.size() + ", should be: " + n + ", array: " + asJsonArray);
         } else {
            float[] floats2 = new float[asJsonArray.size()];

            for (int i = 0; i < floats2.length; i++) {
               floats2[i] = asJsonArray.get(i).getAsFloat();
            }

            return floats2;
         }
      }
   }

   public static boolean getBoolean(JsonObject json, String str, boolean flag) {
      JsonElement elem = json.get(str);
      return elem == null ? flag : elem.getAsBoolean();
   }

   public static String getString(JsonObject json, String str) {
      return getString(json, str, (String)null);
   }

   public static String getString(JsonObject json, String str, String str2) {
      JsonElement elem = json.get(str);
      return elem == null ? str2 : elem.getAsString();
   }

   public static float[] parseFloatArray(JsonElement elem, int n) {
      return parseFloatArray(elem, n, (float[])null);
   }
}
