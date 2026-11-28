package net.augustus.settings.property.codec;

import net.augustus.settings.property.serializer.PrimitivePropertySerializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public final class IntegerJsonPropertyCodec implements PrimitivePropertySerializer {
   public Integer readInteger(JsonPrimitive primitive) {
      return primitive.getAsInt();
   }

   @Override
   public void write(String str, Object obj, JsonObject json) {
      this.writeInteger(str, (Integer)obj, json);
   }

   public void writeInteger(String str, Integer num, JsonObject json) {
      json.addProperty(str, num);
   }

   @Override
   public Object readPrimitive(JsonPrimitive primitive) {
      return this.readInteger(primitive);
   }
}
