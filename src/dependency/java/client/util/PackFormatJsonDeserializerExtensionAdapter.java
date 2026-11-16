package client.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.util.IChatComponent;

public class PackFormatJsonDeserializerExtensionAdapter extends JsonDeserializerExtensionAdapter implements JsonSerializer {
   public JsonElement resolveJsonElement(AuxiliaryIChatComponentEntry auxiliaryIChatComponentEntry, Type type, JsonSerializationContext jsonSerializationContext) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("pack_format", auxiliaryIChatComponentEntry.getCount());
      jsonObject.add("description", jsonSerializationContext.serialize(auxiliaryIChatComponentEntry.getIChatComponent()));
      return jsonObject;
   }

   @Override
   public String getText() {
      return "pack";
   }

   public AuxiliaryIChatComponentEntry resolveAuxiliaryIChatComponentEntry(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      IChatComponent iChatComponent = (IChatComponent)jsonDeserializationContext.deserialize(asJsonObject.get("description"), IChatComponent.class);
      if (iChatComponent == null) {
         throw new JsonParseException("Invalid/missing description!");
      } else {
         int count = MissingManager.computeCount(asJsonObject, "pack_format");
         return new AuxiliaryIChatComponentEntry(iChatComponent, count);
      }
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveAuxiliaryIChatComponentEntry(jsonElement, type, jsonDeserializationContext);
   }

   public JsonElement serialize(Object entry, Type type, JsonSerializationContext jsonSerializationContext) {
      return this.resolveJsonElement((AuxiliaryIChatComponentEntry)entry, type, jsonSerializationContext);
   }
}
