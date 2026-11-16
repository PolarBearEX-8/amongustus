package client.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class VectorMatcher {
   public static synchronized ByteBuffer resolveByteBuffer(int count) {
      return ByteBuffer.allocateDirect(count).order(ByteOrder.nativeOrder());
   }

   public static synchronized void glDeleteLists(int count) {
      GL11.glDeleteLists(count, 1);
   }

   public static synchronized int computeCount(int count) {
      int count2 = GL11.glGenLists(count);
      if (count2 == 0) {
         int count3 = GL11.glGetError();
         String text = "No error code reported";
         if (count3 != 0) {
            text = GLU.gluErrorString(count3);
         }

         throw new IllegalStateException("glGenLists returned an ID of 0 for a count of " + count + ", GL error (" + count3 + "): " + text);
      } else {
         return count2;
      }
   }

   public static IntBuffer resolveIntBuffer(int count) {
      return resolveByteBuffer(count << 2).asIntBuffer();
   }

   public static FloatBuffer resolveFloatBuffer(int count) {
      return resolveByteBuffer(count << 2).asFloatBuffer();
   }

   public static synchronized void glDeleteLists(int count, int count2) {
      GL11.glDeleteLists(count, count2);
   }
}
