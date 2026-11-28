package net.augustus.cosmetics;

public interface CosmeticProfileCallback {
   default boolean shouldAcceptProfile(CosmeticPlayerProfile cosmeticPlayerProfile) {
      return true;
   }

   default void onProfileLoaded(CosmeticPlayerProfile cosmeticPlayerProfile) {
   }
}
