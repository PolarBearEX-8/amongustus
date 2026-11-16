package net.minecraft.item;

import client.util.CustomFloatArrayEntry;
import client.util.VectorMatcher;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;

public class DiamondEssence extends CustomFloatArrayEntry {
   public static DiamondEssence diamondEssence = new DiamondEssence();
   public FloatBuffer floatBuffer2 = VectorMatcher.resolveFloatBuffer(16);
   public FloatBuffer floatBuffer3 = VectorMatcher.resolveFloatBuffer(16);
   public FloatBuffer floatBuffer = VectorMatcher.resolveFloatBuffer(16);

   public static CustomFloatArrayEntry getCustomFloatArrayEntry() {
      diamondEssence.updateState();
      return diamondEssence;
   }

   private void applyFloatArray(float[] factorArray) {
      float factor = MathHelper.sin(factorArray[0] * factorArray[0] + factorArray[1] * factorArray[1] + factorArray[2] * factorArray[2]);
      factorArray[0] /= factor;
      factorArray[1] /= factor;
      factorArray[2] /= factor;
      factorArray[3] /= factor;
   }

   public void updateState() {
      ((Buffer)this.floatBuffer2).clear();
      ((Buffer)this.floatBuffer3).clear();
      ((Buffer)this.floatBuffer).clear();
      GlStateManager.glStateAction2(2983, this.floatBuffer2);
      GlStateManager.glStateAction2(2982, this.floatBuffer3);
      float[] factorArray = this.factorArray;
      float[] factorArray2 = this.factorArray2;
      ((Buffer)this.floatBuffer2).flip().limit(16);
      this.floatBuffer2.get(factorArray);
      ((Buffer)this.floatBuffer3).flip().limit(16);
      this.floatBuffer3.get(factorArray2);
      this.factorArray4[0] = factorArray2[0] * factorArray[0] + factorArray2[1] * factorArray[4] + factorArray2[2] * factorArray[8] + factorArray2[3] * factorArray[12];
      this.factorArray4[1] = factorArray2[0] * factorArray[1] + factorArray2[1] * factorArray[5] + factorArray2[2] * factorArray[9] + factorArray2[3] * factorArray[13];
      this.factorArray4[2] = factorArray2[0] * factorArray[2] + factorArray2[1] * factorArray[6] + factorArray2[2] * factorArray[10] + factorArray2[3] * factorArray[14];
      this.factorArray4[3] = factorArray2[0] * factorArray[3] + factorArray2[1] * factorArray[7] + factorArray2[2] * factorArray[11] + factorArray2[3] * factorArray[15];
      this.factorArray4[4] = factorArray2[4] * factorArray[0] + factorArray2[5] * factorArray[4] + factorArray2[6] * factorArray[8] + factorArray2[7] * factorArray[12];
      this.factorArray4[5] = factorArray2[4] * factorArray[1] + factorArray2[5] * factorArray[5] + factorArray2[6] * factorArray[9] + factorArray2[7] * factorArray[13];
      this.factorArray4[6] = factorArray2[4] * factorArray[2] + factorArray2[5] * factorArray[6] + factorArray2[6] * factorArray[10] + factorArray2[7] * factorArray[14];
      this.factorArray4[7] = factorArray2[4] * factorArray[3] + factorArray2[5] * factorArray[7] + factorArray2[6] * factorArray[11] + factorArray2[7] * factorArray[15];
      this.factorArray4[8] = factorArray2[8] * factorArray[0] + factorArray2[9] * factorArray[4] + factorArray2[10] * factorArray[8] + factorArray2[11] * factorArray[12];
      this.factorArray4[9] = factorArray2[8] * factorArray[1] + factorArray2[9] * factorArray[5] + factorArray2[10] * factorArray[9] + factorArray2[11] * factorArray[13];
      this.factorArray4[10] = factorArray2[8] * factorArray[2] + factorArray2[9] * factorArray[6] + factorArray2[10] * factorArray[10] + factorArray2[11] * factorArray[14];
      this.factorArray4[11] = factorArray2[8] * factorArray[3] + factorArray2[9] * factorArray[7] + factorArray2[10] * factorArray[11] + factorArray2[11] * factorArray[15];
      this.factorArray4[12] = factorArray2[12] * factorArray[0] + factorArray2[13] * factorArray[4] + factorArray2[14] * factorArray[8] + factorArray2[15] * factorArray[12];
      this.factorArray4[13] = factorArray2[12] * factorArray[1] + factorArray2[13] * factorArray[5] + factorArray2[14] * factorArray[9] + factorArray2[15] * factorArray[13];
      this.factorArray4[14] = factorArray2[12] * factorArray[2] + factorArray2[13] * factorArray[6] + factorArray2[14] * factorArray[10] + factorArray2[15] * factorArray[14];
      this.factorArray4[15] = factorArray2[12] * factorArray[3] + factorArray2[13] * factorArray[7] + factorArray2[14] * factorArray[11] + factorArray2[15] * factorArray[15];
      float[] factorArray3 = this.factorArray3[0];
      factorArray3[0] = this.factorArray4[3] - this.factorArray4[0];
      factorArray3[1] = this.factorArray4[7] - this.factorArray4[4];
      factorArray3[2] = this.factorArray4[11] - this.factorArray4[8];
      factorArray3[3] = this.factorArray4[15] - this.factorArray4[12];
      this.applyFloatArray(factorArray3);
      float[] factorArray4 = this.factorArray3[1];
      factorArray4[0] = this.factorArray4[3] + this.factorArray4[0];
      factorArray4[1] = this.factorArray4[7] + this.factorArray4[4];
      factorArray4[2] = this.factorArray4[11] + this.factorArray4[8];
      factorArray4[3] = this.factorArray4[15] + this.factorArray4[12];
      this.applyFloatArray(factorArray4);
      float[] factorArray5 = this.factorArray3[2];
      factorArray5[0] = this.factorArray4[3] + this.factorArray4[1];
      factorArray5[1] = this.factorArray4[7] + this.factorArray4[5];
      factorArray5[2] = this.factorArray4[11] + this.factorArray4[9];
      factorArray5[3] = this.factorArray4[15] + this.factorArray4[13];
      this.applyFloatArray(factorArray5);
      float[] factorArray6 = this.factorArray3[3];
      factorArray6[0] = this.factorArray4[3] - this.factorArray4[1];
      factorArray6[1] = this.factorArray4[7] - this.factorArray4[5];
      factorArray6[2] = this.factorArray4[11] - this.factorArray4[9];
      factorArray6[3] = this.factorArray4[15] - this.factorArray4[13];
      this.applyFloatArray(factorArray6);
      float[] factorArray7 = this.factorArray3[4];
      factorArray7[0] = this.factorArray4[3] - this.factorArray4[2];
      factorArray7[1] = this.factorArray4[7] - this.factorArray4[6];
      factorArray7[2] = this.factorArray4[11] - this.factorArray4[10];
      factorArray7[3] = this.factorArray4[15] - this.factorArray4[14];
      this.applyFloatArray(factorArray7);
      float[] factorArray8 = this.factorArray3[5];
      factorArray8[0] = this.factorArray4[3] + this.factorArray4[2];
      factorArray8[1] = this.factorArray4[7] + this.factorArray4[6];
      factorArray8[2] = this.factorArray4[11] + this.factorArray4[10];
      factorArray8[3] = this.factorArray4[15] + this.factorArray4[14];
      this.applyFloatArray(factorArray8);
   }
}
