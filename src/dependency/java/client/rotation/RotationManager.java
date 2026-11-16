package client.rotation;

import client.Client;
import client.ClientGlobals;
import client.module.Module;
import client.setting.SettingContainer;
import client.util.BacktrackRecord;
import client.util.HitBoxUtils;
import client.util.MinecraftAccessor;
import client.util.RecordNode16Helper;
import client.util.SimulatedPlayer;
import client.util.SimulationSnapshot;
import client.util.TimeHelper;
import client.util.TrajectoryPredictor;
import client.via.ViaProtocolHelper;
import net.minecraft.util.MathHelper;

public class RotationManager implements MinecraftAccessor {
   public boolean state6;
   public int indexOffset7;
   public boolean state4;
   public boolean state;
   public Rotation rotation;
   public Class classValue;
   public Class classValue2;
   public final SimulatedPlayer simulatedPlayer;
   public Rotation rotation2;
   public int indexOffset8;
   public boolean state3;
   public float scaleFactor5;
   public int indexOffset;
   public Rotation rotation3;
   public boolean state2;
   public boolean state1;
   public final BacktrackRecord backtrackRecord = new BacktrackRecord(10);
   public float scaleFactor;

   public boolean isState() {
      return this.state;
   }

   public void setRotation3(Rotation rotation4) {
      this.rotation3 = rotation4;
   }

   public void applyModule(float factor, int count, Module module) {
      if (this.isActive()) {
         if (this.indexOffset8 > count) {
            this.classValue2 = module.getClass();
            this.rotation2.pitch = factor;
            this.indexOffset8 = count;
         }
      } else {
         this.rotation2 = this.rotation3;
      }
   }

   public Rotation resolveRotation(float factor, float factor2) {
      long value = 0x6653cba6e324L;
      int count = (int)((0x3caeadc42e18L ^ value ^ 0x35d67aed5ec5L) << 48 >>> 48);
      ViaProtocolHelper viaProtocolHelper = Client.getClient().getViaProtocolHelper();
      if (this.state4) {
         float scaleFactor2 = this.scaleFactor5;
         float scaleFactor3 = viaProtocolHelper.scaleFactor1;
         int[] intArray = SimulatedPlayer.getIntArray(viaProtocolHelper.computeFactorByFloat(scaleFactor3, scaleFactor2), viaProtocolHelper.scaleFactor2, this.scaleFactor5, this.scaleFactor);
         float scaleFactor4 = this.scaleFactor;
         float scaleFactor6 = this.scaleFactor5;
         Rotation rotation4 = SimulatedPlayer.getRotation(intArray, scaleFactor6, scaleFactor4);
         this.scaleFactor5 = rotation4.yaw;
         this.scaleFactor = rotation4.pitch;
         this.state4 = false;
      }

      this.scaleFactor5 = (float)((double)this.scaleFactor5 + (double)factor * 0.15);
      this.scaleFactor = (float)((double)this.scaleFactor - (double)factor2 * 0.15);
      this.scaleFactor = MathHelper.clamp_float(this.scaleFactor, -90.0F, 90.0F);
      return new Rotation(this.scaleFactor5, this.scaleFactor);
   }

   private boolean isActive() {
      return this.indexOffset7 == 1 || this.state3;
   }

   public boolean checkCondition() {
      return this.state1 && !ClientGlobals.moduleManager.rotationHandler.settingSmoothBackRotate.getSelected().equals("None") && this.hasRotations();
   }

   public Rotation getRotation() {
      return this.rotation;
   }

   public Rotation getRotation3() {
      return this.rotation3;
   }

   public void applyRotation(Rotation rotation4, int count) {
      this.indexOffset8 = 100;
      this.rotation = new Rotation(this.rotation3);
      this.rotation3 = new Rotation(this.rotation2);
      this.rotation2 = rotation4;
      this.classValue = this.classValue2;
      this.classValue2 = null;
      this.indexOffset7 = count;
      this.state3 = ClientGlobals.moduleManager.rotationHandler.settingRotationMode.getSelected().equals("Tick");
      this.indexOffset--;
      this.state = false;
   }

   public boolean checkModule(Module module) {
      return this.classValue2 != null && this.classValue2.equals(module.getClass());
   }

   public boolean hasRotations() {
      return this.classValue2 == null;
   }

   public void applyRotation2(Rotation rotation4, int count, int count2, Module module) {
      this.applyRotation4(rotation4, count, module, 0);
   }

   public void setServerRotation(Rotation serverRotation) {
      this.rotation2 = serverRotation;
      this.indexOffset8 = -10000;
   }

