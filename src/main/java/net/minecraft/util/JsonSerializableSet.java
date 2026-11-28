package net.minecraft.util;

import com.google.common.collect.ForwardingSet;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.Collection;
import java.util.Set;

public class JsonSerializableSet extends ForwardingSet implements IJsonSerializable {
   private final Set underlyingSet = Sets.newHashSet();

   @Override
   protected Set delegate() {
      return this.underlyingSet;
   }

   @Override
   public JsonElement getSerializableElement() {
      JsonArray array = new JsonArray();

      for (String str : (Iterable<String>)(this)) {
         array.add(new JsonPrimitive(str));
      }

      return array;
   }

   @Override
   public void fromJson(JsonElement elem) {
      if (elem.isJsonArray()) {
         for (JsonElement elem2 : elem.getAsJsonArray()) {
            this.add(elem2.getAsString());
         }
      }
   }
}
