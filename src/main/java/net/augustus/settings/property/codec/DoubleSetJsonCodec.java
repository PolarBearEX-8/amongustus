package net.augustus.settings.property.codec;

import net.augustus.settings.property.serializer.SetPropertySerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.Set;

public final class DoubleSetJsonCodec implements SetPropertySerializer {
   public void writeDoubleElement(JsonArray array, Double d) {
      array.add(d);
   }

   @Override
   public void readElement(Set set, JsonElement elem) {
      set.add(elem.getAsDouble());
   }

   @Override
   public void writeElement(JsonArray array, Object obj) {
      this.writeDoubleElement(array, (Double)obj);
   }
}
