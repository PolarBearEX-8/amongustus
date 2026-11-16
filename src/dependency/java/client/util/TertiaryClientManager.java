package client.util;

public class TertiaryClientManager extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (send messages to other augustus/aqua users (unsafe))";
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
         MsgManager.applyTextByText(builder.toString());
      } else {
         String text2 = "§7" + this.getString() + " [§2+message§7]";
         this.applyText(text2);
      }
   }

   public TertiaryClientManager() {
      super("aqua");
   }
}
