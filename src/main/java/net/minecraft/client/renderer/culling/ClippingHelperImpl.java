package net.minecraft.client.renderer.culling;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;

public class ClippingHelperImpl extends ClippingHelper {
   private static ClippingHelperImpl instance = new ClippingHelperImpl();
   private FloatBuffer projectionMatrixBuffer = GLAllocation.createDirectFloatBuffer(16);
   private FloatBuffer modelviewMatrixBuffer = GLAllocation.createDirectFloatBuffer(16);
   private FloatBuffer clippingMatrixBuffer = GLAllocation.createDirectFloatBuffer(16);

   public void init() {
      ((Buffer)this.projectionMatrixBuffer).clear();
      ((Buffer)this.modelviewMatrixBuffer).clear();
      ((Buffer)this.clippingMatrixBuffer).clear();
      GlStateManager.getFloat(2983, this.projectionMatrixBuffer);
      GlStateManager.getFloat(2982, this.modelviewMatrixBuffer);
      float[] projectionMatrix2 = this.projectionMatrix;
      float[] modelviewMatrix2 = this.modelviewMatrix;
      ((Buffer)this.projectionMatrixBuffer).flip().limit(16);
      this.projectionMatrixBuffer.get(projectionMatrix2);
      ((Buffer)this.modelviewMatrixBuffer).flip().limit(16);
      this.modelviewMatrixBuffer.get(modelviewMatrix2);
      this.clippingMatrix[0] = modelviewMatrix2[0] * projectionMatrix2[0] + modelviewMatrix2[1] * projectionMatrix2[4] + modelviewMatrix2[2] * projectionMatrix2[8] + modelviewMatrix2[3] * projectionMatrix2[12];
      this.clippingMatrix[1] = modelviewMatrix2[0] * projectionMatrix2[1] + modelviewMatrix2[1] * projectionMatrix2[5] + modelviewMatrix2[2] * projectionMatrix2[9] + modelviewMatrix2[3] * projectionMatrix2[13];
      this.clippingMatrix[2] = modelviewMatrix2[0] * projectionMatrix2[2] + modelviewMatrix2[1] * projectionMatrix2[6] + modelviewMatrix2[2] * projectionMatrix2[10] + modelviewMatrix2[3] * projectionMatrix2[14];
      this.clippingMatrix[3] = modelviewMatrix2[0] * projectionMatrix2[3] + modelviewMatrix2[1] * projectionMatrix2[7] + modelviewMatrix2[2] * projectionMatrix2[11] + modelviewMatrix2[3] * projectionMatrix2[15];
      this.clippingMatrix[4] = modelviewMatrix2[4] * projectionMatrix2[0] + modelviewMatrix2[5] * projectionMatrix2[4] + modelviewMatrix2[6] * projectionMatrix2[8] + modelviewMatrix2[7] * projectionMatrix2[12];
      this.clippingMatrix[5] = modelviewMatrix2[4] * projectionMatrix2[1] + modelviewMatrix2[5] * projectionMatrix2[5] + modelviewMatrix2[6] * projectionMatrix2[9] + modelviewMatrix2[7] * projectionMatrix2[13];
      this.clippingMatrix[6] = modelviewMatrix2[4] * projectionMatrix2[2] + modelviewMatrix2[5] * projectionMatrix2[6] + modelviewMatrix2[6] * projectionMatrix2[10] + modelviewMatrix2[7] * projectionMatrix2[14];
      this.clippingMatrix[7] = modelviewMatrix2[4] * projectionMatrix2[3] + modelviewMatrix2[5] * projectionMatrix2[7] + modelviewMatrix2[6] * projectionMatrix2[11] + modelviewMatrix2[7] * projectionMatrix2[15];
      this.clippingMatrix[8] = modelviewMatrix2[8] * projectionMatrix2[0] + modelviewMatrix2[9] * projectionMatrix2[4] + modelviewMatrix2[10] * projectionMatrix2[8] + modelviewMatrix2[11] * projectionMatrix2[12];
      this.clippingMatrix[9] = modelviewMatrix2[8] * projectionMatrix2[1] + modelviewMatrix2[9] * projectionMatrix2[5] + modelviewMatrix2[10] * projectionMatrix2[9] + modelviewMatrix2[11] * projectionMatrix2[13];
      this.clippingMatrix[10] = modelviewMatrix2[8] * projectionMatrix2[2] + modelviewMatrix2[9] * projectionMatrix2[6] + modelviewMatrix2[10] * projectionMatrix2[10] + modelviewMatrix2[11] * projectionMatrix2[14];
      this.clippingMatrix[11] = modelviewMatrix2[8] * projectionMatrix2[3] + modelviewMatrix2[9] * projectionMatrix2[7] + modelviewMatrix2[10] * projectionMatrix2[11] + modelviewMatrix2[11] * projectionMatrix2[15];
      this.clippingMatrix[12] = modelviewMatrix2[12] * projectionMatrix2[0] + modelviewMatrix2[13] * projectionMatrix2[4] + modelviewMatrix2[14] * projectionMatrix2[8] + modelviewMatrix2[15] * projectionMatrix2[12];
      this.clippingMatrix[13] = modelviewMatrix2[12] * projectionMatrix2[1] + modelviewMatrix2[13] * projectionMatrix2[5] + modelviewMatrix2[14] * projectionMatrix2[9] + modelviewMatrix2[15] * projectionMatrix2[13];
      this.clippingMatrix[14] = modelviewMatrix2[12] * projectionMatrix2[2] + modelviewMatrix2[13] * projectionMatrix2[6] + modelviewMatrix2[14] * projectionMatrix2[10] + modelviewMatrix2[15] * projectionMatrix2[14];
      this.clippingMatrix[15] = modelviewMatrix2[12] * projectionMatrix2[3] + modelviewMatrix2[13] * projectionMatrix2[7] + modelviewMatrix2[14] * projectionMatrix2[11] + modelviewMatrix2[15] * projectionMatrix2[15];
      float[] floats = this.frustum[0];
      floats[0] = this.clippingMatrix[3] - this.clippingMatrix[0];
      floats[1] = this.clippingMatrix[7] - this.clippingMatrix[4];
      floats[2] = this.clippingMatrix[11] - this.clippingMatrix[8];
      floats[3] = this.clippingMatrix[15] - this.clippingMatrix[12];
      this.normalize(floats);
      float[] floats2 = this.frustum[1];
      floats2[0] = this.clippingMatrix[3] + this.clippingMatrix[0];
      floats2[1] = this.clippingMatrix[7] + this.clippingMatrix[4];
      floats2[2] = this.clippingMatrix[11] + this.clippingMatrix[8];
      floats2[3] = this.clippingMatrix[15] + this.clippingMatrix[12];
      this.normalize(floats2);
      float[] floats3 = this.frustum[2];
      floats3[0] = this.clippingMatrix[3] + this.clippingMatrix[1];
      floats3[1] = this.clippingMatrix[7] + this.clippingMatrix[5];
      floats3[2] = this.clippingMatrix[11] + this.clippingMatrix[9];
      floats3[3] = this.clippingMatrix[15] + this.clippingMatrix[13];
      this.normalize(floats3);
      float[] floats4 = this.frustum[3];
      floats4[0] = this.clippingMatrix[3] - this.clippingMatrix[1];
      floats4[1] = this.clippingMatrix[7] - this.clippingMatrix[5];
      floats4[2] = this.clippingMatrix[11] - this.clippingMatrix[9];
      floats4[3] = this.clippingMatrix[15] - this.clippingMatrix[13];
      this.normalize(floats4);
      float[] floats5 = this.frustum[4];
      floats5[0] = this.clippingMatrix[3] - this.clippingMatrix[2];
      floats5[1] = this.clippingMatrix[7] - this.clippingMatrix[6];
      floats5[2] = this.clippingMatrix[11] - this.clippingMatrix[10];
      floats5[3] = this.clippingMatrix[15] - this.clippingMatrix[14];
      this.normalize(floats5);
      float[] floats6 = this.frustum[5];
      floats6[0] = this.clippingMatrix[3] + this.clippingMatrix[2];
      floats6[1] = this.clippingMatrix[7] + this.clippingMatrix[6];
      floats6[2] = this.clippingMatrix[11] + this.clippingMatrix[10];
      floats6[3] = this.clippingMatrix[15] + this.clippingMatrix[14];
      this.normalize(floats6);
   }

   public static ClippingHelper getInstance() {
      instance.init();
      return instance;
   }

   private void normalize(float[] floats) {
      float f = MathHelper.sqrt_float(floats[0] * floats[0] + floats[1] * floats[1] + floats[2] * floats[2]);
      floats[0] /= f;
      floats[1] /= f;
      floats[2] /= f;
      floats[3] /= f;
   }
}
