package net.minecraft.client.resources.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonUtils;

public class PackMetadataSectionSerializer extends BaseMetadataSectionSerializer implements JsonSerializer {

   public JsonElement serialize(PackMetadataSection packMetadataSection, Type type, JsonSerializationContext jsonSerializationContext) {
      JsonObject json = new JsonObject();
      json.addProperty("pack_format", packMetadataSection.getPackFormat());
      json.add("description", jsonSerializationContext.serialize(packMetadataSection.getPackDescription()));
      return json;
   }

   public JsonElement serialize(Object obj, Type type, JsonSerializationContext jsonSerializationContext) {
      return this.serialize((PackMetadataSection)obj, type, jsonSerializationContext);
   }

   public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
      return this.deserializePack(elem, type, context);
   }

   public PackMetadataSection deserializePack(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
      JsonObject asJsonObject = elem.getAsJsonObject();
      IChatComponent component = (IChatComponent)context.deserialize(asJsonObject.get("description"), IChatComponent.class);
      if (component == null) {
         throw new JsonParseException("Invalid/missing description!");
      } else {
         int n = JsonUtils.getInt(asJsonObject, "pack_format");
         return new PackMetadataSection(component, n);
      }
   }

   @Override
   public String getSectionName() {
      return "pack";
   }
}
