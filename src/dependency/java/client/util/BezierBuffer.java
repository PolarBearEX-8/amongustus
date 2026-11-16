package client.util;

import client.event.JsonListener;
import client.gui.DropdownMenuWidget;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BezierBuffer extends JsonListener {
   @Override
   public JsonElement getJsonElement() {
      return this.getJsonArray();
   }

   public JsonArray getJsonArray() {
      JsonArray jsonArray = new JsonArray();

      for (Object accountProfileObj : DropdownMenuWidget.copyOnWriteArrayList) { AccountProfile accountProfile = (AccountProfile)accountProfileObj;
         try {
            jsonArray.add(accountProfile.getJsonObjectAsJsonObject());
         } catch (Exception exception) {
         }
      }

      return jsonArray;
   }

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public String getString() {
      return "Accounts";
   }

   @Override
   public String getText() {
      return "settings";
   }

   @Override
   public Class getClassAsClass() {
      return JsonArray.class;
   }

   private static void applyJsonElement2(JsonElement jsonElement) {
      try {
         JsonObject asJsonObject = jsonElement.getAsJsonObject();
         AccountProfile accountProfile = new AccountProfile();
         accountProfile.applyJsonObject(asJsonObject);
         DropdownMenuWidget.copyOnWriteArrayList.add(accountProfile);
      } catch (Exception exception) {
         exception.printStackTrace();
         System.err.println("Failed to load alt-account");
      }
   }

   public void applyJsonArray(JsonArray jsonArray) {
      DropdownMenuWidget.copyOnWriteArrayList.clear();
      jsonArray.forEach(BezierBuffer::applyJsonElement2);
   }

   @Override
   public void applyJsonElement(JsonElement jsonElement) {
      JsonArray jsonArray = (JsonArray)jsonElement;
      this.applyJsonArray(jsonArray);
   }
}
