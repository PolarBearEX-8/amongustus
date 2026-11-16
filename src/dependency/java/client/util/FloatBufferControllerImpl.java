package client.util;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class FloatBufferControllerImpl {
   public FloatBuffer floatBuffer;
   public List list = new ArrayList();
   public static final int count = 4;
   public int count2;
   public static final int count3 = 3;
   public static final int count4 = 28;
   public static final int count5 = 36;

   private void applyIndex(int count6) {
      ByteBuffer buffer = ByteBuffer.allocateDirect(count6 * 36 * 28).order(ByteOrder.nativeOrder());
      this.floatBuffer = buffer.asFloatBuffer();
      if (this.count2 != 0) {
         GL15.glDeleteBuffers(this.count2);
      }

      this.count2 = GL15.glGenBuffers();
   }

   private void updateState2() {
      ((Buffer)this.floatBuffer).clear();
      if (this.list.size() * 3 * 4 * 36 > this.floatBuffer.capacity()) {
         int count6 = this.list.size() * 3 * 4 * 36;
         this.applyIndex(count6);
      }

      for (RecordNode24 recordNode : (Iterable<RecordNode24>)(Iterable<?>)this.list) {
         float factor = recordNode.factor4;
         float factor2 = recordNode.factor3;
         float factor3 = recordNode.factor2;
         float factor4 = recordNode.factor;

         for (float[] factorArray : this.resolveFloatArray(factor4, factor3, factor2, factor)) {
            this.floatBuffer.put(factorArray[0]).put(factorArray[1]).put(factorArray[2]);
            this.floatBuffer.put(recordNode.factorArray[0]).put(recordNode.factorArray[1]).put(recordNode.factorArray[2]).put(recordNode.factorArray[3]);
         }
      }

      ((Buffer)this.floatBuffer).flip();
      GL15.glBindBuffer(34962, this.count2);
      GL15.glBufferData(34962, this.floatBuffer, 35048);
      GL15.glBindBuffer(34962, 0);
      GL11.glVertexPointer(3, 5126, 0, 0L);
   }

   public void applyFloatArray(float posX, float posX2, float posY, float posZ, float[] factorArray) {
      this.list.add(new RecordNode24(posX, posX2, posY, posZ, factorArray));
      this.updateState2();
   }

   public void updateState() {
      if (!this.list.isEmpty()) {
         GL11.glPushAttrib(8192);
         GL11.glDisable(3553);
         GL11.glEnableClientState(32884);
         GL11.glEnableClientState(32886);
         GL15.glBindBuffer(34962, this.count2);
         GL11.glVertexPointer(3, 5126, 28, 0L);
         GL11.glColorPointer(4, 5126, 28, 12L);
         GL11.glDrawArrays(4, 0, this.list.size() * 36);
         GL15.glBindBuffer(34962, 0);
         GL11.glPopAttrib();
      }
   }

   private float[][] resolveFloatArray(float posX, float posX2, float posY, float posZ) {
      float factor = posZ / 2.0F;
      return new float[][]{
         {posX - factor, posX2 - factor, posY + factor},
         {posX + factor, posX2 - factor, posY + factor},
         {posX + factor, posX2 + factor, posY + factor},
         {posX - factor, posX2 - factor, posY + factor},
         {posX + factor, posX2 + factor, posY + factor},
         {posX - factor, posX2 + factor, posY + factor},
         {posX + factor, posX2 - factor, posY - factor},
         {posX - factor, posX2 - factor, posY - factor},
         {posX - factor, posX2 + factor, posY - factor},
         {posX + factor, posX2 - factor, posY - factor},
         {posX - factor, posX2 + factor, posY - factor},
         {posX + factor, posX2 + factor, posY - factor},
         {posX - factor, posX2 - factor, posY - factor},
         {posX - factor, posX2 - factor, posY + factor},
         {posX - factor, posX2 + factor, posY + factor},
         {posX - factor, posX2 - factor, posY - factor},
         {posX - factor, posX2 + factor, posY + factor},
         {posX - factor, posX2 + factor, posY - factor},
         {posX + factor, posX2 - factor, posY + factor},
         {posX + factor, posX2 - factor, posY - factor},
         {posX + factor, posX2 + factor, posY - factor},
         {posX + factor, posX2 - factor, posY + factor},
         {posX + factor, posX2 + factor, posY - factor},
         {posX + factor, posX2 + factor, posY + factor},
         {posX - factor, posX2 + factor, posY + factor},
         {posX + factor, posX2 + factor, posY + factor},
         {posX + factor, posX2 + factor, posY - factor},
         {posX - factor, posX2 + factor, posY + factor},
         {posX + factor, posX2 + factor, posY - factor},
         {posX - factor, posX2 + factor, posY - factor},
         {posX - factor, posX2 - factor, posY - factor},
         {posX + factor, posX2 - factor, posY - factor},
         {posX + factor, posX2 - factor, posY + factor},
         {posX - factor, posX2 - factor, posY - factor},
         {posX + factor, posX2 - factor, posY + factor},
         {posX - factor, posX2 - factor, posY + factor}
      };
   }

   public FloatBufferControllerImpl(int count6) {
      this.applyIndex(count6);
   }

   public void glDeleteBuffers() {
      GL15.glDeleteBuffers(this.count2);
   }
}
