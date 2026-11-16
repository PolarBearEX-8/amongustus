package client.util;

import net.minecraft.client.shader.ShaderUniform;
import org.lwjgl.opengl.ARBShaderObjects;

public class StringHandler extends ShaderUniform {
   public int[] countArray2;
   public static final int count4 = Integer.MIN_VALUE;

   public void applyIndex2(int count) {
      int count2 = this.getCount2();
      int count3 = this.countArray2[count2];
      if (count != count3) {
         this.countArray2[count2] = count;
         int count5 = this.getCount();
         if (count5 >= 0) {
            ARBShaderObjects.glUniform1iARB(count5, count);
            this.updateState2();
         }
      }
   }

   @Override
   public void updateState() {
      this.countArray2 = new int[]{Integer.MIN_VALUE};
   }

   @Override
   public void applyIndex(int count) {
      if (count >= this.countArray2.length) {
         int[] countArray = this.countArray2;
         int[] countArray3 = new int[count + 10];
         System.arraycopy(countArray, 0, countArray3, 0, countArray.length);

         for (int index = countArray.length; index < countArray3.length; index++) {
            countArray3[index] = Integer.MIN_VALUE;
         }

         this.countArray2 = countArray3;
      }
   }

   public int getCountAsIndex() {
      int count = this.getCount2();
      return this.countArray2[count];
   }

   public StringHandler(String text) {
      super(text);
      this.updateState();
   }
}
