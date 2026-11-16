package client.util;

import java.nio.IntBuffer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ARBDebugOutput;
import org.lwjgl.opengl.ARBDebugOutputCallback;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.opengl.ARBDebugOutputCallback.Handler;

public class APIWINHandler implements Handler {
   private String resolveText(int count) {
      return "Unknown (0x" + Integer.toHexString(count).toUpperCase() + ")";
   }

   public String resolveTextByIndex(int count) {
      switch (count) {
         case 33350:
            return "API";
         case 33351:
            return "WIN";
         case 33352:
            return "SHADER";
         case 33353:
            return "EXT";
         case 33354:
            return "APP";
         case 33355:
            return "OTHER";
         default:
            return this.resolveText(count);
      }
   }

   public void handleMessage(int count, int count2, int count3, int count4, String text) {
      if (!text.contains("glBindFramebuffer") && !text.contains("Wide lines") && !text.contains("shader recompiled")) {
         BuildManager.applyText4(
            "[LWJGL] source: "
               + this.resolveTextByIndex(count)
               + ", type: "
               + this.resolveText3(count2)
               + ", id: "
               + count3
               + ", severity: "
               + this.resolveText2(count4)
               + ", message: "
               + text
         );
         new Throwable("StackTrace").printStackTrace();
      }
   }

   public String resolveText2(int count) {
      switch (count) {
         case 37190:
            return "HIGH";
         case 37191:
            return "MEDIUM";
         case 37192:
            return "LOW";
         default:
            return this.resolveText(count);
      }
   }

   public String resolveText3(int count) {
      switch (count) {
         case 33356:
            return "ERROR";
         case 33357:
            return "DEPRECATED";
         case 33358:
            return "UNDEFINED";
         case 33359:
            return "PORTABILITY";
         case 33360:
            return "PERFORMANCE";
         case 33361:
            return "OTHER";
         default:
            return this.resolveText(count);
      }
   }

   public static void updateState() throws LWJGLException {
      ContextAttribs contextAttribs = new ContextAttribs().withDebug(true);
      Display.create(new PixelFormat().withDepthBits(24), contextAttribs);
      ARBDebugOutput.glDebugMessageCallbackARB(new ARBDebugOutputCallback(new APIWINHandler()));
      ARBDebugOutput.glDebugMessageControlARB(4352, 4352, 4352, (IntBuffer)null, true);
      GL11.glEnable(33346);
   }
}
