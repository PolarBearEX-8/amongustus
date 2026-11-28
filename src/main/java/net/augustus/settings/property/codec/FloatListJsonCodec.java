package net.augustus.settings.property.codec;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.List;

public final class FloatListJsonCodec implements JsonListPropertyCodec {
   @Override
   public void writeElement(JsonArray array, Object obj) {
      this.writeFloatElement(array, (Float)obj);
   }

   public void writeFloatElement(JsonArray array, Float f) {
      array.add(f);
   }

   @Override
   public void readElement(List list, JsonElement elem) {
      list.add(elem.getAsFloat());
   }
}
