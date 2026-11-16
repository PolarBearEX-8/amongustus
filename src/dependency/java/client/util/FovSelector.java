package client.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.List;

public final class FovSelector implements Listener123Extension {
   @Override
   public void applyJsonArray(JsonArray jsonArray, Object entry) {
      Integer count = (Integer)entry;
      this.add(jsonArray, count);
   }

   @Override
   public void applyList(List list, JsonElement jsonElement) {
      list.add(jsonElement.getAsInt());
   }

   public void add(JsonArray jsonArray, Integer count) {
      jsonArray.add(count);
   }
}
