package net.minecraft.client.renderer.vertex;

import java.nio.ByteBuffer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.optifine.render.VboRange;
import net.optifine.render.VboRegion;
import org.lwjgl.opengl.GL11;

public class VertexBuffer {
   private int glBufferId;
   private final VertexFormat vertexFormat;
   private int count;
   private VboRegion vboRegion;
   private VboRange vboRange;
   private int drawMode;

   public void bindBuffer() {
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, this.glBufferId);
   }

   public VertexBuffer(VertexFormat vertexFormat2) {
      this.vertexFormat = vertexFormat2;
      this.glBufferId = OpenGlHelper.glGenBuffers();
   }

   public void deleteGlBuffers() {
      if (this.glBufferId >= 0) {
         OpenGlHelper.glDeleteBuffers(this.glBufferId);
         this.glBufferId = -1;
      }
   }

   public VboRange getVboRange() {
      return this.vboRange;
   }

   public void unbindBuffer() {
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, 0);
   }

   public void setVboRegion(VboRegion vboRegion2) {
      if (vboRegion2 != null) {
         this.deleteGlBuffers();
         this.vboRegion = vboRegion2;
         this.vboRange = new VboRange();
      }
   }

   public void bufferData(ByteBuffer buf) {
      if (this.vboRegion != null) {
         this.vboRegion.bufferData(buf, this.vboRange);
      } else {
         this.bindBuffer();
         OpenGlHelper.glBufferData(OpenGlHelper.GL_ARRAY_BUFFER, buf, 35044);
         this.unbindBuffer();
         this.count = buf.limit() / this.vertexFormat.getNextOffset();
      }
   }

   public void drawArrays(int n) {
      if (this.drawMode > 0) {
         n = this.drawMode;
      }

      if (this.vboRegion != null) {
         this.vboRegion.drawArrays(n, this.vboRange);
      } else {
         GL11.glDrawArrays(n, 0, this.count);
      }
   }

   public int getDrawMode() {
      return this.drawMode;
   }

   public VboRegion getVboRegion() {
      return this.vboRegion;
   }

   public void setDrawMode(int n) {
      this.drawMode = n;
   }
}
