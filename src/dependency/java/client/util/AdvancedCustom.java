package client.util;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.LookupUserProfileLookupCallback;
import com.mojang.authlib.ProfileLookupCallback;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdvancedCustom {
   public static final Logger logger = LogManager.getLogger();
   public static final File file;
   public static final File file2;
   public static final File file3;
   public static final File file4;

   public static String resolveText(String text) {
      if (!ClientBrandHelper.isEmpty(text) && text.length() <= 16) {
         VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
         GameProfile gameProfile = vanillaRunnable.getYyyyMMDdHHMmSsZManager().resolveGameProfile(text);
         if (gameProfile != null && gameProfile.getId() != null) {
            return gameProfile.getId().toString();
         } else if (!vanillaRunnable.isActive10() && vanillaRunnable.isEnabled2()) {
            ArrayList list = Lists.newArrayList();
            LookupUserProfileLookupCallback lookupUserProfileLookupCallback = new LookupUserProfileLookupCallback(vanillaRunnable, list);
            applyVanillaRunnable(vanillaRunnable, Lists.newArrayList(new String[]{text}), lookupUserProfileLookupCallback);
            return list.size() > 0 && ((GameProfile)list.get(0)).getId() != null ? ((GameProfile)list.get(0)).getId().toString() : "";
         } else {
            return EntityPlayer.resolveUUID(new GameProfile((UUID)null, text)).toString();
         }
      } else {
         return text;
      }
   }

   static {
      String[] textArray = new String[]{"banned-ips.txt", "white-list.txt", "banned-players.txt", "ops.txt"};
      file = new File(textArray[0]);
      file2 = new File(textArray[2]);
      file3 = new File(textArray[3]);
      file4 = new File(textArray[1]);
   }

   private static void applyVanillaRunnable(VanillaRunnable vanillaRunnable, Collection collection, ProfileLookupCallback profileLookupCallback) {
      String[] textArray = (String[])Iterators.toArray(Iterators.filter(collection.iterator(), new Filter()), String.class);
      if (vanillaRunnable.isEnabled2()) {
         vanillaRunnable.getGameProfileRepository().findProfilesByNames(textArray, Agent.MINECRAFT, profileLookupCallback);
      } else {
         for (String text : textArray) {
            UUID uuid = EntityPlayer.resolveUUID(new GameProfile((UUID)null, text));
            GameProfile gameProfile = new GameProfile(uuid, text);
            profileLookupCallback.onProfileLookupSucceeded(gameProfile);
         }
      }
   }

   public static Logger getLogger() {
      return logger;
   }
}
