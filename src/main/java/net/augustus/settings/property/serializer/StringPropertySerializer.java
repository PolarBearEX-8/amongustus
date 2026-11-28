package net.augustus.settings.property.serializer;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public final class StringPropertySerializer implements PrimitivePropertySerializer {
   @Override
   public Object readPrimitive(JsonPrimitive primitive) {
      return this.readString(primitive);
   }

   public void writeString(String str, String str2, JsonObject json) {
      json.addProperty(str, str2);
   }

   @Override
   public void write(String str, Object obj, JsonObject json) {
      this.writeString(str, (String)obj, json);
   }

   public String readString(JsonPrimitive primitive) {
      return primitive.getAsString().intern();
   }
}
