package client.util;

import client.ClientGlobals;

public class DefaultStringEntryAdapter extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (set your a custom skin)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length == 3) {
         if (textArray[1].equalsIgnoreCase("set")) {
            String text2 = textArray[2];
            if (text2.length() < 3 || text2.contains("§") || text2.contains("#") || text2.contains(" ") || text2.length() >= 17) {
               String text3 = "No valid minecraft username";
               this.applyText(text3);
            } else if (MinecraftAccessor.minecraft.thePlayer.getNetworkPlayerInfo() != null) {
               ClientGlobals.moduleManager.customSkin.settingCustomName.setSelected(text2);
               MinecraftAccessor.minecraft.thePlayer.getNetworkPlayerInfo().state6 = false;
               MinecraftAccessor.minecraft.thePlayer.getNetworkPlayerInfo().updateState();
               String text4 = "Skin successfully changed";
               this.applyText(text4);
            } else {
               String text5 = "Internal error. Please try again on an other server.";
               this.applyText(text5);
            }
         } else {
            String text6 = "§7" + this.getString() + " [§cset/get§7] [§2Playername§7]";
            this.applyText(text6);
         }
      } else if (textArray.length == 2 && textArray[1].equalsIgnoreCase("get")) {
         String text7 = "CustomSkinName: §c" + ClientGlobals.moduleManager.customSkin.settingCustomName.getSelected();
         this.applyText(text7);
      } else {
         String text8 = "§7" + this.getString() + " [§cset/get§7] [§2Playername§7]";
         this.applyText(text8);
      }
   }

   public DefaultStringEntryAdapter() {
      super("customSkin");
   }
}
