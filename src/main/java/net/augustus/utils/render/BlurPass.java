package net.augustus.utils.render;

import net.minecraft.client.shader.Framebuffer;

public interface BlurPass {
   void applyMaskedBlur(Framebuffer framebuffer, Framebuffer framebuffer2, int n);

   Framebuffer applyBlurToFramebuffer(Framebuffer framebuffer, int n);
}
