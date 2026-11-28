package net.augustus.render;

import net.augustus.utils.render.ShaderRenderPass;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.LinkedList;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class CubeVboBatchRenderer {
   private FloatBuffer vertexBuffer;
   private GlStateSnapshot glStateSnapshot;
   private static final int FLOAT_BYTES = 4;
   private static final int COLOR_COMPONENTS = 4;
   private int vboId;
   private static final int POSITION_COMPONENTS = 3;
   private static final int VERTICES_PER_CUBE = 36;
   private static final int VERTEX_STRIDE_BYTES = 28;
   private int capacityCubes;
   private final LinkedList freeSlotIndices;
   private final int initialCapacity;

   public void uploadCubeData(VboCubeEntry vboCubeEntry, boolean flag) {
      FloatBuffer cubeVertexBuffer = vboCubeEntry.getCubeVertexBuffer();
      ((Buffer)cubeVertexBuffer).flip();
      ((Buffer)cubeVertexBuffer).limit(cubeVertexBuffer.capacity());
      if (flag) {
         ((Buffer)cubeVertexBuffer).limit(cubeVertexBuffer.capacity());
         this.clearBufferWithOnes(cubeVertexBuffer);
      } else {
         vboCubeEntry.writeCubeVertices(cubeVertexBuffer);
      }

      long l = (long)vboCubeEntry.getSlotIndex() * 36L * 28L;
      ((Buffer)cubeVertexBuffer).flip();
      ((Buffer)this.vertexBuffer).limit(this.vertexBuffer.capacity());
      ((Buffer)this.vertexBuffer).position(vboCubeEntry.getSlotIndex() * 36 * 7);
      this.vertexBuffer.put(cubeVertexBuffer);
      ((Buffer)this.vertexBuffer).flip();
      ((Buffer)cubeVertexBuffer).flip();
      GL15.glBindBuffer(34962, this.vboId);
      GL15.glBufferSubData(34962, l, cubeVertexBuffer);
      GL15.glBindBuffer(34962, 0);
   }

   public void addCube(VboCubeEntry vboCubeEntry) {
      if (this.freeSlotIndices.isEmpty()) {
         this.resizeBuffer((int)((float)this.capacityCubes * 1.5F));
      }

      int n2 = (Integer)this.freeSlotIndices.poll();
      vboCubeEntry.attachToRenderer(this);
      vboCubeEntry.setSlotIndex(n2);
      this.updateCube(vboCubeEntry);
   }

   public void render() {
      if (this.freeSlotIndices.size() != this.capacityCubes) {
         this.glStateSnapshot.capture((RenderPassTask)null);
         GL11.glPushAttrib(8192);
         GlStateManager.disableAlpha();
         GlStateManager.disableCull();
         GL11.glEnableClientState(32884);
         GL11.glEnableClientState(32886);
         GL15.glBindBuffer(34962, this.vboId);
         GL11.glVertexPointer(3, 5126, 28, 0L);
         GL11.glColorPointer(4, 5126, 28, 12L);
         GL11.glDrawArrays(4, 0, this.capacityCubes * 36);
         GL15.glBindBuffer(34962, 0);
         GL11.glDisableClientState(32884);
         GL11.glDisableClientState(32886);
         GL11.glPopAttrib();
         this.glStateSnapshot.restore((ShaderRenderPass)null);
      }
   }

   public void clear() {
      this.resetFreeSlots();
      this.resizeBuffer(this.initialCapacity);
      this.capacityCubes = this.initialCapacity;
   }

   private void enqueueSlotRange(int n, int n2) {

      for (int i = n; i < n2; i++) {
         this.freeSlotIndices.add(i);
      }
   }

   private void fillBufferWithOnes(FloatBuffer floatBuffer, int n) {
      ((Buffer)floatBuffer).position(n);

      for (int i = n; i < floatBuffer.capacity(); i++) {
         floatBuffer.put(1.0F);
      }
   }

   public void removeCube(VboCubeEntry vboCubeEntry) {
      this.freeSlotIndices.add(vboCubeEntry.getSlotIndex());
      this.uploadCubeData(vboCubeEntry, true);
   }

   private void clearBufferWithOnes(FloatBuffer floatBuffer) {
      this.fillBufferWithOnes(floatBuffer, (byte)0);
   }

   public FloatBuffer allocateCubeFloatBuffer(int n) {
      return ByteBuffer.allocateDirect(n * 36 * 28).order(ByteOrder.nativeOrder()).asFloatBuffer();
   }

   private void resizeBuffer(int n) {
      int n2 = n - this.capacityCubes;
      if (n2 > 0) {
         this.enqueueSlotRange(this.capacityCubes, n);
      }

      this.capacityCubes = n;
      FloatBuffer floatBuffer = this.allocateCubeFloatBuffer(n);
      if (this.vboId != 0) {
         GL15.glDeleteBuffers(this.vboId);
      }

      this.vboId = GL15.glGenBuffers();
      if (this.vertexBuffer != null && n2 > 0) {
         ((Buffer)this.vertexBuffer).flip();
         ((Buffer)this.vertexBuffer).limit(this.vertexBuffer.capacity());
         floatBuffer.put(this.vertexBuffer);
         this.fillBufferWithOnes(floatBuffer, this.vertexBuffer.capacity());
         this.vertexBuffer = floatBuffer;
      } else {
         this.vertexBuffer = floatBuffer;
         this.clearBufferWithOnes(this.vertexBuffer);
      }

      ((Buffer)this.vertexBuffer).flip();
      GL15.glBindBuffer(34962, this.vboId);
      GL15.glBufferData(34962, this.vertexBuffer, 35048);
      GL15.glBindBuffer(34962, 0);
   }

   private void resetFreeSlots() {
      this.freeSlotIndices.clear();
      this.enqueueSlotRange((byte)0, this.initialCapacity);
   }

   public CubeVboBatchRenderer(int n) {
       super();
      this.freeSlotIndices = new LinkedList();
      this.vboId = 0;
      this.glStateSnapshot = new GlStateSnapshot();
      this.vertexBuffer = null;
      this.initialCapacity = n;
      this.capacityCubes = n;
      this.resetFreeSlots();
      this.resizeBuffer(n);
   }

   public void updateCube(VboCubeEntry vboCubeEntry) {
      this.uploadCubeData(vboCubeEntry, false);
   }



}
