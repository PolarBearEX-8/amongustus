package client.rotation;

import java.util.List;
import java.util.Random;

public class RandomController {
   public float factor;
   public static final Random random = new Random();
   public float factor2 = 0.0F;

   private float computeFactor(float factor3) {
      return (factor3 + 180.0F) % 360.0F - 180.0F;
   }

   public Rotation resolveRotation(List list, List list2, Rotation rotation) {
      Rotation rotation2 = list.size() > 0 ? (Rotation)list.get(list.size() - 1) : new Rotation();
      if (list.size() >= 4 && list2.size() >= 4) {
         Rotation[] rotationArray = ((Rotation[])list.subList(Math.max(0, list.size() - 4), list.size()).toArray(new Rotation[0]));
         Rotation[] rotationArray2 = ((Rotation[])list2.subList(Math.max(0, list2.size() - 4), list2.size()).toArray(new Rotation[0]));
         float factor3 = 0.5F;
         Rotation rotation3 = this.resolveRotation(rotationArray, factor3);
         float factor4 = 0.5F;
         Rotation rotation4 = this.resolveRotation(rotationArray2, factor4);
         return this.resolveRotation(rotation3, rotation4, rotation);
      } else {
         return rotation2;
      }
   }

   private float computeFactorByFloat(float factor3) {
      return (factor3 + 360.0F) % 360.0F;
   }

   public RandomController() {
      this.factor = 0.0F;
   }

   private float computeFactor2(float factor3) {
      return Math.max(-90.0F, Math.min(90.0F, factor3));
   }

   private Rotation resolveRotation(Rotation[] rotationArray, float factor3) {
      Rotation rotation = rotationArray[0];
      Rotation rotation2 = rotationArray[1];
      Rotation rotation3 = rotationArray[2];
      Rotation rotation4 = rotationArray[3];
      float factor4 = factor3 * factor3;
      float factor5 = factor4 * factor3;
      float factor6 = 0.5F * (-factor5 + 2.0F * factor4 - factor3);
      float factor7 = 0.5F * (3.0F * factor5 - 5.0F * factor4 + 2.0F);
      float factor8 = 0.5F * (-3.0F * factor5 + 4.0F * factor4 + factor3);
      float factor9 = 0.5F * (factor5 - factor4);
      return new Rotation(
         factor6 * rotation.yaw + factor7 * rotation2.yaw + factor8 * rotation3.yaw + factor9 * rotation4.yaw,
         factor6 * rotation.pitch + factor7 * rotation2.pitch + factor8 * rotation3.pitch + factor9 * rotation4.pitch
      );
   }

   private Rotation resolveRotation(Rotation rotation, Rotation rotation2, Rotation rotation3) {
      float factor3 = 0.2F;
      float factor4 = rotation2.yaw - rotation.yaw;
      float factor5 = this.computeFactor(factor4);
      float factor6 = rotation2.pitch - rotation.pitch;
      float factor7 = this.computeFactor(factor6);
      float factor8 = rotation3.yaw - rotation2.yaw;
      float factor9 = this.computeFactor(factor8);
      float factor10 = rotation3.pitch - rotation2.pitch;
      float factor11 = this.computeFactor(factor10);
      float factor12 = factor5 + factor9;
      float factor13 = factor7 + factor11;
      float factor14 = factor12 + this.factor2;
      float factor15 = factor13 + this.factor;
      Rotation rotation4 = new Rotation(rotation.yaw + factor14 * factor3 + this.nextFloat(), rotation.pitch + factor15 * factor3 + this.nextFloat());
      float yaw = rotation4.yaw;
      rotation4.yaw = this.computeFactorByFloat(yaw);
      float pitch = rotation4.pitch;
      rotation4.pitch = this.computeFactor2(pitch);
      this.factor2 = factor12;
      this.factor = factor13;
      return rotation4;
   }

   private float nextFloat() {
      return (random.nextFloat() - 0.5F) * 2.0F * 0.1F;
   }

   static {
      String[] textArray = new String[5];
   }

   private static String[] stringArray;

   public static String[] getStringArray() {
      return null;
   }
}
