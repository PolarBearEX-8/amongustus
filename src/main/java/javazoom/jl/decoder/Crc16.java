package javazoom.jl.decoder;

public final class Crc16 {
   private static short polynomial = -32763;
   private short crc = -1;

   public short checksum() {
      short crc2 = this.crc;
      this.crc = -1;
      return crc2;
   }

   public void add_bits(int n, int n2) {
      int n3 = 1 << n2 - 1;

      do {
         if ((this.crc & '耀') == 0 ^ (n & n3) == 0) {
            this.crc = (short)(this.crc << 1);
            this.crc = (short)(this.crc ^ polynomial);
         } else {
            this.crc = (short)(this.crc << 1);
         }
      } while ((n3 >>>= 1) != 0);
   }
}
