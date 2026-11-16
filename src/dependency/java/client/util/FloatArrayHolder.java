package client.util;

import net.minecraft.client.shader.ShaderUniform;
import org.lwjgl.opengl.ARBShaderObjects;

public class FloatArrayHolder extends ShaderUniform {
   public float[] factorArray;
   public static final float factor = -Float.MAX_VALUE;

   @Override
   public void updateState() {
      this.factorArray = new float[]{-Float.MAX_VALUE};
   }

   public float getFactor() {
      int count = this.getCount2();
      return this.factorArray[count];
   }

   public void applyFloat(float factor2) {
      int count = this.getCount2();
      float factor3 = this.factorArray[count];
      if (factor2 != factor3) {
         this.factorArray[count] = factor2;
         int count2 = this.getCount();
         if (count2 >= 0) {
            ARBShaderObjects.glUniform1fARB(count2, factor2);
            this.updateState2();
         }
      }
   }

   @Override
   public void applyIndex(int count) {
      if (count >= this.factorArray.length) {
         float[] factorArray2 = this.factorArray;
         float[] factorArray3 = new float[count + 10];
         System.arraycopy(factorArray2, 0, factorArray3, 0, factorArray2.length);

         for (int index = factorArray2.length; index < factorArray3.length; index++) {
            factorArray3[index] = -Float.MAX_VALUE;
         }

         this.factorArray = factorArray3;
      }
   }

   public FloatArrayHolder(String text) {
      super(text);
      this.updateState();
   }
}
