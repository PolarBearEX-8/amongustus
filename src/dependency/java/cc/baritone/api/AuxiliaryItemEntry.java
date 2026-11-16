package cc.baritone.api;

import cc.baritone.utils.BaritoneManager;
import cc.baritone.utils.HeuristicRecorder;
import cc.baritone.utils.StaticListener;

public class AuxiliaryItemEntry implements HeuristicRecorder {
   public final BaritoneManager baritoneManager;
   public final StaticListener staticListener;

   public AuxiliaryItemEntry(BaritoneManager baritoneManager2) {
      this.baritoneManager = baritoneManager2;
      this.staticListener = baritoneManager2.getStaticListener();
   }
}
