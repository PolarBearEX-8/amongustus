package client.util;

import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsOpenUrlType;
import client.setting.ClientSettingsShowTextType;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.util.IChatComponent;

public class BoldJsonSerializer implements JsonSerializer, JsonDeserializer {
   public StyleHasParentManager resolveStyleHasParentManager(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      if (jsonElement.isJsonObject()) {
         StyleHasParentManager styleHasParentManager = new StyleHasParentManager();
         JsonObject asJsonObject = jsonElement.getAsJsonObject();
         if (asJsonObject == null) {
            return null;
         } else {
            if (asJsonObject.has("bold")) {
               StyleHasParentManager.resolveBoolean3(styleHasParentManager, asJsonObject.get("bold").getAsBoolean());
            }

            if (asJsonObject.has("italic")) {
               StyleHasParentManager.resolveBoolean(styleHasParentManager, asJsonObject.get("italic").getAsBoolean());
            }

            if (asJsonObject.has("underlined")) {
               StyleHasParentManager.resolveBoolean2(styleHasParentManager, asJsonObject.get("underlined").getAsBoolean());
            }

            if (asJsonObject.has("strikethrough")) {
               StyleHasParentManager.resolveBooleanByStyleHasParentMa(styleHasParentManager, asJsonObject.get("strikethrough").getAsBoolean());
            }

            if (asJsonObject.has("obfuscated")) {
               StyleHasParentManager.resolveBoolean4(styleHasParentManager, asJsonObject.get("obfuscated").getAsBoolean());
            }

            if (asJsonObject.has("color")) {
               StyleHasParentManager.resolveClientSettingsBlackType(
                  styleHasParentManager, (ClientSettingsBlackType)jsonDeserializationContext.deserialize(asJsonObject.get("color"), ClientSettingsBlackType.class)
               );
            }

            if (asJsonObject.has("insertion")) {
               StyleHasParentManager.resolveText(styleHasParentManager, asJsonObject.get("insertion").getAsString());
            }

            if (asJsonObject.has("clickEvent")) {
               JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("clickEvent");
               if (asJsonObject2 != null) {
                  JsonPrimitive asJsonPrimitive = asJsonObject2.getAsJsonPrimitive("action");
                  ClientSettingsOpenUrlType clientSettingsOpenUrlType = asJsonPrimitive == null ? null : ClientSettingsOpenUrlType.get(asJsonPrimitive.getAsString());
                  JsonPrimitive asJsonPrimitive2 = asJsonObject2.getAsJsonPrimitive("value");
                  String text = asJsonPrimitive2 == null ? null : asJsonPrimitive2.getAsString();
                  if (clientSettingsOpenUrlType != null && text != null && clientSettingsOpenUrlType.isEnabled()) {
                     StyleHasParentManager.resolveClickEventActionManager(styleHasParentManager, new ClickEventActionManager(clientSettingsOpenUrlType, text));
                  }
               }
            }

            if (asJsonObject.has("hoverEvent")) {
               JsonObject asJsonObject3 = asJsonObject.getAsJsonObject("hoverEvent");
               if (asJsonObject3 != null) {
                  JsonPrimitive asJsonPrimitive3 = asJsonObject3.getAsJsonPrimitive("action");
                  ClientSettingsShowTextType clientSettingsShowTextType = asJsonPrimitive3 == null ? null : ClientSettingsShowTextType.get(asJsonPrimitive3.getAsString());
                  IChatComponent iChatComponent = (IChatComponent)jsonDeserializationContext.deserialize(asJsonObject3.get("value"), IChatComponent.class);
                  if (clientSettingsShowTextType != null && iChatComponent != null && clientSettingsShowTextType.isEnabled()) {
                     StyleHasParentManager.resolveHoverEventActionManager(styleHasParentManager, new HoverEventActionManager(clientSettingsShowTextType, iChatComponent));
                  }
               }
            }

            return styleHasParentManager;
         }
      } else {
         return null;
      }
   }

   public JsonElement resolveJsonElement(StyleHasParentManager styleHasParentManager, Type type, JsonSerializationContext jsonSerializationContext) {
      if (styleHasParentManager.isActive()) {
         return null;
      } else {
         JsonObject jsonObject = new JsonObject();
         if (StyleHasParentManager.resolveBoolean2(styleHasParentManager) != null) {
            jsonObject.addProperty("bold", StyleHasParentManager.resolveBoolean2(styleHasParentManager));
         }

         if (StyleHasParentManager.resolveBoolean4(styleHasParentManager) != null) {
            jsonObject.addProperty("italic", StyleHasParentManager.resolveBoolean4(styleHasParentManager));
         }

         if (StyleHasParentManager.resolveBoolean3(styleHasParentManager) != null) {
            jsonObject.addProperty("underlined", StyleHasParentManager.resolveBoolean3(styleHasParentManager));
         }

         if (StyleHasParentManager.resolveBooleanByStyleHasParentMa(styleHasParentManager) != null) {
            jsonObject.addProperty("strikethrough", StyleHasParentManager.resolveBooleanByStyleHasParentMa(styleHasParentManager));
         }

         if (StyleHasParentManager.resolveBoolean(styleHasParentManager) != null) {
            jsonObject.addProperty("obfuscated", StyleHasParentManager.resolveBoolean(styleHasParentManager));
         }

         if (StyleHasParentManager.resolveClientSettingsBlackType(styleHasParentManager) != null) {
            jsonObject.add("color", jsonSerializationContext.serialize(StyleHasParentManager.resolveClientSettingsBlackType(styleHasParentManager)));
         }

         if (StyleHasParentManager.resolveText(styleHasParentManager) != null) {
            jsonObject.add("insertion", jsonSerializationContext.serialize(StyleHasParentManager.resolveText(styleHasParentManager)));
         }

         if (StyleHasParentManager.resolveClickEventActionManager(styleHasParentManager) != null) {
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("action", StyleHasParentManager.resolveClickEventActionManager(styleHasParentManager).getClientSettingsOpenUrlType().getLabelText());
            jsonObject2.addProperty("value", StyleHasParentManager.resolveClickEventActionManager(styleHasParentManager).getLabelText());
            jsonObject.add("clickEvent", jsonObject2);
         }

         if (StyleHasParentManager.resolveHoverEventActionManager(styleHasParentManager) != null) {
            JsonObject jsonObject3 = new JsonObject();
            jsonObject3.addProperty("action", StyleHasParentManager.resolveHoverEventActionManager(styleHasParentManager).getClientSettingsShowTextType().getLabelText());
            jsonObject3.add("value", jsonSerializationContext.serialize(StyleHasParentManager.resolveHoverEventActionManager(styleHasParentManager).getIChatComponent()));
            jsonObject.add("hoverEvent", jsonObject3);
         }

         return jsonObject;
      }
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveStyleHasParentManager(jsonElement, type, jsonDeserializationContext);
   }

   public JsonElement serialize(Object entry, Type type, JsonSerializationContext jsonSerializationContext) {
      return this.resolveJsonElement((StyleHasParentManager)entry, type, jsonSerializationContext);
   }
}
