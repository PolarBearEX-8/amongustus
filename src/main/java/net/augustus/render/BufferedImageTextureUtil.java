package net.augustus.render;

import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class BufferedImageTextureUtil {
   public static int uploadTexture(BufferedImage image) {
      int[] ints = new int[image.getWidth() * image.getHeight()];
      image.getRGB(0, 0, image.getWidth(), image.getHeight(), ints, 0, image.getWidth());
      ByteBuffer buf = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);

      for (int i = 0; i < image.getHeight(); i++) {
         for (int j = 0; j < image.getWidth(); j++) {
            int n = ints[i * image.getWidth() + j];
            buf.put((byte)(n >> 16 & 0xFF));
            buf.put((byte)(n >> 8 & 0xFF));
            buf.put((byte)(n & 0xFF));
            buf.put((byte)(n >> 24 & 0xFF));
         }
      }

      ((Buffer)buf).flip();
      int n2 = GlStateManager.generateTexture();
      GlStateManager.bindTexture(n2);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glTexImage2D(3553, 0, 32856, image.getWidth(), image.getHeight(), 0, 6408, 5121, buf);
      return n2;
   }
}
