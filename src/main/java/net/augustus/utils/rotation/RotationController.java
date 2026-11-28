package net.augustus.utils.rotation;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.utils.BoundedHistoryList;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.math.SimplexNoise;
import net.augustus.utils.math.Vec2d;
import net.augustus.modules.Module;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.util.MathHelper;

public class RotationController implements MC {
   private final BoundedHistoryList rotationHistory;
   private float cameraPitch;
   private Class previousOwnerClass;
   private int currentPriority;
   private int rotationStage;
   private boolean returningYaw;
   private float cameraYaw;
   private boolean wasReturningYaw;
   private boolean tickUpdateMode;
   private boolean holdingRotation;
   private Class activeOwnerClass;
   private boolean cameraSyncPending;
   private Rotation previousRotation;
   private Rotation currentRotation;
   private final RotationUtil rotationUtil;
   private Rotation lastTickRotation;
   private int holdTicks;
   private boolean returningPitch;

   public int getCurrentPriority() {
      return this.currentPriority;
   }

   public void applyServerRotation() {
      if (this.holdTicks > 0 && this.hasNoActiveOwner() && this.previousOwnerClass != null) {
         this.activeOwnerClass = this.previousOwnerClass;
         this.currentRotation = this.previousRotation;
         this.holdingRotation = true;
      }

      this.wasReturningYaw = this.returningYaw;
      if (!this.hasNoActiveOwner()) {
         if (!this.returningYaw) {
            this.returningYaw = true;
         }

         if (!this.returningPitch) {
            this.returningPitch = true;
         }
      }

      float f = (float)ClientManagers.moduleManager.rotationHandler.yawSpeed.nextValue();
      float f2 = (float)ClientManagers.moduleManager.rotationHandler.pitchSpeed.nextValue();
      mc.thePlayer.rotationYaw = this.currentRotation.yaw;
      mc.thePlayer.rotationPitch = this.currentRotation.pitch;
      if (!ClientManagers.moduleManager.rotationHandler.smoothBackRotate.getSelectedValue().equals("None") && this.hasNoActiveOwner()) {
         float realYaw = Augustus.getInstance().getYawPitchHelper().realYaw;
         float realPitch = Augustus.getInstance().getYawPitchHelper().realPitch;
         if (mc.currentScreen != null && mc.previousScreen == null) {
            ClientManagers.moduleManager.rotationHandler.smoothBackRotateTickDelayAfterClosingInventory.nextValue();
         }

         if (this.returningYaw || this.returningPitch) {
if (mc.isNotInGui()) {
if (mc.getTicksWithoutScreen() > ClientManagers.moduleManager.rotationHandler.smoothBackRotateTickDelayAfterClosingInventory.getLastIntValue()) {
if (ClientManagers.moduleManager.rotationHandler.smoothBackRotate.getSelectedValue().equals("Default")) {
if (Math.abs(realYaw - this.previousRotation.yaw) > f || Math.abs(realPitch - this.previousRotation.pitch) > f2) {
                        double d = 1.4999;
                        double d2 = -1.4999;
                        mc.thePlayer.rotationYaw = RotationUtil.updateYawWithGcd(this.previousRotation.yaw, realYaw + DefaultRandomGenerator.instance.nextFloat(d2, d), f);
                        double d3 = 1.4999;
                        double d4 = -1.4999;
                        mc.thePlayer.rotationPitch = RotationUtil.updatePitchWithGcd(this.previousRotation.pitch, realPitch + DefaultRandomGenerator.instance.nextFloat(d4, d3), f2);
                     }
} else if (ClientManagers.moduleManager.rotationHandler.smoothBackRotate.getSelectedValue().equals("New")) {
                     f = 5.0F;
                     f2 = 5.0F;
                     RotationUtil rotationUtil2 = this.rotationUtil;
                     double d5 = (double)MathHelper.wrapAngleTo180_float(this.previousRotation.yaw);
                     double pitch = (double)this.previousRotation.pitch;
                     double d6 = (double)MathHelper.wrapAngleTo180_float(realYaw + SimplexNoise.noise((float)mc.thePlayer.ticksExisted / 20.0F, -1.0F) * 2.0F);
                     double d7 = (double)(realPitch + SimplexNoise.noise((float)mc.thePlayer.ticksExisted / 20.0F, -1.0F) * 2.0F);
                     double d8 = ClientManagers.moduleManager.rotationHandler.rotationSpeed.getValue(true) / 100.0 * 8.0 + 2.0;
                     double d9 = 0.0;
                     double d10 = 1.0;
                     Rotation rotation = rotationUtil2.stepKinematicAngles(d5, pitch, d6, d7, d8, 1.0, d10, d9);
                     rotation.yaw = RotationUtil.updateAngle(this.previousRotation.yaw, rotation.yaw, 180.0F);
                     rotation.pitch = RotationUtil.updateAngle(this.previousRotation.pitch, rotation.pitch, 180.0F);
                     rotation = RotationUtil.applyGcdFix(rotation.yaw, rotation.pitch, this.previousRotation.yaw, this.previousRotation.pitch);
                     mc.thePlayer.rotationYaw = rotation.yaw;
                     mc.thePlayer.rotationPitch = rotation.pitch;
                  }
} else {
mc.thePlayer.rotationYaw = this.previousRotation.yaw;
            mc.thePlayer.rotationPitch = this.previousRotation.pitch;
}
} else {
mc.thePlayer.rotationYaw = this.previousRotation.yaw;
            mc.thePlayer.rotationPitch = this.previousRotation.pitch;
}
}

         if (Math.abs(EntityBoundsUtil.getShortestSignedAngleDelta((double)MathHelper.wrapAngleTo180_float(realYaw), (double)MathHelper.wrapAngleTo180_float(mc.thePlayer.rotationYaw)))
            < (double)Math.max(f / 2.0F, 5.0F)) {
            this.returningYaw = false;
         }

         if (Math.abs(realPitch - mc.thePlayer.rotationPitch) < Math.max(f2 / 2.0F, 5.0F)) {
            this.returningPitch = false;
         }
      } else {
         RotationUtil rotationUtil3 = this.rotationUtil;
         Vec2d vec2d = new Vec2d(
            HitboxAngleUtil.getShortestAngularDistance((double)MathHelper.wrapAngleTo180_float(this.previousRotation.yaw), (double)MathHelper.wrapAngleTo180_float(this.currentRotation.yaw)) / 3.0,
            (double)((this.previousRotation.pitch - this.currentRotation.pitch) / 3.0F)
         );
         rotationUtil3.setMouseVelocity(vec2d);
      }

      this.currentRotation.yaw = mc.thePlayer.rotationYaw;
      this.currentRotation.pitch = mc.thePlayer.rotationPitch;
      float rotationPitch = mc.thePlayer.rotationPitch;
      this.syncCameraAngles(mc.thePlayer.rotationYaw, rotationPitch);
      this.rotationHistory.add(new Rotation(this.currentRotation));
   }

