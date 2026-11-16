package client.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.List;

public final class AuxiliaryListener123ExtensionAdapter implements Listener123Extension {
   public void add(JsonArray jsonArray, String text) {
      jsonArray.add(text);
   }

   @Override
   public void applyList(List list, JsonElement jsonElement) {
      list.add(jsonElement.getAsString());
   }

   @Override
   public void applyJsonArray(JsonArray jsonArray, Object entry) {
      String text = (String)entry;
      this.add(jsonArray, text);
   }
}
