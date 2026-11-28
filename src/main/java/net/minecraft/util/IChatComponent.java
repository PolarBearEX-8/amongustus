package net.minecraft.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Map.Entry;
import java.util.List;

public interface IChatComponent extends Iterable {
   IChatComponent copy();

   IChatComponent setChatStyle(ChatStyle chatStyle);

   List getSiblings();

   String getUnformattedText();

   IChatComponent appendText(String str);

   String getFormattedText();

   String getUnformattedTextForChat();

   IChatComponent appendSibling(IChatComponent component);

   ChatStyle getChatStyle();

   boolean isEmpty();


   public static class Serializer implements JsonSerializer, JsonDeserializer {
      private static final Gson GSON;

      public JsonElement serialize(Object obj, Type type, JsonSerializationContext jsonSerializationContext) {
         return this.serialize((IChatComponent)obj, type, jsonSerializationContext);
      }

      public static JsonObject componentToJsonObject(IChatComponent component) {
         return (JsonObject)GSON.fromJson(componentToJson(component), JsonObject.class);
      }

      static {
         GsonBuilder gsonBuilder = new GsonBuilder();
         gsonBuilder.registerTypeHierarchyAdapter(IChatComponent.class, new Serializer());
         gsonBuilder.registerTypeHierarchyAdapter(ChatStyle.class, new ChatStyle.Serializer());
         gsonBuilder.registerTypeAdapterFactory(new EnumTypeAdapterFactory());
         GSON = gsonBuilder.create();
      }

      public static String componentToJson(IChatComponent component) {
         return GSON.toJson(component);
      }

      public IChatComponent deserializeComponent(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         if (elem.isJsonPrimitive()) {
            return new ChatComponentText(elem.getAsString());
         } else if (!elem.isJsonObject()) {
            if (elem.isJsonArray()) {
               JsonArray asJsonArray = elem.getAsJsonArray();
               IChatComponent component = null;

               for (JsonElement elem2 : asJsonArray) {
                  IChatComponent component2 = this.deserializeComponent(elem2, elem2.getClass(), context);
                  if (component == null) {
                     component = component2;
                  } else {
                     component.appendSibling(component2);
                  }
               }

               return component;
            } else {
               throw new JsonParseException("Don't know how to turn " + elem + " into a Component");
            }
         } else {
            JsonObject asJsonObject = elem.getAsJsonObject();
            Object obj;
            if (asJsonObject.has("text")) {
               obj = new ChatComponentText(asJsonObject.get("text").getAsString());
            } else if (asJsonObject.has("translate")) {
               String asString = asJsonObject.get("translate").getAsString();
               if (asJsonObject.has("with")) {
                  JsonArray asJsonArray2 = asJsonObject.getAsJsonArray("with");
                  Object[] array = new Object[asJsonArray2.size()];

                  for (int i = 0; i < array.length; i++) {
                     array[i] = this.deserializeComponent(asJsonArray2.get(i), type, context);
                     if (array[i] instanceof ChatComponentText) {
                        ChatComponentText chatComponentText = (ChatComponentText)array[i];
                        if (chatComponentText.getChatStyle().isEmpty() && chatComponentText.getSiblings().isEmpty()) {
                           array[i] = chatComponentText.getChatComponentText_TextValue();
                        }
                     }
                  }

                  obj = new ChatComponentTranslation(asString, array);
               } else {
                  obj = new ChatComponentTranslation(asString, new Object[0]);
               }
            } else if (asJsonObject.has("score")) {
               JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("score");
               if (!asJsonObject2.has("name") || !asJsonObject2.has("objective")) {
                  throw new JsonParseException("A score component needs a least a name and an objective");
               }

               obj = new ChatComponentScore(JsonUtils.getString(asJsonObject2, "name"), JsonUtils.getString(asJsonObject2, "objective"));
               if (asJsonObject2.has("value")) {
                  ((ChatComponentScore)obj).setValue(JsonUtils.getString(asJsonObject2, "value"));
               }
            } else {
               if (!asJsonObject.has("selector")) {
                  throw new JsonParseException("Don't know how to turn " + elem + " into a Component");
               }

               obj = new ChatComponentSelector(JsonUtils.getString(asJsonObject, "selector"));
            }

            if (asJsonObject.has("extra")) {
               JsonArray asJsonArray3 = asJsonObject.getAsJsonArray("extra");
               if (asJsonArray3.size() <= 0) {
                  throw new JsonParseException("Unexpected empty array of components");
               }

               for (int j = 0; j < asJsonArray3.size(); j++) {
                  ((IChatComponent)obj).appendSibling(this.deserializeComponent(asJsonArray3.get(j), type, context));
               }
            }

            ((IChatComponent)obj).setChatStyle((ChatStyle)context.deserialize(elem, ChatStyle.class));
            return (IChatComponent)obj;
         }
      }

