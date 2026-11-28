package net.augustus.commands.impl;

import net.augustus.commands.Command;
import net.minecraft.client.gui.GuiScreen;

public class IgnCommand extends Command {

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + " §7(Copies in-game name to clipboard)");
   }

   public void commandAction(String[] args, String rawMessage) {
      if (mc.thePlayer != null) {
         String name = mc.thePlayer.getName();
         GuiScreen.setClipboardString(name);
         this.sendChat("Your name: §c" + name);
      }
   }

   public IgnCommand() {
      super("ign");
   }

}
