package net.minecraft.network.play.server;

import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.scoreboard.ScoreObjective;

public class S3BPacketScoreboardObjective implements Packet {
   private String objectiveName;
   private String objectiveValue;
   private IScoreObjectiveCriteria.EnumRenderType type;
   private int action;

   public S3BPacketScoreboardObjective() {
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleScoreboardObjective(this);
   }

   public int getAction() {
      return this.action;
   }

   public S3BPacketScoreboardObjective(ScoreObjective scoreObjective, int n) {
      this.objectiveName = scoreObjective.getName();
      this.objectiveValue = scoreObjective.getDisplayName();
      this.type = scoreObjective.getCriteria().getRenderType();
      this.action = n;
   }

   public String getObjectiveValue() {
      return this.objectiveValue;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeString(this.objectiveName);
      buf.writeByte(this.action);
      if (this.action == 0 || this.action == 2) {
         buf.writeString(this.objectiveValue);
         buf.writeString(this.type.getRenderType());
      }
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public String getObjectiveName() {
      return this.objectiveName;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.objectiveName = buf.readStringFromBuffer(16);
      this.action = buf.readByte();
      if (this.action == 0 || this.action == 2) {
         this.objectiveValue = buf.readStringFromBuffer(32);
         this.type = IScoreObjectiveCriteria.EnumRenderType.getByName(buf.readStringFromBuffer(16));
      }
   }

   public IScoreObjectiveCriteria.EnumRenderType getType() {
      return this.type;
   }
}
