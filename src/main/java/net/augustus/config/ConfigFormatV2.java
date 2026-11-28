package net.augustus.config;

import net.augustus.Augustus;
import net.augustus.clickgui.ClickGuiScreen;
import net.augustus.settings.property.ConfigProperty;
import net.augustus.settings.property.codec.JsonCodecRegistryHolder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map.Entry;
import java.util.Set;
import net.augustus.modules.AbstractFeature;
import net.augustus.modules.FeatureBase;
import net.augustus.modules.Module;
import net.augustus.modules.ModuleManager;

public class ConfigFormatV2 implements ModuleConfigCodec {

   @Override
   public String[] readHeaderInfo(JsonObject json) {
      if (json == null || !json.has("Config_Header") || !json.get("Config_Header").isJsonObject()) {
         return null;
      }
      JsonObject header = json.getAsJsonObject("Config_Header");
      String name = header.has("Config_Name") ? header.get("Config_Name").getAsString() : "";
      String date = header.has("Config_Date") ? header.get("Config_Date").getAsString() : "";
      String time = header.has("Config_Time") ? header.get("Config_Time").getAsString() : "";
      String editable = header.has("Config_Editable") ? header.get("Config_Editable").getAsString() : "true";
      String author = header.has("Config_Author") ? header.get("Config_Author").getAsString() : "";
      return new String[]{name, date, time, editable, author};
   }

   private static Boolean shouldLoadProperty(ConfigProperty configProperty) {
      FeatureBase featureBase = configProperty.getFeatureBase();
      if (!(featureBase instanceof Module)) {
         return true;
      } else {
         if (!(configProperty instanceof AbstractFeature)) {
            if (configProperty.getName().equals("M_T")) {
               return ((Module)featureBase).isLoadFromConfig();
            } else {
               return Augustus.getInstance().getModuleManager().clientSettings.loadKeysFromConfigs.getValue() && configProperty.getName().equals("M_K")
                  ? ((Module)featureBase).isLoadFromConfig()
                  : false;
            }
         } else {
            String name = configProperty.getName();
            ModuleManager moduleManager = Augustus.getInstance().getModuleManager();
            if (name.endsWith("Groupygroupy")) {
               return false;
            } else if (name.contains("Blur")) {
               return moduleManager.blur.isLoadFromConfig();
            } else {
               return name.contains("Shadow") ? moduleManager.shadow.isLoadFromConfig() : ((Module)featureBase).isLoadFromConfig();
            }
         }
      }
   }

   @Override
   public JsonObject encodeConfig(String str, String str2, boolean flag) {
      JsonObject json = new JsonObject();
      JsonObject json2 = new JsonObject();
      GregorianCalendar gregorianCalendar = new GregorianCalendar();
      DateFormat dateInstance = DateFormat.getDateInstance(2);
      DateFormat timeInstance = DateFormat.getTimeInstance(3);
      json2.addProperty("Config_Version", Integer.valueOf(2));
      json2.addProperty("Config_Name", str);
      json2.addProperty("Config_Date", dateInstance.format(gregorianCalendar.getTime()));
      json2.addProperty("Config_Time", timeInstance.format(gregorianCalendar.getTime()));
      json2.addProperty("Config_Editable", String.valueOf(flag));
      json2.addProperty("Config_Author", str2 != null && str2.length() != 0 ? str2 : "No_Author");
      json.add("Config_Header", json2);
      JsonObject json3 = ModuleSettingsConfigSection.abstractFeature.serialize(JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER, new JsonObject(), var -> shouldSaveProperty((ConfigProperty)var));
      json.add("Configuration", json3);
      return json;
   }

   private static Boolean shouldSaveProperty(ConfigProperty configProperty) {
      if (!(configProperty instanceof Module)) {
         return true;
      } else {
         Module module = (Module)configProperty;
         return !module.isHiddenFromConfig() && !module.getCategorys().special;
      }
   }

   @Override
   public void applyConfig(JsonObject json) {
      JsonElement elem = json.get("Configuration");
      if (elem != null && elem.isJsonObject()) {
         JsonElement elem2 = json.get("Config_Header");
         if (elem2 != null && elem2.isJsonObject()) {
            JsonObject asJsonObject = elem2.getAsJsonObject();
            boolean flag = !asJsonObject.has("Config_Editable") || Boolean.parseBoolean(asJsonObject.get("Config_Editable").getAsString());
            Augustus.getInstance().setConfigEditingEnabled(true);
            ClickGuiScreen.configEditable = flag;
            ClickGuiScreen.protectedConfigCreator = false;
            JsonObject asJsonObject2 = json.get("Configuration").getAsJsonObject();
            ModuleSettingsConfigSection.abstractFeature.deserialize(JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER, asJsonObject2, true, var -> shouldLoadProperty((ConfigProperty)var));
         }
      }
   }

}
