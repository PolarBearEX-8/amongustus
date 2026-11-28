package net.augustus.settings.property.serializer;

import net.augustus.settings.property.codec.JsonListPropertyCodec;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.List;

public final class StringListPropertySerializer implements JsonListPropertyCodec {
   @Override
   public void readElement(List list, JsonElement elem) {
      list.add(elem.getAsString());
   }

   public void writeStringElement(JsonArray array, String str) {
      array.add(str);
   }

   @Override
   public void writeElement(JsonArray array, Object obj) {
      this.writeStringElement(array, (String)obj);
   }
}
