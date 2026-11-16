package client.util;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

public class TertiaryJsonDeserializerAdapter implements JsonDeserializer {
   public List resolveList(JsonDeserializationContext jsonDeserializationContext, JsonObject jsonObject) {
      JsonObject jsonObject2 = MissingManager.resolveJsonObject(jsonObject, "variants");
      ArrayList list = Lists.newArrayList();

      for (Entry entry : jsonObject2.entrySet()) {
         list.add(this.resolveEnhancedStringEntry(jsonDeserializationContext, entry));
      }

      return list;
   }

   public GsonEntry resolveGsonEntry(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      List list = this.resolveList(jsonDeserializationContext, asJsonObject);
      return new GsonEntry((Collection)list);
   }

   public EnhancedStringEntry resolveEnhancedStringEntry(JsonDeserializationContext jsonDeserializationContext, Entry entry) {
      String key = (String)entry.getKey();
      ArrayList list = Lists.newArrayList();
      JsonElement value = (JsonElement)entry.getValue();
      if (value.isJsonArray()) {
         for (JsonElement jsonElement : value.getAsJsonArray()) {
            list.add((DynamicResourceLocationEntry)jsonDeserializationContext.deserialize(jsonElement, DynamicResourceLocationEntry.class));
         }
      } else {
         list.add((DynamicResourceLocationEntry)jsonDeserializationContext.deserialize(value, DynamicResourceLocationEntry.class));
      }

      return new EnhancedStringEntry(key, list);
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveGsonEntry(jsonElement, type, jsonDeserializationContext);
   }
}
