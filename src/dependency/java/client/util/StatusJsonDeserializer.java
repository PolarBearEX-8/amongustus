package client.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.util.IChatComponent;

public class StatusJsonDeserializer implements JsonDeserializer, JsonSerializer {
   public IChatComponentEntry resolveIChatComponentEntry(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject jsonObject = MissingManager.resolveJsonObject(jsonElement, "status");
      IChatComponentEntry iChatComponentEntry = new IChatComponentEntry();
      if (jsonObject.has("description")) {
         iChatComponentEntry.setIChatComponent((IChatComponent)jsonDeserializationContext.deserialize(jsonObject.get("description"), IChatComponent.class));
      }

      if (jsonObject.has("players")) {
         iChatComponentEntry.setGameProfileArrayHolder((GameProfileArrayHolder)jsonDeserializationContext.deserialize(jsonObject.get("players"), GameProfileArrayHolder.class));
      }

      if (jsonObject.has("version")) {
         iChatComponentEntry.setDampenerDeserializer((DampenerDeserializer)jsonDeserializationContext.deserialize(jsonObject.get("version"), DampenerDeserializer.class));
      }

      if (jsonObject.has("favicon")) {
         iChatComponentEntry.setLabelText(MissingManager.resolveText(jsonObject, "favicon"));
      }

      return iChatComponentEntry;
   }

   public JsonElement resolveJsonElement(IChatComponentEntry iChatComponentEntry, Type type, JsonSerializationContext jsonSerializationContext) {
      JsonObject jsonObject = new JsonObject();
      if (iChatComponentEntry.getIChatComponent() != null) {
         jsonObject.add("description", jsonSerializationContext.serialize(iChatComponentEntry.getIChatComponent()));
      }

      if (iChatComponentEntry.getGameProfileArrayHolder() != null) {
         jsonObject.add("players", jsonSerializationContext.serialize(iChatComponentEntry.getGameProfileArrayHolder()));
      }

      if (iChatComponentEntry.getDampenerDeserializer() != null) {
         jsonObject.add("version", jsonSerializationContext.serialize(iChatComponentEntry.getDampenerDeserializer()));
      }

      if (iChatComponentEntry.getLabelText() != null) {
         jsonObject.addProperty("favicon", iChatComponentEntry.getLabelText());
      }

      return jsonObject;
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveIChatComponentEntry(jsonElement, type, jsonDeserializationContext);
   }

   public JsonElement serialize(Object entry, Type type, JsonSerializationContext jsonSerializationContext) {
      return this.resolveJsonElement((IChatComponentEntry)entry, type, jsonSerializationContext);
   }
}
