package client.event;

import client.util.AutoReportCache;
import client.util.JsonPropertyGetter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public interface StaticListener extends JsonPropertyGetter {
   @Override
   default Object getEntry(String text, Object entry, JsonObject jsonObject) {
      AutoReportCache.getStringArray();
      JsonElement jsonElement = jsonObject.get(text);
      if (jsonElement != null && jsonElement.isJsonPrimitive()) {
         JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();

         try {
            return this.resolveTarget(asJsonPrimitive);
         } catch (Exception exception) {
            exception.printStackTrace();
            return entry;
         }
      } else {
         return entry;
      }
   }

   Object resolveTarget(JsonPrimitive jsonPrimitive);
}
