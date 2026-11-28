package net.augustus.utils.rotation;

import java.util.List;
import java.util.Random;

public class CatmullRomRotationSmoother {
   private float previousYawDelta = 0.0F;
   private static final Random random = new Random();
   private float previousPitchDelta = 0.0F;


   private Rotation evaluateCatmullRom(Rotation[] rotations, float f) {
      Rotation rotation = rotations[0];
      Rotation rotation2 = rotations[1];
      Rotation rotation3 = rotations[2];
      Rotation rotation4 = rotations[3];
      float f2 = f * f;
      float f3 = f2 * f;
      float f4 = 0.5F * (-f3 + 2.0F * f2 - f);
      float f5 = 0.5F * (3.0F * f3 - 5.0F * f2 + 2.0F);
      float f6 = 0.5F * (-3.0F * f3 + 4.0F * f2 + f);
      float f7 = 0.5F * (f3 - f2);
      return new Rotation(
         f4 * rotation.yaw + f5 * rotation2.yaw + f6 * rotation3.yaw + f7 * rotation4.yaw,
         f4 * rotation.pitch + f5 * rotation2.pitch + f6 * rotation3.pitch + f7 * rotation4.pitch
      );
   }

   private float wrapYaw360(float f) {
      return (f + 360.0F) % 360.0F;
   }

   public Rotation smoothRotation(List list, List list2, Rotation rotation) {
      Rotation rotation2 = list.size() > 0 ? (Rotation)list.get(list.size() - 1) : new Rotation();
      if (list.size() >= 4 && list2.size() >= 4) {
         Rotation[] rotations = (Rotation[])list.subList(Math.max(0, list.size() - 4), list.size()).toArray(new Rotation[0]);
         Rotation[] rotations2 = (Rotation[])list2.subList(Math.max(0, list2.size() - 4), list2.size()).toArray(new Rotation[0]);
         return this.blendWithTarget(this.evaluateCatmullRom(rotations, 0.5F), this.evaluateCatmullRom(rotations2, 0.5F), rotation);
      } else {
         return rotation2;
      }
   }

   private float randomMicroJitter() {
      return (random.nextFloat() - 0.5F) * 2.0F * 0.1F;
   }

   private float clampPitch(float f) {
      return Math.max(-90.0F, Math.min(90.0F, f));
   }

   private Rotation blendWithTarget(Rotation rotation, Rotation rotation2, Rotation rotation3) {
      float f = 0.2F;
      float f3 = this.wrapAngleDelta(rotation2.yaw - rotation.yaw);
      float f5 = this.wrapAngleDelta(rotation2.pitch - rotation.pitch);
      float f7 = this.wrapAngleDelta(rotation3.yaw - rotation2.yaw);
      float f9 = this.wrapAngleDelta(rotation3.pitch - rotation2.pitch);
      f3 += f7;
      f5 += f9;
      float f10 = f3 + this.previousYawDelta;
      float f11 = f5 + this.previousPitchDelta;
      Rotation rotation4 = new Rotation(rotation.yaw + f10 * f + this.randomMicroJitter(), rotation.pitch + f11 * f + this.randomMicroJitter());
      rotation4.yaw = this.wrapYaw360(rotation4.yaw);
      rotation4.pitch = this.clampPitch(rotation4.pitch);
      this.previousYawDelta = f3;
      this.previousPitchDelta = f5;
      return rotation4;
   }

   private float wrapAngleDelta(float f) {
      return (f + 180.0F) % 360.0F - 180.0F;
   }


}
