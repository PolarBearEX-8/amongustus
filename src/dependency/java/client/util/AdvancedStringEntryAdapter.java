package client.util;

import client.ClientGlobals;
import client.setting.ModeSetting;

public class AdvancedStringEntryAdapter extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (set a custom string for clientspoofing)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length > 1) {
         ModeSetting settingCustomString = ClientGlobals.moduleManager.clientSpoofer.settingCustomString;
         String text2 = text.substring(this.getString().length() + 1);
         settingCustomString.setSelected(text2);
         String text3 = "Set spoofer string to: §c§l\"" + ClientGlobals.moduleManager.clientSpoofer.settingCustomString.getSelected() + "\"";
         this.applyText(text3);
      } else {
         String text4 = "§7" + this.getString() + " [§2Custom string§7]";
         this.applyText(text4);
      }
   }

   public AdvancedStringEntryAdapter() {
      super("clientSpoofer");
   }
}
