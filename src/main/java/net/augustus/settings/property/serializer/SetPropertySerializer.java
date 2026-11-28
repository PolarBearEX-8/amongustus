package net.augustus.settings.property.serializer;

import net.augustus.settings.property.codec.JsonPropertyCodec;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.Set;
import net.augustus.modules.movement.Speed;

public interface SetPropertySerializer extends JsonPropertyCodec {

   default void collectElement(Set set, JsonElement elem) {
      this.readElement(set, elem);
   }

   void writeElement(JsonArray array, Object obj);

   default void appendElement(JsonArray array, Object obj) {
      this.writeElement(array, obj);
   }

   void readElement(Set set, JsonElement elem);

   default void writeSet(String str, Set set, JsonObject json) {
      JsonArray array = new JsonArray();
      set.forEach(obj -> this.appendElement(array, obj));
      json.add(str, array);
   }

   @Override
   default Object read(String str, Object obj, JsonObject json) {
      return this.readSet(str, (Set)obj, json);
   }

   @Override
   default void write(String str, Object obj, JsonObject json) {
      this.writeSet(str, (Set)obj, json);
   }

   default Set readSet(String str, Set set, JsonObject json) {
      JsonElement elem = json.get(str);
      if (elem != null && elem.isJsonArray()) {
         set.clear();
         List list = elem.getAsJsonArray().asList();
         list.forEach(obj -> this.collectElement(set, (JsonElement)obj));
         return set;
      } else {
         return set;
      }
   }
}
