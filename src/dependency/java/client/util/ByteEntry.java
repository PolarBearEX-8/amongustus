package client.util;

public class ByteEntry {
   public byte byteFlag;
   public byte byteFlag4;
   public byte byteFlag3;
   public byte byteFlag2;

   public byte getByteFlag() {
      return this.byteFlag;
   }

   public byte getByteFlag2() {
      return this.byteFlag2;
   }

   public byte getByteFlag3() {
      return this.byteFlag3;
   }

   public byte getByteFlag4() {
      return this.byteFlag4;
   }

   public ByteEntry(ByteEntry byteEntry) {
      this.byteFlag = byteEntry.byteFlag;
      this.byteFlag4 = byteEntry.byteFlag4;
      this.byteFlag3 = byteEntry.byteFlag3;
      this.byteFlag2 = byteEntry.byteFlag2;
   }

   public ByteEntry(byte byteValue, byte byteValue2, byte byteValue3, byte byteValue4) {
      this.byteFlag = byteValue;
      this.byteFlag4 = byteValue2;
      this.byteFlag3 = byteValue3;
      this.byteFlag2 = byteValue4;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof ByteEntry)) {
         return false;
      } else {
         ByteEntry byteEntry = (ByteEntry)other;
         return this.byteFlag != byteEntry.byteFlag
            ? false
            : (this.byteFlag2 != byteEntry.byteFlag2 ? false : (this.byteFlag4 != byteEntry.byteFlag4 ? false : this.byteFlag3 == byteEntry.byteFlag3));
      }
   }

   @Override
   public int hashCode() {
      byte byteFlag5 = this.byteFlag;
      int count = 31 * byteFlag5 + this.byteFlag4;
      int count2 = 31 * count + this.byteFlag3;
      return 31 * count2 + this.byteFlag2;
   }
}
