package net.augustus.render;

import java.util.Stack;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

public class FramebufferStack {
   private static final ThreadLocal FRAMEBUFFER_STACK = ThreadLocal.withInitial(Stack::new);

   public static void pushFramebuffer() {
      int currentFramebuffer = getCurrentFramebuffer();
      ((Stack)FRAMEBUFFER_STACK.get()).push(currentFramebuffer);
   }

   public static void bindFramebuffer(int n) {
      EXTFramebufferObject.glBindFramebufferEXT(36160, n);
   }

   public static int getStackDepth() {
      return ((Stack)FRAMEBUFFER_STACK.get()).size();
   }

   public static void popFramebuffer() {
      Stack stack = (Stack)FRAMEBUFFER_STACK.get();
      if (!stack.isEmpty()) {
         int n = (Integer)stack.pop();
         bindFramebuffer(n);
      }
   }

   public static int getCurrentFramebuffer() {
      return GL11.glGetInteger(36006);
   }
}
