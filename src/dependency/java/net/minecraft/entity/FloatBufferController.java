package net.minecraft.entity;

import client.util.RecordNode2;
import client.util.RecordNodeBridge;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class FloatBufferController {
   public FloatBuffer floatBuffer;
   public int count;
   public static final int count2 = 28;
   public int count3;
   public static final int count4 = 4;
   public List list = new ArrayList();
   public static final int count5 = 3;

   public void glDeleteBuffers() {
      GL15.glDeleteBuffers(this.count);
   }

   public FloatBufferController(int count6) {
      this.count3 = count6;
      this.updateState();
   }

   private void updateState() {
      int count6 = this.count3 * 28;
      ByteBuffer buffer = ByteBuffer.allocateDirect(count6).order(ByteOrder.nativeOrder());
      this.floatBuffer = buffer.asFloatBuffer();
      this.count = GL15.glGenBuffers();
      GL15.glBindBuffer(34962, this.count);
      GL15.glBufferData(34962, (long)count6, 35048);
      GL15.glBindBuffer(34962, 0);
   }

   private void applyIndex(int count6) {
      float[] factorArray = new float[this.floatBuffer.position()];
      ((Buffer)this.floatBuffer).rewind();
      this.floatBuffer.get(factorArray);
      this.count3 = count6;
      this.updateState();
      this.floatBuffer.put(factorArray);
      ((Buffer)this.floatBuffer).flip();
   }

   public void updateState2() {
      if (!this.list.isEmpty()) {
         Entity renderViewEntity = Minecraft.getMinecraft().getRenderViewEntity();
         GL11.glPushMatrix();
         GL11.glPushAttrib(1048575);
         GL11.glDisable(2884);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         GL11.glEnable(2929);
         GL11.glDisable(3553);
         GL11.glDisable(2896);
         GL11.glEnableClientState(32884);
         GL11.glEnableClientState(32886);
         GL15.glBindBuffer(34962, this.count);
         GL11.glVertexPointer(3, 5126, 28, 0L);
         GL11.glColorPointer(4, 5126, 28, 12L);
         int count6 = this.getCount();
         GL11.glDrawArrays(4, 0, count6);
         GL15.glBindBuffer(34962, 0);
         GL11.glPopAttrib();
         GL11.glPopMatrix();
      }
   }

   private static int computeCount(RecordNode2 recordNode) {
      return recordNode.factorArray.length / 3;
   }

   public void applyRecordNodeBridge(RecordNodeBridge recordNodeBridge) {
      this.list.add(new RecordNode2(recordNodeBridge.factorArray2, recordNodeBridge.factorArray));
      this.setEnabled(true);
   }

   private int getCount() {
      return this.list.stream().mapToInt((item -> FloatBufferController.computeCount((RecordNode2)item))).sum();
   }

   private void setEnabled(boolean enabled) {
      int count6 = this.getCount();
      if (enabled && count6 > this.count3) {
         int count7 = (int)((double)count6 * 1.5);
         this.applyIndex(count7);
         this.setEnabled(false);
      } else {
         ((Buffer)this.floatBuffer).clear();

         for (RecordNode2 recordNode : (Iterable<RecordNode2>)(Iterable)(this.list)) {
            int count8 = recordNode.factorArray.length / 3;

            for (int index = 0; index < count8; index++) {
               this.floatBuffer.put(recordNode.factorArray[index * 3]);
               this.floatBuffer.put(recordNode.factorArray[index * 3 + 1]);
               this.floatBuffer.put(recordNode.factorArray[index * 3 + 2]);
               this.floatBuffer.put(recordNode.factorArray2[index * 4]);
               this.floatBuffer.put(recordNode.factorArray2[index * 4 + 1]);
               this.floatBuffer.put(recordNode.factorArray2[index * 4 + 2]);
               this.floatBuffer.put(recordNode.factorArray2[index * 4 + 3]);
            }
         }

         ((Buffer)this.floatBuffer).flip();
         GL15.glBindBuffer(34962, this.count);
         GL15.glBufferSubData(34962, 0L, this.floatBuffer);
         GL15.glBindBuffer(34962, 0);
      }
   }
}
