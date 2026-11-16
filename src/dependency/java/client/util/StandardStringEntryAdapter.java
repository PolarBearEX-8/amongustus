package client.util;

import client.ClientGlobals;
import client.setting.ModeSetting;

public class StandardStringEntryAdapter extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (Set killmessage for module KillMessage)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length > 1) {
         StringBuilder builder = new StringBuilder();

         for (int index = 1; index < textArray.length; index++) {
            builder.append(textArray[index]);
            if (index < textArray.length - 1) {
               builder.append(" ");
            }
         }

         ModeSetting settingKillMessageCustom = ClientGlobals.moduleManager.killMessage.settingKillMessageCustom;
         String text2 = String.valueOf(builder);
         settingKillMessageCustom.setSelected(text2);
         String text3 = "§7Sets killmessage!";
         this.applyText(text3);
      } else {
         String text4 = "§7" + this.getString() + " [§2Message§7]";
         this.applyText(text4);
      }
   }

   public StandardStringEntryAdapter() {
      super("killmessage");
   }
}
