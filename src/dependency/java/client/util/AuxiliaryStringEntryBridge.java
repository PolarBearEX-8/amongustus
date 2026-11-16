package client.util;

import client.Client;
import client.module.Module;
import org.lwjgl.input.Keyboard;

public class AuxiliaryStringEntryBridge extends AuxiliaryStringEntry {
   private void applyModule(int count, Module module, String text) {
      module.unregisterEvent(count);
      String text2 = "§c" + module.getString() + " §7bound to §2" + text.toUpperCase();
      this.applyText(text2);
   }

   public void updateState2() {
      String text = "§7" + this.getString() + " [§cModule§7] [§2Key§7]";
      this.applyText(text);
   }

   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (Binds a module to a key)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length >= 3) {
         for (Module module : (Iterable<Module>)Client.getClient().getModuleManager().getCopyOnWriteArrayList2()) {
            if (textArray[1].equalsIgnoreCase(module.getString())) {
               for (int index = 0; index < 84; index++) {
                  if (Keyboard.getKeyName(index).equalsIgnoreCase(textArray[2])) {
                     String text2 = textArray[2].toUpperCase();
                     this.applyModule(index, module, text2);
                     return;
                  }
               }
            }
         }
      }

      this.updateState2();
   }

   public AuxiliaryStringEntryBridge() {
      super("bind");
   }
}
