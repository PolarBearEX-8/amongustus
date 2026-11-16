package client.util;

import client.ClientGlobals;

public class CoreStringEntryAdapter extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (Sets your spam message)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length > 1) {
         String text2 = "";

         for (int index = 0; index < textArray.length; index++) {
            if (index > 0) {
               text2 = text2 + textArray[index] + " ";
            }
         }

         ClientGlobals.moduleManager.spammer.settingSpammMessage.setSelected(text2);
         String text3 = "Set your custom spam message to: §c§l" + ClientGlobals.moduleManager.spammer.settingSpammMessage.getSelected();
         this.applyText(text3);
      } else {
         String text4 = "§7" + this.getString() + " [§2Custom message§7]";
         this.applyText(text4);
      }
   }

   public CoreStringEntryAdapter() {
      super("spammer");
   }
}
