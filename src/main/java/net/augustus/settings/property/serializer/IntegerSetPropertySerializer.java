package net.augustus.settings.property.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.Set;

public final class IntegerSetPropertySerializer implements SetPropertySerializer {
   @Override
   public void readElement(Set set, JsonElement elem) {
      set.add(elem.getAsInt());
   }

   public void writeIntegerElement(JsonArray array, Integer num) {
      array.add(num);
   }

   @Override
   public void writeElement(JsonArray array, Object obj) {
      this.writeIntegerElement(array, (Integer)obj);
   }
}
