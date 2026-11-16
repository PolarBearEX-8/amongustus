package client.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class ThePlayerNameManagerAdapter extends ThePlayerNameManager {
   public final VanillaRunnable vanillaRunnable;
   public final Set set = Sets.newHashSet();
   public AuxiliaryStringEntryHandler auxiliaryStringEntryHandler;

   @Override
   public void applyScorePlayerTeamByScorePlayerTeam(ScorePlayerTeam scorePlayerTeam) {
      super.applyScorePlayerTeamByScorePlayerTeam(scorePlayerTeam);
      this.vanillaRunnable.getMovingManager().applyPacket(new S3EPacketTeams(scorePlayerTeam, 1));
      this.updateState();
   }

   @Override
   public void applyScorePlayerTeam3(ScorePlayerTeam scorePlayerTeam) {
      super.applyScorePlayerTeam3(scorePlayerTeam);
      this.vanillaRunnable.getMovingManager().applyPacket(new S3EPacketTeams(scorePlayerTeam, 0));
      this.updateState();
   }

   @Override
   public void applyText(String text) {
      super.applyText(text);
      this.vanillaRunnable.getMovingManager().applyPacket(new S3CPacketUpdateScore(text));
      this.updateState();
   }

   public List resolveList(ScoreboardData scoreboardData) {
      ArrayList list = Lists.newArrayList();
      list.add(new S3BPacketScoreboardObjective(scoreboardData, 0));

      for (int index = 0; index < 19; index++) {
         if (this.getCustomItemEntry(index) == scoreboardData) {
            list.add(new S3DPacketDisplayScoreboard(index, scoreboardData));
         }
      }

      for (ComparatorEntry comparatorEntry : (Iterable<ComparatorEntry>)(Iterable<?>)this.resolveCollection(scoreboardData)) {
         list.add(new S3CPacketUpdateScore(comparatorEntry));
      }

      return list;
   }

   @Override
   public void applyScorePlayerTeam2(ScorePlayerTeam scorePlayerTeam) {
      super.applyScorePlayerTeam2(scorePlayerTeam);
      this.vanillaRunnable.getMovingManager().applyPacket(new S3EPacketTeams(scorePlayerTeam, 2));
      this.updateState();
   }

   @Override
   public void applyScoreboardData2(ScoreboardData scoreboardData) {
      super.applyScoreboardData2(scoreboardData);
      if (this.set.contains(scoreboardData)) {
         this.vanillaRunnable.getMovingManager().applyPacket(new S3BPacketScoreboardObjective(scoreboardData, 2));
      }

      this.updateState();
   }

   @Override
   public void applyTextByText(String text, ScoreboardData scoreboardData) {
      super.applyTextByText(text, scoreboardData);
      this.vanillaRunnable.getMovingManager().applyPacket(new S3CPacketUpdateScore(text, scoreboardData));
      this.updateState();
   }

   @Override
   public void applyScoreboardData(ScoreboardData scoreboardData) {
      super.applyScoreboardData(scoreboardData);
      if (this.set.contains(scoreboardData)) {
         this.applyScoreboardData5(scoreboardData);
      }

      this.updateState();
   }

   public void applyScoreboardData5(ScoreboardData scoreboardData) {
      List list = this.resolveListByScoreboardData(scoreboardData);

      for (EntityPlayerAdapter entityPlayerAdapter : (Iterable<EntityPlayerAdapter>)(Iterable<?>)this.vanillaRunnable.getMovingManager().getList()) {
         for (Packet packet : (Iterable<Packet>)(Iterable<?>)list) {
            entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(packet);
         }
      }

      this.set.remove(scoreboardData);
   }

   public void applyScoreboardData4(ScoreboardData scoreboardData) {
      List list = this.resolveList(scoreboardData);

      for (EntityPlayerAdapter entityPlayerAdapter : (Iterable<EntityPlayerAdapter>)(Iterable<?>)this.vanillaRunnable.getMovingManager().getList()) {
         for (Packet packet : (Iterable<Packet>)(Iterable<?>)list) {
            entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(packet);
         }
      }

      this.set.add(scoreboardData);
   }

   @Override
   public void applyComparatorEntry(ComparatorEntry comparatorEntry) {
      super.applyComparatorEntry(comparatorEntry);
      if (this.set.contains(comparatorEntry.getScoreboardData())) {
         this.vanillaRunnable.getMovingManager().applyPacket(new S3CPacketUpdateScore(comparatorEntry));
      }

      this.updateState();
   }

   public int computeCount(ScoreboardData scoreboardData) {
      int count = 0;

      for (int index = 0; index < 19; index++) {
         if (this.getCustomItemEntry(index) == scoreboardData) {
            count++;
         }
      }

      return count;
   }

   @Override
   public void applyScoreboardData(int count, ScoreboardData scoreboardData) {
      ScoreboardData customItemEntry = this.getCustomItemEntry(count);
      super.applyScoreboardData(count, scoreboardData);
      if (customItemEntry != scoreboardData && customItemEntry != null) {
         if (this.computeCount(customItemEntry) > 0) {
            this.vanillaRunnable.getMovingManager().applyPacket(new S3DPacketDisplayScoreboard(count, scoreboardData));
         } else {
            this.applyScoreboardData5(customItemEntry);
         }
      }

      if (scoreboardData != null) {
         if (this.set.contains(scoreboardData)) {
            this.vanillaRunnable.getMovingManager().applyPacket(new S3DPacketDisplayScoreboard(count, scoreboardData));
         } else {
            this.applyScoreboardData4(scoreboardData);
         }
      }

      this.updateState();
   }

   public ThePlayerNameManagerAdapter(VanillaRunnable vanillaRunnable2) {
      this.vanillaRunnable = vanillaRunnable2;
   }

   public void setAuxiliaryStringEntryHandler(AuxiliaryStringEntryHandler auxiliaryStringEntryHandler2) {
      this.auxiliaryStringEntryHandler = auxiliaryStringEntryHandler2;
   }

   @Override
   public void applyScoreboardDataByScoreboardData(ScoreboardData scoreboardData) {
      super.applyScoreboardDataByScoreboardData(scoreboardData);
      this.updateState();
   }

   @Override
   public void applyText(String text, ScorePlayerTeam scorePlayerTeam) {
      super.applyText(text, scorePlayerTeam);
      this.vanillaRunnable.getMovingManager().applyPacket(new S3EPacketTeams(scorePlayerTeam, Arrays.asList(text), 4));
      this.updateState();
   }

   @Override
   public boolean matchesText(String text, String text2) {
      if (super.matchesText(text, text2)) {
         ScorePlayerTeam byText = this.getByText(text2);
         this.vanillaRunnable.getMovingManager().applyPacket(new S3EPacketTeams(byText, Arrays.asList(text), 3));
         this.updateState();
         return true;
      } else {
         return false;
      }
   }

   public void updateState() {
      if (this.auxiliaryStringEntryHandler != null) {
         this.auxiliaryStringEntryHandler.updateState();
      }
   }

   public List resolveListByScoreboardData(ScoreboardData scoreboardData) {
      ArrayList list = Lists.newArrayList();
      list.add(new S3BPacketScoreboardObjective(scoreboardData, 1));

      for (int index = 0; index < 19; index++) {
         if (this.getCustomItemEntry(index) == scoreboardData) {
            list.add(new S3DPacketDisplayScoreboard(index, scoreboardData));
         }
      }

      return list;
   }
}
