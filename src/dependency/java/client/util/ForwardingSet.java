package client.util;

import client.event.PreRenderInterceptor;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.Collection;
import java.util.Set;

public class ForwardingSet extends com.google.common.collect.ForwardingSet<String> implements PreRenderInterceptor {
   public final Set<String> set = Sets.newHashSet();

   @Override
   public void applyJsonElement(JsonElement jsonElement) {
      if (jsonElement.isJsonArray()) {
         for (JsonElement jsonElement2 : jsonElement.getAsJsonArray()) {
            this.add(jsonElement2.getAsString());
         }
      }
   }

   @Override
   public JsonElement getJsonElement() {
      JsonArray jsonArray = new JsonArray();

      for (String text : (Iterable<String>)(Iterable<?>)this) {
         jsonArray.add(new JsonPrimitive(text));
      }

      return jsonArray;
   }

   @Override
   protected Set<String> delegate() {
      return this.set;
   }
}
