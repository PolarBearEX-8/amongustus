package client.util;

import java.util.concurrent.Callable;

public class SpecialCustom implements Callable {
   public final VanillaRunnable vanillaRunnable;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public SpecialCustom(VanillaRunnable vanillaRunnable2) {
      this.vanillaRunnable = vanillaRunnable2;
   }

   public String getText() throws Exception {
      return this.vanillaRunnable.uNKNOWNManager.enabled ? this.vanillaRunnable.uNKNOWNManager.getText() : "N/A (disabled)";
   }
}
