package wdl;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

public class ChunkRange {
   public final String tag;
   public final int x1;
   public final int z1;
   public final int x2;
   public final int z2;

   public void writeToOutput(ByteArrayDataOutput byteArrayDataOutput) {
      byteArrayDataOutput.writeUTF(this.tag);
      byteArrayDataOutput.writeInt(this.x1);
      byteArrayDataOutput.writeInt(this.z1);
      byteArrayDataOutput.writeInt(this.x2);
      byteArrayDataOutput.writeInt(this.z2);
   }

   @Override
   public String toString() {
      return "ChunkRange [tag=" + this.tag + ", x1=" + this.x1 + ", z1=" + this.z1 + ", x2=" + this.x2 + ", z2=" + this.z2 + "]";
   }

   public ChunkRange(String str, int n, int n2, int n3, int n4) {
      this.tag = str;
      if (n > n3) {
         this.x1 = n3;
         this.x2 = n;
      } else {
         this.x1 = n;
         this.x2 = n3;
      }

      if (n2 > n4) {
         this.z1 = n4;
         this.z2 = n2;
      } else {
         this.z1 = n2;
         this.z2 = n4;
      }
   }

   public static ChunkRange readFromInput(ByteArrayDataInput byteArrayDataInput) {
      String str = byteArrayDataInput.readUTF();
      int n = byteArrayDataInput.readInt();
      int n2 = byteArrayDataInput.readInt();
      int n3 = byteArrayDataInput.readInt();
      int n4 = byteArrayDataInput.readInt();
      return new ChunkRange(str, n, n2, n3, n4);
   }
}
