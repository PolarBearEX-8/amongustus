package net.augustus.viaversion.provider;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import java.util.concurrent.CompletableFuture;

public class ViaLegacyProfileLookupCallback implements ProfileLookupCallback {
   public final CompletableFuture profileFuture;
   public final ViaLegacyGameProfileFetcher fetcher;

   public ViaLegacyProfileLookupCallback(ViaLegacyGameProfileFetcher fetcher2, CompletableFuture completableFuture) {
      this.fetcher = fetcher2;
      this.profileFuture = completableFuture;
   }

   public void onProfileLookupSucceeded(GameProfile profile) {
      this.profileFuture.complete(profile);
   }

   public void onProfileLookupFailed(GameProfile profile, Exception e) {
      this.profileFuture.completeExceptionally(e);
   }
}
