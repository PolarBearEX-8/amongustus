package client.util;

import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.HttpAuthenticationService;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.yggdrasil.ProfileNotFoundException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import java.net.Proxy;
import java.util.Iterator;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.provider.GameProfileFetcher;

public class GameProfileFetcherAdapter extends GameProfileFetcher {
   public static final HttpAuthenticationService httpAuthenticationService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString());
   public static final MinecraftSessionService minecraftSessionService = httpAuthenticationService.createMinecraftSessionService();
   public static final GameProfileRepository gameProfileRepository = httpAuthenticationService.createProfileRepository();

   public UUID loadMojangUUID(String text) throws Exception {
      CompletableFuture completableFuture = new CompletableFuture();
      gameProfileRepository.findProfilesByNames(new String[]{text}, Agent.MINECRAFT, new FovChecker(this, completableFuture));
      if (!completableFuture.isDone()) {
         completableFuture.completeExceptionally(new ProfileNotFoundException());
      }

      return ((GameProfile)completableFuture.get()).getId();
   }

   public net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.model.GameProfile loadGameProfile(UUID uuid) throws Exception {
      GameProfile gameProfile = new GameProfile(uuid, null);
      GameProfile gameProfile2 = minecraftSessionService.fillProfileProperties(gameProfile, true);
      if (gameProfile2.equals(gameProfile)) {
         throw new ProfileNotFoundException();
      } else {
         net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.model.GameProfile gameProfile3 = new net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.model.GameProfile(
            gameProfile2.getName(), gameProfile2.getId()
         );
         Iterator iterator = gameProfile2.getProperties().entries().iterator();

         while (iterator.hasNext()) {
            Property value = (Property)((Entry)iterator.next()).getValue();
            gameProfile3.addProperty(
               new net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.model.GameProfile.Property(value.getName(), value.getValue(), value.getSignature())
            );
         }

         return gameProfile3;
      }
   }
}
