package net.augustus.commands.impl;

import net.augustus.ClientManagers;
import net.augustus.commands.Command;
import net.augustus.modules.Module;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class BindsCommand extends Command {

   public BindsCommand() {
      super("binds");
   }

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (See all bound modules)");
   }

   public void commandAction(String[] args, String rawMessage) {

      for (Module module : (Iterable<Module>)(ClientManagers.moduleManager.getModules())) {
         if (module.getKeyCode() > 0) {
            StringBuilder sb = new StringBuilder().append("§c").append(module.getModuleName()).append("§7 bound to §2");
            String str2;
            if (module.getKeyCode() >= 10000) {
               str2 = Mouse.getButtonName(module.getKeyCode() - 10000);
            } else {
               str2 = Keyboard.getKeyName(module.getKeyCode());
            }

            this.sendChat(sb.append(str2).toString());
         }
      }
   }

}
