package net.minecraft.server.management;

import com.google.common.base.Predicate;
import java.util.List;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PreYggdrasilConverter {
   private static final Logger LOGGER = LogManager.getLogger();
   public static final File OLD_IPBAN_FILE = new File("banned-ips.txt");
   public static final File OLD_PLAYERBAN_FILE = new File("banned-players.txt");
   public static final File OLD_OPS_FILE = new File("ops.txt");
   public static final File OLD_WHITELIST_FILE = new File("white-list.txt");

   public static String getStringUUIDFromName(String str) {
      if (!StringUtils.isNullOrEmpty(str) && str.length() <= 16) {
         MinecraftServer server = MinecraftServer.getServer();
         GameProfile gameProfileForUsername = server.getPlayerProfileCache().getGameProfileForUsername(str);
         if (gameProfileForUsername != null && gameProfileForUsername.getId() != null) {
            return gameProfileForUsername.getId().toString();
         } else if (!server.isSinglePlayer() && server.isServerInOnlineMode()) {
            ArrayList list = Lists.newArrayList();
            PreYggdrasilConverter.DefaultProfileLookupCallback obj = new PreYggdrasilConverter.DefaultProfileLookupCallback(server, list);
            lookupNames(server, Lists.newArrayList(new String[]{str}), obj);
            return list.size() > 0 && ((GameProfile)list.get(0)).getId() != null ? ((GameProfile)list.get(0)).getId().toString() : "";
         } else {
            return EntityPlayer.getUUID(new GameProfile((UUID)null, str)).toString();
         }
      } else {
         return str;
      }
   }

   private static void lookupNames(MinecraftServer minecraftServer, Collection items, ProfileLookupCallback profileLookupCallback) {
      String[] strings = (String[])Iterators.toArray(Iterators.filter(items.iterator(), new PreYggdrasilConverter.FilterPredicate()), String.class);
      if (minecraftServer.isServerInOnlineMode()) {
         minecraftServer.getGameProfileRepository().findProfilesByNames(strings, Agent.MINECRAFT, profileLookupCallback);
      } else {
         for (String str : strings) {
            UUID uuid = EntityPlayer.getUUID(new GameProfile((UUID)null, str));
            profileLookupCallback.onProfileLookupSucceeded(new GameProfile(uuid, str));
         }
      }
   }

   public static Logger accessLOGGER() {
      return LOGGER;
   }


   public static final class FilterPredicate implements Predicate {
      public boolean apply(Object obj) {
         return this.apply((String)obj);
      }

      public boolean apply(String str) {
         return !StringUtils.isNullOrEmpty(str);
      }
   }

   public static final class DefaultProfileLookupCallback implements ProfileLookupCallback {
      public final MinecraftServer server;
      public final List profiles;

      public void onProfileLookupSucceeded(GameProfile profile) {
         this.server.getPlayerProfileCache().addEntry(profile);
         this.profiles.add(profile);
      }

      public DefaultProfileLookupCallback(MinecraftServer minecraftServer, List list) {
         this.server = minecraftServer;
         this.profiles = list;
      }

      public void onProfileLookupFailed(GameProfile profile, Exception e) {
         PreYggdrasilConverter.accessLOGGER().warn("Could not lookup user whitelist entry for " + profile.getName(), e);
      }
   }
}
