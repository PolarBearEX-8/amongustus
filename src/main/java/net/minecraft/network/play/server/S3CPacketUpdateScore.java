package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;

public class S3CPacketUpdateScore implements Packet {
   private String name = "";
   private String objective = "";
   private int value;
   private S3CPacketUpdateScore.Action action;

   public S3CPacketUpdateScore(Score score) {
      this.name = score.getPlayerName();
      this.objective = score.getObjective().getName();
      this.value = score.getScorePoints();
      this.action = S3CPacketUpdateScore.Action.CHANGE;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleUpdateScore(this);
   }

   public int getScoreValue() {
      return this.value;
   }

   public S3CPacketUpdateScore() {
   }

   public S3CPacketUpdateScore.Action getScoreAction() {
      return this.action;
   }

   public S3CPacketUpdateScore(String str, ScoreObjective scoreObjective) {
      this.name = str;
      this.objective = scoreObjective.getName();
      this.value = 0;
      this.action = S3CPacketUpdateScore.Action.REMOVE;
   }

   public String getObjectiveName() {
      return this.objective;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.name = buf.readUtf8String(40);
      this.action = (S3CPacketUpdateScore.Action)buf.readEnumValue(S3CPacketUpdateScore.Action.class);
      this.objective = buf.readUtf8String(16);
      if (this.action != S3CPacketUpdateScore.Action.REMOVE) {
         this.value = buf.readVarIntFromBuffer();
      }
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeString(this.name);
      buf.writeEnumValue(this.action);
      buf.writeString(this.objective);
      if (this.action != S3CPacketUpdateScore.Action.REMOVE) {
         buf.writeVarIntToBuffer(this.value);
      }
   }

   public S3CPacketUpdateScore(String str) {
      this.name = str;
      this.objective = "";
      this.value = 0;
      this.action = S3CPacketUpdateScore.Action.REMOVE;
   }

   public String getPlayerName() {
      return this.name;
   }


   public enum Action {
      CHANGE,
      REMOVE;
   }
}
