package client.font;

import client.util.JsonDeserializerExtensionAdapter;
import client.util.ListenerAdapter11;
import client.util.MissingManager;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import org.apache.commons.lang3.Validate;

public class FontJsonDeserializerExtensionAdapter extends JsonDeserializerExtensionAdapter {
   @Override
   public String getText() {
      return "font";
   }

   public ListenerAdapter11 resolveListenerAdapter11(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      float[] factorArray = new float[256];
      float[] factorArray2 = new float[256];
      float[] factorArray3 = new float[256];
      float factor = 1.0F;
      float factor2 = 0.0F;
      float factor3 = 0.0F;
      if (asJsonObject.has("characters")) {
         if (!asJsonObject.get("characters").isJsonObject()) {
            throw new JsonParseException("Invalid font->characters: expected object, was " + asJsonObject.get("characters"));
         }

         JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("characters");
         if (asJsonObject2.has("default")) {
            if (!asJsonObject2.get("default").isJsonObject()) {
               throw new JsonParseException("Invalid font->characters->default: expected object, was " + asJsonObject2.get("default"));
            }

            JsonObject asJsonObject3 = asJsonObject2.getAsJsonObject("default");
            factor = MissingManager.computeFactor(asJsonObject3, "width", factor);
            Validate.inclusiveBetween(0.0, Float.MAX_VALUE, (double)factor, "Invalid default width");
            factor2 = MissingManager.computeFactor(asJsonObject3, "spacing", factor2);
            Validate.inclusiveBetween(0.0, Float.MAX_VALUE, (double)factor2, "Invalid default spacing");
            factor3 = MissingManager.computeFactor(asJsonObject3, "left", factor2);
            Validate.inclusiveBetween(0.0, Float.MAX_VALUE, (double)factor3, "Invalid default left");
         }

         for (int index = 0; index < 256; index++) {
            JsonElement jsonElement2 = asJsonObject2.get(Integer.toString(index));
            float factor4 = factor;
            float factor5 = factor2;
            float factor6 = factor3;
            if (jsonElement2 != null) {
               JsonObject jsonObject = MissingManager.resolveJsonObject(jsonElement2, "characters[" + index + "]");
               factor4 = MissingManager.computeFactor(jsonObject, "width", factor);
               Validate.inclusiveBetween(0.0, Float.MAX_VALUE, (double)factor4, "Invalid width");
               factor5 = MissingManager.computeFactor(jsonObject, "spacing", factor2);
               Validate.inclusiveBetween(0.0, Float.MAX_VALUE, (double)factor5, "Invalid spacing");
               factor6 = MissingManager.computeFactor(jsonObject, "left", factor3);
               Validate.inclusiveBetween(0.0, Float.MAX_VALUE, (double)factor6, "Invalid left");
            }

            factorArray[index] = factor4;
            factorArray2[index] = factor5;
            factorArray3[index] = factor6;
         }
      }

      return new ListenerAdapter11(factorArray, factorArray3, factorArray2);
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveListenerAdapter11(jsonElement, type, jsonDeserializationContext);
   }
}
