package client.util;

import client.ClientGlobals;

public class CustomStringEntryBridge extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + " §7(Set custom arraylist suffix)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length == 2) {
         String text2 = textArray[1];
         ClientGlobals.moduleManager.itemList.settingCustomSuffix.setSelected(text2);
         String text3 = "§7Set suffix to " + textArray[1];
         this.applyText(text3);
      } else {
         String text4 = "§7" + this.getString() + " [§2Suffix§7]";
         this.applyText(text4);
      }
   }

   public CustomStringEntryBridge() {
      super("arraylistsuffix");
   }
}
