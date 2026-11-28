package net.augustus.settings.property.codec;

import net.augustus.settings.property.serializer.PrimitivePropertySerializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public final class DoubleJsonPropertyCodec implements PrimitivePropertySerializer {
   @Override
   public Object readPrimitive(JsonPrimitive primitive) {
      return this.readDouble(primitive);
   }

   @Override
   public void write(String str, Object obj, JsonObject json) {
      this.writeDouble(str, (Double)obj, json);
   }

   public void writeDouble(String str, Double d, JsonObject json) {
      json.addProperty(str, d);
   }

   public Double readDouble(JsonPrimitive primitive) {
      return primitive.getAsDouble();
   }
}