      private void serializeChatStyle(ChatStyle chatStyle, JsonObject json, JsonSerializationContext jsonSerializationContext) {
         JsonElement elem = jsonSerializationContext.serialize(chatStyle);
         if (elem.isJsonObject()) {

            for (Entry entry : ((JsonObject)elem).entrySet()) {
               json.add((String)entry.getKey(), (JsonElement)entry.getValue());
            }
         }
      }

      public static IChatComponent jsonToComponent(String str) {
         return (IChatComponent)GSON.fromJson(str, IChatComponent.class);
      }

      public JsonElement serialize(IChatComponent component, Type type, JsonSerializationContext jsonSerializationContext) {
         if (component instanceof ChatComponentText && component.getChatStyle().isEmpty() && component.getSiblings().isEmpty()) {
            return new JsonPrimitive(((ChatComponentText)component).getChatComponentText_TextValue());
         } else {
            JsonObject json = new JsonObject();
            if (!component.getChatStyle().isEmpty()) {
               this.serializeChatStyle(component.getChatStyle(), json, jsonSerializationContext);
            }

            if (!component.getSiblings().isEmpty()) {
               JsonArray array = new JsonArray();

               for (IChatComponent component2 : (Iterable<IChatComponent>)(component.getSiblings())) {
                  array.add(this.serialize(component2, component2.getClass(), jsonSerializationContext));
               }

               json.add("extra", array);
            }

            if (component instanceof ChatComponentText) {
               json.addProperty("text", ((ChatComponentText)component).getChatComponentText_TextValue());
            } else if (component instanceof ChatComponentTranslation) {
               ChatComponentTranslation chatComponentTranslation = (ChatComponentTranslation)component;
               json.addProperty("translate", chatComponentTranslation.getKey());
               if (chatComponentTranslation.getFormatArgs() != null && chatComponentTranslation.getFormatArgs().length > 0) {
                  JsonArray array2 = new JsonArray();

                  for (Object obj : chatComponentTranslation.getFormatArgs()) {
                     if (obj instanceof IChatComponent) {
                        array2.add(this.serialize((IChatComponent)obj, obj.getClass(), jsonSerializationContext));
                     } else {
                        array2.add(new JsonPrimitive(String.valueOf(obj)));
                     }
                  }

                  json.add("with", array2);
               }
            } else if (component instanceof ChatComponentScore) {
               ChatComponentScore chatComponentScore = (ChatComponentScore)component;
               JsonObject json2 = new JsonObject();
               json2.addProperty("name", chatComponentScore.getName());
               json2.addProperty("objective", chatComponentScore.getObjective());
               json2.addProperty("value", chatComponentScore.getUnformattedTextForChat());
               json.add("score", json2);
            } else {
               if (!(component instanceof ChatComponentSelector)) {
                  throw new IllegalArgumentException("Don't know how to serialize " + component + " as a Component");
               }

               ChatComponentSelector chatComponentSelector = (ChatComponentSelector)component;
               json.addProperty("selector", chatComponentSelector.getSelector());
            }

            return json;
         }
      }

      public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         return this.deserializeComponent(elem, type, context);
      }
   }
}
