package client.util;

import client.network.NetworkPlayerInfo;
import client.setting.WorldSettingsGameType;
import com.google.common.collect.ComparisonChain;
import java.util.Comparator;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class SecondaryComparatorAdapter implements Comparator {
   public int computeCount(NetworkPlayerInfo networkPlayerInfo, NetworkPlayerInfo networkPlayerInfo2) {
      ScorePlayerTeam scorePlayerTeam = networkPlayerInfo.getScorePlayerTeam();
      ScorePlayerTeam scorePlayerTeam2 = networkPlayerInfo2.getScorePlayerTeam();
      return ComparisonChain.start()
         .compareTrueFirst(
            networkPlayerInfo.getWorldSettingsGameType() != WorldSettingsGameType.SPECTATOR, networkPlayerInfo2.getWorldSettingsGameType() != WorldSettingsGameType.SPECTATOR
         )
         .compare(scorePlayerTeam != null ? scorePlayerTeam.getLabelText2() : "", scorePlayerTeam2 != null ? scorePlayerTeam2.getLabelText2() : "")
         .compare(networkPlayerInfo.getGameProfile().getName(), networkPlayerInfo2.getGameProfile().getName())
         .result();
   }

   public SecondaryComparatorAdapter(RayTraceParser rayTraceParser) {
      this();
   }

   private SecondaryComparatorAdapter() {
   }

   @Override
   public int compare(Object entry, Object entry2) {
      return this.computeCount((NetworkPlayerInfo)entry, (NetworkPlayerInfo)entry2);
   }
}
