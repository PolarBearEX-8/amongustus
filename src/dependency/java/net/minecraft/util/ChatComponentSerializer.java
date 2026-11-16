package net.minecraft.util;

import client.util.AuxiliaryBaseManagerStyleIChatComponentAdapter;
import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.BoldJsonSerializer;
import client.util.InternalBaseManagerStyleIChatComponentAdapter;
import client.util.MissingManager;
import client.util.StyleHasParentManager;
import client.util.TypeAdapterFactoryAdapter;
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

public class ChatComponentSerializer implements JsonSerializer, JsonDeserializer {
   public static final Gson gson;

   public static JsonObject resolveJsonObject(IChatComponent iChatComponent) {
      return (JsonObject)gson.fromJson(toJson(iChatComponent), JsonObject.class);
   }

   private void applyStyleHasParentManager(StyleHasParentManager styleHasParentManager, JsonObject jsonObject, JsonSerializationContext jsonSerializationContext) {
      JsonElement jsonElement = jsonSerializationContext.serialize(styleHasParentManager);
      if (jsonElement.isJsonObject()) {
         for (Entry entry : ((JsonObject)jsonElement).entrySet()) {
            jsonObject.add((String)entry.getKey(), (JsonElement)entry.getValue());
         }
      }
   }

   public IChatComponent resolveIChatComponent(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      if (jsonElement.isJsonPrimitive()) {
         return new ChatComponentText(jsonElement.getAsString());
      } else if (!jsonElement.isJsonObject()) {
         if (jsonElement.isJsonArray()) {
            JsonArray asJsonArray = jsonElement.getAsJsonArray();
            IChatComponent iChatComponent = null;

            for (JsonElement jsonElement2 : asJsonArray) {
               IChatComponent iChatComponent2 = this.resolveIChatComponent(jsonElement2, jsonElement2.getClass(), jsonDeserializationContext);
               if (iChatComponent == null) {
                  iChatComponent = iChatComponent2;
               } else {
                  iChatComponent.appendChatComponent(iChatComponent2);
               }
            }

            return iChatComponent;
         } else {
            throw new JsonParseException("Don't know how to turn " + jsonElement + " into a Component");
         }
      } else {
         JsonObject asJsonObject = jsonElement.getAsJsonObject();
         Object entry;
         if (asJsonObject.has("text")) {
            entry = new ChatComponentText(asJsonObject.get("text").getAsString());
         } else if (asJsonObject.has("translate")) {
            String asString = asJsonObject.get("translate").getAsString();
            if (asJsonObject.has("with")) {
               JsonArray asJsonArray2 = asJsonObject.getAsJsonArray("with");
               Object[] entryArray = new Object[asJsonArray2.size()];

               for (int index = 0; index < entryArray.length; index++) {
                  entryArray[index] = this.resolveIChatComponent(asJsonArray2.get(index), type, jsonDeserializationContext);
                  if (entryArray[index] instanceof ChatComponentText) {
                     ChatComponentText chatComponentText = (ChatComponentText)entryArray[index];
                     if (chatComponentText.getStyleHasParentManager().isActive() && chatComponentText.getList().isEmpty()) {
                        entryArray[index] = chatComponentText.getLabelTextAsText();
                     }
                  }
               }

               entry = new BaseManagerStyleIChatComponentAdapter(asString, entryArray);
            } else {
               entry = new BaseManagerStyleIChatComponentAdapter(asString, new Object[0]);
            }
         } else if (asJsonObject.has("score")) {
            JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("score");
            if (!asJsonObject2.has("name") || !asJsonObject2.has("objective")) {
               throw new JsonParseException("A score component needs a least a name and an objective");
            }

            entry = new AuxiliaryBaseManagerStyleIChatComponentAdapter(
               MissingManager.resolveText(asJsonObject2, "name"), MissingManager.resolveText(asJsonObject2, "objective")
            );
            if (asJsonObject2.has("value")) {
               ((AuxiliaryBaseManagerStyleIChatComponentAdapter)entry).setLabelText3(MissingManager.resolveText(asJsonObject2, "value"));
            }
         } else {
            if (!asJsonObject.has("selector")) {
               throw new JsonParseException("Don't know how to turn " + jsonElement + " into a Component");
            }

            entry = new InternalBaseManagerStyleIChatComponentAdapter(MissingManager.resolveText(asJsonObject, "selector"));
         }

         if (asJsonObject.has("extra")) {
            JsonArray asJsonArray3 = asJsonObject.getAsJsonArray("extra");
            if (asJsonArray3.size() <= 0) {
               throw new JsonParseException("Unexpected empty array of components");
            }

            for (int index2 = 0; index2 < asJsonArray3.size(); index2++) {
               ((IChatComponent)entry).appendChatComponent(this.resolveIChatComponent(asJsonArray3.get(index2), type, jsonDeserializationContext));
            }
         }

         ((IChatComponent)entry).getIChat((StyleHasParentManager)jsonDeserializationContext.deserialize(jsonElement, StyleHasParentManager.class));
         return (IChatComponent)entry;
      }
   }

