package client.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class SecondaryJsonDeserializerAdapter implements JsonDeserializer {
   public int computeCount(JsonObject jsonObject) {
      int count = MissingManager.computeCount(jsonObject, "rotation", 0);
      if (count >= 0 && count % 90 == 0 && count / 90 <= 3) {
         return count;
      } else {
         throw new JsonParseException("Invalid rotation " + count + " found, only 0/90/180/270 allowed");
      }
   }

   private float[] resolveFloatArray(JsonObject jsonObject) {
      if (!jsonObject.has("uv")) {
         return null;
      } else {
         JsonArray jsonArray = MissingManager.resolveJsonArray(jsonObject, "uv");
         if (jsonArray.size() != 4) {
            throw new JsonParseException("Expected 4 uv values, found: " + jsonArray.size());
         } else {
            float[] factorArray = new float[4];

            for (int index = 0; index < factorArray.length; index++) {
               factorArray[index] = MissingManager.computeFactor(jsonArray.get(index), "uv[" + index + "]");
            }

            return factorArray;
         }
      }
   }

   public TertiaryFloatArrayEntry resolveTertiaryFloatArrayEntry(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      float[] factorArray = this.resolveFloatArray(asJsonObject);
      int count = this.computeCount(asJsonObject);
      return new TertiaryFloatArrayEntry(factorArray, count);
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveTertiaryFloatArrayEntry(jsonElement, type, jsonDeserializationContext);
   }
}
