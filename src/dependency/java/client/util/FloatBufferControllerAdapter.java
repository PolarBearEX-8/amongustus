package client.util;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class FloatBufferControllerAdapter {
   public float[] factorArray;
   public final int count = 36;
   public final int count2;

   private void applyFloatBuffer(
      FloatBuffer buffer,
      float posX,
      float posX2,
      float posX3,
      float posX4,
      float posX5,
      float posX6,
      float posX7,
      float posX8,
      float posX9,
      float posX10,
      float posY,
      float posZ
   ) {
      this.applyFloatBuffer(buffer, posX, posX2, posX3);
      this.applyFloatBuffer(buffer, posX4, posX5, posX6);
      this.applyFloatBuffer(buffer, posX7, posX8, posX9);
      this.applyFloatBuffer(buffer, posX, posX2, posX3);
      this.applyFloatBuffer(buffer, posX7, posX8, posX9);
      this.applyFloatBuffer(buffer, posX10, posY, posZ);
   }

   private void applyFloatBuffer(FloatBuffer buffer, float posX, float posY, float posZ) {
      buffer.put(posX).put(posY).put(posZ);
   }

   public void arraycopy(float[] factorArray2) {
      System.arraycopy(factorArray2, 0, this.factorArray, 0, 4);
   }

   private void applyFloatBufferByFloatBuffer(FloatBuffer buffer, float posX, float posY, float posZ) {
      float factor = posX + 1.0F;
      float factor2 = posY + 1.0F;
      float factor3 = posZ + 1.0F;
      float factor4 = posZ + 1.0F;
      XYType xYType = XYType.Z;
      float factor5 = factor4;
      float factor6 = factor3;
      float factor7 = factor2;
      float factor8 = factor;
      this.applyFloatBuffer(buffer, posX, factor8, posY, factor7, factor6, factor5, xYType);
      factor = posX + 1.0F;
      factor2 = posY + 1.0F;
      XYType xYType2 = XYType.Z;
      float factor9 = factor2;
      float factor10 = factor;
      this.applyFloatBuffer(buffer, posX, factor10, posY, factor9, posZ, posZ, xYType2);
      factor2 = posY + 1.0F;
      factor4 = posZ + 1.0F;
      XYType xYType3 = XYType.X;
      float factor11 = factor4;
      float factor12 = factor2;
      this.applyFloatBuffer(buffer, posX, posX, posY, factor12, posZ, factor11, xYType3);
      float factor13 = posX + 1.0F;
      factor = posX + 1.0F;
      factor2 = posY + 1.0F;
      factor4 = posZ + 1.0F;
      XYType xYType4 = XYType.X;
      float factor14 = factor4;
      float factor15 = factor2;
      float factor16 = factor;
      float factor17 = factor13;
      this.applyFloatBuffer(buffer, factor17, factor16, posY, factor15, posZ, factor14, xYType4);
      factor = posX + 1.0F;
      float factor18 = posY + 1.0F;
      factor2 = posY + 1.0F;
      factor4 = posZ + 1.0F;
      XYType xYType5 = XYType.Y;
      float factor19 = factor4;
      float factor20 = factor2;
      float factor21 = factor18;
      float factor22 = factor;
      this.applyFloatBuffer(buffer, posX, factor22, factor21, factor20, posZ, factor19, xYType5);
      factor = posX + 1.0F;
      factor4 = posZ + 1.0F;
      XYType xYType6 = XYType.Y;
      float factor23 = factor4;
      float factor24 = factor;
      this.applyFloatBuffer(buffer, posX, factor24, posY, posY, posZ, factor23, xYType6);
   }

   public FloatBufferControllerAdapter(float posX, float posY, float posZ, float[] factorArray2) {
      this.factorArray = (float[])factorArray2.clone();
      ByteBuffer buffer = ByteBuffer.allocateDirect(this.count * 3 * 4);
      buffer.order(ByteOrder.nativeOrder());
      FloatBuffer buffer2 = buffer.asFloatBuffer();
      this.applyFloatBufferByFloatBuffer(buffer2, posX, posY, posZ);
      ((Buffer)buffer2).flip();
      this.count2 = GL15.glGenBuffers();
      GL15.glBindBuffer(34962, this.count2);
      GL15.glBufferData(34962, buffer, 35044);
      GL15.glBindBuffer(34962, 0);
   }

   public void updateState() {
      GL11.glColor4f(this.factorArray[0], this.factorArray[1], this.factorArray[2], this.factorArray[3]);
      GL15.glBindBuffer(34962, this.count2);
      GL11.glVertexPointer(3, 5126, 0, 0L);
      GL11.glDrawArrays(4, 0, this.count);
      GL15.glBindBuffer(34962, 0);
   }

   public void glDeleteBuffers() {
      GL15.glDeleteBuffers(this.count2);
   }

   private void applyFloatBuffer(FloatBuffer buffer, float posX, float posX2, float posX3, float posX4, float posY, float posZ, XYType xYType) {
      switch (FloatBufferControllerAdaXYTypeSwitchMap.countArray[xYType.ordinal()]) {
         case 1:
            this.applyFloatBuffer(buffer, posX, posX3, posY, posX2, posX3, posY, posX2, posX4, posY, posX, posX4, posY);
            break;
         case 2:
            this.applyFloatBuffer(buffer, posX, posX3, posY, posX, posX3, posZ, posX, posX4, posZ, posX, posX4, posY);
            break;
         case 3:
            this.applyFloatBuffer(buffer, posX, posX3, posY, posX2, posX3, posY, posX2, posX3, posZ, posX, posX3, posZ);
      }
   }
}
