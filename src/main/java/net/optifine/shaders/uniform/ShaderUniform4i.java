package net.optifine.shaders.uniform;

import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderUniform4i extends ShaderUniformBase {
   private int[][] programValues;
   private static final int VALUE_UNKNOWN = Integer.MIN_VALUE;

   public ShaderUniform4i(String str) {
      super(str);
      this.resetValue();
   }

   @Override
   public void resetValue() {
      this.programValues = new int[][]{{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}};
   }

   public int[] getValue() {
      int program2 = this.getProgram();
      return this.programValues[program2];
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
         this.programValues[n] = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
      }
   }

   public void setValue(int n, int n2, int n3, int n4) {
      int program2 = this.getProgram();
      int[] ints = this.programValues[program2];
      if (ints[0] != n || ints[1] != n2 || ints[2] != n3 || ints[3] != n4) {
         ints[0] = n;
         ints[1] = n2;
         ints[2] = n3;
         ints[3] = n4;
         int location = this.getLocation();
         if (location >= 0) {
            ARBShaderObjects.glUniform4iARB(location, n, n2, n3, n4);
            this.checkGLError();
         }
      }
   }
}
