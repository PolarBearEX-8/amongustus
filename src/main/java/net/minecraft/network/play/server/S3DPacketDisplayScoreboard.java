package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.scoreboard.ScoreObjective;

public class S3DPacketDisplayScoreboard implements Packet {
   private int position;
   private String scoreName;

   public S3DPacketDisplayScoreboard(int n, ScoreObjective scoreObjective) {
      this.position = n;
      if (scoreObjective == null) {
         this.scoreName = "";
      } else {
         this.scoreName = scoreObjective.getName();
      }
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByte(this.position);
      buf.writeString(this.scoreName);
   }

   public S3DPacketDisplayScoreboard() {
   }

   public String getScoreName() {
      return this.scoreName;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleDisplayScoreboard(this);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.position = buf.readByte();
      this.scoreName = buf.readStringFromBuffer(16);
   }

   public int getPosition() {
      return this.position;
   }
}
