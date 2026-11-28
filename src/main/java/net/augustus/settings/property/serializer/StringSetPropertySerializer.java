package net.augustus.settings.property.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.Set;

public final class StringSetPropertySerializer implements SetPropertySerializer {
   @Override
   public void writeElement(JsonArray array, Object obj) {
      this.writeStringElement(array, (String)obj);
   }

   public void writeStringElement(JsonArray array, String str) {
      array.add(str);
   }

   @Override
   public void readElement(Set set, JsonElement elem) {
      set.add(elem.getAsString());
   }
}
