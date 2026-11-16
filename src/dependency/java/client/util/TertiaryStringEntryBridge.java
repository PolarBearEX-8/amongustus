package client.util;

import client.ClientGlobals;
import client.setting.ModeSetting;

public class TertiaryStringEntryBridge extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (set the death message)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length > 1) {
         StringBuilder builder = new StringBuilder();

         for (int index = 1; index < textArray.length; index++) {
            builder.append(textArray[index]).append(" ");
         }

         builder.setLength(Math.max(0, builder.length() - 1));
         ModeSetting settingMessage = ClientGlobals.moduleManager.deathCommand.settingMessage;
         String text2 = builder.toString();
         settingMessage.setSelected(text2);
      }
   }

   public TertiaryStringEntryBridge() {
      super("deathmessage");
   }
}
