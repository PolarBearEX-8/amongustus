package client.util;

import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map.Entry;

public class LanguageJsonDeserializerExtensionAdapter extends JsonDeserializerExtensionAdapter {
   @Override
   public String getText() {
      return "language";
   }

   public AuxiliaryListenerAdapter7 resolveAuxiliaryListenerAdapter7(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      HashSet set = Sets.newHashSet();

      for (Entry entry : asJsonObject.entrySet()) {
         String key = (String)entry.getKey();
         JsonObject jsonObject = MissingManager.resolveJsonObject((JsonElement)entry.getValue(), "language");
         String text = MissingManager.resolveText(jsonObject, "region");
         String text2 = MissingManager.resolveText(jsonObject, "name");
         boolean flag = MissingManager.matchesJsonObject(jsonObject, "bidirectional", false);
         if (text.isEmpty()) {
            throw new JsonParseException("Invalid language->'" + key + "'->region: empty value");
         }

         if (text2.isEmpty()) {
            throw new JsonParseException("Invalid language->'" + key + "'->name: empty value");
         }

         if (!set.add(new BrandProperty(key, text, text2, flag))) {
            throw new JsonParseException("Duplicate language->'" + key + "' defined");
         }
      }

      return new AuxiliaryListenerAdapter7(set);
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveAuxiliaryListenerAdapter7(jsonElement, type, jsonDeserializationContext);
   }
}
