package net.augustus.settings.property.serializer;

import net.augustus.settings.property.codec.JsonPropertyCodec;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.augustus.modules.movement.Speed;

public interface ArrayPropertySerializer extends JsonPropertyCodec {

   default void writeArray(String str, Object[] array, JsonObject json) {
      JsonArray array2 = new JsonArray();
      Arrays.stream(array).forEach(obj -> this.appendElement(array2, obj));
      json.add(str, array2);
   }

   @Override
   default Object read(String str, Object obj, JsonObject json) {
      return this.readArray(str, (Object[])obj, json);
   }

   void writeElement(JsonArray array, Object obj);

   default Object[] readArray(String str, Object[] array, JsonObject json) {
      JsonElement elem = json.get(str);
      if (elem != null && elem.isJsonArray()) {
         List list = elem.getAsJsonArray().asList();
         ArrayList list2 = Lists.newArrayList();
         list.forEach(obj -> this.collectElement(list2, (JsonElement)obj));
         return list.toArray();
      } else {
         return array;
      }
   }

   default void collectElement(List list, JsonElement elem) {
      this.readElement(list, elem);
   }

   @Override
   default void write(String str, Object obj, JsonObject json) {
      this.writeArray(str, (Object[])obj, json);
   }

   void readElement(List list, JsonElement elem);

   default void appendElement(JsonArray array, Object obj) {
      this.writeElement(array, obj);
   }
}
