package net.minecraft.scoreboard;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.server.MinecraftServer;

public class ServerScoreboard extends Scoreboard {
   private final MinecraftServer scoreboardMCServer;
   private final Set addedObjectives = Sets.newHashSet();
   private ScoreboardSaveData scoreboardSaveData;

   public void sendDisplaySlotRemovalPackets(ScoreObjective scoreObjective) {
      List destroyPackets = this.getDestroyPackets(scoreObjective);

      for (EntityPlayerMP entityPlayerMP : (Iterable<EntityPlayerMP>)(this.scoreboardMCServer.getConfigurationManager().getPlayerList())) {
         for (Packet packet : (Iterable<Packet>)(destroyPackets)) {
            entityPlayerMP.playerNetServerHandler.sendPacket(packet);
         }
      }

      this.addedObjectives.remove(scoreObjective);
   }

   public void addObjective(ScoreObjective scoreObjective) {
      List createPackets = this.getCreatePackets(scoreObjective);

      for (EntityPlayerMP entityPlayerMP : (Iterable<EntityPlayerMP>)(this.scoreboardMCServer.getConfigurationManager().getPlayerList())) {
         for (Packet packet : (Iterable<Packet>)(createPackets)) {
            entityPlayerMP.playerNetServerHandler.sendPacket(packet);
         }
      }

      this.addedObjectives.add(scoreObjective);
   }

   @Override
   public void broadcastTeamRemove(ScorePlayerTeam scorePlayerTeam) {
      super.broadcastTeamRemove(scorePlayerTeam);
      this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3EPacketTeams(scorePlayerTeam, 1));
      this.markSaveDataDirty();
   }

   public List getDestroyPackets(ScoreObjective scoreObjective) {
      ArrayList list = Lists.newArrayList();
      list.add(new S3BPacketScoreboardObjective(scoreObjective, 1));

      for (int i = 0; i < 19; i++) {
         if (this.getObjectiveInDisplaySlot(i) == scoreObjective) {
            list.add(new S3DPacketDisplayScoreboard(i, scoreObjective));
         }
      }

      return list;
   }

   @Override
   public void onObjectiveDisplayNameChanged(ScoreObjective scoreObjective) {
      super.onObjectiveDisplayNameChanged(scoreObjective);
      if (this.addedObjectives.contains(scoreObjective)) {
         this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3BPacketScoreboardObjective(scoreObjective, 2));
      }

      this.markSaveDataDirty();
   }

   @Override
   public void broadcastScoreUpdate(String str) {
      super.broadcastScoreUpdate(str);
      this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3CPacketUpdateScore(str));
      this.markSaveDataDirty();
   }

   @Override
   public boolean addPlayerToTeam(String str, String str2) {
      if (super.addPlayerToTeam(str, str2)) {
         ScorePlayerTeam team = this.getTeam(str2);
         this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3EPacketTeams(team, Arrays.asList(str), 3));
         this.markSaveDataDirty();
         return true;
      } else {
         return false;
      }
   }

   public List getCreatePackets(ScoreObjective scoreObjective) {
      ArrayList list = Lists.newArrayList();
      list.add(new S3BPacketScoreboardObjective(scoreObjective, 0));

      for (int i = 0; i < 19; i++) {
         if (this.getObjectiveInDisplaySlot(i) == scoreObjective) {
            list.add(new S3DPacketDisplayScoreboard(i, scoreObjective));
         }
      }

      for (Score score : (Iterable<Score>)(this.getSortedScores(scoreObjective))) {
         list.add(new S3CPacketUpdateScore(score));
      }

      return list;
   }

   @Override
   public void removePlayerFromTeam(String str, ScorePlayerTeam scorePlayerTeam) {
      super.removePlayerFromTeam(str, scorePlayerTeam);
      this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3EPacketTeams(scorePlayerTeam, Arrays.asList(str), 4));
      this.markSaveDataDirty();
   }

   public ServerScoreboard(MinecraftServer minecraftServer) {
      this.scoreboardMCServer = minecraftServer;
   }

   @Override
   public void broadcastTeamCreated(ScorePlayerTeam scorePlayerTeam) {
      super.broadcastTeamCreated(scorePlayerTeam);
      this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3EPacketTeams(scorePlayerTeam, 0));
      this.markSaveDataDirty();
   }

   @Override
   public void onScoreUpdated(Score score) {
      super.onScoreUpdated(score);
      if (this.addedObjectives.contains(score.getObjective())) {
         this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3CPacketUpdateScore(score));
      }

      this.markSaveDataDirty();
   }

   @Override
   public void sendTeamUpdate(ScorePlayerTeam scorePlayerTeam) {
      super.sendTeamUpdate(scorePlayerTeam);
      this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3EPacketTeams(scorePlayerTeam, 2));
      this.markSaveDataDirty();
   }

   public int getObjectiveDisplaySlotCount(ScoreObjective scoreObjective) {
      int n = 0;

      for (int i = 0; i < 19; i++) {
         if (this.getObjectiveInDisplaySlot(i) == scoreObjective) {
            n++;
         }
      }

      return n;
   }

   @Override
   public void broadcastScoreUpdate(String str, ScoreObjective scoreObjective) {
      super.broadcastScoreUpdate(str, scoreObjective);
      this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3CPacketUpdateScore(str, scoreObjective));
      this.markSaveDataDirty();
   }

   public void setScoreboardSaveData(ScoreboardSaveData scoreboardSaveData2) {
      this.scoreboardSaveData = scoreboardSaveData2;
   }

   @Override
   public void onScoreObjectiveAdded(ScoreObjective scoreObjective) {
      super.onScoreObjectiveAdded(scoreObjective);
      this.markSaveDataDirty();
   }

   @Override
   public void onScoreObjectiveRemoved(ScoreObjective scoreObjective) {
      super.onScoreObjectiveRemoved(scoreObjective);
      if (this.addedObjectives.contains(scoreObjective)) {
         this.sendDisplaySlotRemovalPackets(scoreObjective);
      }

      this.markSaveDataDirty();
   }

   @Override
   public void setObjectiveInDisplaySlot(int n, ScoreObjective scoreObjective) {
      ScoreObjective objectiveInDisplaySlot = this.getObjectiveInDisplaySlot(n);
      super.setObjectiveInDisplaySlot(n, scoreObjective);
      if (objectiveInDisplaySlot != scoreObjective && objectiveInDisplaySlot != null) {
         if (this.getObjectiveDisplaySlotCount(objectiveInDisplaySlot) > 0) {
            this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3DPacketDisplayScoreboard(n, scoreObjective));
         } else {
            this.sendDisplaySlotRemovalPackets(objectiveInDisplaySlot);
         }
      }

      if (scoreObjective != null) {
         if (this.addedObjectives.contains(scoreObjective)) {
            this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3DPacketDisplayScoreboard(n, scoreObjective));
         } else {
            this.addObjective(scoreObjective);
         }
      }

      this.markSaveDataDirty();
   }

   public void markSaveDataDirty() {
      if (this.scoreboardSaveData != null) {
         this.scoreboardSaveData.markDirty();
      }
   }
}
