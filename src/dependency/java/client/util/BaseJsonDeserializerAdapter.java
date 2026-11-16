package client.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.item.AuxiliaryItemEntry;
import net.minecraft.item.SpecialItemEntry;

public class BaseJsonDeserializerAdapter implements JsonDeserializer {
   private AuxiliaryItemEntry resolveAuxiliaryItemEntry(JsonDeserializationContext jsonDeserializationContext, JsonObject jsonObject, String text) {
      return jsonObject.has(text) ? (AuxiliaryItemEntry)jsonDeserializationContext.deserialize(jsonObject.get(text), AuxiliaryItemEntry.class) : AuxiliaryItemEntry.auxiliaryItemEntry;
   }

   public SpecialItemEntry resolveSpecialItemEntry(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      AuxiliaryItemEntry auxiliaryItemEntry = this.resolveAuxiliaryItemEntry(jsonDeserializationContext, asJsonObject, "thirdperson");
      AuxiliaryItemEntry auxiliaryItemEntry2 = this.resolveAuxiliaryItemEntry(jsonDeserializationContext, asJsonObject, "firstperson");
      AuxiliaryItemEntry auxiliaryItemEntry3 = this.resolveAuxiliaryItemEntry(jsonDeserializationContext, asJsonObject, "head");
      AuxiliaryItemEntry auxiliaryItemEntry4 = this.resolveAuxiliaryItemEntry(jsonDeserializationContext, asJsonObject, "gui");
      AuxiliaryItemEntry auxiliaryItemEntry5 = this.resolveAuxiliaryItemEntry(jsonDeserializationContext, asJsonObject, "ground");
      AuxiliaryItemEntry auxiliaryItemEntry6 = this.resolveAuxiliaryItemEntry(jsonDeserializationContext, asJsonObject, "fixed");
      return new SpecialItemEntry(auxiliaryItemEntry, auxiliaryItemEntry2, auxiliaryItemEntry3, auxiliaryItemEntry4, auxiliaryItemEntry5, auxiliaryItemEntry6);
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveSpecialItemEntry(jsonElement, type, jsonDeserializationContext);
   }
}
