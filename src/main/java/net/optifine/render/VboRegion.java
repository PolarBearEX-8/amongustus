package net.optifine.render;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.VboRenderList;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumWorldBlockLayer;
import net.optifine.config.Config;
import net.optifine.util.LinkedList;

public class VboRegion {
   private EnumWorldBlockLayer layer = null;
   private int glBufferId = OpenGlHelper.glGenBuffers();
   private int capacity = 4096;
   private int positionTop = 0;
   private int sizeUsed;
   private LinkedList rangeList = new LinkedList();
   private VboRange compactRangeLast = null;
   private IntBuffer bufferIndexVertex = Config.createDirectIntBuffer(this.capacity);
   private IntBuffer bufferCountVertex = Config.createDirectIntBuffer(this.capacity);
   private int drawMode = 7;
   private final int vertexBytes = DefaultVertexFormats.BLOCK.getNextOffset();

   public void bufferData(ByteBuffer buf, VboRange vboRange) {
      int position = vboRange.getPosition();
      int size = vboRange.getSize();
      int n = this.toVertex((long)buf.limit());
      if (n <= 0) {
         if (position >= 0) {
            vboRange.setPosition(-1);
            vboRange.setSize(0);
            this.rangeList.remove(vboRange.getNode());
            this.sizeUsed -= size;
         }
      } else {
         if (n > size) {
            vboRange.setPosition(this.positionTop);
            vboRange.setSize(n);
            this.positionTop += n;
            if (position >= 0) {
               this.rangeList.remove(vboRange.getNode());
            }

            this.rangeList.addLast(vboRange.getNode());
         }

         vboRange.setSize(n);
         this.sizeUsed += n - size;
         this.checkVboSize(vboRange.getPositionNext());
         long l = this.toBytes(vboRange.getPosition());
         this.bindBuffer();
         OpenGlHelper.glBufferSubData(OpenGlHelper.GL_ARRAY_BUFFER, l, buf);
         this.unbindBuffer();
         if (this.positionTop > this.sizeUsed * 11 / 10) {
            this.compactRanges(1);
         }
      }
   }

