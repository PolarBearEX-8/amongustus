package net.labymod;

import net.augustus.cosmetics.MoreCosmeticsCore;

public class ConfigAccessor {

   public static Object get(ModelConfig modelConfig, ModelData modelData) {

      try {
         String[] strings = modelConfig.key.split("\\.");
         if (strings.length == 1) {
            return SettingOverlay.class.getDeclaredField(modelConfig.key).get(modelData);
         }

         if (strings.length == 3) {
            Object obj = ((Object[])ModelData.class.getDeclaredField(strings[0]).get(modelData))[Integer.valueOf(strings[1])];
            return SettingOverlay.class.getDeclaredField(strings[2]).get(obj);
         }
      } catch (Exception e) {
         MoreCosmeticsCore.logInfo("Error while getting config value: " + modelConfig.key + " (" + modelConfig.name + ")");
         MoreCosmeticsCore.logError(e);
      }

      return null;
   }

   public static void set(ModelConfig modelConfig, ModelData modelData, Object obj) {

      try {
         String[] strings = modelConfig.key.split("\\.");
         if (strings.length == 1) {
            SettingOverlay.class.getDeclaredField(modelConfig.key).set(modelData, obj);
            return;
         }

         if (strings.length == 3) {
            Object obj2 = ((Object[])ModelData.class.getDeclaredField(strings[0]).get(modelData))[Integer.valueOf(strings[1])];
            SettingOverlay.class.getDeclaredField(strings[2]).set(obj2, obj);
         }
      } catch (Exception e) {
         MoreCosmeticsCore.logInfo("Error while setting config value: " + modelConfig.key + " (" + modelConfig.name + ")");
         MoreCosmeticsCore.logError(e);
      }
   }
}
