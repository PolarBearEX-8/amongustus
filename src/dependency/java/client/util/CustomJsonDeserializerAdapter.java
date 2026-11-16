package client.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class CustomJsonDeserializerAdapter implements JsonDeserializer, JsonSerializer {
   public final CommonLoggerEntry commonLoggerEntry;

   public CustomJsonDeserializerAdapter(CommonLoggerEntry commonLoggerEntry2, ExtendedParameterizedTypeAdapter extendedParameterizedTypeAdapter) {
      this(commonLoggerEntry2);
   }

   public JsonElement serialize(Object entry, Type type, JsonSerializationContext jsonSerializationContext) {
      return this.resolveJsonElement((TargetStack)entry, type, jsonSerializationContext);
   }

   public TargetStack resolveTargetStack(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      if (jsonElement.isJsonObject()) {
         JsonObject asJsonObject = jsonElement.getAsJsonObject();
         return this.commonLoggerEntry.resolveTargetStack(asJsonObject);
      } else {
         return null;
      }
   }

   private CustomJsonDeserializerAdapter(CommonLoggerEntry commonLoggerEntry2) {
      this.commonLoggerEntry = commonLoggerEntry2;
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveTargetStack(jsonElement, type, jsonDeserializationContext);
   }

   public JsonElement resolveJsonElement(TargetStack targetStack, Type type, JsonSerializationContext jsonSerializationContext) {
      JsonObject jsonObject = new JsonObject();
      targetStack.applyJsonObject(jsonObject);
      return jsonObject;
   }
}
