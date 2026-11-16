package client.event;

import net.minecraft.client.shader.Framebuffer;

public interface PostRenderFilter {
   default void updateState() {
   }

   default void updateState2() {
   }

   Framebuffer resolveFramebuffer(Framebuffer framebuffer, int count, int count2);
}
