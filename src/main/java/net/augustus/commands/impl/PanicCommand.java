package net.augustus.commands.impl;

import net.augustus.ClientManagers;
import net.augustus.commands.Command;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;

public class PanicCommand extends Command {

   public PanicCommand() {
      super("panic");
   }

   public void commandAction(String[] args, String rawMessage) {
      boolean flag = args.length == 2 && args[1].equalsIgnoreCase("norender");

      for (Module module : (Iterable<Module>)(ClientManagers.moduleManager.getModules())) {
         if (module.isToggled() && (module.getCategorys() != Categorys.RENDER || !flag)) {
            module.setToggled(false, false);
         }
      }

      ClientManagers.moduleManager.sortModules();
   }

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (disables all modules, except for visuals when norender is specified (.panic norender))");
   }

}
