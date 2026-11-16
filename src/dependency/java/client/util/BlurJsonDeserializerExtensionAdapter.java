package client.util;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class BlurJsonDeserializerExtensionAdapter extends JsonDeserializerExtensionAdapter {
   public SupplementalListEntry resolveSupplementalListEntry(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      boolean flag = MissingManager.matchesJsonObject(asJsonObject, "blur", false);
      boolean flag2 = MissingManager.matchesJsonObject(asJsonObject, "clamp", false);
      ArrayList list = Lists.newArrayList();
      if (asJsonObject.has("mipmaps")) {
         try {
            JsonArray asJsonArray = asJsonObject.getAsJsonArray("mipmaps");

            for (int index = 0; index < asJsonArray.size(); index++) {
               JsonElement jsonElement2 = asJsonArray.get(index);
               if (jsonElement2.isJsonPrimitive()) {
                  try {
                     list.add(jsonElement2.getAsInt());
                  } catch (NumberFormatException numberFormatException) {
                     throw new JsonParseException("Invalid texture->mipmap->" + index + ": expected number, was " + jsonElement2, numberFormatException);
                  }
               } else if (jsonElement2.isJsonObject()) {
                  throw new JsonParseException("Invalid texture->mipmap->" + index + ": expected number, was " + jsonElement2);
               }
            }
         } catch (ClassCastException classCastException) {
            throw new JsonParseException("Invalid texture->mipmaps: expected array, was " + asJsonObject.get("mipmaps"), classCastException);
         }
      }

      return new SupplementalListEntry(flag, flag2, list);
   }

   @Override
   public String getText() {
      return "texture";
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveSupplementalListEntry(jsonElement, type, jsonDeserializationContext);
   }
}
