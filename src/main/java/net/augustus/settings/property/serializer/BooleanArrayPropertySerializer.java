package net.augustus.settings.property.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.List;

public final class BooleanArrayPropertySerializer implements ArrayPropertySerializer {
   @Override
   public void writeElement(JsonArray array, Object obj) {
      this.writeBooleanElement(array, (Boolean)obj);
   }

   public void writeBooleanElement(JsonArray array, Boolean bool) {
      array.add(bool);
   }

   @Override
   public void readElement(List list, JsonElement elem) {
      list.add(elem.getAsBoolean());
   }
}
