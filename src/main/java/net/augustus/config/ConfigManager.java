package net.augustus.config;

import net.augustus.settings.Setting;
import java.util.ArrayList;

public class ConfigManager {
   public static final ArrayList CONFIG_FILES = new ArrayList();
   public final ModuleSettingsConfigSection moduleSettingsConfig;
   public final AccountConfigStorage accountConfig;
   public final ProxyConfigStorage proxyConfig;
   public final ClientSettingsConfigFile clientSettingsConfig;
   public final ModuleConfigStorage moduleConfigStorage;

   public ConfigManager() {
      super();
      this.moduleSettingsConfig = new ModuleSettingsConfigSection();
      this.moduleConfigStorage = new ModuleConfigStorage();
      this.proxyConfig = new ProxyConfigStorage();
      this.accountConfig = new AccountConfigStorage();
      this.clientSettingsConfig = new ClientSettingsConfigFile();
   }

   public void loadAll() {
      ((java.util.List<JsonConfigFile>)CONFIG_FILES).forEach((JsonConfigFile jsonConfigFile) -> {
         if (jsonConfigFile.isAutoManaged()) {
            jsonConfigFile.load();
         }
      });
   }

   public void saveAll() {
      ((java.util.List<JsonConfigFile>)CONFIG_FILES).forEach((JsonConfigFile jsonConfigFile) -> saveConfigFile(jsonConfigFile));
   }

   private static void saveConfigFile(JsonConfigFile jsonConfigFile) {
      if (jsonConfigFile.isAutoManaged()) {
         jsonConfigFile.save();
      }
   }

   public void snapshotAll() {
      ((java.util.List<JsonConfigFile>)CONFIG_FILES).forEach((JsonConfigFile jsonConfigFile) -> snapshotConfigFile(jsonConfigFile));
   }

   private static void snapshotConfigFile(JsonConfigFile jsonConfigFile) {
      if (jsonConfigFile.isAutoManaged()) {
         jsonConfigFile.captureSnapshot();
      }
   }

}