   public BoundedHistoryList getRotationHistory() {
      return this.rotationHistory;
   }

   public void beginRotationTick(Rotation rotation, int n) {
      this.currentPriority = 100;
      this.lastTickRotation = new Rotation(this.previousRotation);
      this.previousRotation = new Rotation(this.currentRotation);
      this.currentRotation = rotation;
      this.previousOwnerClass = this.activeOwnerClass;
      this.activeOwnerClass = null;
      this.rotationStage = n;
      this.tickUpdateMode = ClientManagers.moduleManager.rotationHandler.rotationMode.getSelectedValue().equals("Tick");
      this.holdTicks--;
      this.holdingRotation = false;
   }

   public void setPreviousRotation(Rotation rotation) {
      this.previousRotation = rotation;
   }

   public void syncCameraAngles(float f, float f2) {
      this.cameraYaw = f;
      this.cameraPitch = f2;
      this.cameraSyncPending = true;
   }

   public boolean isOwnedBy(Module module) {
      return this.activeOwnerClass != null && this.activeOwnerClass.equals(module.getClass());
   }

   public void forceRotation(Rotation rotation) {
      this.currentRotation = rotation;
      this.currentPriority = -10000;
   }

   public void requestRotation(float[] floats, int n, Module module) {
      this.requestRotation(floats, n, module, (byte)0);
   }

   public void requestRotation(Rotation rotation, int n, Module module) {
      this.requestRotation(rotation, n, module, (byte)0);
   }

   private boolean canAcceptRotation() {
      return this.rotationStage == 1 || this.tickUpdateMode;
   }

