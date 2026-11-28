package net.augustus.commands.impl;

import net.augustus.Augustus;
import net.augustus.commands.Command;
import net.augustus.modules.Module;

public class ResetBindsCommand extends Command {

   public void commandAction(String[] args, String rawMessage) {

      for (Module module : (Iterable<Module>)(Augustus.getInstance().getModuleManager().getModules())) {
         module.setKeyCode((byte)0);
      }

      this.sendChat("§cAll module binds were reset");
   }

   public ResetBindsCommand() {
      super("resetbinds");
   }

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (resets all module binds)");
   }

}
