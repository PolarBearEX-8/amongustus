package client.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class NameJsonSerializer implements JsonSerializer, JsonDeserializer {
   public JsonElement resolveJsonElement(DampenerDeserializer dampenerDeserializer, Type type, JsonSerializationContext jsonSerializationContext) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("name", dampenerDeserializer.getLabelText());
      jsonObject.addProperty("protocol", dampenerDeserializer.getCount());
      return jsonObject;
   }

   public DampenerDeserializer resolveDampenerDeserializer(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject jsonObject = MissingManager.resolveJsonObject(jsonElement, "version");
      return new DampenerDeserializer(MissingManager.resolveText(jsonObject, "name"), MissingManager.computeCount(jsonObject, "protocol"));
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveDampenerDeserializer(jsonElement, type, jsonDeserializationContext);
   }

   public JsonElement serialize(Object entry, Type type, JsonSerializationContext jsonSerializationContext) {
      return this.resolveJsonElement((DampenerDeserializer)entry, type, jsonSerializationContext);
   }
}
