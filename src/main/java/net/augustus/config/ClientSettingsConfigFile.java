package net.augustus.config;

import net.augustus.modules.AbstractFeature;

public class ClientSettingsConfigFile extends AbstractFeatureConfigFile {
   public static final AbstractFeature abstractFeature = new AbstractFeature(null);

   @Override
   public String getSubDirectory() {
      return "settings";
   }

   @Override
   public String getName() {
      return "Client_Settings";
   }

   public ClientSettingsConfigFile() {
      super(new AbstractFeature[]{abstractFeature});
   }

   @Override
   public boolean alwaysReserializeOnSave() {
      return true;
   }
}
