package client.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.List;

public final class DistanceSerializer implements Listener123Extension {
   public void add(JsonArray jsonArray, Boolean flag) {
      jsonArray.add(flag);
   }

   @Override
   public void applyList(List list, JsonElement jsonElement) {
      list.add(jsonElement.getAsBoolean());
   }

   @Override
   public void applyJsonArray(JsonArray jsonArray, Object entry) {
      Boolean flag = (Boolean)entry;
      this.add(jsonArray, flag);
   }
}
