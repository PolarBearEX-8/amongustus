package net.augustus.viaversion.provider;

import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.HttpAuthenticationService;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.ProfileNotFoundException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.viaversion.viaversion.api.minecraft.GameProfile.Property;
import java.net.Proxy;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.provider.GameProfileFetcher;

public final class ViaLegacyGameProfileFetcher extends GameProfileFetcher {
   public static final HttpAuthenticationService AUTHENTICATION_SERVICE = new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString());
   public static final MinecraftSessionService SESSION_SERVICE = ViaLegacyGameProfileFetcher.AUTHENTICATION_SERVICE.createMinecraftSessionService();
   public static final GameProfileRepository PROFILE_REPOSITORY = ViaLegacyGameProfileFetcher.AUTHENTICATION_SERVICE.createProfileRepository();

   @Override
   public UUID loadMojangUuid(String str) throws Exception {
      CompletableFuture completableFuture = new CompletableFuture();
      PROFILE_REPOSITORY.findProfilesByNames(new String[]{str}, Agent.MINECRAFT, new ViaLegacyProfileLookupCallback(this, completableFuture));
      if (!completableFuture.isDone()) {
         completableFuture.completeExceptionally(new ProfileNotFoundException());
      }

      return ((GameProfile)completableFuture.get()).getId();
   }


   @Override
   public com.viaversion.viaversion.api.minecraft.GameProfile loadGameProfile(UUID uuid) {
      GameProfile profile = new GameProfile(uuid, null);
      GameProfile profile2 = SESSION_SERVICE.fillProfileProperties(profile, true);
      if (profile2.equals(profile)) {
         throw new ProfileNotFoundException();
      } else {
         Property[] propertys = new Property[profile2.getProperties().size()];
         int n = 0;

         for (Entry entry : profile2.getProperties().entries()) {
            propertys[n++] = new Property(
               ((com.mojang.authlib.properties.Property)entry.getValue()).getName(),
               ((com.mojang.authlib.properties.Property)entry.getValue()).getValue(),
               ((com.mojang.authlib.properties.Property)entry.getValue()).getSignature()
            );
         }

         return new com.viaversion.viaversion.api.minecraft.GameProfile(profile2.getName(), profile2.getId(), propertys);
      }
   }



}
