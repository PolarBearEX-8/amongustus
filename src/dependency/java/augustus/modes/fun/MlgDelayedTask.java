package augustus.modes.fun;

import augustus.modules.fun.MLG;

public class MlgDelayedTask {
   public long timestamp;
   public int count;
   public Runnable runnable;
   public final MLG mLG;

   public MlgDelayedTask(MLG mLG2, long value, int count2, Runnable task) {
      this.mLG = mLG2;
      this.count = count2;
      this.runnable = task;
      this.timestamp = value;
   }

   public void processAction() {
      MLG.getStringArray();
      if (System.currentTimeMillis() - this.timestamp > (long)this.count) {
         MLG.getCopyOnWriteArrayList(this.mLG).remove(this);
      } else {
         this.runnable.run();
      }
   }
}
