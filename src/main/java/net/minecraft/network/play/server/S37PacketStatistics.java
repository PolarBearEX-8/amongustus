package net.minecraft.network.play.server;

import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;

public class S37PacketStatistics implements Packet {
   private Map statisticMap;

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleStatistics(this);
   }

   public Map getStatisticMap() {
      return this.statisticMap;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.statisticMap.size());

      for (Entry entry : (Iterable<Entry>)(this.statisticMap.entrySet())) {
         buf.writeString(((StatBase)entry.getKey()).statId);
         buf.writeVarIntToBuffer((Integer)entry.getValue());
      }
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public S37PacketStatistics(Map map) {
      this.statisticMap = map;
   }

   public S37PacketStatistics() {
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      int n = buf.readVarIntFromBuffer();
      this.statisticMap = Maps.newHashMap();

      for (int i = 0; i < n; i++) {
         StatBase oneShotStat = StatList.getOneShotStat(buf.readStringFromBuffer(32767));
         int n2 = buf.readVarIntFromBuffer();
         if (oneShotStat != null) {
            this.statisticMap.put(oneShotStat, n2);
         }
      }
   }
}
