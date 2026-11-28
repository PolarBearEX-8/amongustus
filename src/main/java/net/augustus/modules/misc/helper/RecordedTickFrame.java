package net.augustus.modules.misc.helper;

import net.augustus.utils.simulation.EntityMotionSnapshot;
import net.augustus.utils.simulation.PlayerTickSnapshot;
import net.minecraft.util.MovementInput;
import net.minecraft.util.Vec3;

public class RecordedTickFrame {
   public int ticksExisted;
   public double playerY;
   public double motionX;
   public boolean isSprinting;
   public double playerZ;
   public double motionY;
   public double playerX;
   public float pitch;
   public double targetX;
   public PlayerTickSnapshot playerState;
   public float AI_MOVESPEED;
   public Vec3 targetState;
   public double targetZ;
   public double motionZ;
   public MovementInput movementInput;
   public boolean applyVelocity;
   public double targetY;
   public EntityMotionSnapshot entityState;
   public float yaw;
   public float jumpMovementFactor;
}
