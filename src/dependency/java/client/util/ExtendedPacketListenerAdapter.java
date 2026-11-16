package client.util;

import client.event.EventBusManager;
import client.event.EventPacket;
import client.event.PacketListener;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;

public class ExtendedPacketListenerAdapter extends ConsumerEntry implements PacketListener {
   @Override
   public void updateState2() {
      EventBusManager.getDietrichEvents2().unsubscribe(26, this);
   }

   @Override
   public String getTextAsText() {
      return "blocks";
   }

   @Override
   public void onPacketSend(EventPacket packet) {
      Packet packet2 = packet.getPacket();
      if (packet2 instanceof C08PacketPlayerBlockPlacement) {
         C08PacketPlayerBlockPlacement packet3 = (C08PacketPlayerBlockPlacement)packet2;
         if (packet3.getItemStack() != null
            && packet3.getItemStack().getItem() instanceof ItemBlock
            && packet3.getEntityId() < 6
            && packet3.getYawRotation2() != 0.0F
            && packet3.getYawRotation() != 0.0F
            && packet3.getYawRotation1() != 0.0F) {
            double offset = 1.0;
            this.applyDouble(offset);
         }
      }
   }

   @Override
   public String getText() {
      return "Place";
   }

   @Override
   public void updateState() {
      EventBusManager.getDietrichEvents2().subscribe(26, this);
   }

   public ExtendedPacketListenerAdapter(int count) {
      super((double)count);
   }
}
