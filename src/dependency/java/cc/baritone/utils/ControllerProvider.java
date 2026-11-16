package cc.baritone.utils;

public final class ControllerProvider {
   public static short shortCode = -32763;
   public short shortCode2 = -1;

   public short getShortCode() {
      short shortCode3 = this.shortCode2;
      this.shortCode2 = -1;
      return shortCode3;
   }

   public void applyIndex(int count, int count2) {
      int count3 = 1 << count2 - 1;

      do {
         if ((this.shortCode2 & '\u8000') == 0 ^ (count & count3) == 0) {
            this.shortCode2 = (short)(this.shortCode2 << 1);
            this.shortCode2 = (short)(this.shortCode2 ^ shortCode);
         } else {
            this.shortCode2 = (short)(this.shortCode2 << 1);
         }
      } while ((count3 >>>= 1) != 0);
   }
}
