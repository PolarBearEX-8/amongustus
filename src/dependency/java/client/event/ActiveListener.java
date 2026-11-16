package client.event;

import client.setting.SettingGroup;

public interface ActiveListener {
   void setEnabled(boolean enabled);

   SettingGroup getSettingGroup();
}
