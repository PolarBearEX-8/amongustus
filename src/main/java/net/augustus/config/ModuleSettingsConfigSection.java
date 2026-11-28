package net.augustus.config;

import net.augustus.Augustus;
import net.augustus.settings.Setting;
import net.augustus.settings.property.ConfigProperty;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map.Entry;
import net.augustus.modules.AbstractFeature;
import net.augustus.modules.FeatureBase;
import net.augustus.modules.Module;
import net.augustus.settings.SettingGroup;

public class ModuleSettingsConfigSection extends AbstractFeatureConfigFile {
   public static AbstractFeature abstractFeature = new AbstractFeature(null);

   private JsonObject serializeRestrictedProperties() {
      return this.getCodec().serializeToObject(this.getReadOnlySaveFilter());
   }

   @Override
   public String getSubDirectory() {
      return "settings";
   }

   public ModuleSettingsConfigSection() {
      super(new AbstractFeature[]{abstractFeature});
   }

   private static Boolean isAllowedInReadOnlyConfig(ConfigProperty configProperty) {
      if (configProperty instanceof Module) {
         return true;
      } else if (configProperty instanceof Setting) {
         Setting setting = (Setting)configProperty;
         Module parentModule = setting.getParentModule();
         if (parentModule != null && !parentModule.isLoadFromConfig()) {
            return true;
         } else if (setting.isVisualOnly()) {
            return true;
         } else {
            return setting instanceof SettingGroup ? true : false;
         }
      } else {
         FeatureBase featureBase = configProperty.getFeatureBase();
         if (featureBase instanceof Module) {
            return !((Module)featureBase).isLoadFromConfig();
         } else if (featureBase instanceof Setting) {
            Setting setting2 = (Setting)featureBase;
            Module parentModule2 = setting2.getParentModule();
            if (parentModule2 != null && !parentModule2.isLoadFromConfig()) {
               return true;
            } else {
               return setting2.isVisualOnly() ? true : false;
            }
         } else {
            return true;
         }
      }
   }

   @Override
   public void captureSnapshot() {
      if (Augustus.getInstance().isConfigEditingEnabled()) {
         super.captureSnapshot();
      } else {
         this.mergeRestrictedSnapshot();
      }
   }

   private void mergeRestrictedSnapshot() {
      JsonObject cachedJson2 = (JsonObject)this.getCachedJson();
      JsonObject json = this.serializeRestrictedProperties();
      if (json != null) {
         JsonObject json2;
         if (cachedJson2 != null) {
            json2 = deepMergeJson(cachedJson2, json);
         } else {
            json2 = json;
         }

         this.setCachedJson((JsonElement)json2);
      }
   }

   @Override
   public String getName() {
      return "Modules_Settings";
   }

   private static JsonObject deepMergeJson(JsonObject json, JsonObject json2) {
      JsonObject json3 = new JsonObject();

      for (Entry entry : json.entrySet()) {
         json3.add((String)entry.getKey(), (JsonElement)entry.getValue());
      }

      for (Entry entry2 : json2.entrySet()) {
         String key = (String)entry2.getKey();
         JsonElement value = (JsonElement)entry2.getValue();
         if (json3.has(key) && json3.get(key).isJsonObject() && value.isJsonObject()) {
            json3.add(key, deepMergeJson(json3.get(key).getAsJsonObject(), value.getAsJsonObject()));
         } else {
            json3.add(key, value);
         }
      }

      return json3;
   }

   private UnaryFunction getReadOnlySaveFilter() {
      return obj -> isAllowedInReadOnlyConfig((ConfigProperty)obj);
   }

}
