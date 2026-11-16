package augustus.modes.movement;

import augustus.modules.movement.NoWeb;
import client.event.EventStateUpdate;
import client.setting.SettingGroup;
import client.util.CommonUpdateStateListenerAdapter;

public class NoWebNcpMode extends CommonUpdateStateListenerAdapter {
   @Override
   public void onUpdateState(EventStateUpdate event) {
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
   }

   public NoWebNcpMode(NoWeb noWeb, SettingGroup settingGroup) {
      super(noWeb, settingGroup, "Ignore");
   }
}
