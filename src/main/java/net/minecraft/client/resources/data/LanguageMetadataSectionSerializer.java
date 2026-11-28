package net.minecraft.client.resources.data;

import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map.Entry;
import net.minecraft.client.resources.Language;
import net.minecraft.util.JsonUtils;

public class LanguageMetadataSectionSerializer extends BaseMetadataSectionSerializer {

   @Override
   public String getSectionName() {
      return "language";
   }

   public LanguageMetadataSection deserializeLanguage(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
      JsonObject asJsonObject = elem.getAsJsonObject();
      HashSet set = Sets.newHashSet();

      for (Entry entry : asJsonObject.entrySet()) {
         String key = (String)entry.getKey();
         JsonObject jsonObject = JsonUtils.getJsonObject((JsonElement)entry.getValue(), "language");
         String string = JsonUtils.getString(jsonObject, "region");
         String string2 = JsonUtils.getString(jsonObject, "name");
         boolean flag = JsonUtils.getBoolean(jsonObject, "bidirectional", false);
         if (string.isEmpty()) {
            throw new JsonParseException("Invalid language->'" + key + "'->region: empty value");
         }

         if (string2.isEmpty()) {
            throw new JsonParseException("Invalid language->'" + key + "'->name: empty value");
         }

         if (!set.add(new Language(key, string, string2, flag))) {
            throw new JsonParseException("Duplicate language->'" + key + "' defined");
         }
      }

      return new LanguageMetadataSection(set);
   }

   public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
      return this.deserializeLanguage(elem, type, context);
   }
}
