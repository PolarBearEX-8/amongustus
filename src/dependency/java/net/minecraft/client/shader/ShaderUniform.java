package net.minecraft.client.shader;

import client.util.PreEndHandManager;
import java.util.Arrays;
import org.lwjgl.opengl.ARBShaderObjects;

public abstract class ShaderUniform {
   public String labelText;
   public int count2 = 0;
   public int[] countArray = new int[]{-1};
   public static final int count3 = -1;
   public static final int count = Integer.MIN_VALUE;

   private void updateState4() {
      if (this.count2 >= this.countArray.length) {
         int[] countArray2 = new int[this.count2 * 2];
         Arrays.fill(countArray2, Integer.MIN_VALUE);
         System.arraycopy(this.countArray, 0, countArray2, 0, this.countArray.length);
         this.countArray = countArray2;
      }
   }

   public void applyIndexByIndex(int count4) {
      if (this.count2 != count4) {
         this.count2 = count4;
         this.updateState4();
         this.applyIndex(count4);
      }
   }

   public int getCount2() {
      return this.count2;
   }

   public void updateState2() {
      if (PreEndHandManager.computeCount3(this.labelText) != 0) {
         this.updateState3();
      }
   }

   public abstract void updateState();

   public int getCount() {
      if (this.count2 <= 0) {
         return -1;
      } else {
         int count4 = this.countArray[this.count2];
         if (count4 == Integer.MIN_VALUE) {
            count4 = ARBShaderObjects.glGetUniformLocationARB(this.count2, this.labelText);
            this.countArray[this.count2] = count4;
         }

         return count4;
      }
   }

   public void updateState5() {
      this.count2 = 0;
      this.countArray = new int[]{-1};
      this.updateState();
   }

   public void updateState3() {
      this.countArray[this.count2] = -1;
   }

   public abstract void applyIndex(int count4);

   public String getLabelText() {
      return this.labelText;
   }

   public boolean isActive() {
      return this.getCount() >= 0;
   }

   public ShaderUniform(String text) {
      this.labelText = text;
   }

   @Override
   public String toString() {
      return this.labelText;
   }
}
