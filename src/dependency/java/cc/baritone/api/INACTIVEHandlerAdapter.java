package cc.baritone.api;

import cc.baritone.utils.BaritoneManager;
import cc.baritone.utils.DRNHandler;
import cc.baritone.utils.StaticListener;

public abstract class INACTIVEHandlerAdapter implements INACTIVEHandler, DRNHandler {
   public final BaritoneManager baritoneManager;
   public final StaticListener staticListener;

   @Override
   public boolean isActive() {
      return false;
   }

   public INACTIVEHandlerAdapter(BaritoneManager baritoneManager2) {
      this.baritoneManager = baritoneManager2;
      this.staticListener = baritoneManager2.getStaticListener();
   }
}
