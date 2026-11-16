package client.util;

import client.ClientGlobals;
import client.module.Category;
import client.module.Module;

public class PrimaryStringEntryAdapter extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      boolean flag = textArray.length == 2 && textArray[1].equalsIgnoreCase("norender");

      for (Object moduleObj : ClientGlobals.moduleManager.getCopyOnWriteArrayList2()) {
         Module module = (Module)moduleObj;
         if (module.isToggled() && (module.getCategory() != Category.RENDER || !flag)) {
            module.processBoolean(false, false);
         }
      }

      ClientGlobals.moduleManager.updateState();
   }

   public PrimaryStringEntryAdapter() {
      super("panic");
   }
}
