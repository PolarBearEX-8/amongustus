package client.util;

import client.Client;
import client.event.PostRenderDispatcher;
import client.module.Module;
import client.module.ModuleManager;
import client.network.MysteryBoxPacketListener;
import client.setting.ModeOptionConfigurable;
import client.setting.ModuleModeOption;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;

public class GroupygroupyManager implements PostRenderDispatcher {
   private static Boolean resolveBoolean(ModeOptionConfigurable modeOptionConfigurable) {
      ModuleModeOption moduleModeOption = modeOptionConfigurable.getModuleModeOption();
      if (!(moduleModeOption instanceof Module)) {
         return true;
      } else if (modeOptionConfigurable instanceof ListenerTracker) {
         Module module = (Module)moduleModeOption;
         String string = modeOptionConfigurable.getString();
         ModuleManager moduleManager = Client.getClient().getModuleManager();
         return !string.endsWith("Groupygroupy")
            && (
               string.contains("Blur")
                  ? ((Module)moduleManager.blur).canExecute4()
                  : (string.contains("Shadow") ? ((Module)moduleManager.shadow).canExecute4() : module.canExecute4())
            );
      } else {
         return (
               modeOptionConfigurable.getString().equals("M_T")
                  || Client.getClient().getModuleManager().clientSettings.settingLoadKeysFromConfigs.isEnabled() && modeOptionConfigurable.getString().equals("M_K")
            )
            && ((Module)moduleModeOption).canExecute4();
      }
   }

   @Override
   public int getCount() {
      return 0;
   }

   @Override
   public void applyJsonObject(JsonObject jsonObject) {
      JsonElement jsonElement = jsonObject.get("Configuration");
      if (jsonElement != null && jsonElement.isJsonObject()) {
         JsonObject asJsonObject = jsonObject.get("Configuration").getAsJsonObject();
         ListenerTracker listenerTracker = AuxiliaryListener60EntryAdapter.listenerTracker;
         MysteryBoxPacketListener mysteryBoxPacketListener = MysteryBoxService.mysteryBoxPacketListener;
         Function mapper = (item -> GroupygroupyManager.resolveBoolean((ModeOptionConfigurable)item));
         boolean flag = true;
         MysteryBoxPacketListener packet = mysteryBoxPacketListener;
         listenerTracker.updateListener(packet, asJsonObject, flag, mapper);
      }
   }

   private static Boolean resolveBooleanByModeOptionConfig(ModeOptionConfigurable modeOptionConfigurable) {
      if (!(modeOptionConfigurable instanceof Module)) {
         return true;
      } else {
         Module module = (Module)modeOptionConfigurable;
         return !module.isVisible() && !module.getCategory().special;
      }
   }

   @Override
   public String[] resolveTextArray(JsonObject jsonObject) {
      Set entries = jsonObject.get("Config_Header").getAsJsonObject().entrySet();
      ArrayList list = new ArrayList();

      for (Object entry_o : entries) {
         Entry entry = (Entry)entry_o;
         if (!((String)entry.getKey()).equals("Config_Version")) {
            list.add(((JsonElement)entry.getValue()).getAsString());
         }
      }

      return ((String[])list.toArray(new String[0]));
   }

   @Override
   public JsonObject resolveJsonObject(String text) {
      JsonObject jsonObject = new JsonObject();
      JsonObject jsonObject2 = new JsonObject();
      GregorianCalendar gregorianCalendar = new GregorianCalendar();
      DateFormat dateInstance = DateFormat.getDateInstance(2);
      DateFormat timeInstance = DateFormat.getTimeInstance(3);
      jsonObject2.addProperty("Config_Version", 2);
      jsonObject2.addProperty("Config_Name", text);
      jsonObject2.addProperty("Config_Date", dateInstance.format(gregorianCalendar.getTime()));
      jsonObject2.addProperty("Config_Time", timeInstance.format(gregorianCalendar.getTime()));
      String string = Client.getClient().getString7();
      jsonObject2.addProperty("Config_Author", string != null && !string.isEmpty() ? string : "No_Author");
      jsonObject.add("Config_Header", jsonObject2);
      ListenerTracker listenerTracker = AuxiliaryListener60EntryAdapter.listenerTracker;
      MysteryBoxPacketListener mysteryBoxPacketListener = MysteryBoxService.mysteryBoxPacketListener;
      JsonObject jsonObject3 = new JsonObject();
      Function mapper = (item -> GroupygroupyManager.resolveBooleanByModeOptionConfig((ModeOptionConfigurable)item));
      Function mapper2 = mapper;
      JsonObject jsonObject4 = jsonObject3;
      MysteryBoxPacketListener packet = mysteryBoxPacketListener;
      JsonObject jsonObject5 = listenerTracker.getJsonObject(packet, jsonObject4, mapper2);
      jsonObject.add("Configuration", jsonObject5);
      return jsonObject;
   }
}
