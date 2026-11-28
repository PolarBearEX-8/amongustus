package net.augustus.utils.render;

import net.augustus.utils.shader.ShaderProgram;
import net.minecraft.client.shader.Framebuffer;

public enum BlurMode implements BlurPass {
   GAUSSIAN("Gaussian", new SeparableBlurPass(
            new ShaderProgram(RenderSystemAssetLoader.loadAssetString("legacy.vert"), RenderSystemAssetLoader.loadAssetString("impl/gaussian.frag"))
         )),
   MC_BOX("Box", new SeparableBlurPass(new ShaderProgram(RenderSystemAssetLoader.loadAssetString("legacy.vert"), RenderSystemAssetLoader.loadAssetString("impl/box.frag")))),
   KAWASE("Kawase", new KawaseBlurPass(
            new ShaderProgram(RenderSystemAssetLoader.loadAssetString("legacy.vert"), RenderSystemAssetLoader.loadAssetString("impl/kawase/upsample.frag")),
            new ShaderProgram(RenderSystemAssetLoader.loadAssetString("legacy.vert"), RenderSystemAssetLoader.loadAssetString("impl/kawase/downsample.frag"))
         ));

private final String displayName;
private final BlurPass blurPass;

   @Override
   public Framebuffer applyBlurToFramebuffer(Framebuffer framebuffer, int n) {
      return this.blurPass.applyBlurToFramebuffer(framebuffer, n);
   }

   public static BlurMode fromName(String str) {

      for (BlurMode blurMode : values()) {
         if (blurMode.getDisplayName().equalsIgnoreCase(str)) {
            return blurMode;
         }
      }

      return KAWASE;
   }

   @Override
   public void applyMaskedBlur(Framebuffer framebuffer, Framebuffer framebuffer2, int n) {
      this.blurPass.applyMaskedBlur(framebuffer, framebuffer2, n);
   }

   public String getDisplayName() {
      return this.displayName;
   }

   private BlurMode(String str, BlurPass blurPass2) {
      this.displayName = str;
      this.blurPass = blurPass2;
   }


}
