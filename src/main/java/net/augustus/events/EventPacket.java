package net.augustus.events;

import net.augustus.events.listeners.PacketListener;
import de.florianmichael.dietrichevents2.CancellableEvent;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;

public class EventPacket extends CancellableEvent<PacketListener> {
   private final INetHandler netHandler;
   private final EnumPacketDirection direction;
   private Packet packet;
   private final PacketStage stage;
   public static final int ID = 26;

   public void setPacket(Packet packet2) {
      this.packet = packet2;
   }

   @Override
   public void call(PacketListener listener) {
      switch (this.stage) {
         case READ_NETTY:
            listener.onPacketReceiveNetty(this);
            break;
         case SEND:
            listener.onPacketSend(this);
            break;
         case SEND_NETTY:
            listener.onPacketSendNetty(this);
            break;
         case SEND_NETTY_DELAY:
            listener.onPacketSendNettyDelay(this);
            break;
         case READ:
            listener.onPacketReceive(this);
      }
   }

   public INetHandler getNetHandler() {
      return this.netHandler;
   }

   public Packet getPacket() {
      return this.packet;
   }

   public EnumPacketDirection getDirection() {
      return this.direction;
   }


   public EventPacket(Packet packet2, PacketStage packetStage) {
      this.packet = packet2;
      this.stage = packetStage;
      this.netHandler = null;
      this.direction = null;
   }

}
