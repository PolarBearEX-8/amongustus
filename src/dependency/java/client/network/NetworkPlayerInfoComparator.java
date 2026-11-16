package client.network;

import com.google.common.collect.ComparisonChain;
import java.util.Comparator;

public final class NetworkPlayerInfoComparator implements Comparator {
   public int computeCount(NetworkPlayerInfo networkPlayerInfo, NetworkPlayerInfo networkPlayerInfo2) {
      return ComparisonChain.start().compare(networkPlayerInfo.getGameProfile().getId(), networkPlayerInfo2.getGameProfile().getId()).result();
   }

   @Override
   public int compare(Object entry, Object entry2) {
      return this.computeCount((NetworkPlayerInfo)entry, (NetworkPlayerInfo)entry2);
   }
}
