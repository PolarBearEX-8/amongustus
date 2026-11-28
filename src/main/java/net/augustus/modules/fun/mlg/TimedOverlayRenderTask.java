package net.augustus.modules.fun.mlg;

import net.augustus.modules.fun.MLG;
import net.augustus.modules.fun.Spammer;

public class TimedOverlayRenderTask {
   private long startTimeMs;
   private int durationMs;
   private Runnable renderAction;
   public final MLG mlg;

   public TimedOverlayRenderTask(MLG mlg2, long l, int n, Runnable runnable) {
      this.mlg = mlg2;
      this.durationMs = n;
      this.renderAction = runnable;
      this.startTimeMs = l;
   }

   public void renderOrExpire() {
      if (System.currentTimeMillis() - this.startTimeMs > (long)this.durationMs) {
         MLG.getActiveOverlayTasks(this.mlg).remove(this);
      } else {
         this.renderAction.run();
      }
   }
}
