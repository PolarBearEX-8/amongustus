package client.util;

import client.Client;
import client.event.JsonListener;
import client.setting.Setting;
import client.setting.SettingSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BezierPool extends JsonListener {
   private static void applyJsonElement2(JsonElement jsonElement) {
      if (jsonElement != null && jsonElement.isJsonObject()) {
         JsonObject asJsonObject = jsonElement.getAsJsonObject();
         String asString = asJsonObject.get("ParentName").getAsString();
         String asString2 = asJsonObject.get("Name").getAsString();

         for (Setting setting : (Iterable<Setting>)(Iterable<?>)Client.getClient().getAuxiliaryCopyOnWriteArrayLis().getList()) {
            if (setting.getString().equals(asString2) && setting.getTextAsText().equals(asString)) {
               try {
                  SettingSerializer advancedItemEntry = SettingSerializer.advancedItemEntry;
                  setting.updateSupplementalListener(advancedItemEntry, asJsonObject);
               } catch (Exception exception) {
                  System.err.println("Failed to load Setting: " + setting.getString() + " " + setting.getTextAsText());
               }
               break;
            }
         }
      }
   }

   @Override
   public JsonElement getJsonElement() {
      return this.getJsonArray();
   }

   @Override
   public String getString() {
      return "settings";
   }

   @Override
   public String getText() {
      return "settings";
   }

   public JsonArray getJsonArray() {
      return null;
   }

   @Override
   public Class getClassAsClass() {
      return JsonArray.class;
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   public void forEach(JsonArray jsonArray) {
      jsonArray.forEach(BezierPool::applyJsonElement2);
   }

   @Override
   public void applyJsonElement(JsonElement jsonElement) {
      JsonArray jsonArray = (JsonArray)jsonElement;
      this.forEach(jsonArray);
   }
}
