package net.augustus.utils.render;

import net.augustus.events.EventManager;
import net.augustus.events.listeners.WindowResizeListener;
import net.minecraft.client.shader.Framebuffer;

public class ResizableFramebuffer extends Framebuffer implements WindowResizeListener {

   @Override
   public void onWindowResize(int width, int height) {
      this.resizeFramebuffer(width, height);
   }

   public ResizableFramebuffer(int n, int n2, boolean flag) {
      super(n, n2, flag);
      EventManager.getEventBus().subscribe(47, (WindowResizeListener)this::resizeFramebuffer);
   }



}
