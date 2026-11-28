package net.augustus.modules.modes.killaura.unoise3;

import net.minecraft.util.MovingObjectPosition;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.utils.rotation.RayTraceUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class UNoise3RangeBasedAimMode extends UNoise3AimMode {
   private RandomizedRangeSettings reaimanyway;
   private float cachedPitch;
   private double lastTargetDistance;
   private int ticksOffTarget;
   private RandomizedRangeSettings ticksofftarget;
   private RandomizedRangeSettings tickoffset;
   private RandomizedRangeSettings distance;
   private RandomizedRangeSettings rotationoffset;
   private int ticksUntilReaim;

   public UNoise3RangeBasedAimMode(UNoise3SettingContext uNoise3SettingContext2) {
       super(uNoise3SettingContext2, "Range based", UNoise3AimConstraintType.POINT, true, true);
      this.ticksUntilReaim = 0;
      this.ticksOffTarget = 0;
      RandomizedRangeSettings randomizedRangeSettings = new RandomizedRangeSettings(
         uNoise3SettingContext2.getModule(), uNoise3SettingContext2.getKeyPrefix() + this.getSettingsGroup().getName() + "_distance", 2.9, 3.1, 2.0, 4.0, 2, this.getSettingsGroup()
      );
      this.distance = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Distance");
      randomizedRangeSettings = new RandomizedRangeSettings(uNoise3SettingContext2.getModule(), uNoise3SettingContext2.getKeyPrefix() + this.getSettingsGroup().getName() + "_tickoffset", 0.0, 2.0, 0.0, 8.0, 0, this.getSettingsGroup());
      this.tickoffset = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Tick Offset");
      randomizedRangeSettings = new RandomizedRangeSettings(
         uNoise3SettingContext2.getModule(), uNoise3SettingContext2.getKeyPrefix() + this.getSettingsGroup().getName() + "_ticksofftarget", 3.0, 5.0, 0.0, 20.0, 0, this.getSettingsGroup()
      );
      this.ticksofftarget = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Ticks off target until reaim");
      randomizedRangeSettings = new RandomizedRangeSettings(
         uNoise3SettingContext2.getModule(), uNoise3SettingContext2.getKeyPrefix() + this.getSettingsGroup().getName() + "_reaimanyway", 15.0, 30.0, 0.0, 200.0, 0, this.getSettingsGroup()
      );
      this.reaimanyway = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("Reaim anyway");
      RandomizedRangeSettings randomizedRangeSettings2 = new RandomizedRangeSettings(
         uNoise3SettingContext2.getModule(), uNoise3SettingContext2.getKeyPrefix() + this.getSettingsGroup().getName() + "_rotationoffset", -3.0, 3.0, -20.0, 20.0, 2, this.getSettingsGroup()
      );
      this.rotationoffset = (RandomizedRangeSettings)randomizedRangeSettings2.withDisplayNamePrefix("Rotation offset");
   }

   @Override
   public double computeTargetAngle(UNoise3AimContext uNoise3AimContext) {
      double d = uNoise3AimContext.playerEyePos.distanceTo(uNoise3AimContext.closestTargetPoint);
      boolean flag = false;
      if (this.ticksUntilReaim > 0) {
         this.ticksUntilReaim--;
      }

      if (!uNoise3AimContext.firstTickOnTarget && this.ticksUntilReaim > 0) {
         if (this.distance.getLastValue() < this.lastTargetDistance && this.distance.getLastValue() > d) {
            this.ticksUntilReaim = this.tickoffset.nextInt();
            flag = true;
         }
      } else {
         flag = true;
      }

      if (!flag && d <= 3.0) {
         double d2 = uNoise3AimContext.closestTargetPoint.xCoord - uNoise3AimContext.playerEyePos.xCoord;
         float f = (float)(MathHelper.atan2(uNoise3AimContext.closestTargetPoint.zCoord - uNoise3AimContext.playerEyePos.zCoord, d2) * 180.0 / 3.141592653589793 - 90.0);
         float lastGoalAngle = (float)uNoise3AimContext.lastGoalAngle;
         double xCoord = uNoise3AimContext.playerEyePos.xCoord;
         double d4 = uNoise3AimContext.playerEyePos.yCoord - (double)uNoise3AimContext.player.getEyeHeight();
         EntityLivingBase target = uNoise3AimContext.target;
         if (RayTraceUtil.rayTraceSingleBox(f, lastGoalAngle, 3.0, xCoord, d4, uNoise3AimContext.playerEyePos.zCoord, uNoise3AimContext.targetBoundingBox, target).typeOfHit == MovingObjectPosition.MovingObjectType.MISS) {
            this.ticksOffTarget++;
         } else {
            this.ticksOffTarget = 0;
         }
      } else {
         this.ticksOffTarget = 0;
      }

      if ((double)this.ticksOffTarget >= this.ticksofftarget.getLastValue()) {
         flag = true;
      }

      if (flag) {
         this.ticksOffTarget = 0;
         this.ticksUntilReaim = this.reaimanyway.nextInt();
         double d5 = uNoise3AimContext.closestTargetPoint.xCoord - uNoise3AimContext.playerEyePos.xCoord;
         double d6 = uNoise3AimContext.closestTargetPoint.yCoord - uNoise3AimContext.playerEyePos.yCoord;
         double d7 = uNoise3AimContext.closestTargetPoint.zCoord - uNoise3AimContext.playerEyePos.zCoord;
         this.cachedPitch = (float)(
            -(MathHelper.atan2(d6, (double)MathHelper.sqrt_double(d5 * d5 + d7 * d7)) * 180.0 / 3.141592653589793) + this.rotationoffset.nextValue()
         );
      }

      this.lastTargetDistance = d;
      return (double)this.cachedPitch;
   }

}
