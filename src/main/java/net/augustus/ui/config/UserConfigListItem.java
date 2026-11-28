package net.augustus.ui.config;

import net.augustus.Augustus;

public class UserConfigListItem extends AbstractConfigListItem {
   public UserConfigListItem(String configName, String author, String updatedDate, String updatedTime, boolean editable) {
      super(configName, author, updatedDate, updatedTime, editable);
   }

   @Override
   public void loadConfig() {
      Augustus.getInstance().getConfigManager().snapshotAll();
      Augustus.getInstance().getConfigManager().moduleConfigStorage.loadConfig(this.getConfigName(), "configs");
   }
}
