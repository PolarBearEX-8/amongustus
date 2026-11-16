package client.util;

import client.setting.OptionType;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.util.ResourceLocation;

public class StandardJsonDeserializerAdapter implements JsonDeserializer {
   private ResourceLocation resolveResourceLocation(String text) {
      ResourceLocation location = new ResourceLocation(text);
      return new ResourceLocation(location.getText(), "block/" + location.getText1());
   }

   public DynamicResourceLocationEntry resolveDynamicResourceLocationEntry(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      String text = this.resolveText(asJsonObject);
      OptionType optionType = this.resolveOptionType(asJsonObject);
      boolean flag = this.matchesJsonObject(asJsonObject);
      int count = this.computeCount(asJsonObject);
      return new DynamicResourceLocationEntry(this.resolveResourceLocation(text), optionType, flag, count);
   }

   public OptionType resolveOptionType(JsonObject jsonObject) {
      int count = MissingManager.computeCount(jsonObject, "x", 0);
      int count2 = MissingManager.computeCount(jsonObject, "y", 0);
      OptionType optionType = OptionType.resolveOptionType(count, count2);
      if (optionType == null) {
         throw new JsonParseException("Invalid BlockModelRotation x: " + count + ", y: " + count2);
      } else {
         return optionType;
      }
   }

   public String resolveText(JsonObject jsonObject) {
      return MissingManager.resolveText(jsonObject, "model");
   }

   private boolean matchesJsonObject(JsonObject jsonObject) {
      return MissingManager.matchesJsonObject(jsonObject, "uvlock", false);
   }

   public int computeCount(JsonObject jsonObject) {
      return MissingManager.computeCount(jsonObject, "weight", 1);
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveDynamicResourceLocationEntry(jsonElement, type, jsonDeserializationContext);
   }
}
