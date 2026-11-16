package client.util;

import net.minecraft.client.gui.GuiScreen;

public class ExtraStringEntryAdapter extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + " §7(Copys ingame name to clipboard)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (MinecraftAccessor.minecraft.thePlayer != null) {
         String text2 = MinecraftAccessor.minecraft.thePlayer.getText();
         GuiScreen.applyText4(text2);
         String text3 = "Your name: §c" + text2;
         this.applyText(text3);
      }
   }

   public ExtraStringEntryAdapter() {
      super("ign");
   }
}
