package net.minecraft.client.resources.data;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import net.minecraft.util.JsonUtils;

public class TextureMetadataSectionSerializer extends BaseMetadataSectionSerializer {

   public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
      return this.deserializeTexture(elem, type, context);
   }

   public TextureMetadataSection deserializeTexture(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
      JsonObject asJsonObject = elem.getAsJsonObject();
      boolean flag = JsonUtils.getBoolean(asJsonObject, "blur", false);
      boolean flag2 = JsonUtils.getBoolean(asJsonObject, "clamp", false);
      ArrayList list = Lists.newArrayList();
      if (asJsonObject.has("mipmaps")) {
         try {
            JsonArray asJsonArray = asJsonObject.getAsJsonArray("mipmaps");

            for (int i = 0; i < asJsonArray.size(); i++) {
               JsonElement elem2 = asJsonArray.get(i);
               if (elem2.isJsonPrimitive()) {
                  try {
                     list.add(elem2.getAsInt());
                  } catch (NumberFormatException e) {
                     throw new JsonParseException("Invalid texture->mipmap->" + i + ": expected number, was " + elem2, e);
                  }
               } else if (elem2.isJsonObject()) {
                  throw new JsonParseException("Invalid texture->mipmap->" + i + ": expected number, was " + elem2);
               }
            }
         } catch (ClassCastException e2) {
            throw new JsonParseException("Invalid texture->mipmaps: expected array, was " + asJsonObject.get("mipmaps"), e2);
         }
      }

      return new TextureMetadataSection(flag, flag2, list);
   }

   @Override
   public String getSectionName() {
      return "texture";
   }
}
