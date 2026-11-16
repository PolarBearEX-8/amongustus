package client.util;

import client.Client;
import client.module.Module;

public class EnhancedStringEntryAdapter extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (Toggles a module)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length > 1) {
         for (Module module : (Iterable<Module>)Client.getClient().getModuleManager().getCopyOnWriteArrayList2()) {
            if (textArray[1].equalsIgnoreCase(module.getString()) && !module.isEnabled()) {
               module.onReset();
               String text2 = "§c" + module.getString() + " §7toggled " + (module.isToggled() ? "§2on" : "§4off");
               this.applyText(text2);
               return;
            }
         }
      } else {
         String text3 = "§7" + this.getString() + " [§2Module§7]";
         this.applyText(text3);
      }
   }

   public EnhancedStringEntryAdapter() {
      super("t");
   }
}
