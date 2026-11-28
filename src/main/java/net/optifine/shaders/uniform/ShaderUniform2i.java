package net.optifine.shaders.uniform;

import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderUniform2i extends ShaderUniformBase {
   private int[][] programValues;
   private static final int VALUE_UNKNOWN = Integer.MIN_VALUE;

   public int[] getValue() {
      int program2 = this.getProgram();
      return this.programValues[program2];
   }

   public void setValue(int n, int n2) {
      int program2 = this.getProgram();
      int[] ints = this.programValues[program2];
      if (ints[0] != n || ints[1] != n2) {
         ints[0] = n;
         ints[1] = n2;
         int location = this.getLocation();
         if (location >= 0) {
            ARBShaderObjects.glUniform2iARB(location, n, n2);
            this.checkGLError();
         }
      }
   }

   @Override
   public void onProgramSet(int n) {
      if (n >= this.programValues.length) {
         int[][] programValues2 = this.programValues;
         int[][] intsArr = new int[n + 10][];
         System.arraycopy(programValues2, 0, intsArr, 0, programValues2.length);
         this.programValues = intsArr;
      }

      if (this.programValues[n] == null) {
         this.programValues[n] = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
      }
   }

   public ShaderUniform2i(String str) {
      super(str);
      this.resetValue();
   }

   @Override
   public void resetValue() {
      this.programValues = new int[][]{{Integer.MIN_VALUE, Integer.MIN_VALUE}};
   }
}
