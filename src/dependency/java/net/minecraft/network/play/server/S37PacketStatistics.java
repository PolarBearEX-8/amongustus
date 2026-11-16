package net.minecraft.network.play.server;

import client.util.DuplicateStatIdManager;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import client.util.StatCraftItemManager;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

public class S37PacketStatistics implements Packet {
   public Map map;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      int intItem = packet.getInt3();
      this.map = Maps.newHashMap();

      for (int index = 0; index < intItem; index++) {
         DuplicateStatIdManager duplicateStatIdManager = StatCraftItemManager.get(packet.resolveTextByIndex(32767));
         int intItem2 = packet.getInt3();
         if (duplicateStatIdManager != null) {
            this.map.put(duplicateStatIdManager, intItem2);
         }
      }
   }

   public Map getMap() {
      return this.map;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.map.size());

      for (Entry entry : (Iterable<Entry>)(Iterable<?>)this.map.entrySet()) {
         packet.getPacketBuffer(((DuplicateStatIdManager)entry.getKey()).labelText);
         packet.updateInt((Integer)entry.getValue());
      }
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS37PacketStatistics(this);
   }

   public S37PacketStatistics() {
   }

   public S37PacketStatistics(Map map2) {
      this.map = map2;
   }
}
