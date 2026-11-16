package client.util;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.apache.commons.lang3.Validate;

public class MetadataSectionJsonDeserializerExtensionAdapter extends JsonDeserializerExtensionAdapter implements JsonSerializer {
   public BaseListEntry resolveBaseListEntry(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      ArrayList list = Lists.newArrayList();
      JsonObject jsonObject = MissingManager.resolveJsonObject(jsonElement, "metadata section");
      int count = MissingManager.computeCount(jsonObject, "frametime", 1);
      if (count != 1) {
         Validate.inclusiveBetween(1L, 2147483647L, (long)count, "Invalid default frame time");
      }

      if (jsonObject.has("frames")) {
         try {
            JsonArray jsonArray = MissingManager.resolveJsonArray(jsonObject, "frames");

            for (int index = 0; index < jsonArray.size(); index++) {
               JsonElement jsonElement2 = jsonArray.get(index);
               SplineReader splineReader = this.resolveSplineReader(index, jsonElement2);
               if (splineReader != null) {
                  list.add(splineReader);
               }
            }
         } catch (ClassCastException classCastException) {
            throw new JsonParseException("Invalid animation->frames: expected array, was " + jsonObject.get("frames"), classCastException);
         }
      }

      int count2 = MissingManager.computeCount(jsonObject, "width", -1);
      int count3 = MissingManager.computeCount(jsonObject, "height", -1);
      if (count2 != -1) {
         Validate.inclusiveBetween(1L, 2147483647L, (long)count2, "Invalid width");
      }

      if (count3 != -1) {
         Validate.inclusiveBetween(1L, 2147483647L, (long)count3, "Invalid height");
      }

      boolean flag = MissingManager.matchesJsonObject(jsonObject, "interpolate", false);
      return new BaseListEntry(list, count2, count3, count, flag);
   }

   @Override
   public String getText() {
      return "animation";
   }

   public JsonElement resolveJsonElement(BaseListEntry baseListEntry, Type type, JsonSerializationContext jsonSerializationContext) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("frametime", baseListEntry.getCount());
      if (baseListEntry.getCount2() != -1) {
         jsonObject.addProperty("width", baseListEntry.getCount2());
      }

      if (baseListEntry.getCount3() != -1) {
         jsonObject.addProperty("height", baseListEntry.getCount3());
      }

      if (baseListEntry.size() > 0) {
         JsonArray jsonArray = new JsonArray();

         for (int index = 0; index < baseListEntry.size(); index++) {
            if (baseListEntry.checkIndex(index)) {
               JsonObject jsonObject2 = new JsonObject();
               jsonObject2.addProperty("index", baseListEntry.computeCount(index));
               jsonObject2.addProperty("time", baseListEntry.computeCountByIndex(index));
               jsonArray.add(jsonObject2);
            } else {
               jsonArray.add(new JsonPrimitive(baseListEntry.computeCount(index)));
            }
         }

         jsonObject.add("frames", jsonArray);
      }

      return jsonObject;
   }

   private SplineReader resolveSplineReader(int count, JsonElement jsonElement) {
      if (jsonElement.isJsonPrimitive()) {
         return new SplineReader(MissingManager.computeCount(jsonElement, "frames[" + count + "]"));
      } else if (jsonElement.isJsonObject()) {
         JsonObject jsonObject = MissingManager.resolveJsonObject(jsonElement, "frames[" + count + "]");
         int count2 = MissingManager.computeCount(jsonObject, "time", -1);
         if (jsonObject.has("time")) {
            Validate.inclusiveBetween(1L, 2147483647L, (long)count2, "Invalid frame time");
         }

         int count3 = MissingManager.computeCount(jsonObject, "index");
         Validate.inclusiveBetween(0L, 2147483647L, (long)count3, "Invalid frame index");
         return new SplineReader(count3, count2);
      } else {
         return null;
      }
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveBaseListEntry(jsonElement, type, jsonDeserializationContext);
   }

   public JsonElement serialize(Object entry, Type type, JsonSerializationContext jsonSerializationContext) {
      return this.resolveJsonElement((BaseListEntry)entry, type, jsonSerializationContext);
   }
}
