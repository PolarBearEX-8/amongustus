package net.augustus.modules.modes.fly;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.modules.movement.Fly;
import net.minecraft.network.play.client.C03PacketPlayer;

public class AirJumpFlyMode extends FlyMode {
   public AirJumpFlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void onPreUpdate() {
      if (mc.gameSettings.keyBindJump.isKeyDown()) {
         if (!mc.thePlayer.onGround) {
            mc.thePlayer.onGround = true;
            if (this.fly.onGroundPacket.getValue()) {
               mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer(true));
            }
         }
      } else if (this.fly.autoJump.getValue() && mc.thePlayer.motionY < -0.44 && !mc.thePlayer.onGround) {
         mc.thePlayer.jump();
         if (this.fly.onGroundPacket.getValue()) {
            mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer(true));
         }
      }
   }

   public void onMotion(EventMotion event) {
   }

   public void onMoveEntity(EventMoveEntity event) {
   }
}
