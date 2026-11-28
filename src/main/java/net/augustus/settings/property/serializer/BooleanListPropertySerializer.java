package net.augustus.settings.property.serializer;

import net.augustus.settings.property.codec.JsonListPropertyCodec;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.List;

public final class BooleanListPropertySerializer implements JsonListPropertyCodec {
   @Override
   public void readElement(List list, JsonElement elem) {
      list.add(elem.getAsBoolean());
   }

   @Override
   public void writeElement(JsonArray array, Object obj) {
      this.writeBooleanElement(array, (Boolean)obj);
   }

   public void writeBooleanElement(JsonArray array, Boolean bool) {
      array.add(bool);
   }
}