   public static String toJson(IChatComponent iChatComponent) {
      return gson.toJson(iChatComponent);
   }

   public static IChatComponent parseChatComponent(String text) {
      return (IChatComponent)gson.fromJson(text, IChatComponent.class);
   }

   public JsonElement resolveJsonElement(IChatComponent iChatComponent, Type type, JsonSerializationContext jsonSerializationContext) {
      if (iChatComponent instanceof ChatComponentText && iChatComponent.getStyleHasParentManager().isActive() && iChatComponent.getList().isEmpty()) {
         return new JsonPrimitive(((ChatComponentText)iChatComponent).getLabelTextAsText());
      } else {
         JsonObject jsonObject = new JsonObject();
         if (!iChatComponent.getStyleHasParentManager().isActive()) {
            this.applyStyleHasParentManager(iChatComponent.getStyleHasParentManager(), jsonObject, jsonSerializationContext);
         }

         if (!iChatComponent.getList().isEmpty()) {
            JsonArray jsonArray = new JsonArray();

            for (IChatComponent iChatComponent2 : (Iterable<IChatComponent>)(Iterable<?>)iChatComponent.getList()) {
               jsonArray.add(this.resolveJsonElement(iChatComponent2, iChatComponent2.getClass(), jsonSerializationContext));
            }

            jsonObject.add("extra", jsonArray);
         }

         if (iChatComponent instanceof ChatComponentText) {
            jsonObject.addProperty("text", ((ChatComponentText)iChatComponent).getLabelTextAsText());
         } else if (iChatComponent instanceof BaseManagerStyleIChatComponentAdapter) {
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = (BaseManagerStyleIChatComponentAdapter)iChatComponent;
            jsonObject.addProperty("translate", baseManagerStyleIChatComponentAdapter.getLabelTextAsText());
            if (baseManagerStyleIChatComponentAdapter.getObjectArray() != null && baseManagerStyleIChatComponentAdapter.getObjectArray().length > 0) {
               JsonArray jsonArray2 = new JsonArray();

               for (Object element : baseManagerStyleIChatComponentAdapter.getObjectArray()) {
                  if (element instanceof IChatComponent) {
                     jsonArray2.add(this.resolveJsonElement((IChatComponent)element, element.getClass(), jsonSerializationContext));
                  } else {
                     jsonArray2.add(new JsonPrimitive(String.valueOf(element)));
                  }
               }

               jsonObject.add("with", jsonArray2);
            }
         } else if (iChatComponent instanceof AuxiliaryBaseManagerStyleIChatComponentAdapter) {
            AuxiliaryBaseManagerStyleIChatComponentAdapter auxiliaryBaseManagerStyleIChatComponentAdapter = (AuxiliaryBaseManagerStyleIChatComponentAdapter)iChatComponent;
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("name", auxiliaryBaseManagerStyleIChatComponentAdapter.getLabelTextAsText());
            jsonObject2.addProperty("objective", auxiliaryBaseManagerStyleIChatComponentAdapter.getLabelText2());
            jsonObject2.addProperty("value", auxiliaryBaseManagerStyleIChatComponentAdapter.getLabelText());
            jsonObject.add("score", jsonObject2);
         } else {
            if (!(iChatComponent instanceof InternalBaseManagerStyleIChatComponentAdapter)) {
               throw new IllegalArgumentException("Don't know how to serialize " + iChatComponent + " as a Component");
            }

            InternalBaseManagerStyleIChatComponentAdapter internalBaseManagerStyleIChatComponentAdapter = (InternalBaseManagerStyleIChatComponentAdapter)iChatComponent;
            jsonObject.addProperty("selector", internalBaseManagerStyleIChatComponentAdapter.getLabelTextAsText());
         }

         return jsonObject;
      }
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveIChatComponent(jsonElement, type, jsonDeserializationContext);
   }

   public JsonElement serialize(Object entry, Type type, JsonSerializationContext jsonSerializationContext) {
      return this.resolveJsonElement((IChatComponent)entry, type, jsonSerializationContext);
   }

   static {
      GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.registerTypeHierarchyAdapter(IChatComponent.class, new ChatComponentSerializer());
      gsonBuilder.registerTypeHierarchyAdapter(StyleHasParentManager.class, new BoldJsonSerializer());
      gsonBuilder.registerTypeAdapterFactory(new TypeAdapterFactoryAdapter());
      gson = gsonBuilder.create();
   }
}
