package client.render;

import client.util.TimeHelper;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class DynamicCapeTexture {
   public static int getIndex(BufferedImage bufferedImage) {
      int[] countArray = new int[bufferedImage.getWidth() * bufferedImage.getHeight()];
      bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), countArray, 0, bufferedImage.getWidth());
      ByteBuffer buffer = BufferUtils.createByteBuffer(bufferedImage.getWidth() * bufferedImage.getHeight() * 4);

      for (int index = 0; index < bufferedImage.getHeight(); index++) {
         for (int index2 = 0; index2 < bufferedImage.getWidth(); index2++) {
            int count = countArray[index * bufferedImage.getWidth() + index2];
            buffer.put((byte)(count >> 16 & 0xFF));
            buffer.put((byte)(count >> 8 & 0xFF));
            buffer.put((byte)(count & 0xFF));
            buffer.put((byte)(count >> 24 & 0xFF));
         }
      }

      ((Buffer)buffer).flip();
      int stateInt = GlStateManager.getStateInt6();
      GlStateManager.setActiveTexture(stateInt);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glTexImage2D(3553, 0, 32856, bufferedImage.getWidth(), bufferedImage.getHeight(), 0, 6408, 5121, buffer);
      return stateInt;
   }
}