   public void setRotations(float factor, float factor2) {
      this.scaleFactor5 = factor;
      this.scaleFactor = factor2;
      this.state4 = true;
   }

   public void updateState() {
      long value = 0x2c38acda4a06L;
      if (this.indexOffset > 0 && this.hasRotations() && this.classValue != null) {
         this.classValue2 = this.classValue;
         this.rotation2 = this.rotation3;
         this.state = true;
      }

      this.state1 = this.state2;
      if (!this.hasRotations()) {
         if (!this.state2) {
            this.state2 = true;
         }

         if (!this.state6) {
            this.state6 = true;
         }
      }

      float factor = (float)ClientGlobals.moduleManager.rotationHandler.settingYawSpeed.recalculate();
      float factor2 = (float)ClientGlobals.moduleManager.rotationHandler.settingPitchSpeed.recalculate();
      MinecraftAccessor.minecraft.thePlayer.rotationYaw = this.rotation2.yaw;
      MinecraftAccessor.minecraft.thePlayer.rotationPitch = this.rotation2.pitch;
      if (!ClientGlobals.moduleManager.rotationHandler.settingSmoothBackRotate.getSelected().equals("None") && this.hasRotations()) {
         float scaleFactor2 = Client.getClient().getViaProtocolHelper().scaleFactor1;
         float scaleFactor3 = Client.getClient().getViaProtocolHelper().scaleFactor2;
         if (MinecraftAccessor.minecraft.currentScreen != null && MinecraftAccessor.minecraft.guiScreen == null) {
            ClientGlobals.moduleManager.rotationHandler.settingSmoothBackRotateTickDelayAfterClosingInventory.recalculate();
         }

         if (this.state2 || this.state6) {
            if (!MinecraftAccessor.minecraft.isIntegratedServerRunning()
               || MinecraftAccessor.minecraft.getInt3()
                  <= ClientGlobals.moduleManager.rotationHandler.settingSmoothBackRotateTickDelayAfterClosingInventory.getIndexOffset2()) {
               MinecraftAccessor.minecraft.thePlayer.rotationYaw = this.rotation3.yaw;
               MinecraftAccessor.minecraft.thePlayer.rotationPitch = this.rotation3.pitch;
            } else if (ClientGlobals.moduleManager.rotationHandler.settingSmoothBackRotate.getSelected().equals("Default")) {
               if (Math.abs(scaleFactor2 - this.rotation3.yaw) > factor || Math.abs(scaleFactor3 - this.rotation3.pitch) > factor2) {
                  double offset = 1.4999;
                  double offset2 = -1.4999;
                  MinecraftAccessor.minecraft.thePlayer.rotationYaw = SimulatedPlayer.getFloat8(
                     this.rotation3.yaw, scaleFactor2 + SettingContainer.settingProvider.getFloat3(offset2, offset), factor
                  );
                  double offset3 = 1.4999;
                  double offset4 = -1.4999;
                  MinecraftAccessor.minecraft.thePlayer.rotationPitch = SimulatedPlayer.getFloat5(
                     this.rotation3.pitch, scaleFactor3 + SettingContainer.settingProvider.getFloat3(offset4, offset3), factor2
                  );
               }
            } else if (ClientGlobals.moduleManager.rotationHandler.settingSmoothBackRotate.getSelected().equals("New")) {
               factor = 5.0F;
               factor2 = 5.0F;
               SimulatedPlayer simulatedPlayer2 = this.simulatedPlayer;
               double offset5 = (double)MathHelper.sqrt_float(this.rotation3.yaw);
               double pitch = (double)this.rotation3.pitch;
               double offset6 = (double)MathHelper.sqrt_float(
                  scaleFactor2 + RecordNode16Helper.computeFactor((float)MinecraftAccessor.minecraft.thePlayer.ticksExisted / 20.0F, -1.0F) * 2.0F
               );
               double offset7 = (double)(
                  scaleFactor3 + RecordNode16Helper.computeFactor((float)MinecraftAccessor.minecraft.thePlayer.ticksExisted / 20.0F, -1.0F) * 2.0F
               );
               double offset8 = ClientGlobals.moduleManager.rotationHandler.settingRotationSpeed.getDouble2(true) / 100.0 * 8.0 + 2.0;
               double offset9 = 0.0;
               double offset10 = 1.0;
               double offset11 = 1.0;
               double offset12 = offset8;
               double offset13 = offset7;
               double offset14 = offset6;
               double offset15 = pitch;
               double offset16 = offset5;
               Rotation rotation4 = simulatedPlayer2.getRotation3(offset16, offset15, offset14, offset13, offset12, offset11, offset10, offset9);
               rotation4.yaw = SimulatedPlayer.getFloat9(this.rotation3.yaw, rotation4.yaw, 180.0F);
               rotation4.pitch = SimulatedPlayer.getFloat9(this.rotation3.pitch, rotation4.pitch, 180.0F);
               Rotation rotation5 = SimulatedPlayer.getRotation8(rotation4.yaw, rotation4.pitch, this.rotation3.yaw, this.rotation3.pitch);
               MinecraftAccessor.minecraft.thePlayer.rotationYaw = rotation5.yaw;
               MinecraftAccessor.minecraft.thePlayer.rotationPitch = rotation5.pitch;
            }
         }

         if (Math.abs(
               HitBoxUtils.getDouble((double)MathHelper.sqrt_float(scaleFactor2), (double)MathHelper.sqrt_float(MinecraftAccessor.minecraft.thePlayer.rotationYaw))
            )
            < (double)Math.max(factor / 2.0F, 5.0F)) {
            this.state2 = false;
         }

         if (Math.abs(scaleFactor3 - MinecraftAccessor.minecraft.thePlayer.rotationPitch) < Math.max(factor2 / 2.0F, 5.0F)) {
            this.state6 = false;
         }
      } else {
         SimulatedPlayer simulatedPlayer3 = this.simulatedPlayer;
         SimulationSnapshot simulationSnapshot = new SimulationSnapshot(
            TrajectoryPredictor.getDouble4((double)MathHelper.sqrt_float(this.rotation3.yaw), (double)MathHelper.sqrt_float(this.rotation2.yaw)) / 3.0,
            (double)((this.rotation3.pitch - this.rotation2.pitch) / 3.0F)
         );
         simulatedPlayer3.updateSupplementalItemEntry(simulationSnapshot);
      }

      this.rotation2.yaw = MinecraftAccessor.minecraft.thePlayer.rotationYaw;
      this.rotation2.pitch = MinecraftAccessor.minecraft.thePlayer.rotationPitch;
      float pitch2 = MinecraftAccessor.minecraft.thePlayer.rotationPitch;
      float yaw = MinecraftAccessor.minecraft.thePlayer.rotationYaw;
      this.setRotations(yaw, pitch2);
      BacktrackRecord backtrackRecord2 = this.backtrackRecord;
      Rotation rotation6 = new Rotation(this.rotation2);
      backtrackRecord2.applyObject(rotation6);
   }