   private void copyVboData(int n, int n2, int n3) {
      long l = this.toBytes(n);
      long l2 = this.toBytes(n2);
      long l3 = this.toBytes(n3);
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_COPY_READ_BUFFER, this.glBufferId);
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_COPY_WRITE_BUFFER, this.glBufferId);
      OpenGlHelper.glCopyBufferSubData(OpenGlHelper.GL_COPY_READ_BUFFER, OpenGlHelper.GL_COPY_WRITE_BUFFER, l, l2, l3);
      Config.checkGlError("Copy VBO range");
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_COPY_READ_BUFFER, 0);
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_COPY_WRITE_BUFFER, 0);
   }

   public int getPositionTop() {
      return this.positionTop;
   }

   private void checkVboSize(int n) {
      if (this.capacity < n) {
         this.expandVbo(n);
      }
   }

   public void deleteGlBuffers() {
      if (this.glBufferId >= 0) {
         OpenGlHelper.glDeleteBuffers(this.glBufferId);
         this.glBufferId = -1;
      }
   }

   public VboRegion(EnumWorldBlockLayer enumWorldBlockLayer) {
      this.layer = enumWorldBlockLayer;
      this.bindBuffer();
      long l = this.toBytes(this.capacity);
      OpenGlHelper.glBufferData2(OpenGlHelper.GL_ARRAY_BUFFER, l, OpenGlHelper.GL_STATIC_DRAW);
      this.unbindBuffer();
   }

   private void compactRanges(int n) {
      if (!this.rangeList.isEmpty()) {
         VboRange compactRangeLast2 = this.compactRangeLast;
         if (compactRangeLast2 == null || !this.rangeList.contains(compactRangeLast2.getNode())) {
            compactRangeLast2 = (VboRange)this.rangeList.getFirst().getItem();
         }

         int position = compactRangeLast2.getPosition();
         VboRange prev = compactRangeLast2.getPrev();
         if (prev == null) {
            position = 0;
         } else {
            position = prev.getPositionNext();
         }

         int n2 = 0;

         while (compactRangeLast2 != null && n2 < n) {
            n2++;
            if (compactRangeLast2.getPosition() == position) {
               position += compactRangeLast2.getSize();
               compactRangeLast2 = compactRangeLast2.getNext();
            } else {
               int n3 = compactRangeLast2.getPosition() - position;
               if (compactRangeLast2.getSize() <= n3) {
                  this.copyVboData(compactRangeLast2.getPosition(), position, compactRangeLast2.getSize());
                  compactRangeLast2.setPosition(position);
                  position += compactRangeLast2.getSize();
                  compactRangeLast2 = compactRangeLast2.getNext();
               } else {
                  this.checkVboSize(this.positionTop + compactRangeLast2.getSize());
                  this.copyVboData(compactRangeLast2.getPosition(), this.positionTop, compactRangeLast2.getSize());
                  compactRangeLast2.setPosition(this.positionTop);
                  this.positionTop = this.positionTop + compactRangeLast2.getSize();
                  VboRange next = compactRangeLast2.getNext();
                  this.rangeList.remove(compactRangeLast2.getNode());
                  this.rangeList.addLast(compactRangeLast2.getNode());
                  compactRangeLast2 = next;
               }
            }
         }

         if (compactRangeLast2 == null) {
            this.positionTop = ((VboRange)this.rangeList.getLast().getItem()).getPositionNext();
         }

         this.compactRangeLast = compactRangeLast2;
      }
   }

   private long toBytes(int n) {
      return (long)n * (long)this.vertexBytes;
   }

   public void drawArrays(int n, VboRange vboRange) {
      if (this.drawMode != n) {
         if (this.bufferIndexVertex.position() > 0) {
            throw new IllegalArgumentException("Mixed region draw modes: " + this.drawMode + " != " + n);
         }

         this.drawMode = n;
      }

      this.bufferIndexVertex.put(vboRange.getPosition());
      this.bufferCountVertex.put(vboRange.getSize());
   }

   private int toVertex(long l) {
      return (int)(l / (long)this.vertexBytes);
   }

   public void finishDraw(VboRenderList vboRenderList) {
      this.bindBuffer();
      vboRenderList.setupArrayPointers();
      ((Buffer)this.bufferIndexVertex).flip();
      ((Buffer)this.bufferCountVertex).flip();
      GlStateManager.glMultiDrawArrays(this.drawMode, this.bufferIndexVertex, this.bufferCountVertex);
      ((Buffer)this.bufferIndexVertex).limit(this.bufferIndexVertex.capacity());
      ((Buffer)this.bufferCountVertex).limit(this.bufferCountVertex.capacity());
      if (this.positionTop > this.sizeUsed * 11 / 10) {
         this.compactRanges(1);
      }
   }

   private void checkRanges() {
      int n = 0;
      int n2 = 0;

      for (VboRange i = (VboRange)this.rangeList.getFirst().getItem(); i != null; i = i.getNext()) {
         n++;
         n2 += i.getSize();
         if (i.getPosition() < 0 || i.getSize() <= 0 || i.getPositionNext() > this.positionTop) {
            throw new RuntimeException("Invalid range: " + i);
         }

         VboRange prev = i.getPrev();
         if (prev != null && i.getPosition() < prev.getPositionNext()) {
            throw new RuntimeException("Invalid range: " + i);
         }

         VboRange next = i.getNext();
         if (next != null && i.getPositionNext() > next.getPosition()) {
            throw new RuntimeException("Invalid range: " + i);
         }
      }

      if (n != this.rangeList.getSize()) {
         throw new RuntimeException("Invalid count: " + n + " <> " + this.rangeList.getSize());
      } else if (n2 != this.sizeUsed) {
         throw new RuntimeException("Invalid size: " + n2 + " <> " + this.sizeUsed);
      }
   }

   public void bindBuffer() {
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, this.glBufferId);
   }

   private void expandVbo(int n) {
      int n2 = this.capacity * 6 / 4;

      while (n2 < n) {
         n2 = n2 * 6 / 4;
      }

      long l = this.toBytes(this.capacity);
      long l2 = this.toBytes(n2);
      int n3 = OpenGlHelper.glGenBuffers();
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, n3);
      OpenGlHelper.glBufferData2(OpenGlHelper.GL_ARRAY_BUFFER, l2, OpenGlHelper.GL_STATIC_DRAW);
      Config.checkGlError("Expand VBO");
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, 0);
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_COPY_READ_BUFFER, this.glBufferId);
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_COPY_WRITE_BUFFER, n3);
      OpenGlHelper.glCopyBufferSubData(OpenGlHelper.GL_COPY_READ_BUFFER, OpenGlHelper.GL_COPY_WRITE_BUFFER, 0L, 0L, l);
      Config.checkGlError("Copy VBO: " + l2);
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_COPY_READ_BUFFER, 0);
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_COPY_WRITE_BUFFER, 0);
      OpenGlHelper.glDeleteBuffers(this.glBufferId);
      this.bufferIndexVertex = Config.createDirectIntBuffer(n2);
      this.bufferCountVertex = Config.createDirectIntBuffer(n2);
      this.glBufferId = n3;
      this.capacity = n2;
   }

   public void unbindBuffer() {
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, 0);
   }
}
