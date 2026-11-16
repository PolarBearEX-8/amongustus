package client.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.List;

public final class DirectClientManager implements AuxiliaryListener123Extension {
   @Override
   public void applyList(List list, JsonElement jsonElement) {
      list.add(jsonElement.getAsFloat());
   }

   @Override
   public void applyJsonArray(JsonArray jsonArray, Object entry) {
      Float factor = (Float)entry;
      this.add(jsonArray, factor);
   }

   public void add(JsonArray jsonArray, Float factor) {
      jsonArray.add(factor);
   }
}
