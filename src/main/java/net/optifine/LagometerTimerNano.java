package net.optifine;


public class LagometerTimerNano {
   public long timeStartNano = 0L;
   public long timeNano = 0L;

   public void start() {
      if (Lagometer.active && this.timeStartNano == 0L) {
         this.timeStartNano = System.nanoTime();
      }
   }

   private void reset() {
      this.timeNano = 0L;
      this.timeStartNano = 0L;
   }

   public void end() {
      if (Lagometer.active && this.timeStartNano != 0L) {
         this.timeNano = this.timeNano + (System.nanoTime() - this.timeStartNano);
         this.timeStartNano = 0L;
      }
   }

   public static void reset(LagometerTimerNano lagometerTimerNano) {
      lagometerTimerNano.reset();
   }
}
