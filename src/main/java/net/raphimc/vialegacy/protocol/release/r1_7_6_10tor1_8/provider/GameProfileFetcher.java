package net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.provider;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ExecutionError;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.viaversion.viaversion.api.minecraft.GameProfile;
import com.viaversion.viaversion.api.platform.providers.Provider;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import net.raphimc.vialegacy.ViaLegacy;
import net.raphimc.vialegacy.api.util.GameProfileUtil;

public abstract class GameProfileFetcher implements Provider {
   static final GameProfile NULL_GAME_PROFILE = new GameProfile(null, null);
   static final Pattern COLOR_CODE_PATTERN = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");
   final LoadingCache<String, UUID> uuidCache = CacheBuilder.newBuilder().expireAfterWrite(6L, TimeUnit.HOURS).build(new CacheLoader<String, UUID>() {
      public UUID load(String key) throws Exception {
         return GameProfileFetcher.this.loadMojangUuid(key);
      }
   });
   final LoadingCache<UUID, GameProfile> gameProfileCache = CacheBuilder.newBuilder()
      .expireAfterWrite(6L, TimeUnit.HOURS)
      .build(new CacheLoader<UUID, GameProfile>() {
         public GameProfile load(UUID key) throws Exception {
            return GameProfileFetcher.this.loadGameProfile(key);
         }
      });

   public boolean isUuidLoaded(String playerName) {
      return this.uuidCache.getIfPresent(playerName) != null;
   }

   public UUID getMojangUuid(String playerName) {
      playerName = COLOR_CODE_PATTERN.matcher(playerName).replaceAll("");

      try {
         return (UUID)this.uuidCache.get(playerName);
      } catch (Throwable e2) {
         Throwable e = e2;

         while (e instanceof ExecutionException || e instanceof UncheckedExecutionException || e instanceof CompletionException || e instanceof ExecutionError) {
            e = e.getCause();
         }

         Logger logger = ViaLegacy.getPlatform().getLogger();
         Level warning = Level.WARNING;
         String name = e.getClass().getName();
         logger.log(warning, "Failed to load uuid for player '" + playerName + "' (" + name + ")");
         UUID uuid = GameProfileUtil.getOfflinePlayerUuid(playerName);
         this.uuidCache.put(playerName, uuid);
         return uuid;
      }
   }

   public CompletableFuture<UUID> getMojangUuidAsync(String playerName) {
      return this.isUuidLoaded(playerName)
         ? CompletableFuture.completedFuture(this.getMojangUuid(playerName))
         : CompletableFuture.supplyAsync(() -> this.getMojangUuid(playerName));
   }

   public boolean isGameProfileLoaded(UUID uuid) {
      return this.gameProfileCache.getIfPresent(uuid) != null;
   }

   public GameProfile getGameProfile(UUID uuid) {
      try {
         GameProfile value = (GameProfile)this.gameProfileCache.get(uuid);
         return NULL_GAME_PROFILE.equals(value) ? null : value;
      } catch (Throwable e2) {
         Throwable e = e2;

         while (e instanceof ExecutionException || e instanceof UncheckedExecutionException || e instanceof CompletionException || e instanceof ExecutionError) {
            e = e.getCause();
         }

         Logger logger = ViaLegacy.getPlatform().getLogger();
         Level warning = Level.WARNING;
         String str = String.valueOf(uuid);
         String name = e.getClass().getName();
         logger.log(warning, "Failed to load game profile for uuid '" + str + "' (" + name + ")");
         this.gameProfileCache.put(uuid, NULL_GAME_PROFILE);
         return null;
      }
   }

   public CompletableFuture<GameProfile> getGameProfileAsync(UUID uuid) {
      return this.isGameProfileLoaded(uuid)
         ? CompletableFuture.completedFuture(this.getGameProfile(uuid))
         : CompletableFuture.supplyAsync(() -> this.getGameProfile(uuid));
   }

   public abstract UUID loadMojangUuid(String str) throws Exception;

   public abstract GameProfile loadGameProfile(UUID uuid) throws Exception;
}
