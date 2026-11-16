package client.util;

import client.render.WorldRenderer;
import net.minecraft.item.ExtraItemArrayEntry;

public class DampenerSelector {
   public Vec3Entry4[] vec3Entry4Array;
   public ExtraItemArrayEntry[] extraItemArrayEntryArray;
   public final float factor4;
   public final float factor;
   public final float factor3;
   public final float factor6;
   public final float factor2;
   public final float factor5;
   public String labelText;

   public DampenerSelector withLabelText(String text) {
      this.labelText = text;
      return this;
   }

   private ExtraItemArrayEntry resolveExtraItemArrayEntry(Vec3Entry4[] vec3Entry4Array2, int[] countArray, boolean flag, float factor7, float factor8) {
      return countArray == null
         ? null
         : (
            flag
               ? new ExtraItemArrayEntry(vec3Entry4Array2, countArray[2], countArray[3], countArray[0], countArray[1], factor7, factor8)
               : new ExtraItemArrayEntry(vec3Entry4Array2, countArray[0], countArray[1], countArray[2], countArray[3], factor7, factor8)
         );
   }

   public void applyWorldRenderer(WorldRenderer worldRenderer, float factor7) {
      for (int index = 0; index < this.extraItemArrayEntryArray.length; index++) {
         ExtraItemArrayEntry extraItemArrayEntry = this.extraItemArrayEntryArray[index];
         if (extraItemArrayEntry != null) {
            extraItemArrayEntry.applyWorldRenderer(worldRenderer, factor7);
         }
      }
   }

   public DampenerSelector(BoxesManager boxesManager, int[][] countMatrix, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ, boolean flag) {
      this.factor4 = posX;
      this.factor = posX2;
      this.factor3 = posX3;
      this.factor6 = posX + posX4;
      this.factor2 = posX2 + posX5;
      this.factor5 = posX3 + posY;
      this.vec3Entry4Array = new Vec3Entry4[8];
      this.extraItemArrayEntryArray = new ExtraItemArrayEntry[6];
      float factor7 = posX + posX4;
      float factor8 = posX2 + posX5;
      float factor9 = posX3 + posY;
      posX -= posZ;
      posX2 -= posZ;
      posX3 -= posZ;
      float factor10 = factor7 + posZ;
      float factor11 = factor8 + posZ;
      float factor12 = factor9 + posZ;
      if (flag) {
         float factor13 = factor10;
         factor10 = posX;
         posX = factor13;
      }

      Vec3Entry4 vec3Entry = new Vec3Entry4(posX, posX2, posX3, 0.0F, 0.0F);
      Vec3Entry4 vec3Entry2 = new Vec3Entry4(factor10, posX2, posX3, 0.0F, 8.0F);
      Vec3Entry4 vec3Entry3 = new Vec3Entry4(factor10, factor11, posX3, 8.0F, 8.0F);
      Vec3Entry4 vec3Entry4 = new Vec3Entry4(posX, factor11, posX3, 8.0F, 0.0F);
      Vec3Entry4 vec3Entry5 = new Vec3Entry4(posX, posX2, factor12, 0.0F, 0.0F);
      Vec3Entry4 vec3Entry6 = new Vec3Entry4(factor10, posX2, factor12, 0.0F, 8.0F);
      Vec3Entry4 vec3Entry7 = new Vec3Entry4(factor10, factor11, factor12, 8.0F, 8.0F);
      Vec3Entry4 vec3Entry8 = new Vec3Entry4(posX, factor11, factor12, 8.0F, 0.0F);
      this.vec3Entry4Array[0] = vec3Entry;
      this.vec3Entry4Array[1] = vec3Entry2;
      this.vec3Entry4Array[2] = vec3Entry3;
      this.vec3Entry4Array[3] = vec3Entry4;
      this.vec3Entry4Array[4] = vec3Entry5;
      this.vec3Entry4Array[5] = vec3Entry6;
      this.vec3Entry4Array[6] = vec3Entry7;
      this.vec3Entry4Array[7] = vec3Entry8;
      this.extraItemArrayEntryArray[0] = this.resolveExtraItemArrayEntry(
         new Vec3Entry4[]{vec3Entry6, vec3Entry2, vec3Entry3, vec3Entry7}, countMatrix[4], false, boxesManager.factor12, boxesManager.factor11
      );
      this.extraItemArrayEntryArray[1] = this.resolveExtraItemArrayEntry(
         new Vec3Entry4[]{vec3Entry, vec3Entry5, vec3Entry8, vec3Entry4}, countMatrix[5], false, boxesManager.factor12, boxesManager.factor11
      );
      this.extraItemArrayEntryArray[2] = this.resolveExtraItemArrayEntry(
         new Vec3Entry4[]{vec3Entry6, vec3Entry5, vec3Entry, vec3Entry2}, countMatrix[1], true, boxesManager.factor12, boxesManager.factor11
      );
      this.extraItemArrayEntryArray[3] = this.resolveExtraItemArrayEntry(
         new Vec3Entry4[]{vec3Entry3, vec3Entry4, vec3Entry8, vec3Entry7}, countMatrix[0], true, boxesManager.factor12, boxesManager.factor11
      );
      this.extraItemArrayEntryArray[4] = this.resolveExtraItemArrayEntry(
         new Vec3Entry4[]{vec3Entry2, vec3Entry, vec3Entry4, vec3Entry3}, countMatrix[2], false, boxesManager.factor12, boxesManager.factor11
      );
      this.extraItemArrayEntryArray[5] = this.resolveExtraItemArrayEntry(
         new Vec3Entry4[]{vec3Entry5, vec3Entry6, vec3Entry7, vec3Entry8}, countMatrix[3], false, boxesManager.factor12, boxesManager.factor11
      );
      if (flag) {
         ExtraItemArrayEntry[] extraItemArrayEntryArray2 = this.extraItemArrayEntryArray;
         int length = extraItemArrayEntryArray2.length;

         for (int index = 0; index < length; index++) {
            extraItemArrayEntryArray2[index].updateState();
         }
      }
   }

