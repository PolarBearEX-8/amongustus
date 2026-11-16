package net.minecraft.network.play.server;

import client.setting.TabListIntegerType;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import client.util.ScoreboardData;
import java.io.IOException;

public class S3BPacketScoreboardObjective implements Packet {
   public String labelText;
   public String labelText2;
   public TabListIntegerType tabListIntegerType;
   public int count;

   public String getLabelText() {
      return this.labelText;
   }

   public TabListIntegerType getTabListIntegerType() {
      return this.tabListIntegerType;
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.labelText = packet.resolveTextByIndex(16);
      this.count = packet.readByte();
      if (this.count == 0 || this.count == 2) {
         this.labelText2 = packet.resolveTextByIndex(32);
         this.tabListIntegerType = TabListIntegerType.get(packet.resolveTextByIndex(16));
      }
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS3BPacketScoreboardObjecti(this);
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.getPacketBuffer(this.labelText);
      packet.writeByte(this.count);
      if (this.count == 0 || this.count == 2) {
         packet.getPacketBuffer(this.labelText2);
         packet.getPacketBuffer(this.tabListIntegerType.getLabelText());
      }
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public S3BPacketScoreboardObjective() {
   }

   public S3BPacketScoreboardObjective(ScoreboardData scoreboardData, int count2) {
      this.labelText = scoreboardData.getLabelText2();
      this.labelText2 = scoreboardData.getLabelText();
      this.tabListIntegerType = scoreboardData.getHealthHandler().getINTEGER();
      this.count = count2;
   }
}
