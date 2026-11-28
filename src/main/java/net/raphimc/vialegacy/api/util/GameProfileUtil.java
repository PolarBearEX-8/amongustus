package net.raphimc.vialegacy.api.util;

import com.viaversion.viaversion.api.minecraft.GameProfile;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class GameProfileUtil {
   public static UUID getOfflinePlayerUuid(String name) {
      return UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(StandardCharsets.UTF_8));
   }

   public static boolean isOfflineGameProfile(GameProfile gameProfile) {
      return gameProfile.id() != null && gameProfile.name() != null && gameProfile.id().equals(getOfflinePlayerUuid(gameProfile.name()));
   }
}
