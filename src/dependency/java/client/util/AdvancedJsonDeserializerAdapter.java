package client.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.util.EnumFacing;

public class AdvancedJsonDeserializerAdapter implements JsonDeserializer {
   private String resolveText(JsonObject jsonObject) {
      return MissingManager.resolveText(jsonObject, "texture");
   }

   public int computeCount(JsonObject jsonObject) {
      return MissingManager.computeCount(jsonObject, "tintindex", -1);
   }

   private EnumFacing resolveEnumFacing(JsonObject jsonObject) {
      return EnumFacing.resolveEnumFacing(MissingManager.resolveText(jsonObject, "cullface", ""));
   }

   public EnumFacingHolder resolveEnumFacingHolder(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      EnumFacing enumFacing = this.resolveEnumFacing(asJsonObject);
      int count = this.computeCount(asJsonObject);
      String text = this.resolveText(asJsonObject);
      TertiaryFloatArrayEntry tertiaryFloatArrayEntry = (TertiaryFloatArrayEntry)jsonDeserializationContext.deserialize(asJsonObject, TertiaryFloatArrayEntry.class);
      return new EnumFacingHolder(enumFacing, count, text, tertiaryFloatArrayEntry);
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveEnumFacingHolder(jsonElement, type, jsonDeserializationContext);
   }
}
