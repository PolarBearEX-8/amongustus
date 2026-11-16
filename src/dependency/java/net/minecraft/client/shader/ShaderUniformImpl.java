package net.minecraft.client.shader;

import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderUniformImpl extends ShaderUniform {
   public float[][] factorArray;
   public static final float factor = -Float.MAX_VALUE;

   public void applyFloat(float factor2, float factor3) {
      int count = this.getCount2();
      float[] factorArray2 = this.factorArray[count];
      if (factorArray2[0] != factor2 || factorArray2[1] != factor3) {
         factorArray2[0] = factor2;
         factorArray2[1] = factor3;
         int count2 = this.getCount();
         if (count2 >= 0) {
            ARBShaderObjects.glUniform2fARB(count2, factor2, factor3);
            this.updateState2();
         }
      }
   }

   public float[] getFloatArray() {
      int count = this.getCount2();
      return this.factorArray[count];
   }

   public ShaderUniformImpl(String text) {
      super(text);
      this.updateState();
   }

   @Override
   public void applyIndex(int count) {
      if (count >= this.factorArray.length) {
         float[][] factorArray2 = this.factorArray;
         float[][] factorMatrix = new float[count + 10][];
         System.arraycopy(factorArray2, 0, factorMatrix, 0, factorArray2.length);
         this.factorArray = factorMatrix;
      }

      if (this.factorArray[count] == null) {
         this.factorArray[count] = new float[]{-Float.MAX_VALUE, -Float.MAX_VALUE};
      }
   }

   @Override
   public void updateState() {
      this.factorArray = new float[][]{{-Float.MAX_VALUE, -Float.MAX_VALUE}};
   }
}
