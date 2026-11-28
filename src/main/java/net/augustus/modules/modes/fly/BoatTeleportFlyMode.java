package net.augustus.modules.modes.fly;

import net.augustus.events.EventClick;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventPacket;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Fly;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S1BPacketEntityAttach;

public class BoatTeleportFlyMode extends FlyMode {
   private boolean attachedToBoat;
   private int teleportTicks = 0;

   public BoatTeleportFlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   public void onEnable() {
      this.attachedToBoat = false;
      this.teleportTicks = 0;
      if (this.fly.helpMessage.getValue()) {
         String str = "1. Toggle Fly";
         this.fly.sendChatMessage(str);
         String str2 = "2. Look at boat (in hit reach)";
         this.fly.sendChatMessage(str2);
         String str3 = "3. Enjoy flying";
         this.fly.sendChatMessage(str3);
      }
   }

   public void onDisable() {
   }

   public void onClick(EventClick event) {
      if (mc.objectMouseOver != null && mc.objectMouseOver.entityHit instanceof EntityBoat && this.teleportTicks > 2) {
         event.addRightClicks((byte)1);
         event.addLeftClicks((byte)2);
      }
   }

   public void onPreUpdate() {
      double d = Math.toRadians((double)MoveUtil.getDirectionDegrees(false));
      double d2 = -(Math.sin(d) * 4.0);
      double d3 = Math.cos(d) * 4.0;
      if (mc.theWorld != null) {
         if (mc.thePlayer != null) {
            mc.thePlayer.setPosition(mc.thePlayer.posX + d2, mc.thePlayer.posY, mc.thePlayer.posZ + d3);
         }
      }

      this.teleportTicks++;
   }

   public void onMotion(EventMotion event) {
   }

   public void onMoveEntity(EventMoveEntity event) {
   }

   public void onReadPacket(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S1BPacketEntityAttach) {
         if (((S1BPacketEntityAttach)packet).getEntityId() == mc.thePlayer.getEntityId()) {
            event.cancel();
            this.attachedToBoat = true;
         }
      }

      if (packet instanceof S08PacketPlayerPosLook && this.attachedToBoat && this.teleportTicks < 1) {
         this.teleportTicks = 2;
      }
   }


}
