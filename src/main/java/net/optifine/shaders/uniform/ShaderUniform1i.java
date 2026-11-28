package net.optifine.shaders.uniform;

import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderUniform1i extends ShaderUniformBase {
   private int[] programValues;
   private static final int VALUE_UNKNOWN = Integer.MIN_VALUE;

   @Override
   public void onProgramSet(int n) {
      if (n >= this.programValues.length) {
         int[] programValues2 = this.programValues;
         int[] ints = new int[n + 10];
         System.arraycopy(programValues2, 0, ints, 0, programValues2.length);

         for (int i = programValues2.length; i < ints.length; i++) {
            ints[i] = Integer.MIN_VALUE;
         }

         this.programValues = ints;
      }
   }

   public void setValue(int n) {
      int program2 = this.getProgram();
      if (n != this.programValues[program2]) {
         this.programValues[program2] = n;
         int location = this.getLocation();
         if (location >= 0) {
            ARBShaderObjects.glUniform1iARB(location, n);
            this.checkGLError();
         }
      }
   }

   public ShaderUniform1i(String str) {
      super(str);
      this.resetValue();
   }

   public int getValue() {
      int program2 = this.getProgram();
      return this.programValues[program2];
   }

   @Override
   public void resetValue() {
      this.programValues = new int[]{Integer.MIN_VALUE};
   }
}
