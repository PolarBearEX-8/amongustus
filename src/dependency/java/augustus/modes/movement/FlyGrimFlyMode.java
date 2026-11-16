package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.util.MinecraftAccessor;
import client.util.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;

public class FlyGrimFlyMode extends FlyModeHandler {
   public boolean enabled = false;

   @Override
   public void onPacket(EventPacket packet) {
      Packet packet2 = packet.getPacket();
      if (packet2 instanceof S12PacketEntityVelocity && ((S12PacketEntityVelocity)packet2).getEntityId() == MinecraftAccessor.minecraft.thePlayer.getEntityId()) {
         this.enabled = true;
      }

      if (packet2 instanceof S27PacketExplosion) {
         S27PacketExplosion packet3 = (S27PacketExplosion)packet2;
         if (MinecraftAccessor.minecraft.thePlayer.getDouble4(packet3.getCoordinate2(), packet3.getCoordinate3(), packet3.getCoordinate()) < 5.0) {
            this.enabled = true;
         }
      }
   }

   @Override
   public void processChar() {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      if (this.enabled) {
         float factor = (float)Math.toRadians((double)MinecraftAccessor.minecraft.thePlayer.rotationYaw);
         float factor2 = (float)Math.toRadians((double)MinecraftAccessor.minecraft.thePlayer.rotationPitch);
         float factor3 = factor + 20.0F;
         event.setScaleFactor(factor3);
         event.setScaleFactor(factor2);
         double offset = Math.sin((double)factor) * 10000.0;
         event.setCoordOffset3(offset);
      }
   }

   @Override
   public void updateState2() {
      this.enabled = false;
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void updateState3() {
      this.enabled = false;
   }

   public FlyGrimFlyMode(String text, Fly fly) {
      super(text, fly);
   }
}
