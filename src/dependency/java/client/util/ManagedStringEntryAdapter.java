package client.util;

import client.config.ClickPatternManager;

public class ManagedStringEntryAdapter extends AuxiliaryStringEntry {
   public static final ClickPatternManager clickPatternManager = new ClickPatternManager();

   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (Start or stop the click recording)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length > 1) {
         if (textArray[1].equalsIgnoreCase("start")) {
            if (clickPatternManager.isEnabled2()) {
               String text2 = "Started Recording... Please press sneak key to record";
               this.applyText(text2);
            } else {
               String text3 = "You are already recording";
               this.applyText(text3);
            }

            return;
         }

         if (textArray[1].equalsIgnoreCase("stop")) {
            if (textArray.length == 3) {
               String text4 = textArray[2];
               if (clickPatternManager.matchesText(text4)) {
                  String text5 = "Saved recording successfully to " + textArray[2] + ".json!";
                  this.applyText(text5);
               } else {
                  String text6 = "Something went wrong while recording/saving. Please try again!";
                  this.applyText(text6);
               }
            } else {
               String text7 = "§7" + this.getString() + " [§cstop§7] [§2name§7]";
               this.applyText(text7);
            }

            return;
         }

         if (textArray[1].equalsIgnoreCase("abort")) {
            clickPatternManager.updateState();
            String text8 = "Recording successfully canceled.";
            this.applyText(text8);
            return;
         }
      }

      String text9 = "§7" + this.getString() + " [§cstart/stop/abort§7] ";
      this.applyText(text9);
   }

   public ManagedStringEntryAdapter() {
      super("clicker");
   }
}
