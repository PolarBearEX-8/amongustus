package net.augustus.settings.property;

import net.augustus.settings.DynamicSettingList;
import net.augustus.settings.Setting;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import net.augustus.modules.FeatureBase;
import net.augustus.modules.fun.Spammer;
import net.augustus.settings.SettingGroup;

public class SettingGroupListProperty extends AbstractConfigProperty {
   private final IndexedSettingFactory settingFactory;
   private final FeatureBase featureBase2;

   private void deserializeNestedSettings(SettingGroup doubleValue, ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {

      for (Setting setting : (Iterable<Setting>)(doubleValue.getChildren())) {
         setting.deserialize(configSerializerProvider, json, flag, unaryFunction);
         if (setting instanceof SettingGroup && json.has(setting.getName())) {
            JsonObject asJsonObject = json.getAsJsonObject(setting.getName());
            if (asJsonObject.isJsonObject()) {
               this.deserializeNestedSettings((SettingGroup)setting, configSerializerProvider, asJsonObject, flag, unaryFunction);
            }
         }
      }
   }

   private int extractUniqueId(String str) {

      try {
         String str2 = str.replace("Groupygroupy", "");
         int n = str2.lastIndexOf(95);
         if (n != -1 && n < str2.length() - 1) {
            return Integer.parseInt(str2.substring(n + 1));
         }
      } catch (NumberFormatException e) {
      }

      throw new RuntimeException("Invalid settings name. _ is missing: " + str);
   }

   @Override
   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      super.serialize(configSerializerProvider, json, unaryFunction);
      JsonObject json2 = new JsonObject();
      List value2 = (List)this.getValue();

      for (int i = 0; i < value2.size(); i++) {
         Setting setting = (Setting)value2.get(i);
         if (setting.getParentModule() instanceof Spammer) {
            boolean flag = false;
         }

         JsonObject json3 = new JsonObject();
         int n = this.extractUniqueId(setting.getName());
         json3.addProperty("UniqueId", n);
         this.serializeEntry(configSerializerProvider, setting, json3, unaryFunction);
         json2.add("Entry_" + i, json3);
      }

      json.add(this.getName(), json2);
      return json;
   }

   private void serializeNestedSettings(SettingGroup doubleValue, ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {

      for (Setting setting : (Iterable<Setting>)(doubleValue.getChildren())) {
         if (setting != null) {
            JsonObject json2 = new JsonObject();

            for (ConfigProperty configProperty : (Iterable<ConfigProperty>)(setting.getProperties())) {
               if (unaryFunction == null || (Boolean)unaryFunction.apply(configProperty)) {
                  configProperty.serialize(configSerializerProvider, json2, unaryFunction);
               }
            }

            if (setting instanceof SettingGroup) {
               this.serializeNestedSettings((SettingGroup)setting, configSerializerProvider, json2, unaryFunction);
            }

            json.add(setting.getName(), json2);
         }
      }
   }

   public SettingGroupListProperty(FeatureBase featureBase3, String str, List list, IndexedSettingFactory indexedSettingFactory) {
       super(featureBase3, str, list, (new ArrayList()));
       ArrayList list2 = new ArrayList();
      this.settingFactory = indexedSettingFactory;
      this.featureBase2 = featureBase3;
   }

   private void serializeEntry(ConfigSerializerProvider configSerializerProvider, FeatureBase featureBase3, JsonObject json, UnaryFunction unaryFunction) {
      JsonObject json2 = new JsonObject();

      for (ConfigProperty configProperty : (Iterable<ConfigProperty>)(featureBase3.getProperties())) {
         if (unaryFunction == null || (Boolean)unaryFunction.apply(configProperty)) {
            configProperty.serialize(configSerializerProvider, json2, unaryFunction);
         }
      }

      if (featureBase3 instanceof SettingGroup) {
         this.serializeNestedSettings((SettingGroup)featureBase3, configSerializerProvider, json2, unaryFunction);
      }

      json.add(featureBase3.getName(), json2);
   }

   public void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {
      if (!json.has(this.getName())) {
         if (flag) {
            this.applyValue(this.getDefaultValue());
         }
      } else {
         JsonElement elem = json.get(this.getName());
         if (elem.isJsonObject()) {
            JsonObject asJsonObject = elem.getAsJsonObject();
            ArrayList list = new ArrayList();
            int n = 0;

            for (Entry entry : asJsonObject.entrySet()) {
               JsonElement value2 = (JsonElement)entry.getValue();
               if (value2.isJsonObject()) {
                  JsonObject json2 = (JsonObject)value2;
                  int asInt = json2.get("UniqueId").getAsInt();
                  if (asInt > n) {
                     n = asInt;
                  }

                  Setting setting = this.settingFactory.createSetting(asInt);
                  setting.deserialize(configSerializerProvider, json2, flag, unaryFunction);
                  if (setting instanceof SettingGroup && json2.has(setting.getName())) {
                     SettingGroup settingGroup = (SettingGroup)setting;
                     JsonObject asJsonObject2 = json2.getAsJsonObject(setting.getName());
                     if (asJsonObject2.isJsonObject()) {
                        this.deserializeNestedSettings(settingGroup, configSerializerProvider, asJsonObject2, flag, unaryFunction);
                     }
                  }

                  list.add(setting);
               }
            }

            this.applyValue(list);
            if (this.featureBase2 instanceof DynamicSettingList) {
               DynamicSettingList featureBase22 = (DynamicSettingList)this.featureBase2;
               featureBase22.setNextEntryId(n + 1);
               featureBase22.reindexEntryLabels();
            }
         }
      }
   }

}
