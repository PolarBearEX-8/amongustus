package client.util;

import client.ClientGlobals;

public class SecondaryStringEntryBridge extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (Sets your custom name)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length == 2) {
         String text2 = textArray[1];
         ClientGlobals.moduleManager.customName.settingCusom.setSelected(text2);
         String text3 = "Set your custom name to: §c§l" + ClientGlobals.moduleManager.customName.settingCusom.getSelected();
         this.applyText(text3);
         ClientGlobals.moduleManager.customName.onModesAction();
      } else {
         String text4 = "§7" + this.getString() + " [§2Name§7]";
         this.applyText(text4);
      }
   }

   public SecondaryStringEntryBridge() {
      super("customName");
   }
}