   public DampenerSelector(BoxesManager boxesManager, int count, int count2, float posX, float posY, float posZ, int count3, int count4, int count5, float factor7) {
      this(boxesManager, count, count2, posX, posY, posZ, count3, count4, count5, factor7, boxesManager.enabled);
   }

   public DampenerSelector(BoxesManager boxesManager, int count, int count2, float posX, float posY, float posZ, int count3, int count4, int count5, float factor7, boolean flag) {
      this.factor4 = posX;
      this.factor = posY;
      this.factor3 = posZ;
      this.factor6 = posX + (float)count3;
      this.factor2 = posY + (float)count4;
      this.factor5 = posZ + (float)count5;
      this.vec3Entry4Array = new Vec3Entry4[8];
      this.extraItemArrayEntryArray = new ExtraItemArrayEntry[6];
      float factor8 = posX + (float)count3;
      float factor9 = posY + (float)count4;
      float factor10 = posZ + (float)count5;
      posX -= factor7;
      posY -= factor7;
      posZ -= factor7;
      float factor11 = factor8 + factor7;
      float factor12 = factor9 + factor7;
      float factor13 = factor10 + factor7;
      if (flag) {
         float factor14 = factor11;
         factor11 = posX;
         posX = factor14;
      }

      Vec3Entry4 vec3Entry = new Vec3Entry4(posX, posY, posZ, 0.0F, 0.0F);
      Vec3Entry4 vec3Entry2 = new Vec3Entry4(factor11, posY, posZ, 0.0F, 8.0F);
      Vec3Entry4 vec3Entry3 = new Vec3Entry4(factor11, factor12, posZ, 8.0F, 8.0F);
      Vec3Entry4 vec3Entry4 = new Vec3Entry4(posX, factor12, posZ, 8.0F, 0.0F);
      Vec3Entry4 vec3Entry5 = new Vec3Entry4(posX, posY, factor13, 0.0F, 0.0F);
      Vec3Entry4 vec3Entry6 = new Vec3Entry4(factor11, posY, factor13, 0.0F, 8.0F);
      Vec3Entry4 vec3Entry7 = new Vec3Entry4(factor11, factor12, factor13, 8.0F, 8.0F);
      Vec3Entry4 vec3Entry8 = new Vec3Entry4(posX, factor12, factor13, 8.0F, 0.0F);
      this.vec3Entry4Array[0] = vec3Entry;
      this.vec3Entry4Array[1] = vec3Entry2;
      this.vec3Entry4Array[2] = vec3Entry3;
      this.vec3Entry4Array[3] = vec3Entry4;
      this.vec3Entry4Array[4] = vec3Entry5;
      this.vec3Entry4Array[5] = vec3Entry6;
      this.vec3Entry4Array[6] = vec3Entry7;
      this.vec3Entry4Array[7] = vec3Entry8;
      this.extraItemArrayEntryArray[0] = new ExtraItemArrayEntry(
         new Vec3Entry4[]{vec3Entry6, vec3Entry2, vec3Entry3, vec3Entry7},
         count + count5 + count3,
         count2 + count5,
         count + count5 + count3 + count5,
         count2 + count5 + count4,
         boxesManager.factor12,
         boxesManager.factor11
      );
      this.extraItemArrayEntryArray[1] = new ExtraItemArrayEntry(
         new Vec3Entry4[]{vec3Entry, vec3Entry5, vec3Entry8, vec3Entry4}, count, count2 + count5, count + count5, count2 + count5 + count4, boxesManager.factor12, boxesManager.factor11
      );
      this.extraItemArrayEntryArray[2] = new ExtraItemArrayEntry(
         new Vec3Entry4[]{vec3Entry6, vec3Entry5, vec3Entry, vec3Entry2}, count + count5, count2, count + count5 + count3, count2 + count5, boxesManager.factor12, boxesManager.factor11
      );
      this.extraItemArrayEntryArray[3] = new ExtraItemArrayEntry(
         new Vec3Entry4[]{vec3Entry3, vec3Entry4, vec3Entry8, vec3Entry7}, count + count5 + count3, count2 + count5, count + count5 + count3 + count3, count2, boxesManager.factor12, boxesManager.factor11
      );
      this.extraItemArrayEntryArray[4] = new ExtraItemArrayEntry(
         new Vec3Entry4[]{vec3Entry2, vec3Entry, vec3Entry4, vec3Entry3}, count + count5, count2 + count5, count + count5 + count3, count2 + count5 + count4, boxesManager.factor12, boxesManager.factor11
      );
      this.extraItemArrayEntryArray[5] = new ExtraItemArrayEntry(
         new Vec3Entry4[]{vec3Entry5, vec3Entry6, vec3Entry7, vec3Entry8},
         count + count5 + count3 + count5,
         count2 + count5,
         count + count5 + count3 + count5 + count3,
         count2 + count5 + count4,
         boxesManager.factor12,
         boxesManager.factor11
      );
      if (flag) {
         for (int index = 0; index < this.extraItemArrayEntryArray.length; index++) {
            this.extraItemArrayEntryArray[index].updateState();
         }
      }
   }
}
