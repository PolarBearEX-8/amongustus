package net.augustus.modules.fun;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventKey;
import net.augustus.settings.Setting;
import net.augustus.settings.KeybindSetting;
import net.augustus.settings.TextInputSetting;
import net.augustus.settings.DynamicSettingList;
import net.augustus.events.listeners.KeyListener;
import net.augustus.settings.SettingGroup;

public class Hotkeys extends Module implements KeyListener {
   private final DynamicSettingList hotkeys;

   public Hotkeys() {
       super("Hotkeys", "HeißeSchlüssel", new Color(150, 255, 211), Categorys.FUN);
      this.hotkeys = new DynamicSettingList(this, "Hotkeys", n -> this.createHotkeyEntry(n), 0, 50);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(14);
   }

   @Override
   public void onToggleKey(EventKey event) {

      for (SettingGroup settingGroup : (Iterable<SettingGroup>)(this.hotkeys.getEntries())) {
         KeybindSetting keybindSetting = null;
         TextInputSetting textInputSetting = null;

         for (Setting setting : (Iterable<Setting>)(settingGroup.getChildren())) {
            if (setting instanceof KeybindSetting) {
               keybindSetting = (KeybindSetting)setting;
            } else if (setting instanceof TextInputSetting) {
               textInputSetting = (TextInputSetting)setting;
            }
         }

         if (keybindSetting != null && textInputSetting != null && event.getKeyCode() == keybindSetting.getKeyCode() && mc.thePlayer != null) {
            mc.thePlayer.sendChatMessage(textInputSetting.getValue());
         }
      }
   }

   private SettingGroup createHotkeyEntry(int n) {
      SettingGroup settingGroup = new SettingGroup(this, "Hotkey_" + n, true);
      new KeybindSetting(this, "Key_" + n, settingGroup);
      new TextInputSetting(this, "Text_" + n, "", settingGroup);
      return settingGroup;
   }

}
