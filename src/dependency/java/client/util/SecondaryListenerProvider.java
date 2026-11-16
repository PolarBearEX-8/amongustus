package client.util;

import client.event.PreMotionNotifier;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.Set;

public final class SecondaryListenerProvider implements PreMotionNotifier {
   public void add(JsonArray jsonArray, Boolean flag) {
      jsonArray.add(flag);
   }

   @Override
   public void applySetBySet(Set set, JsonElement jsonElement) {
      set.add(jsonElement.getAsBoolean());
   }

   @Override
   public void applyJsonArray(JsonArray jsonArray, Object entry) {
      Boolean flag = (Boolean)entry;
      this.add(jsonArray, flag);
   }
}