   public void applyRotation4(Rotation rotation4, int count, Module module, int count2) {
      if (this.isActive()) {
         if (this.indexOffset8 > count) {
            this.classValue2 = module.getClass();
            this.rotation2 = rotation4;
            this.indexOffset = count2;
            this.indexOffset8 = count;
         }
      } else {
         this.rotation2 = this.rotation3;
      }
   }

   public int getIndexOffset8() {
      return this.indexOffset8;
   }

   public Rotation getRotation2() {
      return this.rotation2;
   }

   public void applyModule2(float factor, int count, Module module) {
      if (this.isActive()) {
         if (this.indexOffset8 > count) {
            this.classValue2 = module.getClass();
            this.rotation2.yaw = factor;
            this.indexOffset8 = count;
         }
      } else {
         this.rotation2 = this.rotation3;
      }
   }

   public void applyFloatArray(float[] factorArray, int count, Module module) {
      this.applyFloatArray2(factorArray, count, module, 0);
   }

   public BacktrackRecord getBacktrackRecord() {
      return this.backtrackRecord;
   }

   public void applyFloatArray2(float[] factorArray, int count, Module module, int count2) {
      if (this.isActive()) {
         if (this.indexOffset8 > count) {
            this.classValue2 = module.getClass();
            this.rotation2 = new Rotation(factorArray);
            this.indexOffset = count2;
            this.indexOffset8 = count;
         }
      } else {
         this.rotation2 = this.rotation3;
      }
   }

   public void setRotation2(Rotation rotation4) {
      this.rotation2 = rotation4;
   }

   public RotationManager() {
      this.rotation2 = new Rotation();
      this.rotation3 = new Rotation();
      this.rotation = new Rotation();
      this.simulatedPlayer = new SimulatedPlayer();
      this.indexOffset8 = 0;
      this.indexOffset = 0;
      this.classValue2 = null;
      this.classValue = null;
      this.state2 = false;
      this.state6 = false;
      this.state1 = false;
      this.indexOffset7 = 0;
      this.state3 = false;
      this.state = false;
      this.state4 = false;
   }
}
