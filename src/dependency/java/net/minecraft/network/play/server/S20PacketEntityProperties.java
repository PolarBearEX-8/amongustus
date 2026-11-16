package net.minecraft.network.play.server;

import client.event.AuxiliaryListener;
import client.util.ExternalStringEntry;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.InvalidOperationManager;
import client.util.Packet;
import client.util.PacketBuffer;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class S20PacketEntityProperties implements Packet {
   public int count;
   public final List list = Lists.newArrayList();

   public List getList() {
      return this.list;
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.getInt3();
      int count2 = packet.readInt();

      for (int index = 0; index < count2; index++) {
         String text = packet.resolveTextByIndex(64);
         double offset = packet.readDouble();
         ArrayList list2 = Lists.newArrayList();
         int intItem = packet.getInt3();

         for (int index2 = 0; index2 < intItem; index2++) {
            UUID uUID = packet.getUUID();
            list2.add(new InvalidOperationManager(uUID, "Unknown synced attribute modifier", packet.readDouble(), packet.readByte()));
         }

         this.list.add(new ExternalStringEntry(this, text, offset, list2));
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count);
      packet.writeInt(this.list.size());

      for (ExternalStringEntry externalStringEntry : (Iterable<ExternalStringEntry>) (Iterable) (this.list)) {
         packet.getPacketBuffer(externalStringEntry.getLabelText());
         packet.writeDouble(externalStringEntry.getValue());
         packet.updateInt(externalStringEntry.getCollection().size());

         for (InvalidOperationManager invalidOperationManager : (Iterable<InvalidOperationManager>) (Iterable) (externalStringEntry.getCollection())) {
            packet.applyUUID(invalidOperationManager.getUUID());
            packet.writeDouble(invalidOperationManager.getValue());
            packet.writeByte(invalidOperationManager.getCount());
         }
      }
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS20PacketEntityProperties(this);
   }

   public S20PacketEntityProperties() {
   }

   public S20PacketEntityProperties(int count2, Collection collection) {
      this.count = count2;

      for (AuxiliaryListener auxiliaryListener : (Iterable<AuxiliaryListener>) (Iterable) (collection)) {
         this.list.add(new ExternalStringEntry(this, auxiliaryListener.getExtendedListener().getLabelText(), auxiliaryListener.getValue(), auxiliaryListener.getCollection()));
      }
   }
}
