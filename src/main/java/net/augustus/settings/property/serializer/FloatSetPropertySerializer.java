package net.augustus.settings.property.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.Set;

public final class FloatSetPropertySerializer implements SetPropertySerializer {
   public void writeFloatElement(JsonArray array, Float f) {
      array.add(f);
   }

   @Override
   public void writeElement(JsonArray array, Object obj) {
      this.writeFloatElement(array, (Float)obj);
   }

   @Override
   public void readElement(Set set, JsonElement elem) {
      set.add(elem.getAsFloat());
   }
}
