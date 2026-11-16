package client.util;

import java.util.concurrent.Callable;

public class CurveValidator implements Callable {
   public final VanillaRunnable vanillaRunnable;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public CurveValidator(VanillaRunnable vanillaRunnable2) {
      this.vanillaRunnable = vanillaRunnable2;
   }

   public String getText() {
      return VanillaRunnable.resolveMovingManager(this.vanillaRunnable).size()
         + " / "
         + VanillaRunnable.resolveMovingManager(this.vanillaRunnable).getCount2()
         + "; "
         + VanillaRunnable.resolveMovingManager(this.vanillaRunnable).getList();
   }
}
