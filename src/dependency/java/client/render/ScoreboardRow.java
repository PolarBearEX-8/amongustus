package client.render;

import client.util.RunnableTask;
import client.util.StepHistoryArray;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.LinkedList;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class ScoreboardRow {
   public static final int count3 = 4;
   public final LinkedList linkedList = new LinkedList();
   public StepHistoryArray stepHistoryArray;
   public int count5 = 0;
   public final int count;
   public static final int count7 = 4;
   public FloatBuffer floatBuffer;
   public static final int count6 = 36;
   public static final int count4 = 28;
   public int count2;
   public static final int count8 = 3;

   private void applyFloatBuffer(FloatBuffer buffer, int count9) {
      ((Buffer)buffer).position(count9);

      for (int index = count9; index < buffer.capacity(); index++) {
         buffer.put(1.0F);
      }
   }

   private void applyFloatBuffer(FloatBuffer buffer) {
      this.applyFloatBuffer(buffer, 0);
   }

   public void processAction() {
      this.renderElement();
      int count10 = this.count;
      this.applyIndex(count10);
      this.count2 = this.count;
   }

   public void updateExtendedFloatEntry(int count9, byte byteValue, BlockEspGeometry blockEspGeometry, int count10) {
      if (this.linkedList.isEmpty()) {
         int count13 = (int)((float)this.count2 * 1.5F);
         int count14 = count13;
         this.applyIndex(count14);
      }

      int count15 = (Integer)this.linkedList.poll();
      blockEspGeometry.applyScoreboardRow(this);
      blockEspGeometry.setCount(count15);
      this.applyBlockEspGeometry(blockEspGeometry);
   }

   private void renderElement() {
      this.linkedList.clear();
      int count10 = this.count;
      byte byteValue = 0;
      this.applyIndex(byteValue, count10);
   }

   public void updateExtendedFloatEntry(BlockEspGeometry blockEspGeometry) {
      this.linkedList.add(blockEspGeometry.getCount());
      this.applyBlockEspGeometry(blockEspGeometry, true);
   }

   public void applyBlockEspGeometry(BlockEspGeometry blockEspGeometry, boolean flag) {
      FloatBuffer floatBuffer2 = blockEspGeometry.getFloatBuffer();
      ((Buffer)floatBuffer2).flip();
      ((Buffer)floatBuffer2).limit(floatBuffer2.capacity());
      if (flag) {
         ((Buffer)floatBuffer2).limit(floatBuffer2.capacity());
         this.applyFloatBuffer(floatBuffer2);
      } else {
         blockEspGeometry.applyFloatBuffer(floatBuffer2);
      }

      long value = (long)blockEspGeometry.getCount() * 36L * 28L;
      ((Buffer)floatBuffer2).flip();
      ((Buffer)this.floatBuffer).limit(this.floatBuffer.capacity());
      ((Buffer)this.floatBuffer).position(blockEspGeometry.getCount() * 36 * 7);
      this.floatBuffer.put(floatBuffer2);
      ((Buffer)this.floatBuffer).flip();
      ((Buffer)floatBuffer2).flip();
      GL15.glBindBuffer(34962, this.count5);
      GL15.glBufferSubData(34962, value, floatBuffer2);
      GL15.glBindBuffer(34962, 0);
   }

   private void applyIndex(int count9) {
      int count10 = count9 - this.count2;
      if (count10 > 0) {
         int count11 = this.count2;
         this.applyIndex(count11, count9);
      }

      this.count2 = count9;
      FloatBuffer buffer = this.resolveFloatBuffer(count9);
      if (this.count5 != 0) {
         GL15.glDeleteBuffers(this.count5);
      }

      this.count5 = GL15.glGenBuffers();
      if (this.floatBuffer != null && count10 > 0) {
         ((Buffer)this.floatBuffer).flip();
         ((Buffer)this.floatBuffer).limit(this.floatBuffer.capacity());
         buffer.put(this.floatBuffer);
         int count12 = this.floatBuffer.capacity();
         this.applyFloatBuffer(buffer, count12);
         this.floatBuffer = buffer;
      } else {
         this.floatBuffer = buffer;
         FloatBuffer floatBuffer2 = this.floatBuffer;
         this.applyFloatBuffer(floatBuffer2);
      }

      ((Buffer)this.floatBuffer).flip();
      GL15.glBindBuffer(34962, this.count5);
      GL15.glBufferData(34962, this.floatBuffer, 35048);
      GL15.glBindBuffer(34962, 0);
   }

   public void applyBlockEspGeometry(BlockEspGeometry blockEspGeometry) {
      this.applyBlockEspGeometry(blockEspGeometry, false);
   }

   public void updateState() {
      if (this.linkedList.size() != this.count2) {
         this.stepHistoryArray.applyRunnableTask((RunnableTask)null);
         GL11.glPushAttrib(8192);
         GlStateManager.applyState10();
         GlStateManager.disableAlpha();
         GL11.glEnableClientState(32884);
         GL11.glEnableClientState(32886);
         GL15.glBindBuffer(34962, this.count5);
         GL11.glVertexPointer(3, 5126, 28, 0L);
         GL11.glColorPointer(4, 5126, 28, 12L);
         GL11.glDrawArrays(4, 0, this.count2 * 36);
         GL15.glBindBuffer(34962, 0);
         GL11.glDisableClientState(32884);
         GL11.glDisableClientState(32886);
         GL11.glPopAttrib();
         this.stepHistoryArray.applyRenderPassByRenderPass((RenderPass)null);
      }
   }

   private void applyIndex(int count9, int count10) {
      for (int index = count9; index < count10; index++) {
         this.linkedList.add(index);
      }
   }

   public FloatBuffer resolveFloatBuffer(int count9) {
      return ByteBuffer.allocateDirect(count9 * 36 * 28).order(ByteOrder.nativeOrder()).asFloatBuffer();
   }

   public ScoreboardRow(int count9) {
      this.stepHistoryArray = new StepHistoryArray();
      this.floatBuffer = null;
      this.count = count9;
      this.count2 = count9;
      this.renderElement();
      this.applyIndex(count9);
   }
}
