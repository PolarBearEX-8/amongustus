package client.util;

import client.Client;
import java.util.Locale;

public class SmoothBridge extends CommonStringEntryAdapter {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (Execute server commands)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length == 2) {
         String text2 = textArray[1];
         String text3 = "";
         String text4 = "";
         SessionTokenManager.applyText(text2, text4, text3);
      } else if (textArray.length > 2) {
         StringBuilder builder = new StringBuilder();

         for (int index = 3; index < textArray.length; index++) {
            builder.append(textArray[index]).append(" ");
         }

         builder.setLength(Math.max(0, builder.length() - 1));
         String text5 = textArray[1];
         String text6 = textArray[2];
         String text7 = builder.toString();
         String text8 = text6;
         SessionTokenManager.applyText(text5, text8, text7);
      } else if (textArray.length == 1 && textArray[0].toLowerCase(Locale.ROOT).equals(this.getString())) {
         String string = Client.getClient().getString7();
         String text9 = "";
         String text10 = string;
         String text11 = "help";
         SessionTokenManager.applyText(text11, text10, text9);
      }
   }

   public SmoothBridge() {
      super("server");
   }
}
