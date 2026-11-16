package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import net.minecraft.network.play.client.C03PacketPlayer;

public class FlyAirJumpMode extends FlyModeHandler {
   @Override
   public void processChar() {
      if (MinecraftAccessor.minecraft.gameSettings.keyBindJump.isPressed()) {
         if (!MinecraftAccessor.minecraft.thePlayer.onGround) {
            MinecraftAccessor.minecraft.thePlayer.onGround = true;
            if (super.fly.settingOnGroundPacket.isEnabled()) {
               MinecraftAccessor.minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C03PacketPlayer(true));
            }
         }
      } else if (super.fly.settingAutoJump.isEnabled()
         && MinecraftAccessor.minecraft.thePlayer.motionY < -0.44
         && !MinecraftAccessor.minecraft.thePlayer.onGround) {
         MinecraftAccessor.minecraft.thePlayer.closeScreen();
         if (super.fly.settingOnGroundPacket.isEnabled()) {
            MinecraftAccessor.minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C03PacketPlayer(true));
         }
      }
   }

   @Override
   public void updateState2() {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void updateState3() {
   }

   public FlyAirJumpMode(String text, Fly fly) {
      super(text, fly);
   }
}
