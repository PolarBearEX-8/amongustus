package net.minecraft.network.play.server;

import client.util.DataWatcher;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import java.util.List;

public class S1CPacketEntityMetadata implements Packet {
   public int count;
   public List list;

   public List getList() {
      return this.list;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS1CPacketEntityMetadata(this);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.getInt3();
      this.list = DataWatcher.resolveList(packet);
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count);
      DataWatcher.applyList(this.list, packet);
   }

   public S1CPacketEntityMetadata() {
   }

   public S1CPacketEntityMetadata(int count2, DataWatcher dataWatcher, boolean flag) {
      this.count = count2;
      if (flag) {
         this.list = dataWatcher.getList();
      } else {
         this.list = dataWatcher.getListAsList();
      }
   }
}
