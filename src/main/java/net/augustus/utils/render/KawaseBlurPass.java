package net.augustus.utils.render;

import net.augustus.utils.TuplePair;
import net.augustus.utils.shader.ShaderProgram;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.shader.Framebuffer;

public class KawaseBlurPass implements BlurPass, MC {
   private final Map passPresets;
   private final Framebuffer pongFramebuffer;
   private final Framebuffer pingFramebuffer;
   private final ShaderProgram upsampleShader;
   private final ShaderProgram downsampleShader;

   public void applyMaskedBlur(Framebuffer framebuffer, Framebuffer framebuffer2, int n) {
   }

   public KawaseBlurPass(ShaderProgram shaderProgram, ShaderProgram shaderProgram2) {
       super();
      this.passPresets = new HashMap();
      this.pingFramebuffer = new ResizableFramebuffer(mc.displayWidth, mc.displayHeight, true);
      this.pongFramebuffer = new ResizableFramebuffer(mc.displayWidth, mc.displayHeight, true);
      this.upsampleShader = shaderProgram;
      this.downsampleShader = shaderProgram2;
      ArrayList list = new ArrayList();
      list.add(new TuplePair(1, 1.25F));
      list.add(new TuplePair(1, 2.25F));
      list.add(new TuplePair(2, 2.0F));
      list.add(new TuplePair(2, 3.0F));
      list.add(new TuplePair(2, 4.25F));
      list.add(new TuplePair(3, 2.5F));
      list.add(new TuplePair(3, 3.25F));
      list.add(new TuplePair(3, 4.25F));
      list.add(new TuplePair(3, 5.5F));
      list.add(new TuplePair(4, 3.25F));
      list.add(new TuplePair(4, 4.0F));
      list.add(new TuplePair(4, 5.0F));
      list.add(new TuplePair(4, 6.0F));
      list.add(new TuplePair(4, 7.25F));
      list.add(new TuplePair(4, 8.25F));
      list.add(new TuplePair(5, 4.5F));
      list.add(new TuplePair(5, 5.25F));
      list.add(new TuplePair(5, 6.25F));
      list.add(new TuplePair(5, 7.25F));
      list.add(new TuplePair(5, 8.5F));

      for (int i = 0; i < list.size(); i++) {
         TuplePair tuplePair = (TuplePair)list.get(i);
         TuplePair[] tuplePairs = new TuplePair[(Integer)tuplePair.getFirst() * 2];

         for (int j = 0; j < tuplePairs.length; j++) {
            tuplePairs[j] = new TuplePair(tuplePair.getSecond(), j < tuplePairs.length / 2 ? 0.5F : 2.0F);
         }

         if (tuplePairs.length % 2 != 0) {
            int n = tuplePairs.length - 1;
            tuplePairs[n] = new TuplePair(tuplePairs[n].getFirst(), 1.0F);
         }

         this.passPresets.put(i, tuplePairs);
      }
   }

   public Framebuffer applyBlurToFramebuffer(Framebuffer framebuffer, int n) {
      if (n > 20) {
         n = 20;
      }

      Framebuffer framebuffer2 = null;
      float f = 1.0F;
      int n2 = 0;

      for (TuplePair tuplePair : (TuplePair[])this.passPresets.get(n - 1)) {
         Framebuffer framebuffer3 = null;
         Framebuffer framebuffer4 = null;
         if (n2 == 0) {
            framebuffer3 = framebuffer;
            framebuffer4 = this.pingFramebuffer;
         } else if (n2 % 2 != 0) {
            framebuffer3 = this.pingFramebuffer;
            framebuffer4 = this.pongFramebuffer;
         } else if (n2 % 2 == 0) {
            framebuffer3 = this.pongFramebuffer;
            framebuffer4 = this.pingFramebuffer;
         }

         f *= (Float)tuplePair.getSecond();
         float second = (Float)tuplePair.getSecond();
         float first = (Float)tuplePair.getFirst();
         framebuffer2 = this.renderSamplePass(first, f, second, framebuffer4, framebuffer3);
         n2++;
      }

      return framebuffer2;
   }

   public Framebuffer renderSamplePass(float f, float f2, float f3, Framebuffer framebuffer, Framebuffer framebuffer2) {
      framebuffer.bindFramebuffer(true);
      ShaderProgram shaderProgram = f3 > 1.0F ? this.upsampleShader : this.downsampleShader;
      shaderProgram.bind();
      shaderProgram.setUniform1f("offset", f);
      shaderProgram.bindTextureUniform("tex", framebuffer2);
      float f4 = (float)framebuffer2.framebufferTextureWidth * f3;
      float f5 = (float)framebuffer2.framebufferTextureHeight * f3;
      shaderProgram.setUniform2f("resolution", f4, f5);
      float f7 = f2 == 1.0F ? (float)framebuffer2.framebufferTextureHeight : (float)framebuffer2.framebufferTextureHeight - (float)framebuffer2.framebufferTextureHeight * f2;
      float f8 = (float)framebuffer2.framebufferTextureWidth * f2;
      float f9 = 0.0F;
      shaderProgram.drawQuad(0.0F, f7, f8, f9);
      shaderProgram.unbind();
      return framebuffer;
   }

}
