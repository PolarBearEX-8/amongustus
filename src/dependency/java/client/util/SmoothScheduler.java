package client.util;

public class SmoothScheduler {
   public static void updateState() {
      MoreCosmeticsManager.getMoreCosmeticsManager().updateState2();
   }

   public static MoreCosmeticsManager resolveMoreCosmeticsManager(int count) {
      return MoreCosmeticsManager.resolveMoreCosmeticsManager(count);
   }
}
