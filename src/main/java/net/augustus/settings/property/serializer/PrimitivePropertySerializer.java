package net.augustus.settings.property.serializer;

import net.augustus.settings.property.codec.JsonPropertyCodec;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.augustus.modules.movement.Speed;

public interface PrimitivePropertySerializer extends JsonPropertyCodec {
   default Object read(String str, Object obj, JsonObject json) {
      JsonElement elem = json.get(str);
      if (elem != null && elem.isJsonPrimitive()) {
         JsonPrimitive asJsonPrimitive = elem.getAsJsonPrimitive();

         try {
            return this.readPrimitive(asJsonPrimitive);
         } catch (Exception e) {
            e.printStackTrace();
            return obj;
         }
      } else {
         return obj;
      }
   }

   Object readPrimitive(JsonPrimitive primitive);
}
