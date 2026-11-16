package cc.baritone.utils;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

public class AStarPlanner {
   public final String labelText;
   public final int count2;
   public final int count;
   public final int count3;
   public final int count4;

   @Override
   public String toString() {
      return "ChunkRange [tag=" + this.labelText + ", x1=" + this.count2 + ", z1=" + this.count + ", x2=" + this.count3 + ", z2=" + this.count4 + "]";
   }

   public static AStarPlanner resolveAStarPlanner(ByteArrayDataInput byteArrayDataInput) {
      String text = byteArrayDataInput.readUTF();
      int count5 = byteArrayDataInput.readInt();
      int count6 = byteArrayDataInput.readInt();
      int count7 = byteArrayDataInput.readInt();
      int count8 = byteArrayDataInput.readInt();
      return new AStarPlanner(text, count5, count6, count7, count8);
   }

   public void applyByteArrayDataOutput(ByteArrayDataOutput byteArrayDataOutput) {
      byteArrayDataOutput.writeUTF(this.labelText);
      byteArrayDataOutput.writeInt(this.count2);
      byteArrayDataOutput.writeInt(this.count);
      byteArrayDataOutput.writeInt(this.count3);
      byteArrayDataOutput.writeInt(this.count4);
   }

   public AStarPlanner(String text, int count5, int count6, int count7, int count8) {
      this.labelText = text;
      if (count5 > count7) {
         this.count2 = count7;
         this.count3 = count5;
      } else {
         this.count2 = count5;
         this.count3 = count7;
      }

      if (count6 > count8) {
         this.count = count8;
         this.count4 = count6;
      } else {
         this.count = count6;
         this.count4 = count8;
      }
   }
}
