package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import client.util.ScoreboardData;
import java.io.IOException;

public class S3DPacketDisplayScoreboard implements Packet {
   public int count;
   public String labelText;

   public int getCount() {
      return this.count;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.readByte();
      this.labelText = packet.resolveTextByIndex(16);
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS3DPacketDisplayScoreboard(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeByte(this.count);
      packet.getPacketBuffer(this.labelText);
   }

   public String getLabelText() {
      return this.labelText;
   }

   public S3DPacketDisplayScoreboard(int count2, ScoreboardData scoreboardData) {
      this.count = count2;
      if (scoreboardData == null) {
         this.labelText = "";
      } else {
         this.labelText = scoreboardData.getLabelText2();
      }
   }

   public S3DPacketDisplayScoreboard() {
   }
}
