package net.augustus.modules.combat;

import net.augustus.settings.KeybindSetting;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.modules.Module;
import net.augustus.settings.SettingGroup;

public class KBDisplacementHotkeyGroup extends SettingGroup {
   public KeybindSetting keybind;
   private RandomizedRangeSettings degreeRange;

   public KBDisplacementHotkeyGroup(Module module, String str) {
       super(module, str);
   }

   public KBDisplacementHotkeyGroup(Module module, String str, SettingGroup doubleValue) {
       super(module, str, doubleValue);
   }

   public KBDisplacementHotkeyGroup(Module module, String str, boolean flag) {
       super(module, str, flag);
   }

   public KBDisplacementHotkeyGroup(Module module, String str, boolean flag, SettingGroup doubleValue) {
       super(module, str, flag, doubleValue);
   }

   public void setKeybind(KeybindSetting keybindSetting) {
      if (this.keybind == null) {
         this.keybind = keybindSetting;
      }
   }

   public void setDegreeRange(RandomizedRangeSettings randomizedRangeSettings) {
      if (this.degreeRange == null) {
         this.degreeRange = randomizedRangeSettings;
      }
   }

   public static RandomizedRangeSettings getDegreeRange(KBDisplacementHotkeyGroup group) {
      return group.degreeRange;
   }
}
