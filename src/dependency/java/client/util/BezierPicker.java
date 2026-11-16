package client.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.List;

public final class BezierPicker implements AuxiliaryListener123Extension {
   public void add(JsonArray jsonArray, Double offset) {
      jsonArray.add(offset);
   }

   @Override
   public void applyList(List list, JsonElement jsonElement) {
      list.add(jsonElement.getAsDouble());
   }

   @Override
   public void applyJsonArray(JsonArray jsonArray, Object entry) {
      Double offset = (Double)entry;
      this.add(jsonArray, offset);
   }
}
