package client.util;

import net.minecraft.client.shader.ShaderUniform;
import org.lwjgl.opengl.ARBShaderObjects;

public class ExtendedStringHandler extends ShaderUniform {
   public int[][] countArray2;
   public static final int count4 = Integer.MIN_VALUE;

   public void applyIndex(int count, int count2) {
      int count3 = this.getCount2();
      int[] countArray = this.countArray2[count3];
      if (countArray[0] != count || countArray[1] != count2) {
         countArray[0] = count;
         countArray[1] = count2;
         int count5 = this.getCount();
         if (count5 >= 0) {
            ARBShaderObjects.glUniform2iARB(count5, count, count2);
            this.updateState2();
         }
      }
   }

   @Override
   public void updateState() {
      this.countArray2 = new int[][]{{Integer.MIN_VALUE, Integer.MIN_VALUE}};
   }

   public int[] getIndexArray() {
      int count = this.getCount2();
      return this.countArray2[count];
   }

   @Override
   public void applyIndex(int count) {
      if (count >= this.countArray2.length) {
         int[][] countArray = this.countArray2;
         int[][] countMatrix = new int[count + 10][];
         System.arraycopy(countArray, 0, countMatrix, 0, countArray.length);
         this.countArray2 = countMatrix;
      }

      if (this.countArray2[count] == null) {
         this.countArray2[count] = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
      }
   }

   public ExtendedStringHandler(String text) {
      super(text);
      this.updateState();
   }
}
