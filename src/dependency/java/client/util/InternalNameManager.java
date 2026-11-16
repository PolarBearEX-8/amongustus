package client.util;

import augustus.modules.misc.ClientAntiCheat;
import client.Client;
import client.ClientGlobals;
import client.event.PostRenderDispatcher;
import client.module.Module;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.setting.SettingSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class InternalNameManager implements PostRenderDispatcher {
   public static HashMap hashMap2;
   public static HashMap hashMap;

   @Override
   public int getCount() {
      return 0;
   }

   private static void applyJsonElement(JsonElement jsonElement) {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      String asString = asJsonObject.get("Name").getAsString();
      Module module = (Module)hashMap2.get(asString);
      if (module != null && module.getDescription().equals(asString) && module.canExecute4()) {
         try {
            module.applyJsonObject(asJsonObject);
         } catch (Exception exception) {
            System.err.println("Failed to load Module from Config: " + module.getDescription());
         }
      }
   }

   @Override
   public void applyJsonObject(JsonObject jsonObject) {
      try {
         JsonArray asJsonArray = jsonObject.get("settings").getAsJsonArray();
         if (hashMap == null) {
            hashMap = new HashMap();

            for (Setting setting : (Iterable<Setting>)(Iterable)(ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.getList())) {
               hashMap.put(setting.getString() + "_" + setting.getTextAsText(), setting);
            }
         }

         asJsonArray.forEach(InternalNameManager::applyJsonElementByJsonElement);
      } catch (Exception exception) {
         exception.printStackTrace();
         System.err.println("Config wasn't loaded correctly");
      }

      try {
         JsonArray asJsonArray2 = jsonObject.get("modules").getAsJsonArray();
         if (hashMap2 == null) {
            hashMap2 = new HashMap();

            for (Module module : (Iterable<Module>)(Iterable)(ClientGlobals.moduleManager.getCopyOnWriteArrayList2())) {
               hashMap2.put(module.getDescription(), module);
            }
         }

         asJsonArray2.forEach(InternalNameManager::applyJsonElement);
         Client.getClient().getModuleManager().updateState();
      } catch (Exception exception2) {
         System.err.println("Config wasn't loaded correctly");
      }
   }

   private static void applyJsonElementByJsonElement(JsonElement jsonElement) {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      String asString = asJsonObject.get("ParentName").getAsString();
      String asString2 = asJsonObject.get("Name").getAsString();
      Setting setting = (Setting)hashMap.get(asString2 + "_" + asString);
      if (setting != null
         && !(setting instanceof SettingGroup)
         && setting.getString().equals(asString2)
         && setting.getTextAsText().equals(asString)
         && (
            setting.getString().contains("Blur")
               ? ((Module)ClientGlobals.moduleManager.blur).canExecute4()
               : (setting.getString().contains("Shadow") ? ((Module)ClientGlobals.moduleManager.shadow).canExecute4() : setting.getModule().canExecute4())
         )) {
         try {
            SettingSerializer advancedItemEntry = SettingSerializer.advancedItemEntry;
            setting.updateSupplementalListener(advancedItemEntry, asJsonObject);
         } catch (Exception exception) {
            System.err.println("Failed to load Setting from Config: " + setting.getString() + " " + setting.getTextAsText());
         }
      }
   }

   @Override
   public String[] resolveTextArray(JsonObject jsonObject) {
      String asString = jsonObject.get("name").getAsString();
      String asString2 = jsonObject.get("date").getAsString();
      String asString3 = jsonObject.get("time").getAsString();
      return new String[]{asString, asString2, asString3};
   }

   @Override
   public JsonObject resolveJsonObject(String text) {
      GregorianCalendar gregorianCalendar = new GregorianCalendar();
      DateFormat dateInstance = DateFormat.getDateInstance(2);
      DateFormat timeInstance = DateFormat.getTimeInstance(3);
      JsonArray jsonArray = new JsonArray();

      for (Setting setting : (Iterable<Setting>)(Iterable)(ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.getList())) {
         if (!(setting instanceof SettingGroup) && !(setting.getModule() instanceof ClientAntiCheat)) {
            SettingSerializer advancedItemEntry = SettingSerializer.advancedItemEntry;
            jsonArray.add(setting.resolveJsonObject(advancedItemEntry));
         }
      }

      JsonArray jsonArray2 = new JsonArray();

      for (Module module : (Iterable<Module>)(Iterable)(ClientGlobals.moduleManager.getCopyOnWriteArrayList2())) {
         jsonArray2.add(module.getJsonObject());
      }

      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("name", text);
      jsonObject.addProperty("date", dateInstance.format(gregorianCalendar.getTime()));
      jsonObject.addProperty("time", timeInstance.format(gregorianCalendar.getTime()));
      jsonObject.add("modules", jsonArray2);
      jsonObject.add("settings", jsonArray);
      return jsonObject;
   }
}