   public void requestRotation(float[] floats, int n, Module module, int n2) {
      if (this.canAcceptRotation()) {
         if (this.currentPriority > n) {
            this.activeOwnerClass = module.getClass();
            this.currentRotation = new Rotation(floats);
            this.holdTicks = n2;
            this.currentPriority = n;
         }
      } else {
         this.currentRotation = this.previousRotation;
      }
   }

public void requestRotation(Rotation rotation, int n, Module module, int n2) {
      if (this.canAcceptRotation()) {
         if (this.currentPriority > n) {
            this.activeOwnerClass = module.getClass();
            this.currentRotation = rotation;
            this.holdTicks = n2;
            this.currentPriority = n;
         }
      } else {
         this.currentRotation = this.previousRotation;
      }
   }

   public RotationController(String str) {
       super();
      this.rotationHistory = new BoundedHistoryList(10);
      this.currentRotation = new Rotation();
      this.previousRotation = new Rotation();
      this.lastTickRotation = new Rotation();
      this.rotationUtil = new RotationUtil();
      this.currentPriority = 0;
      this.holdTicks = 0;
      this.activeOwnerClass = null;
      this.previousOwnerClass = null;
      this.returningYaw = false;
      this.returningPitch = false;
      this.wasReturningYaw = false;
      this.rotationStage = 0;
      this.tickUpdateMode = false;
      this.holdingRotation = false;
      this.cameraSyncPending = false;
   }

   public Rotation applyMouseLookDelta(float f, float f2) {
      YawPitchHelper yawPitchHelper = Augustus.getInstance().getYawPitchHelper();
      if (this.cameraSyncPending) {
         float cameraPitch2 = this.cameraPitch;
         float cameraYaw2 = this.cameraYaw;
         float cameraYaw3 = this.cameraYaw;
         int[] ints = RotationUtil.computeMouseDeltas(yawPitchHelper.alignYawToReference(yawPitchHelper.realYaw, cameraYaw3), yawPitchHelper.realPitch, this.cameraYaw, this.cameraPitch);
         float cameraPitch3 = this.cameraPitch;
         Rotation rotation = RotationUtil.applyMouseDeltas(ints, this.cameraYaw, cameraPitch3);
         this.cameraYaw = rotation.yaw;
         this.cameraPitch = rotation.pitch;
         this.cameraSyncPending = false;
      }

      float cameraPitch4 = this.cameraPitch;
      float cameraYaw5 = this.cameraYaw;
      this.cameraYaw = (float)((double)this.cameraYaw + (double)f * 0.15);
      this.cameraPitch = (float)((double)this.cameraPitch - (double)f2 * 0.15);
      this.cameraPitch = MathHelper.clamp_float(this.cameraPitch, -90.0F, 90.0F);
      return new Rotation(this.cameraYaw, this.cameraPitch);
   }

   public boolean isHoldingRotation() {
      return this.holdingRotation;
   }

   public void requestYaw(float f, int n, Module module) {
      if (this.canAcceptRotation()) {
         if (this.currentPriority > n) {
            this.activeOwnerClass = module.getClass();
            this.currentRotation.yaw = f;
            this.currentPriority = n;
         }
      } else {
         this.currentRotation = this.previousRotation;
      }
   }

   public Rotation getPreviousRotation() {
      return this.previousRotation;
   }

   public void requestPitch(float f, int n, Module module) {
      if (this.canAcceptRotation()) {
         if (this.currentPriority > n) {
            this.activeOwnerClass = module.getClass();
            this.currentRotation.pitch = f;
            this.currentPriority = n;
         }
      } else {
         this.currentRotation = this.previousRotation;
      }
   }

   public Rotation getLastTickRotation() {
      return this.lastTickRotation;
   }

   public boolean isSmoothlyReturning() {
      return this.wasReturningYaw && !ClientManagers.moduleManager.rotationHandler.smoothBackRotate.getSelectedValue().equals("None") && this.hasNoActiveOwner();
   }

   public boolean hasNoActiveOwner() {
      return this.activeOwnerClass == null;
   }

   public void setCurrentRotation(Rotation rotation) {
      this.currentRotation = rotation;
   }

   public Rotation getCurrentRotation() {
      return this.currentRotation;
   }

}
