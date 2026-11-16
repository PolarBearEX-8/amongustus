package client.setting;

import augustus.modules.misc.Disabler;
import client.module.Module;

public class DisablerIncomingPacketsSetting extends MultiSelectSetting {
   public final Disabler disabler;

   @Override
   public String getText4() {
      return "PacketsToCancel";
   }

   public DisablerIncomingPacketsSetting(Disabler disabler2, Module module, String text, NamedOption[] namedOptionArray, SettingGroup settingGroup) {
      super(module, text, namedOptionArray, settingGroup);
      this.disabler = disabler2;
   }
}
