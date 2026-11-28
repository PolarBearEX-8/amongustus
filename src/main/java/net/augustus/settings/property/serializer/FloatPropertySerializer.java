package net.augustus.settings.property.serializer;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public final class FloatPropertySerializer implements PrimitivePropertySerializer {
   @Override
   public void write(String str, Object obj, JsonObject json) {
      this.writeFloat(str, (Float)obj, json);
   }

   public Float readFloat(JsonPrimitive primitive) {
      return primitive.getAsFloat();
   }

   public void writeFloat(String str, Float f, JsonObject json) {
      json.addProperty(str, f);
   }

   @Override
   public Object readPrimitive(JsonPrimitive primitive) {
      return this.readFloat(primitive);
   }
}
