package client.util;

import net.minecraft.client.shader.ShaderUniform;
import org.lwjgl.opengl.ARBShaderObjects;

public class AuxiliaryFloatArrayHolder extends ShaderUniform {
   public float[][] factorArray;
   public static final float factor = -Float.MAX_VALUE;

   public void applyFloat(float posX, float posY, float posZ) {
      int count = this.getCount2();
      float[] factorArray2 = this.factorArray[count];
      if (factorArray2[0] != posX || factorArray2[1] != posY || factorArray2[2] != posZ) {
         factorArray2[0] = posX;
         factorArray2[1] = posY;
         factorArray2[2] = posZ;
         int count2 = this.getCount();
         if (count2 >= 0) {
            ARBShaderObjects.glUniform3fARB(count2, posX, posY, posZ);
            this.updateState2();
         }
      }
   }

   @Override
   public void updateState() {
      this.factorArray = new float[][]{{-Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE}};
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
         this.factorArray[count] = new float[]{-Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE};
      }
   }

   public float[] getFloatArray() {
      int count = this.getCount2();
      return this.factorArray[count];
   }

   public AuxiliaryFloatArrayHolder(String text) {
      super(text);
      this.updateState();
   }
}
