package net.minecraft.client.shader;

import java.nio.FloatBuffer;
import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderUniformFloat extends ShaderUniform {
   public boolean enabled;
   public FloatBuffer floatBuffer;

   public float computeFactor(int count, int count2) {
      if (this.floatBuffer == null) {
         return 0.0F;
      } else {
         int count3 = this.enabled ? count2 * 4 + count : count * 4 + count2;
         return this.floatBuffer.get(count3);
      }
   }

   @Override
   public void updateState() {
      this.floatBuffer = null;
   }

   public void applyFloatBuffer(boolean flag, FloatBuffer buffer) {
      this.enabled = flag;
      this.floatBuffer = buffer;
      int count = this.getCount();
      if (count >= 0) {
         ARBShaderObjects.glUniformMatrix4ARB(count, flag, buffer);
         this.updateState2();
      }
   }

   @Override
   public void applyIndex(int count) {
   }

   public ShaderUniformFloat(String text) {
      super(text);
   }
}
