package client.util;

import client.event.PreUpdateSubscriber;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class CurveDeserializer implements PreUpdateSubscriber {
   public int count = GL11.glGenTextures();
   public int count2 = 15;

   private int computeCount(int count3) {
      int count4 = count3 ^ count3 << 13;
      count4 ^= count4 >> 17;
      return count4 ^ count4 << 5;
   }

   public int getCount3() {
      return this.count;
   }

   private byte[] resolveByteArray(int count3, int count4) {
      byte[] byteValueArray = new byte[count3 * count4 * 3];
      int count5 = 0;

      for (int index = 0; index < count4; index++) {
         for (int index2 = 0; index2 < count3; index2++) {
            for (int index3 = 1; index3 < 4; index3++) {
               byteValueArray[count5++] = this.computeByteCode(index2, index, index3);
            }
         }
      }

      return byteValueArray;
   }

   @Override
   public int getCount2() {
      return this.count;
   }

   @Override
   public void glDeleteTextures() {
      GlStateManager.deleteTexture(this.count);
      this.count = 0;
   }

   @Override
   public int getCount() {
      return 3553;
   }

   private byte computeByteCode(int count3, int count4, int count5) {
      int count6 = (this.computeCount(count3) + this.computeCount(count4 * 19)) * this.computeCount(count5 * 23) - count5;
      return (byte)(this.computeCount(count6) % 128);
   }

   @Override
   public int getCountAsIndex() {
      return this.count2;
   }

   public CurveDeserializer(int count3, int count4) {
      byte[] byteValueArray = this.resolveByteArray(count3, count4);
      ByteBuffer buffer = BufferUtils.createByteBuffer(byteValueArray.length);
      buffer.put(byteValueArray);
      ((Buffer)buffer).flip();
      GlStateManager.setActiveTexture(this.count);
      GL11.glTexImage2D(3553, 0, 6407, count3, count4, 0, 6407, 5121, buffer);
      GL11.glTexParameteri(3553, 10242, 10497);
      GL11.glTexParameteri(3553, 10243, 10497);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glTexParameteri(3553, 10241, 9729);
      GlStateManager.setActiveTexture(0);
   }
}
