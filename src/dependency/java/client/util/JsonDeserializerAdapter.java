package client.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.item.SpecialItemEntry;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.StringUtils;

public class JsonDeserializerAdapter implements JsonDeserializer {
   public boolean matchesJsonObject(JsonObject jsonObject) {
      return MissingManager.matchesJsonObject(jsonObject, "ambientocclusion", true);
   }

   private Map resolveMap(JsonObject jsonObject) {
      HashMap map = Maps.newHashMap();
      if (jsonObject.has("textures")) {
         for (Entry entry : jsonObject.getAsJsonObject("textures").entrySet()) {
            map.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
         }
      }

      return map;
   }

   public List resolveList(JsonDeserializationContext jsonDeserializationContext, JsonObject jsonObject) {
      ArrayList list = Lists.newArrayList();
      if (jsonObject.has("elements")) {
         for (JsonElement jsonElement : MissingManager.resolveJsonArray(jsonObject, "elements")) {
            list.add((Vector3fHolder)jsonDeserializationContext.deserialize(jsonElement, Vector3fHolder.class));
         }
      }

      return list;
   }

   public NativeLoggerEntry resolveNativeLoggerEntry(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      List list = this.resolveList(jsonDeserializationContext, asJsonObject);
      String text = this.resolveText(asJsonObject);
      boolean empty = StringUtils.isEmpty(text);
      boolean empty2 = list.isEmpty();
      if (empty2 && empty) {
         throw new JsonParseException("BlockModel requires either elements or parent, found neither");
      } else if (!empty && !empty2) {
         throw new JsonParseException("BlockModel requires either elements or parent, found both");
      } else {
         Map map = this.resolveMap(asJsonObject);
         boolean flag = this.matchesJsonObject(asJsonObject);
         SpecialItemEntry specialItemEntry = SpecialItemEntry.specialItemEntry;
         if (asJsonObject.has("display")) {
            JsonObject jsonObject = MissingManager.resolveJsonObject(asJsonObject, "display");
            specialItemEntry = (SpecialItemEntry)jsonDeserializationContext.deserialize(jsonObject, SpecialItemEntry.class);
         }

         return empty2 ? new NativeLoggerEntry(new ResourceLocation(text), map, flag, true, specialItemEntry) : new NativeLoggerEntry(list, map, flag, true, specialItemEntry);
      }
   }

   private String resolveText(JsonObject jsonObject) {
      return MissingManager.resolveText(jsonObject, "parent", "");
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveNativeLoggerEntry(jsonElement, type, jsonDeserializationContext);
   }
}
