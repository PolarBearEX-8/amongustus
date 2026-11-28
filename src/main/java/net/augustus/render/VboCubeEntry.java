package net.augustus.render;

import net.augustus.utils.render.ColorUtil;
import java.nio.FloatBuffer;

public class VboCubeEntry {
   private float height;
   private float green;
   private float z;
   private float red;
   private boolean dirty;
   private float depth;
   private int slotIndex;
   private float alpha;
   private float blue;
   private float width;
   private FloatBuffer cubeVertexBuffer;
   private float y;
   private float x = 0.0F;
   private CubeVboBatchRenderer batchRenderer;

   public VboCubeEntry setColor(int color) {
      return this.setColor(ColorUtil.getRedFloat(color), ColorUtil.getGreenFloat(color), ColorUtil.getBlueFloat(color), ColorUtil.getAlphaFloat(color));
   }

   public void writeCubeVertices(FloatBuffer floatBuffer) {
      float f = this.x + this.width;
      float f2 = this.y + this.height;
      float f3 = this.z + this.depth;
      float f4 = this.z + this.depth;
      CubeFaceAxis cubeFaceAxis = CubeFaceAxis.Z;
      float f5 = f4;
      float f6 = f3;
      float f7 = f2;
      float f8 = this.y;
      float f9 = f;
      this.writeFace(floatBuffer, this.x, f9, f8, f7, f6, f5, cubeFaceAxis);
      f = this.x + this.width;
      f2 = this.y + this.height;
      CubeFaceAxis cubeFaceAxis2 = CubeFaceAxis.Z;
      float f11 = this.z;
      float f12 = this.z;
      float f13 = f2;
      float f14 = this.y;
      float f15 = f;
      this.writeFace(floatBuffer, this.x, f15, f14, f13, f12, f11, cubeFaceAxis2);
      f2 = this.y + this.height;
      f4 = this.z + this.depth;
      CubeFaceAxis cubeFaceAxis3 = CubeFaceAxis.X;
      float f17 = f4;
      float f18 = this.z;
      float f19 = f2;
      float f20 = this.y;
      float f21 = this.x;
      this.writeFace(floatBuffer, this.x, f21, f20, f19, f18, f17, cubeFaceAxis3);
      f = this.x + this.width;
      float f23 = this.x + this.width;
      f3 = this.y + this.height;
      float f24 = this.z + this.depth;
      CubeFaceAxis cubeFaceAxis4 = CubeFaceAxis.X;
      float f26 = this.z;
      float f27 = f3;
      this.writeFace(floatBuffer, f, f23, this.y, f27, f26, f24, cubeFaceAxis4);
      f = this.x + this.width;
      f23 = this.y + this.height;
      f2 = this.y + this.height;
      f4 = this.z + this.depth;
      CubeFaceAxis cubeFaceAxis5 = CubeFaceAxis.Y;
      float f31 = f4;
      float f32 = this.z;
      float f33 = f2;
      float f34 = f23;
      float f35 = f;
      this.writeFace(floatBuffer, this.x, f35, f34, f33, f32, f31, cubeFaceAxis5);
      f = this.x + this.width;
      f4 = this.z + this.depth;
      CubeFaceAxis cubeFaceAxis6 = CubeFaceAxis.Y;
      float f37 = f4;
      float f38 = this.z;
      float f39 = this.y;
      float f40 = this.y;
      float f41 = f;
      this.writeFace(floatBuffer, this.x, f41, f40, f39, f38, f37, cubeFaceAxis6);
   }

   public VboCubeEntry setDimensions(float f, float f2, float f3) {
      if (f != this.width || f2 != this.height || f3 != this.depth) {
         this.width = f;
         this.height = f2;
         this.depth = f3;
         this.dirty = true;
      }

      return this;
   }

   public VboCubeEntry setPosition(float f, float f2, float f3) {
      if (f != this.x || f2 != this.y || f3 != this.z) {
         this.x = f;
         this.y = f2;
         this.z = f3;
         this.dirty = true;
      }

      return this;
   }

   private void writeQuadAsTriangles(
      FloatBuffer floatBuffer,
      float f,
      float f2,
      float f3,
      float f4,
      float f5,
      float f6,
      float f7,
      float f8,
      float f9,
      float f10,
      float f11,
      float f12
   ) {
      this.writeVertex(floatBuffer, f, f2, f3);
      this.writeVertex(floatBuffer, f4, f5, f6);
      this.writeVertex(floatBuffer, f7, f8, f9);
      this.writeVertex(floatBuffer, f, f2, f3);
      this.writeVertex(floatBuffer, f7, f8, f9);
      this.writeVertex(floatBuffer, f10, f11, f12);
   }

   public void attachToRenderer(CubeVboBatchRenderer cubeVboBatchRenderer) {
      this.batchRenderer = cubeVboBatchRenderer;
      this.cubeVertexBuffer = cubeVboBatchRenderer.allocateCubeFloatBuffer((byte)1);
      this.dirty = false;
   }

   public int getSlotIndex() {
      return this.slotIndex;
   }

   public CubeVboBatchRenderer getBatchRenderer() {
      return this.batchRenderer;
   }

   private void writeVertex(FloatBuffer floatBuffer, float f, float f2, float f3) {
      floatBuffer.put(f).put(f2).put(f3).put(this.red).put(this.green).put(this.blue).put(this.alpha);
   }

   public VboCubeEntry setColor(float f, float f2, float f3, float f4) {
      if (this.red != f || this.green != f2 || this.blue != f3 || this.alpha != f4) {
         this.red = f;
         this.green = f2;
         this.blue = f3;
         this.alpha = f4;
         this.dirty = true;
      }

      return this;
   }

   public void flushIfDirty() {
      if (this.dirty && this.batchRenderer != null) {
         this.batchRenderer.updateCube(this);
         this.dirty = false;
      }
   }

   public FloatBuffer getCubeVertexBuffer() {
      return this.cubeVertexBuffer;
   }

   public void setSlotIndex(int n) {
      this.slotIndex = n;
   }

   private void writeFace(FloatBuffer floatBuffer, float f, float f2, float f3, float f4, float f5, float f6, CubeFaceAxis cubeFaceAxis) {
      switch (cubeFaceAxis) {
         case Z:
            this.writeQuadAsTriangles(floatBuffer, f, f3, f5, f2, f3, f5, f2, f4, f5, f, f4, f5);
            break;
         case X:
            this.writeQuadAsTriangles(floatBuffer, f, f3, f5, f, f3, f6, f, f4, f6, f, f4, f5);
            break;
         case Y:
            this.writeQuadAsTriangles(floatBuffer, f, f3, f5, f2, f3, f5, f2, f3, f6, f, f3, f6);
      }
   }

   public VboCubeEntry(float f, float f2, float f3, float f4, float f5, float f6) {
      this.red = 1.0F;
      this.green = 0.0F;
      this.blue = 0.0F;
      this.alpha = 0.7F;
      this.dirty = false;
      this.cubeVertexBuffer = null;
      this.x = f;
      this.y = f2;
      this.z = f3;
      this.width = f4;
      this.height = f5;
      this.depth = f6;
   }

   public VboCubeEntry(double d, double d2, double d3, double d4, double d5, double d6) {
      this.red = 1.0F;
      this.green = 0.0F;
      this.blue = 0.0F;
      this.alpha = 0.7F;
      this.dirty = false;
      this.cubeVertexBuffer = null;
      this.x = (float)d;
      this.y = (float)d2;
      this.z = (float)d3;
      this.width = (float)d4;
      this.height = (float)d5;
      this.depth = (float)d6;
   }
}
