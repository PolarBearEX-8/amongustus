package net.augustus.settings.property.serializer;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public final class LongPropertySerializer implements PrimitivePropertySerializer {
   public Long readLong(JsonPrimitive primitive) {
      return primitive.getAsLong();
   }

   @Override
   public void write(String str, Object obj, JsonObject json) {
      this.writeLong(str, (Long)obj, json);
   }

   @Override
   public Object readPrimitive(JsonPrimitive primitive) {
      return this.readLong(primitive);
   }

   public void writeLong(String str, Long val, JsonObject json) {
      json.addProperty(str, val);
   }
}
