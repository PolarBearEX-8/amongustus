package net.augustus.modules.modes.anticheat.aim;

import net.augustus.modules.ModuleMode;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.augustus.utils.rotation.Rotation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.util.Vec3;

public abstract class AbstractAimTrackCheck extends ModuleMode {
   public Vec3 currentPlayerPos;
   public Rotation prevRotationAcceleration;
   public Rotation targetRotationDelta;
   public Rotation currentRotation = new Rotation();
   public Rotation rotationDelta;
   public Vec3 prevPlayerPos;
   public Vec3 targetPos;
   public Rotation prevRotation = new Rotation();
   public Rotation prevTargetRotationDelta;
   public Rotation prevRotationDelta;
   public Rotation rotationAcceleration;

   private void trackTarget(EntityLivingBase entity) {
   }

   public abstract void onTrackSample(
      Rotation rotation, Rotation rotation2, Rotation rotation3, Rotation rotation4, Rotation rotation5, Rotation rotation6, Rotation rotation7, Rotation rotation8, Rotation rotation9
   );

   public void onTick() {
      if (this.antiCheatPlayerData.getTargetEntity() != null && !(mc.thePlayer.getDistanceToEntityBox(this.antiCheatPlayerData.getTargetEntity()) > 7.0F)) {
         this.trackTarget(this.antiCheatPlayerData.getTargetEntity());
      } else {
         this.antiCheatPlayerData.resetTarget();
      }
   }

   public AbstractAimTrackCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.rotationDelta = new Rotation();
      this.prevRotationDelta = new Rotation();
      this.targetRotationDelta = new Rotation();
      this.prevTargetRotationDelta = new Rotation();
      this.rotationAcceleration = new Rotation();
      this.prevRotationAcceleration = new Rotation();
      this.currentPlayerPos = new Vec3(0.0, 0.0, 0.0);
      this.prevPlayerPos = new Vec3(0.0, 0.0, 0.0);
      this.targetPos = new Vec3(0.0, 0.0, 0.0);
   }

   public void onClientPacket(Packet packet) {
   }
}
