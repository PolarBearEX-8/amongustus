package net.optifine.shaders;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class HFNoiseTexture implements ICustomTexture {
   private int texID = GL11.glGenTextures();
   private int textureUnit = 15;

   public int getTexID() {
      return this.texID;
   }

   private byte[] genHFNoiseImage(int n, int n2) {
      byte[] bytes = new byte[n * n2 * 3];
      int n3 = 0;

      for (int i = 0; i < n2; i++) {
         for (int j = 0; j < n; j++) {
            for (int k = 1; k < 4; k++) {
               bytes[n3++] = this.random(j, i, k);
            }
         }
      }

      return bytes;
   }

   public HFNoiseTexture(int n, int n2) {
      byte[] bytes = this.genHFNoiseImage(n, n2);
      ByteBuffer buf = BufferUtils.createByteBuffer(bytes.length);
      buf.put(bytes);
      ((Buffer)buf).flip();
      GlStateManager.bindTexture(this.texID);
      GL11.glTexImage2D(3553, 0, 6407, n, n2, 0, 6407, 5121, buf);
      GL11.glTexParameteri(3553, 10242, 10497);
      GL11.glTexParameteri(3553, 10243, 10497);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glTexParameteri(3553, 10241, 9729);
      GlStateManager.bindTexture(0);
   }

   @Override
   public void deleteTexture() {
      GlStateManager.deleteTexture(this.texID);
      this.texID = 0;
   }

   @Override
   public int getTextureUnit() {
      return this.textureUnit;
   }

   @Override
   public int getTextureId() {
      return this.texID;
   }

   @Override
   public int getTarget() {
      return 3553;
   }

   private int random(int n) {
      n ^= n << 13;
      n ^= n >> 17;
      return n ^ n << 5;
   }

   private byte random(int n, int n2, int n3) {
      return (byte)(this.random((this.random(n) + this.random(n2 * 19)) * this.random(n3 * 23) - n3) % 128);
   }
}
