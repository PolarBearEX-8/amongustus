package client.util;

import client.Client;
import client.event.JsonListener;
import client.module.Module;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BezierBuilder extends JsonListener {
   public void forEach(JsonArray jsonArray) {
      jsonArray.forEach(BezierBuilder::applyJsonElement2);
   }

   @Override
   public JsonElement getJsonElement() {
      return this.getJsonArray();
   }

   private static void applyJsonElement2(JsonElement jsonElement) {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      String asString = asJsonObject.get("Name").getAsString();

      for (Module module : (Iterable<Module>)(Iterable)Client.getClient().getModuleManager().getCopyOnWriteArrayList2()) {
         if (module.getString().equals(asString)) {
            try {
               module.applyJsonObject2(asJsonObject);
            } catch (Exception exception) {
               System.err.println("Failed to load Setting: " + module.getDescription());
            }
            break;
         }
      }
   }

   @Override
   public String getString() {
      return "module";
   }

   @Override
   public String getText() {
      return "settings";
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   public JsonArray getJsonArray() {
      return null;
   }

   @Override
   public Class getClassAsClass() {
      return JsonArray.class;
   }

   @Override
   public void applyJsonElement(JsonElement jsonElement) {
      JsonArray jsonArray = (JsonArray)jsonElement;
      this.forEach(jsonArray);
   }
}
