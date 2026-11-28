package net.augustus.settings.property.codec;

import net.augustus.settings.property.serializer.SetPropertySerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.Set;

public final class BooleanSetJsonCodec implements SetPropertySerializer {
   @Override
   public void writeElement(JsonArray array, Object obj) {
      this.writeBooleanElement(array, (Boolean)obj);
   }

   public void writeBooleanElement(JsonArray array, Boolean bool) {
      array.add(bool);
   }

   @Override
   public void readElement(Set set, JsonElement elem) {
      set.add(elem.getAsBoolean());
   }
}
