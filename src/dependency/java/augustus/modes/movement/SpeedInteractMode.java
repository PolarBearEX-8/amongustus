package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;

public class SpeedInteractMode extends SpeedModeHandler {
   @Override
   public void onMove(EventMove event) {
   }

   @Override
   public void updateState() {
      if (MoveUtil.canExecute()) {
         for (int index = 0; (double)index < super.speed.settingInteractSpeed.getValue(); index++) {
            MinecraftAccessor.minecraft
               .thePlayer
               .netHandlerPlayClient
               .handlePacket2(
                  new C08PacketPlayerBlockPlacement(
                     MinecraftAccessor.minecraft.thePlayer.inventoryPlayer.processAction3(MinecraftAccessor.minecraft.thePlayer.inventoryPlayer.currentItem)
                  )
               );
            MinecraftAccessor.minecraft
               .thePlayer
               .netHandlerPlayClient
               .handlePacket2(
                  new C04PacketPlayerPosition(
                     MinecraftAccessor.minecraft.thePlayer.posX,
                     MinecraftAccessor.minecraft.thePlayer.posY,
                     MinecraftAccessor.minecraft.thePlayer.posZ,
                     MinecraftAccessor.minecraft.thePlayer.onGround
                  )
               );
            double posX = MinecraftAccessor.minecraft.thePlayer.posX;
            double posY = MinecraftAccessor.minecraft.thePlayer.posY;
            double posZ = MinecraftAccessor.minecraft.thePlayer.posZ;
            MinecraftAccessor.minecraft.thePlayer.updateState33();
            MinecraftAccessor.minecraft.thePlayer.posX = posX;
            MinecraftAccessor.minecraft.thePlayer.posY = posY;
            MinecraftAccessor.minecraft.thePlayer.posZ = posZ;
         }
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
   public void updateState3() {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   public SpeedInteractMode(String text, Speed speed) {
      super(text, speed);
   }
}
