package net.minecraft.client.renderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class GLAllocation {
   public static synchronized void deleteDisplayLists(int n) {
      GL11.glDeleteLists(n, 1);
   }

   public static synchronized int generateDisplayLists(int n) {
      int n2 = GL11.glGenLists(n);
      if (n2 == 0) {
         int n3 = GL11.glGetError();
         String str = "No error code reported";
         if (n3 != 0) {
            str = GLU.gluErrorString(n3);
         }

         throw new IllegalStateException("glGenLists returned an ID of 0 for a count of " + n + ", GL error (" + n3 + "): " + str);
      } else {
         return n2;
      }
   }

   public static FloatBuffer createDirectFloatBuffer(int n) {
      return createDirectByteBuffer(n << 2).asFloatBuffer();
   }

   public static synchronized void deleteDisplayLists(int n, int n2) {
      GL11.glDeleteLists(n, n2);
   }

   public static IntBuffer createDirectIntBuffer(int n) {
      return createDirectByteBuffer(n << 2).asIntBuffer();
   }

   public static synchronized ByteBuffer createDirectByteBuffer(int n) {
      return ByteBuffer.allocateDirect(n).order(ByteOrder.nativeOrder());
   }
}
