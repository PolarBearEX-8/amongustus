package client.util;

import java.nio.IntBuffer;
import java.util.Arrays;

public class IntBufferEntry {
   public IntBuffer intBuffer;
   public int count;
   public boolean[] enabledArray;
   public boolean[] enabledArray2;

   public int computeCount(int count2) {
      return this.get(count2, this.enabledArray[count2]);
   }

   public boolean checkIndex(int count2) {
      return this.enabledArray2[count2];
   }

   public void updateState() {
      Arrays.fill(this.enabledArray, false);
      Arrays.fill(this.enabledArray2, false);
   }

   public void applyIndex(int count2) {
      this.enabledArray[count2] = !this.enabledArray[count2];
      this.enabledArray2[count2] = true;
   }

   private int get(int count2, boolean flag) {
      int count3 = flag ? this.count : 0;
      return this.intBuffer.get(count3 + count2);
   }

   public int computeCountByIndex(int count2) {
      return this.get(count2, !this.enabledArray[count2]);
   }

   public IntBufferEntry(IntBuffer buffer, int count2) {
      this.intBuffer = buffer;
      this.count = count2;
      this.enabledArray = new boolean[buffer.capacity()];
      this.enabledArray2 = new boolean[buffer.capacity()];
   }
}
