package net.augustus.utils.network;

import net.augustus.utils.math.MathStatisticsUtil;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;
import io.netty.handler.traffic.TrafficCounter;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class NetworkStatsTrafficHandler extends ChannelTrafficShapingHandler {
   private double incomingPacketsPerSecond;
   public static final String HANDLER_NAME = "network-stats-tracker";
   private long readBytesPerSecond;
   private double outgoingPacketsPerSecond;
   private long writeBytesPerSecond;
   private int incomingPacketCount;
   private int outgoingPacketCount;

   public void doAccounting(TrafficCounter trafficCounter2) {
      this.readBytesPerSecond = trafficCounter2.lastReadThroughput();
      this.writeBytesPerSecond = trafficCounter2.lastWriteThroughput();
      this.outgoingPacketsPerSecond = MathStatisticsUtil.lerp((double)this.outgoingPacketCount, this.outgoingPacketsPerSecond, 0.75);
      this.incomingPacketsPerSecond = MathStatisticsUtil.lerp((double)this.incomingPacketCount, this.incomingPacketsPerSecond, 0.75);
      this.outgoingPacketCount = 0;
      this.incomingPacketCount = 0;
   }

   public long getWriteBytesPerSecond() {
      return this.writeBytesPerSecond;
   }

   public void recordIncomingPacket() {
      this.incomingPacketCount++;
   }

   public double getOutgoingPacketsPerSecond() {
      return this.outgoingPacketsPerSecond;
   }

   public void recordOutgoingPacket() {
      this.outgoingPacketCount++;
   }

   public long getReadBytesPerSecond() {
      return this.readBytesPerSecond;
   }

   public NetworkStatsTrafficHandler() {
      super(1000L);
   }

   public double getIncomingPacketsPerSecond() {
      return this.incomingPacketsPerSecond;
   }


}
