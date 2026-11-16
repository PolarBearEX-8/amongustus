package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.util.MinecraftAccessor;
import client.util.Packet;
import client.util.RayTraceUtil;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;

public class FirstFlyModeHandler extends FlyModeHandler {
   public final Queue queue = new ConcurrentLinkedQueue();
   public boolean enabled;
   public int count2;

   public FirstFlyModeHandler(String text, Fly fly) {
      super(text, fly);
   }

   @Override
   public void updateState2() {
      this.count2 = 0;
   }

   @Override
   public void updateState3() {
      MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 1.0F;
      if (MinecraftAccessor.minecraft.theWorld != null) {
         this.queue.forEach(packet -> applyPacket((Packet)packet));
      }

      this.queue.clear();
   }

   @Override
   public void processChar() {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      if (this.count2 > 0) {
         this.count2--;
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void onPacket(EventPacket packet) {
      Packet packet2 = packet.getPacket();
      if (packet2 instanceof S19PacketEntityStatus) {
         S19PacketEntityStatus packet3 = (S19PacketEntityStatus)packet.getPacket();
         if (packet3.processValue() == 2 && packet3.getEntity(MinecraftAccessor.minecraft.theWorld) == MinecraftAccessor.minecraft.thePlayer && this.count2 <= 0) {
            this.enabled = true;
         }
      }

      if (packet2 instanceof S32PacketConfirmTransaction) {
         if (this.count2 > 0) {
            this.queue.add(packet.getPacket());
            packet.setCancelled(true);
            this.count2--;
         } else if (!this.queue.isEmpty()) {
            this.queue.forEach(queuedPacket -> applyPacketByPacket((Packet)queuedPacket));
            RayTraceUtil.applyString("Twice");
            this.queue.clear();
         }
      }

      if (packet2 instanceof S12PacketEntityVelocity) {
         if (((S12PacketEntityVelocity)packet.getPacket()).getEntityId() != MinecraftAccessor.minecraft.thePlayer.getEntityId()) {
            return;
         }

         if (!this.enabled) {
            return;
         }

         this.queue.add(packet2);
         RayTraceUtil.applyString("First");
         this.count2 = 6;
         this.enabled = false;
      }
   }

   private static void applyPacketByPacket(Packet packet) {
      MinecraftAccessor.minecraft.getNetHandlerPlayClient().getNetworkManager().applyPacket(packet, false);
   }

   private static void applyPacket(Packet packet) {
      MinecraftAccessor.minecraft.getNetHandlerPlayClient().getNetworkManager().applyPacket(packet, true);
   }
}
