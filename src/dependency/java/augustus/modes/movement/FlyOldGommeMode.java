package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.ClientGlobals;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class FlyOldGommeMode extends FlyModeHandler {
   public boolean enabled = false;

   @Override
   public void onPacket(EventPacket packet) {
      if (packet.getPacket() instanceof S08PacketPlayerPosLook
         && MinecraftAccessor.minecraft.thePlayer != null
         && MinecraftAccessor.minecraft.thePlayer.isSneaking()) {
         this.enabled = true;
         MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 0.2F;
      }
   }

   @Override
   public void processChar() {
      if (this.enabled) {
         MinecraftAccessor.minecraft.thePlayer.motionX = -Math.sin(MoveUtil.getDouble2()) * ClientGlobals.moduleManager.fly.settingBoost.getValue();
         MinecraftAccessor.minecraft.thePlayer.motionY = ClientGlobals.moduleManager.fly.settingHeight.getValue();
         MinecraftAccessor.minecraft.thePlayer.motionZ = Math.cos(MoveUtil.getDouble2()) * ClientGlobals.moduleManager.fly.settingBoost.getValue();
      }
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   @Override
   public void updateState2() {
      this.updateState4();
   }

   private void updateState4() {
      this.enabled = false;
      MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 1.0F;
   }

   @Override
   public void updateState3() {
      this.updateState4();
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   public FlyOldGommeMode(String text, Fly fly) {
      super(text, fly);
   }
}
