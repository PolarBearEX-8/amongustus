package client.util;

import client.setting.CombatSliderProperty;
import client.setting.OptionType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class SystemFloatEntry {
   public static final float factor2 = 1.0F / (float)Math.cos((float) (Math.PI / 8)) - 1.0F;
   public static final float factor = 1.0F / (float)Math.cos(Math.PI / 4) - 1.0F;

   public static EnumFacing resolveEnumFacing(int[] countArray) {
      int count = countArray.length / 4;
      int count2 = count * 2;
      Vector3f vector3f = new Vector3f(Float.intBitsToFloat(countArray[0]), Float.intBitsToFloat(countArray[1]), Float.intBitsToFloat(countArray[2]));
      Vector3f vector3f2 = new Vector3f(Float.intBitsToFloat(countArray[count]), Float.intBitsToFloat(countArray[count + 1]), Float.intBitsToFloat(countArray[count + 2]));
      Vector3f vector3f3 = new Vector3f(Float.intBitsToFloat(countArray[count2]), Float.intBitsToFloat(countArray[count2 + 1]), Float.intBitsToFloat(countArray[count2 + 2]));
      Vector3f vector3f4 = new Vector3f();
      Vector3f vector3f5 = new Vector3f();
      Vector3f vector3f6 = new Vector3f();
      Vector3f.sub(vector3f, vector3f2, vector3f4);
      Vector3f.sub(vector3f3, vector3f2, vector3f5);
      Vector3f.cross(vector3f5, vector3f4, vector3f6);
      float factor3 = (float)Math.sqrt((double)(vector3f6.x * vector3f6.x + vector3f6.y * vector3f6.y + vector3f6.z * vector3f6.z));
      vector3f6.x /= factor3;
      vector3f6.y /= factor3;
      vector3f6.z /= factor3;
      EnumFacing enumFacing = null;
      float factor4 = 0.0F;

      for (EnumFacing enumFacing2 : EnumFacing.getEntries()) {
         Vec3i vec3i = enumFacing2.getVec3i();
         Vector3f vector3f7 = new Vector3f((float)vec3i.getX(), (float)vec3i.getY(), (float)vec3i.getZ());
         float factor5 = Vector3f.dot(vector3f6, vector3f7);
         if (factor5 >= 0.0F && factor5 > factor4) {
            factor4 = factor5;
            enumFacing = enumFacing2;
         }
      }

      return enumFacing == null ? EnumFacing.UP : enumFacing;
   }

   private int computeCount(EnumFacing enumFacing) {
      int intItem = MathHelper.getInt12((int)(computeFactor(enumFacing) * 255.0F), 0, 255);
      return 0xFF000000 | intItem << 16 | intItem << 8 | intItem;
   }

   private void applyVector3f(Vector3f vector3f, Vector3fEntry vector3fEntry) {
      if (vector3fEntry != null) {
         Matrix4f matrix = this.setIdentity();
         Vector3f vector3f2 = new Vector3f(0.0F, 0.0F, 0.0F);
         switch (ManagedIntArrayEntry.countArray[vector3fEntry.enumFacingXType.ordinal()]) {
            case 1:
               Matrix4f.rotate(vector3fEntry.factor * (float) (Math.PI / 180.0), new Vector3f(1.0F, 0.0F, 0.0F), matrix, matrix);
               vector3f2.set(0.0F, 1.0F, 1.0F);
               break;
            case 2:
               Matrix4f.rotate(vector3fEntry.factor * (float) (Math.PI / 180.0), new Vector3f(0.0F, 1.0F, 0.0F), matrix, matrix);
               vector3f2.set(1.0F, 0.0F, 1.0F);
               break;
            case 3:
               Matrix4f.rotate(vector3fEntry.factor * (float) (Math.PI / 180.0), new Vector3f(0.0F, 0.0F, 1.0F), matrix, matrix);
               vector3f2.set(1.0F, 1.0F, 0.0F);
         }

         if (vector3fEntry.enabled) {
            if (Math.abs(vector3fEntry.factor) == 22.5F) {
               vector3f2.scale(factor2);
            } else {
               vector3f2.scale(factor);
            }

            Vector3f.add(vector3f2, new Vector3f(1.0F, 1.0F, 1.0F), vector3f2);
         } else {
            vector3f2.set(1.0F, 1.0F, 1.0F);
         }

         this.applyVector3f(vector3f, new Vector3f(vector3fEntry.vector3f), matrix, vector3f2);
      }
   }

   public int computeCount(Vector3f vector3f, EnumFacing enumFacing, int count, OptionType optionType) {
      if (optionType == OptionType.X0_Y0) {
         return count;
      } else {
         this.applyVector3f(vector3f, new Vector3f(0.5F, 0.5F, 0.5F), optionType.getMatrix4fAsMatrix4f(), new Vector3f(1.0F, 1.0F, 1.0F));
         return optionType.computeCountByEnumFacing(enumFacing, count);
      }
   }

   public static float computeFactor(EnumFacing enumFacing) {
      switch (ManagedIntArrayEntry.countArray2[enumFacing.ordinal()]) {
         case 1:
            if (BuildManager.canExecute()) {
               return PreEndHandManager.factor32;
            }

            return 0.5F;
         case 2:
            return 1.0F;
         case 3:
         case 4:
            if (BuildManager.canExecute()) {
               return PreEndHandManager.factor18;
            }

            return 0.8F;
         case 5:
         case 6:
            if (BuildManager.canExecute()) {
               return PreEndHandManager.factor8;
            }

            return 0.6F;
         default:
            return 1.0F;
      }
   }

   private void applyIndexArray(int[] countArray, int count, int count2, Vector3f vector3f, int count3, ServiceStringEntry serviceStringEntry, TertiaryFloatArrayEntry tertiaryFloatArrayEntry) {
      int count4 = countArray.length / 4;
      int count5 = count * count4;
      countArray[count5] = Float.floatToRawIntBits(vector3f.x);
      countArray[count5 + 1] = Float.floatToRawIntBits(vector3f.y);
      countArray[count5 + 2] = Float.floatToRawIntBits(vector3f.z);
      countArray[count5 + 3] = count3;
      countArray[count5 + 4] = Float.floatToRawIntBits(
         serviceStringEntry.computeFactorByDouble((double)tertiaryFloatArrayEntry.computeFactorByIndex(count2) * 0.999 + (double)tertiaryFloatArrayEntry.computeFactorByIndex((count2 + 2) % 4) * 0.001)
      );
      countArray[count5 + 4 + 1] = Float.floatToRawIntBits(
         serviceStringEntry.computeFactor((double)tertiaryFloatArrayEntry.computeFactor(count2) * 0.999 + (double)tertiaryFloatArrayEntry.computeFactor((count2 + 2) % 4) * 0.001)
      );
   }

   private void applyVector3f(Vector3f vector3f, Vector3f vector3f2, Matrix4f matrix, Vector3f vector3f3) {
      Vector4f vector4f = new Vector4f(vector3f.x - vector3f2.x, vector3f.y - vector3f2.y, vector3f.z - vector3f2.z, 1.0F);
      Matrix4f.transform(matrix, vector4f, vector4f);
      vector4f.x = vector4f.x * vector3f3.x;
      vector4f.y = vector4f.y * vector3f3.y;
      vector4f.z = vector4f.z * vector3f3.z;
      vector3f.set(vector4f.x + vector3f2.x, vector4f.y + vector3f2.y, vector4f.z + vector3f2.z);
   }

   public void applyIndexArray(int[] countArray, EnumFacing enumFacing, TertiaryFloatArrayEntry tertiaryFloatArrayEntry, ServiceStringEntry serviceStringEntry) {
      for (int index = 0; index < 4; index++) {
         this.applyIndexArray(index, countArray, enumFacing, tertiaryFloatArrayEntry, serviceStringEntry);
      }
   }

   public LocalIntArrayEntry resolveLocalIntArrayEntry(
      Vector3f vector3f,
      Vector3f vector3f2,
      EnumFacingHolder enumFacingHolder,
      ServiceStringEntry serviceStringEntry,
      EnumFacing enumFacing,
      OptionType optionType,
      Vector3fEntry vector3fEntry,
      boolean flag,
      boolean flag2
   ) {
      int[] countArray = this.resolveIndexArray(enumFacingHolder, serviceStringEntry, enumFacing, this.resolveFloatArray(vector3f, vector3f2), optionType, vector3fEntry, flag, flag2);
      EnumFacing enumFacing2 = resolveEnumFacing(countArray);
      if (flag) {
         this.applyIndexArray(countArray, enumFacing2, enumFacingHolder.tertiaryFloatArrayEntry, serviceStringEntry);
      }

      if (vector3fEntry == null) {
         this.applyIndexArray(countArray, enumFacing2);
      }

      return new LocalIntArrayEntry(countArray, enumFacingHolder.count, enumFacing2);
   }

   private int[] resolveIndexArray(
      EnumFacingHolder enumFacingHolder, ServiceStringEntry serviceStringEntry, EnumFacing enumFacing, float[] factorArray, OptionType optionType, Vector3fEntry vector3fEntry, boolean flag, boolean flag2
   ) {
      byte byteValue = 28;
      if (BuildManager.canExecute()) {
         byteValue = 56;
      }

      int[] countArray = new int[byteValue];

      for (int index = 0; index < 4; index++) {
         this.applyIndexArray(countArray, index, enumFacing, enumFacingHolder, factorArray, serviceStringEntry, optionType, vector3fEntry, flag, flag2);
      }

      return countArray;
   }

   private Matrix4f setIdentity() {
      Matrix4f matrix = new Matrix4f();
      matrix.setIdentity();
      return matrix;
   }

   private void applyIndexArray(int[] countArray, EnumFacing enumFacing) {
      int[] countArray2 = new int[countArray.length];
      System.arraycopy(countArray, 0, countArray2, 0, countArray.length);
      float[] factorArray = new float[EnumFacing.getEntries().length];
      factorArray[GeometrySorter.count3] = 999.0F;
      factorArray[GeometrySorter.count] = 999.0F;
      factorArray[GeometrySorter.count5] = 999.0F;
      factorArray[GeometrySorter.count2] = -999.0F;
      factorArray[GeometrySorter.count6] = -999.0F;
      factorArray[GeometrySorter.count4] = -999.0F;
      int count = countArray.length / 4;

      for (int index = 0; index < 4; index++) {
         int count2 = count * index;
         float factor3 = Float.intBitsToFloat(countArray2[count2]);
         float factor4 = Float.intBitsToFloat(countArray2[count2 + 1]);
         float factor5 = Float.intBitsToFloat(countArray2[count2 + 2]);
         if (factor3 < factorArray[GeometrySorter.count3]) {
            factorArray[GeometrySorter.count3] = factor3;
         }

         if (factor4 < factorArray[GeometrySorter.count]) {
            factorArray[GeometrySorter.count] = factor4;
         }

         if (factor5 < factorArray[GeometrySorter.count5]) {
            factorArray[GeometrySorter.count5] = factor5;
         }

         if (factor3 > factorArray[GeometrySorter.count2]) {
            factorArray[GeometrySorter.count2] = factor3;
         }

         if (factor4 > factorArray[GeometrySorter.count6]) {
            factorArray[GeometrySorter.count6] = factor4;
         }

         if (factor5 > factorArray[GeometrySorter.count4]) {
            factorArray[GeometrySorter.count4] = factor5;
         }
      }

      CombatSliderProperty combatSliderProperty = CombatSliderProperty.resolveCombatSliderProperty(enumFacing);

      for (int index2 = 0; index2 < 4; index2++) {
         int count3 = count * index2;
         AngleReader angleReader = combatSliderProperty.resolveAngleReader(index2);
         float factor6 = factorArray[angleReader.count3];
         float factor7 = factorArray[angleReader.count2];
         float factor8 = factorArray[angleReader.count];
         countArray[count3] = Float.floatToRawIntBits(factor6);
         countArray[count3 + 1] = Float.floatToRawIntBits(factor7);
         countArray[count3 + 2] = Float.floatToRawIntBits(factor8);

         for (int index3 = 0; index3 < 4; index3++) {
            int count4 = count * index3;
            float factor9 = Float.intBitsToFloat(countArray2[count4]);
            float factor10 = Float.intBitsToFloat(countArray2[count4 + 1]);
            float factor11 = Float.intBitsToFloat(countArray2[count4 + 2]);
            if (MathHelper.checkFloat(factor6, factor9) && MathHelper.checkFloat(factor7, factor10) && MathHelper.checkFloat(factor8, factor11)) {
               countArray[count3 + 4] = countArray2[count4 + 4];
               countArray[count3 + 4 + 1] = countArray2[count4 + 4 + 1];
            }
         }
      }
   }

   private void applyIndexArray(
      int[] countArray,
      int count,
      EnumFacing enumFacing,
      EnumFacingHolder enumFacingHolder,
      float[] factorArray,
      ServiceStringEntry serviceStringEntry,
      OptionType optionType,
      Vector3fEntry vector3fEntry,
      boolean flag,
      boolean flag2
   ) {
      EnumFacing enumFacing2 = optionType.resolveEnumFacingByEnumFacing(enumFacing);
      int count2 = flag2 ? this.computeCount(enumFacing2) : -1;
      AngleReader angleReader = CombatSliderProperty.resolveCombatSliderProperty(enumFacing).resolveAngleReader(count);
      Vector3f vector3f = new Vector3f(factorArray[angleReader.count3], factorArray[angleReader.count2], factorArray[angleReader.count]);
      this.applyVector3f(vector3f, vector3fEntry);
      int count3 = this.computeCount(vector3f, enumFacing, count, optionType);
      NormalManager.applyVector3f(vector3f);
      this.applyIndexArray(countArray, count3, count, vector3f, count2, serviceStringEntry, enumFacingHolder.tertiaryFloatArrayEntry);
   }

   private float[] resolveFloatArray(Vector3f vector3f, Vector3f vector3f2) {
      float[] factorArray = new float[EnumFacing.getEntries().length];
      factorArray[GeometrySorter.count3] = vector3f.x / 16.0F;
      factorArray[GeometrySorter.count] = vector3f.y / 16.0F;
      factorArray[GeometrySorter.count5] = vector3f.z / 16.0F;
      factorArray[GeometrySorter.count2] = vector3f2.x / 16.0F;
      factorArray[GeometrySorter.count6] = vector3f2.y / 16.0F;
      factorArray[GeometrySorter.count4] = vector3f2.z / 16.0F;
      return factorArray;
   }

   private void applyIndexArray(int count, int[] countArray, EnumFacing enumFacing, TertiaryFloatArrayEntry tertiaryFloatArrayEntry, ServiceStringEntry serviceStringEntry) {
      int count2 = countArray.length / 4;
      int count3 = count2 * count;
      float factor3 = Float.intBitsToFloat(countArray[count3]);
      float factor4 = Float.intBitsToFloat(countArray[count3 + 1]);
      float factor5 = Float.intBitsToFloat(countArray[count3 + 2]);
      if (factor3 < -0.1F || factor3 >= 1.1F) {
         factor3 -= (float)MathHelper.floor_float(factor3);
      }

      if (factor4 < -0.1F || factor4 >= 1.1F) {
         factor4 -= (float)MathHelper.floor_float(factor4);
      }

      if (factor5 < -0.1F || factor5 >= 1.1F) {
         factor5 -= (float)MathHelper.floor_float(factor5);
      }

      float factor6 = 0.0F;
      float factor7 = 0.0F;
      switch (ManagedIntArrayEntry.countArray2[enumFacing.ordinal()]) {
         case 1:
            factor6 = factor3 * 16.0F;
            factor7 = (1.0F - factor5) * 16.0F;
            break;
         case 2:
            factor6 = factor3 * 16.0F;
            factor7 = factor5 * 16.0F;
            break;
         case 3:
            factor6 = (1.0F - factor3) * 16.0F;
            factor7 = (1.0F - factor4) * 16.0F;
            break;
         case 4:
            factor6 = factor3 * 16.0F;
            factor7 = (1.0F - factor4) * 16.0F;
            break;
         case 5:
            factor6 = factor5 * 16.0F;
            factor7 = (1.0F - factor4) * 16.0F;
            break;
         case 6:
            factor6 = (1.0F - factor5) * 16.0F;
            factor7 = (1.0F - factor4) * 16.0F;
      }

      int count4 = tertiaryFloatArrayEntry.computeCount(count) * count2;
      countArray[count4 + 4] = Float.floatToRawIntBits(serviceStringEntry.computeFactorByDouble((double)factor6));
      countArray[count4 + 4 + 1] = Float.floatToRawIntBits(serviceStringEntry.computeFactor((double)factor7));
   }
}
