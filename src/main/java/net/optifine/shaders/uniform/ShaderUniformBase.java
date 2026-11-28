package net.optifine.shaders.uniform;

import java.util.Arrays;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.ARBShaderObjects;

public abstract class ShaderUniformBase {
   private String name;
   private int program = 0;
   private int[] locations = new int[]{-1};
   private static final int LOCATION_UNDEFINED = -1;
   private static final int LOCATION_UNKNOWN = Integer.MIN_VALUE;

   public void disable() {
      this.locations[this.program] = -1;
   }

   public void checkGLError() {
      if (Shaders.checkGLError(this.name) != 0) {
         this.disable();
      }
   }

   public ShaderUniformBase(String str) {
      this.name = str;
   }

   public abstract void onProgramSet(int n);

   public String getName() {
      return this.name;
   }

   public boolean isDefined() {
      return this.getLocation() >= 0;
   }

   public void reset() {
      this.program = 0;
      this.locations = new int[]{-1};
      this.resetValue();
   }

   public int getLocation() {
      if (this.program <= 0) {
         return -1;
      } else {
         int n = this.locations[this.program];
         if (n == Integer.MIN_VALUE) {
            n = ARBShaderObjects.glGetUniformLocationARB(this.program, this.name);
            this.locations[this.program] = n;
         }

         return n;
      }
   }

   public int getProgram() {
      return this.program;
   }

   public void setProgram(int n) {
      if (this.program != n) {
         this.program = n;
         this.expandLocations();
         this.onProgramSet(n);
      }
   }

   private void expandLocations() {
      if (this.program >= this.locations.length) {
         int[] ints = new int[this.program * 2];
         Arrays.fill(ints, Integer.MIN_VALUE);
         System.arraycopy(this.locations, 0, ints, 0, this.locations.length);
         this.locations = ints;
      }
   }

   @Override
   public String toString() {
      return this.name;
   }

   public abstract void resetValue();
}
