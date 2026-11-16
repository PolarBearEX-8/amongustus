package client.util;

import client.event.PreMotionNotifier;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.Set;

public final class ListenerAdapter7 implements PreMotionNotifier {
   @Override
   public void applySetBySet(Set set, JsonElement jsonElement) {
      set.add(jsonElement.getAsDouble());
   }

   @Override
   public void applyJsonArray(JsonArray jsonArray, Object entry) {
      Double offset = (Double)entry;
      this.add(jsonArray, offset);
   }

   public void add(JsonArray jsonArray, Double offset) {
      jsonArray.add(offset);
   }
}
