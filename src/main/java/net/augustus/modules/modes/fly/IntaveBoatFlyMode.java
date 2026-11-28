package net.augustus.modules.modes.fly;

import net.augustus.ClientManagers;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventPacket;
import net.augustus.events.EventPacketDelay;
import net.augustus.utils.input.MouseInputUtil;
import net.augustus.modules.movement.Fly;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S1BPacketEntityAttach;

public class IntaveBoatFlyMode extends FlyMode {
   private boolean blinkActive = false;
   private boolean attachedToBoat = false;
   private boolean justAttached = false;
   private int verticalSetbackCount = 0;
   private int blinkTicks = 0;
   private double lastSetbackX;
   private double lastSetbackY;
   private double lastSetbackZ;

   public IntaveBoatFlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   public void onEnable() {
      this.blinkActive = false;
      this.attachedToBoat = false;
      this.justAttached = false;
      this.lastSetbackX = 0.0;
      this.lastSetbackY = 0.0;
      this.lastSetbackZ = 0.0;
      this.verticalSetbackCount = 0;
      this.blinkTicks = 0;
      if (this.fly.helpMessage.getValue()) {
         String str = "1. Toggle Fly";
         this.fly.sendChatMessage(str);
         String str2 = "2. Right click boat";
         this.fly.sendChatMessage(str2);
         String str3 = "3. Enjoy flying (if you fail, press Sneak to tp back)";
         this.fly.sendChatMessage(str3);
      }
   }

   public void onDisable() {
   }

   public void onPreUpdate() {
   }

   public void onMotion(EventMotion event) {
      if (this.attachedToBoat && !this.blinkActive) {
         mc.thePlayer.motionY = this.justAttached ? 0.2 : 0.0;
         mc.thePlayer.motionX = 0.0;
         mc.thePlayer.motionZ = 0.0;
         this.blinkTicks = 0;
      }

      this.justAttached = false;
      if (MouseInputUtil.isButtonDown(0)) {
         this.blinkActive = true;
      }

      if (this.blinkActive) {
         this.blinkTicks++;
      }
   }

   public void onMoveInput(EventMoveInput event) {
      if (this.blinkActive && this.blinkTicks > 20) {
         ClientManagers.moduleManager.fly.setToggled(false);
      }
   }

   public void onMoveEntity(EventMoveEntity event) {
   }

   public void onIncomingPacketDelay(EventPacketDelay event) {
      if (this.blinkActive) {
         event.setDelayed(true);
      }
   }

   public void onReadPacket(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S1BPacketEntityAttach) {
         if (((S1BPacketEntityAttach)packet).getEntityId() == mc.thePlayer.getEntityId()) {
            this.attachedToBoat = true;
            event.cancel();
            this.justAttached = true;
         }
      }

      if (packet instanceof S08PacketPlayerPosLook) {
         S08PacketPlayerPosLook packet3 = (S08PacketPlayerPosLook)packet;
         if (this.verticalSetbackCount > 3) {
            this.blinkActive = true;
         }

         if (this.lastSetbackX == packet3.getX() && this.lastSetbackY != packet3.getY() && this.lastSetbackZ == packet3.getZ() && this.attachedToBoat) {
            this.verticalSetbackCount++;
         } else {
            this.verticalSetbackCount = 0;
         }

         this.lastSetbackX = packet3.getX();
         this.lastSetbackY = packet3.getY();
         this.lastSetbackZ = packet3.getZ();
      }
   }


}
