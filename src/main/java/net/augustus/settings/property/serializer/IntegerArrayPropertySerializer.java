package net.augustus.settings.property.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.List;

public final class IntegerArrayPropertySerializer implements ArrayPropertySerializer {
   @Override
   public void writeElement(JsonArray array, Object obj) {
      this.writeIntegerElement(array, (Integer)obj);
   }

   @Override
   public void readElement(List list, JsonElement elem) {
      list.add(elem.getAsInt());
   }

   public void writeIntegerElement(JsonArray array, Integer num) {
      array.add(num);
   }
}
