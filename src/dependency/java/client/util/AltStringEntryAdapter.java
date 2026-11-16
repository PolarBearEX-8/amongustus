package client.util;

import client.ClientGlobals;

public class AltStringEntryAdapter extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (Get or set the blockID for custom mode)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length > 1) {
         if (textArray[1].equalsIgnoreCase("set") && textArray.length >= 3) {
            int count = -1;

            try {
               count = Integer.parseInt(textArray[2]);
            } catch (Exception exception) {
               System.err.println("No Integer");
            }

            if (count != -1) {
               double offset = (double)count;
               ClientGlobals.moduleManager.fucker.settingCustomID.applyDouble(offset);
               String text2 = "Set Fucker ID to §2" + (int)ClientGlobals.moduleManager.fucker.settingCustomID.getValue();
               this.applyText(text2);
               return;
            }
         } else if (textArray[1].equalsIgnoreCase("get")) {
            String text3 = "BlockID: §2" + (int)ClientGlobals.moduleManager.fucker.settingCustomID.getValue();
            this.applyText(text3);
            return;
         }
      }

      this.updateState2();
   }

   public void updateState2() {
      String text = "§7" + this.getString() + " [§cset/get§7] [§2BlockID§7]";
      this.applyText(text);
   }

   public AltStringEntryAdapter() {
      super("fucker");
   }
}
