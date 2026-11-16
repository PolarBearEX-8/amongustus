package client.event;

import client.setting.EventPacketServerboundType;
import client.setting.SupplementalOptionType;
import client.util.INetHandler;
import client.util.Packet;
import client.util.SystemIntArrayEntry;
import de.florianmichael.dietrichevents2.CancellableEvent;

public class EventPacket extends CancellableEvent {
   public final INetHandler iNetHandler;
   public final EventPacketServerboundType eventPacketServerboundType;
   public final SupplementalOptionType supplementalOptionType;
   public static final int count = 26;
   public Packet packet;

   public Packet getPacket() {
      return this.packet;
   }

   public void processPacket(PacketListener packet2) {
      switch (SystemIntArrayEntry.countArray[this.supplementalOptionType.ordinal()]) {
         case 1:
            packet2.onPacketPostReceive(this);
            break;
         case 2:
            packet2.onPacketSend(this);
            break;
         case 3:
            packet2.onPacket(this);
            break;
         case 4:
            packet2.onPacketReceive(this);
            break;
         case 5:
            packet2.onPacketPostSend(this);
      }
   }

   public void setPacket(Packet packet2) {
      this.packet = packet2;
   }

   public void call(Object entry) {
      PacketListener packet2 = (PacketListener)entry;
      this.processPacket(packet2);
   }

   public INetHandler getINetHandler() {
      return this.iNetHandler;
   }

   public EventPacketServerboundType getEventPacketServerboundType() {
      return this.eventPacketServerboundType;
   }

   public EventPacket(Packet packet2, SupplementalOptionType supplementalOptionType2) {
      this.packet = packet2;
      this.supplementalOptionType = supplementalOptionType2;
      this.iNetHandler = null;
      this.eventPacketServerboundType = null;
   }
}
