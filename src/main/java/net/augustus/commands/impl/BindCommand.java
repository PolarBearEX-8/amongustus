package net.augustus.commands.impl;

import net.augustus.Augustus;
import net.augustus.commands.Command;
import net.augustus.modules.FeatureBase;
import net.augustus.modules.Module;
import org.lwjgl.input.Keyboard;

public class BindCommand extends Command {

   public void sendUsageMessage() {
      this.sendChat("§7" + this.getCommand() + " [§cModule§7] [§2Key§7]");
   }

   public BindCommand() {
      super("bind");
   }

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (Binds a module to a key)");
   }

   public void commandAction(String[] args, String rawMessage) {
      if (args.length >= 3) {
         for (Module module : (Iterable<Module>)(Augustus.getInstance().getModuleManager().getModules())) {
            if (args[1].equalsIgnoreCase(((FeatureBase)module).getName())) {
               for (int i = 0; i < 84; i++) {
                  if (Keyboard.getKeyName(i).equalsIgnoreCase(args[2])) {
                     this.bindModuleKey(i, module, args[2].toUpperCase());
                     return;
                  }
               }
            }
         }
      }

      this.sendUsageMessage();
   }

   private void bindModuleKey(int n, Module module, String str) {
      module.setKeyCode(n);
      this.sendChat("§c" + ((FeatureBase)module).getName() + " §7bound to §2" + str.toUpperCase());
   }

}
