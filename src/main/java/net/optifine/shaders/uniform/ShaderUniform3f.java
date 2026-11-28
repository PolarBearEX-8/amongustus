package net.optifine.shaders.uniform;

import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderUniform3f extends ShaderUniformBase {
   private float[][] programValues;
   private static final float VALUE_UNKNOWN = -Float.MAX_VALUE;

   @Override
   public void resetValue() {
      this.programValues = new float[][]{{-Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE}};
   }

   @Override
   public void onProgramSet(int n) {
      if (n >= this.programValues.length) {
         float[][] programValues2 = this.programValues;
         float[][] floatsArr = new float[n + 10][];
         System.arraycopy(programValues2, 0, floatsArr, 0, programValues2.length);
         this.programValues = floatsArr;
      }

      if (this.programValues[n] == null) {
         this.programValues[n] = new float[]{-Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE};
      }
   }

   public float[] getValue() {
      int program2 = this.getProgram();
      return this.programValues[program2];
   }

   public ShaderUniform3f(String str) {
      super(str);
      this.resetValue();
   }

   public void setValue(float f, float f2, float f3) {
      int program2 = this.getProgram();
      float[] floats = this.programValues[program2];
      if (floats[0] != f || floats[1] != f2 || floats[2] != f3) {
         floats[0] = f;
         floats[1] = f2;
         floats[2] = f3;
         int location = this.getLocation();
         if (location >= 0) {
            ARBShaderObjects.glUniform3fARB(location, f, f2, f3);
            this.checkGLError();
         }
      }
   }
}
