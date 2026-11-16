package augustus.modes.movement;

import augustus.modes.combat.AuraRenderMode;
import augustus.modules.movement.Speed;
import client.ClientGlobals;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.module.Module;
import client.rotation.RotationManager;
import client.util.KeyUtils;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;
import client.util.SimulatedPlayer;

public class SpeedAngleStrafeMode extends SpeedModeHandler {
   public boolean enabled = false;

   @Override
   public void onMove(EventMove event) {
      if (this.enabled && this.isActive2()) {
         AuraRenderMode auraRenderMode = AuraRenderMode.DEFAULT;
         event.setAuraRenderMode(auraRenderMode);
         event.setState1(false);
         if (ClientGlobals.moduleManager.speed.settingAutoJump.isEnabled()) {
            event.setState(true);
         }
      }
   }

   private int getCount2() {
      int intItem = KeyUtils.getInt2();
      return intItem != 0 ? intItem : 1;
   }

   private boolean isActive2() {
      return MinecraftAccessor.minecraft.isIntegratedServerRunning()
         && (MoveUtil.canExecute() || !ClientGlobals.moduleManager.speed.settingOnlyWhileMove.isEnabled());
   }

   @Override
   public void updateState() {
      this.enabled = false;
      if (this.isActive2()) {
         if (MinecraftAccessor.minecraft.thePlayer.onGround
            && (
               ClientGlobals.moduleManager.speed.settingAutoJump.isEnabled()
                  || KeyUtils.checkIndexByIndex(MinecraftAccessor.minecraft.gameSettings.keyBindJump.getKeyCode())
                     && KeyUtils.checkCondition(MinecraftAccessor.minecraft.gameSettings.keyBindJump.getKeyCode())
            )) {
            RotationManager rotationManager = ClientGlobals.rotationManager;
            float factor = ClientGlobals.moduleManager.speed.settingMouseSensi.isEnabled()
               ? SimulatedPlayer.getFloat2(MinecraftAccessor.minecraft.thePlayer.rotationYaw, ClientGlobals.rotationManager.getRotation3().yaw)
               : MinecraftAccessor.minecraft.thePlayer.rotationYaw;
            Speed speed = ClientGlobals.moduleManager.speed;
            byte byteValue = 10;
            float factor2 = factor;
            rotationManager.applyModule2(factor2, byteValue, (Module)speed);
         } else {
            int count = this.getCount2();
            RotationManager rotationManager2 = ClientGlobals.rotationManager;
            float factor3 = ClientGlobals.moduleManager.speed.settingMouseSensi.isEnabled()
               ? SimulatedPlayer.getFloat2(
                  MinecraftAccessor.minecraft.thePlayer.rotationYaw - (float)(45 * count), ClientGlobals.rotationManager.getRotation3().yaw
               )
               : MinecraftAccessor.minecraft.thePlayer.rotationYaw - (float)(45 * count);
            Speed speed2 = ClientGlobals.moduleManager.speed;
            byte byteValue2 = 10;
            float factor4 = factor3;
            rotationManager2.applyModule2(factor4, byteValue2, (Module)speed2);
         }

         this.enabled = true;
      }
   }

   @Override
   public void updateState2() {
   }

   @Override
   public void processAction() {
   }

   @Override
   public void processChar() {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void updateState3() {
      this.enabled = false;
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   public SpeedAngleStrafeMode(String text, Speed speed) {
      super(text, speed);
   }
}
