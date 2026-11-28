package net.augustus.commands.impl;

import net.augustus.Augustus;
import net.augustus.commands.Command;
import net.augustus.modules.FeatureBase;
import net.augustus.modules.Module;

public class ToggleCommand extends Command {

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (Toggles a module)");
   }

   public ToggleCommand() {
      super("t");
   }

   public void commandAction(String[] args, String rawMessage) {
      if (args.length > 1) {
         for (Module module : (Iterable<Module>)(Augustus.getInstance().getModuleManager().getModules())) {
            if (args[1].equalsIgnoreCase(((FeatureBase)module).getName()) && !module.isHiddenInGui()) {
               module.toggle();
               StringBuilder sb2 = new StringBuilder().append("§c").append(((FeatureBase)module).getName()).append(" §7toggled ");
               String str2;
               if (module.isToggled()) {
                  str2 = "§2on";
               } else {
                  str2 = "§4off";
               }

               this.sendChat(sb2.append(str2).toString());
               return;
            }
         }
      } else {
         this.sendChat("§7" + this.getCommand() + " [§2Module§7]");
      }
   }


}
