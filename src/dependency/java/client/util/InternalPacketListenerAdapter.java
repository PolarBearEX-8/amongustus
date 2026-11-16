package client.util;

import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.event.EventBusManager;
import client.event.EventPacket;
import client.event.PacketListener;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S19PacketEntityStatus;

public class InternalPacketListenerAdapter extends ConsumerEntry implements PacketListener, AttackEntityListener {
   public boolean enabled2 = true;
   public int count = -1;

   @Override
   public void updateState2() {
      EventBusManager.getDietrichEvents2().unsubscribe(1, this);
      EventBusManager.getDietrichEvents2().unsubscribe(26, this);
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      if (event.getEntity() instanceof EntityLivingBase) {
         if (this.enabled2) {
            double offset = 1.0;
            this.applyDouble(offset);
            this.enabled2 = false;
         }

         this.count = event.getEntity().getEntityId();
      }
   }

   @Override
   public String getTextAsText() {
      return "players";
   }

   @Override
   public String getText() {
      return "Attack";
   }

   @Override
   public void updateState() {
      EventBusManager.getDietrichEvents2().subscribe(1, this);
      EventBusManager.getDietrichEvents2().subscribe(26, this);
      this.enabled2 = true;
   }

   @Override
   public void onPacketPostSend(EventPacket packet) {
      Packet packet2 = packet.getPacket();
      if (packet2 instanceof S19PacketEntityStatus) {
         S19PacketEntityStatus packet3 = (S19PacketEntityStatus)packet2;
         if (this.count == packet3.getIndex() && 2 == packet3.processValue()) {
            this.enabled2 = true;
         }
      }

      if (packet2 instanceof S0BPacketAnimation) {
         S0BPacketAnimation packet4 = (S0BPacketAnimation)packet2;
         if (this.count == packet4.getIndex() && 1 == packet4.getIndex2()) {
            this.enabled2 = true;
         }
      }
   }

   public InternalPacketListenerAdapter(int count2) {
      super((double)count2);
   }
}
