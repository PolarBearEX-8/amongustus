package augustus.modes.movement;

import augustus.modules.movement.Jesus;
import client.setting.SettingGroup;
import client.util.CoreStringEntry;

public class JesusAACMode extends CoreStringEntry {
   @Override
   public void updateState() {
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
   }

   public JesusAACMode(Jesus jesus, SettingGroup settingGroup) {
      super(jesus, settingGroup, "Solid");
   }
}
