package net.optifine.shaders.uniform;

import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderUniform1f extends ShaderUniformBase {
   private float[] programValues;
   private static final float VALUE_UNKNOWN = -Float.MAX_VALUE;

   @Override
   public void onProgramSet(int n) {
      if (n >= this.programValues.length) {
         float[] programValues2 = this.programValues;
         float[] floats = new float[n + 10];
         System.arraycopy(programValues2, 0, floats, 0, programValues2.length);

         for (int i = programValues2.length; i < floats.length; i++) {
            floats[i] = -Float.MAX_VALUE;
         }

         this.programValues = floats;
      }
   }

   public void setValue(float f) {
      int program2 = this.getProgram();
      if (f != this.programValues[program2]) {
         this.programValues[program2] = f;
         int location = this.getLocation();
         if (location >= 0) {
            ARBShaderObjects.glUniform1fARB(location, f);
            this.checkGLError();
         }
      }
   }

   @Override
   public void resetValue() {
      this.programValues = new float[]{-Float.MAX_VALUE};
   }

   public ShaderUniform1f(String str) {
      super(str);
      this.resetValue();
   }

   public float getValue() {
      int program2 = this.getProgram();
      return this.programValues[program2];
   }
}
