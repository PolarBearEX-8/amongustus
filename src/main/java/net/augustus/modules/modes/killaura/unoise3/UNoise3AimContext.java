package net.augustus.modules.modes.killaura.unoise3;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class UNoise3AimContext {
   public double delayedFuturePitch;
   public double[] expandedAngleBounds;
   public AxisAlignedBB targetBoundingBox;
   public Vec3 targetPos;
   public double lastGoalAngle;
   public boolean firstTickOnTarget;
   public double[] delayedFuturePitchBounds;
   public double[] hittableAngleBounds;
   public EntityPlayerSP player;
   public double silencedGoalAngle;
   public double currentAngle;
   public boolean vertical;
   public Vec3 closestTargetPoint;
   public Vec3 playerEyePos;
   public double[] unoccludedAngleBounds;
   public EntityLivingBase target;
   public double[] exactAngleBounds;
   public double delayedNoobPitch;
}
