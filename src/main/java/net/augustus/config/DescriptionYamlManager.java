package net.augustus.config;

import net.augustus.ClientManagers;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.Setting;
import net.augustus.settings.WeaponFilterSettings;
import net.augustus.settings.property.ConfigProperty;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import net.augustus.modules.FeatureBase;
import net.augustus.modules.Module;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;

public class DescriptionYamlManager {
   private final Yaml yaml;

   public void saveToFile(String str) {
      LinkedHashMap map = new LinkedHashMap();

      for (Module module : (Iterable<Module>)(ClientManagers.moduleManager.getModules())) {
         String str2 = module.getCategorys().name().toLowerCase(Locale.ROOT);
         String str3 = ((FeatureBase)module).getName().toLowerCase(Locale.ROOT);
         if (!map.containsKey(str2)) {
            map.put(str2, new LinkedHashMap());
         }

         Map map2 = (Map)map.get(str2);
         LinkedHashMap map3 = new LinkedHashMap();
         map3.put("description", module.getDescription() != null && !module.getDescription().isEmpty() ? module.getDescription() : "");
         LinkedHashMap map4 = new LinkedHashMap();
         if (!module.equals(ClientManagers.moduleManager.motionModifier) && !module.equals(ClientManagers.moduleManager.clientAntiCheat)) {
            for (ConfigProperty configProperty : (Iterable<ConfigProperty>)(((FeatureBase)module).getProperties())) {
               if (configProperty instanceof Setting && !(configProperty instanceof RandomizedRangeSettings) && !(configProperty instanceof BlurShadowSettings) && !(configProperty instanceof WeaponFilterSettings)) {
                  Setting setting = (Setting)configProperty;
                  map4.put(setting.getName().toLowerCase(Locale.ROOT), setting.getDescription() != null && !setting.getDescription().isEmpty() ? setting.getDescription() : "");
               }
            }
         }

         map3.put("settings", map4);
         map2.put(str3, map3);
      }

      try (FileWriter fileWriter = new FileWriter(str)) {
         this.yaml.dump(map, fileWriter);
      } catch (IOException e) {
         System.err.println("Failed to save descriptions to YAML: " + e.getMessage());
         e.printStackTrace();
      }
   }

   public void loadAndApply(String str) {
      this.applyDescriptions(this.loadFromFile(str));
   }

   public DescriptionYamlManager() {
      DumperOptions dumperOptions = new DumperOptions();
      dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK);
      dumperOptions.setPrettyFlow(true);
      this.yaml = new Yaml(dumperOptions);
   }

   public Map loadFromFile(String str) {

      try (FileInputStream fileInputStream = new FileInputStream(str)) {
         Map map = (Map)this.yaml.load(fileInputStream);
         HashMap map2 = new HashMap();

         for (Entry entry : (Iterable<Entry>)(map.entrySet())) {
            String key = (String)entry.getKey();

            for (Entry entry2 : (Iterable<Entry>)(((Map)entry.getValue()).entrySet())) {
               String key2 = (String)entry2.getKey();
               Map value2 = (Map)entry2.getValue();
               if (value2.containsKey("description")) {
                  String str2 = String.valueOf(value2.get("description")).trim();
                  if (!str2.isEmpty()) {
                     map2.put((key + "." + key2).toLowerCase(Locale.ROOT), str2);
                  }
               }

               if (value2.containsKey("settings")) {

                  for (Entry entry3 : (Iterable<Entry>)(((Map)value2.get("settings")).entrySet())) {
                     String key3 = (String)entry3.getKey();
                     String str4 = ((String)entry3.getValue()).trim();
                     if (!str4.isEmpty()) {
                        map2.put((key + "." + key2 + "." + key3).toLowerCase(Locale.ROOT), str4);
                     }
                  }
               }
            }
         }

         return map2;
      } catch (IOException e) {
         System.err.println("Failed to load descriptions from YAML: " + e.getMessage());
         e.printStackTrace();
         return new HashMap();
      }
   }

   public void applyDescriptions(Map map) {

      for (Module module : (Iterable<Module>)(ClientManagers.moduleManager.getModules())) {
         String str = module.getCategorys().name().toLowerCase(Locale.ROOT) + "." + ((FeatureBase)module).getName().toLowerCase(Locale.ROOT);
         if (map.containsKey(str)) {
            module.setDescription((String)map.get(str));
         }

         for (ConfigProperty configProperty : (Iterable<ConfigProperty>)(((FeatureBase)module).getProperties())) {
            if (configProperty instanceof Setting) {
               Setting setting = (Setting)configProperty;
               String str3 = str + "." + setting.getName().toLowerCase(Locale.ROOT);
               if (map.containsKey(str3)) {
                  setting.setDescription((String)map.get(str3));
               }
            }
         }
      }
   }




}
