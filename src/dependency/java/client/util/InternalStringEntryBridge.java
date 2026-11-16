package client.util;

import client.ClientGlobals;
import client.module.Module;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class InternalStringEntryBridge extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (See all bound modules)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {

      for (Module module : (Iterable<Module>)(Iterable<?>)ClientGlobals.moduleManager.getCopyOnWriteArrayList2()) {
         if (module.getInt() > 0) {
            String text2 = "§c"
               + module.getDescription()
               + "§7 bound to §2"
               + (module.getInt() >= 10000 ? Mouse.getButtonName(module.getInt() - 10000) : Keyboard.getKeyName(module.getInt()));
            this.applyText(text2);
         }
      }
   }

   public InternalStringEntryBridge() {
      super("binds");
   }
}
