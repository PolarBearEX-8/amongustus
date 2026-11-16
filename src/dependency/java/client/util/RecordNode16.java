package client.util;

public class RecordNode16 {
   public byte byteFlag;
   public byte byteFlag2;
   public byte byteFlag3;
   public byte byteFlag4;

   public RecordNode16(int count, int count2, int count3, int count4) {
      this.byteFlag = (byte)count;
      this.byteFlag4 = (byte)count2;
      this.byteFlag3 = (byte)count3;
      this.byteFlag2 = (byte)count4;
   }
}
