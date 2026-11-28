package net.augustus.utils.simulation;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class PlayerTickSnapshot {
   public float pitch;
   public float moveStrafe;
   public float moveForward;
   public boolean sneaking;
   public Vec3 position;
   public boolean onGround;
   public float aiMoveSpeed;
   public boolean sprinting;
   public Vec3 motion;
   public int ticksExisted;
   public AxisAlignedBB boundingBox;
   public boolean jumping;
   public float yaw;

   private PlayerTickSnapshot() {
   }

   public PlayerTickSnapshot(EntityPlayerSP player) {
      this.position = player.getInterpolatedPos(1.0F);
      this.motion = new Vec3(player.motionX, player.motionY, player.motionZ);
      this.yaw = player.rotationYaw;
      this.pitch = player.rotationPitch;
      this.moveStrafe = player.movementInput.moveForward;
      this.moveForward = player.movementInput.moveStrafe;
      this.onGround = player.onGround;
      this.sprinting = player.isSprinting();
      this.sneaking = player.movementInput.sneak;
      this.jumping = player.movementInput.jump;
      this.ticksExisted = player.ticksExisted;
      this.aiMoveSpeed = player.getAIMoveSpeed();
      this.boundingBox = player.getEntityBoundingBox();
   }

   public PlayerTickSnapshot(Vec3 vec, int n) {
      this.position = vec;
      this.ticksExisted = n;
   }
}
