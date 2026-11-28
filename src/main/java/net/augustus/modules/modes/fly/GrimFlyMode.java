package net.augustus.modules.modes.fly;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventPacket;
import net.augustus.modules.movement.Fly;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;

public class GrimFlyMode extends FlyMode {
   private boolean receivedVelocity = false;

   public GrimFlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   @Override
   public void onEnable() {
      this.receivedVelocity = false;
   }

   @Override
   public void onDisable() {
      this.receivedVelocity = false;
   }

   @Override
   public void onPreUpdate() {
   }

   @Override
   public void onMotion(EventMotion event) {
      if (this.receivedVelocity) {
         float f = (float)Math.toRadians((double)mc.thePlayer.rotationYaw);
         float f2 = (float)Math.toRadians((double)mc.thePlayer.rotationPitch);
         event.setYaw(f + 20.0F);
         event.setYaw(f2);
         event.setX(Math.sin((double)f) * 10000.0);
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void onReadPacket(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S12PacketEntityVelocity) {
         if (((S12PacketEntityVelocity)packet).getEntityID() == mc.thePlayer.getEntityId()) {
            this.receivedVelocity = true;
         }
      }

      if (packet instanceof S27PacketExplosion) {
         S27PacketExplosion packet3 = (S27PacketExplosion)packet;
         if (mc.thePlayer.getDistance(packet3.getX(), packet3.getY(), packet3.getZ()) < 5.0) {
            this.receivedVelocity = true;
         }
      }
   }
}
