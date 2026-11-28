package net.augustus.settings.property.codec;

import net.augustus.settings.property.serializer.PrimitivePropertySerializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public final class BooleanJsonPropertyCodec implements PrimitivePropertySerializer {
   public void writeBoolean(String str, Boolean bool, JsonObject json) {
      json.addProperty(str, bool);
   }

   @Override
   public void write(String str, Object obj, JsonObject json) {
      this.writeBoolean(str, (Boolean)obj, json);
   }

   public Boolean readBoolean(JsonPrimitive primitive) {
      return primitive.getAsBoolean();
   }

   @Override
   public Object readPrimitive(JsonPrimitive primitive) {
      return this.readBoolean(primitive);
   }
}
