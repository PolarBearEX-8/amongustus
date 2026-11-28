package net.augustus.modules.misc;

import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.SelectableOption;
import net.augustus.modules.Module;
import net.augustus.settings.SettingGroup;

public class DisablerOutgoingPacketCancelSetting extends MultiOptionSetting {
   public final Disabler disabler;

   public DisablerOutgoingPacketCancelSetting(Disabler disabler2, Module module, String str, SelectableOption[] selectableOptions, SettingGroup doubleValue) {
       super(module, str, selectableOptions, doubleValue);
       this.disabler = disabler2;
   }

   @Override
   public String getDisplayName() {
      return "PacketsToCancel";
   }
}
