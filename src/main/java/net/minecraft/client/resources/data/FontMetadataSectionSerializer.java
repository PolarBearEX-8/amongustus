package net.minecraft.client.resources.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;
import org.apache.commons.lang3.Validate;

public class FontMetadataSectionSerializer extends BaseMetadataSectionSerializer {

   public FontMetadataSection deserializeFont(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
      JsonObject asJsonObject = elem.getAsJsonObject();
      float[] floats = new float[256];
      float[] floats2 = new float[256];
      float[] floats3 = new float[256];
      float f = 1.0F;
      float f2 = 0.0F;
      float f3 = 0.0F;
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
            f = JsonUtils.getFloat(asJsonObject3, "width", f);
            Validate.inclusiveBetween(0.0, Float.MAX_VALUE, (double)f, "Invalid default width");
            f2 = JsonUtils.getFloat(asJsonObject3, "spacing", f2);
            Validate.inclusiveBetween(0.0, Float.MAX_VALUE, (double)f2, "Invalid default spacing");
            f3 = JsonUtils.getFloat(asJsonObject3, "left", f2);
            Validate.inclusiveBetween(0.0, Float.MAX_VALUE, (double)f3, "Invalid default left");
         }

         for (int i = 0; i < 256; i++) {
            JsonElement elem2 = asJsonObject2.get(Integer.toString(i));
            float f4 = f;
            float f5 = f2;
            float f6 = f3;
            if (elem2 != null) {
               JsonObject jsonObject = JsonUtils.getJsonObject(elem2, "characters[" + i + "]");
               f4 = JsonUtils.getFloat(jsonObject, "width", f);
               Validate.inclusiveBetween(0.0, Float.MAX_VALUE, (double)f4, "Invalid width");
               f5 = JsonUtils.getFloat(jsonObject, "spacing", f2);
               Validate.inclusiveBetween(0.0, Float.MAX_VALUE, (double)f5, "Invalid spacing");
               f6 = JsonUtils.getFloat(jsonObject, "left", f3);
               Validate.inclusiveBetween(0.0, Float.MAX_VALUE, (double)f6, "Invalid left");
            }

            floats[i] = f4;
            floats2[i] = f5;
            floats3[i] = f6;
         }
      }

      return new FontMetadataSection(floats, floats3, floats2);
   }

   public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
      return this.deserializeFont(elem, type, context);
   }

   @Override
   public String getSectionName() {
      return "font";
   }
}
