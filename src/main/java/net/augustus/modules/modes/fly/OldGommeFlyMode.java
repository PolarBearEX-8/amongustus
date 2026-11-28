package net.augustus.modules.modes.fly;

import net.augustus.ClientManagers;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventPacket;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Fly;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class OldGommeFlyMode extends FlyMode {
   private boolean boostActive = false;

   public OldGommeFlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   public void onEnable() {
      this.resetState();
   }

   public void onDisable() {
      this.resetState();
   }

   public void onPreUpdate() {
      if (this.boostActive) {
         mc.thePlayer.motionX = -Math.sin(MoveUtil.getDirection()) * ClientManagers.moduleManager.fly.boost.getValue();
         mc.thePlayer.motionY = ClientManagers.moduleManager.fly.height.getValue();
         mc.thePlayer.motionZ = Math.cos(MoveUtil.getDirection()) * ClientManagers.moduleManager.fly.boost.getValue();
      }
   }

   public void onMotion(EventMotion event) {
   }

   public void onMoveEntity(EventMoveEntity event) {
   }

   private void resetState() {
      this.boostActive = false;
      mc.getTimer().timerSpeed = 1.0F;
   }

   public void onReadPacket(EventPacket event) {
      if (event.getPacket() instanceof S08PacketPlayerPosLook && mc.thePlayer != null && mc.thePlayer.isRiding()) {
         this.boostActive = true;
         mc.getTimer().timerSpeed = 0.2F;
      }
   }
}
