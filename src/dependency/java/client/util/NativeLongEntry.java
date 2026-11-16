package client.util;

public class NativeLongEntry {
   public long timestamp2 = 0L;
   public long timestamp = 0L;

   public void updateState() {
      if (MBSManager.enabled && this.timestamp2 != 0L) {
         this.timestamp = this.timestamp + (System.nanoTime() - this.timestamp2);
         this.timestamp2 = 0L;
      }
   }

   public void nanoTime() {
      if (MBSManager.enabled && this.timestamp2 == 0L) {
         this.timestamp2 = System.nanoTime();
      }
   }

   private void updateState2() {
      this.timestamp = 0L;
      this.timestamp2 = 0L;
   }

   public static void applyNativeLongEntry(NativeLongEntry nativeLongEntry) {
      nativeLongEntry.updateState2();
   }
}
