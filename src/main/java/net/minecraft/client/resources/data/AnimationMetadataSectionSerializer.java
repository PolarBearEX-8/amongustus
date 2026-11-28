package net.minecraft.client.resources.data;

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
import net.minecraft.util.JsonUtils;
import org.apache.commons.lang3.Validate;

public class AnimationMetadataSectionSerializer extends BaseMetadataSectionSerializer implements JsonSerializer {

   public JsonElement serialize(Object obj, Type type, JsonSerializationContext jsonSerializationContext) {
      return this.serialize((AnimationMetadataSection)obj, type, jsonSerializationContext);
   }

   public JsonElement serialize(AnimationMetadataSection animationMetadataSection, Type type, JsonSerializationContext jsonSerializationContext) {
      JsonObject json = new JsonObject();
      json.addProperty("frametime", animationMetadataSection.getFrameTime());
      if (animationMetadataSection.getFrameWidth() != -1) {
         json.addProperty("width", animationMetadataSection.getFrameWidth());
      }

      if (animationMetadataSection.getFrameHeight() != -1) {
         json.addProperty("height", animationMetadataSection.getFrameHeight());
      }

      if (animationMetadataSection.getFrameCount() > 0) {
         JsonArray array = new JsonArray();

         for (int i = 0; i < animationMetadataSection.getFrameCount(); i++) {
            if (animationMetadataSection.frameHasTime(i)) {
               JsonObject json2 = new JsonObject();
               json2.addProperty("index", animationMetadataSection.getFrameIndex(i));
               json2.addProperty("time", animationMetadataSection.getFrameTimeSingle(i));
               array.add(json2);
            } else {
               array.add(new JsonPrimitive(animationMetadataSection.getFrameIndex(i)));
            }
         }

         json.add("frames", array);
      }

      return json;
   }

   @Override
   public String getSectionName() {
      return "animation";
   }

   public AnimationMetadataSection deserializeAnimation(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
      ArrayList list = Lists.newArrayList();
      JsonObject jsonObject = JsonUtils.getJsonObject(elem, "metadata section");
      int n = JsonUtils.getInt(jsonObject, "frametime", 1);
      if (n != 1) {
         Validate.inclusiveBetween(1L, 2147483647L, (long)n, "Invalid default frame time");
      }

      if (jsonObject.has("frames")) {
         try {
            JsonArray jsonArray = JsonUtils.getJsonArray(jsonObject, "frames");

            for (int i = 0; i < jsonArray.size(); i++) {
               AnimationFrame animationFrame = this.parseAnimationFrame(i, jsonArray.get(i));
               if (animationFrame != null) {
                  list.add(animationFrame);
               }
            }
         } catch (ClassCastException e) {
            throw new JsonParseException("Invalid animation->frames: expected array, was " + jsonObject.get("frames"), e);
         }
      }

      int n2 = JsonUtils.getInt(jsonObject, "width", -1);
      int n3 = JsonUtils.getInt(jsonObject, "height", -1);
      if (n2 != -1) {
         Validate.inclusiveBetween(1L, 2147483647L, (long)n2, "Invalid width");
      }

      if (n3 != -1) {
         Validate.inclusiveBetween(1L, 2147483647L, (long)n3, "Invalid height");
      }

      boolean flag = JsonUtils.getBoolean(jsonObject, "interpolate", false);
      return new AnimationMetadataSection(list, n2, n3, n, flag);
   }

   public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
      return this.deserializeAnimation(elem, type, context);
   }

   private AnimationFrame parseAnimationFrame(int n, JsonElement elem) {
      if (elem.isJsonPrimitive()) {
         return new AnimationFrame(JsonUtils.getInt(elem, "frames[" + n + "]"));
      } else if (elem.isJsonObject()) {
         JsonObject jsonObject = JsonUtils.getJsonObject(elem, "frames[" + n + "]");
         int n2 = JsonUtils.getInt(jsonObject, "time", -1);
         if (jsonObject.has("time")) {
            Validate.inclusiveBetween(1L, 2147483647L, (long)n2, "Invalid frame time");
         }

         int n3 = JsonUtils.getInt(jsonObject, "index");
         Validate.inclusiveBetween(0L, 2147483647L, (long)n3, "Invalid frame index");
         return new AnimationFrame(n3, n2);
      } else {
         return null;
      }
   }
}
