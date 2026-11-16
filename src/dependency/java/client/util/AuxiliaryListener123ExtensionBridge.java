package client.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.List;

public final class AuxiliaryListener123ExtensionBridge implements AuxiliaryListener123Extension {
   @Override
   public void applyList(List list, JsonElement jsonElement) {
      list.add(jsonElement.getAsBoolean());
   }

   public void add(JsonArray jsonArray, Boolean flag) {
      jsonArray.add(flag);
   }

   @Override
   public void applyJsonArray(JsonArray jsonArray, Object entry) {
      Boolean flag = (Boolean)entry;
      this.add(jsonArray, flag);
   }
}
