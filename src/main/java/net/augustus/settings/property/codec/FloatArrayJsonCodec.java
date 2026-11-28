package net.augustus.settings.property.codec;

import net.augustus.settings.property.serializer.ArrayPropertySerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.List;

public final class FloatArrayJsonCodec implements ArrayPropertySerializer {
   @Override
   public void writeElement(JsonArray array, Object obj) {
      this.writeFloatElement(array, (Float)obj);
   }

   @Override
   public void readElement(List list, JsonElement elem) {
      list.add(elem.getAsFloat());
   }

   public void writeFloatElement(JsonArray array, Float f) {
      array.add(f);
   }
}
