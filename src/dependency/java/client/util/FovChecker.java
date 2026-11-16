package client.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import java.util.concurrent.CompletableFuture;

public class FovChecker implements ProfileLookupCallback {
   public final CompletableFuture completableFuture;
   public final GameProfileFetcherAdapter gameProfileFetcherAdapter;

   public FovChecker(GameProfileFetcherAdapter gameProfileFetcherAdapter2, CompletableFuture completableFuture2) {
      this.gameProfileFetcherAdapter = gameProfileFetcherAdapter2;
      this.completableFuture = completableFuture2;
   }

   public void onProfileLookupFailed(GameProfile gameProfile, Exception exception) {
      this.completableFuture.completeExceptionally(exception);
   }

   public void onProfileLookupSucceeded(GameProfile gameProfile) {
      this.completableFuture.complete(gameProfile);
   }
}
