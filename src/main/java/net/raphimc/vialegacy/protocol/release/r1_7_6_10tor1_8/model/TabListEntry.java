package net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.model;

import com.viaversion.viaversion.api.minecraft.GameProfile;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class TabListEntry {
   public GameProfile gameProfile;
   public int ping;
   public int gameMode;
   public boolean resolved;

   public TabListEntry(String name, UUID uuid) {
      this(new GameProfile(name, uuid));
   }

   public TabListEntry(GameProfile gameProfile) {
      this.gameProfile = gameProfile;
      this.resolved = true;
   }

   public TabListEntry(String name, short ping) {
      this.gameProfile = new GameProfile(name, UUID.nameUUIDFromBytes(("LegacyPlayer:" + name).getBytes(StandardCharsets.UTF_8)));
      this.ping = ping;
   }
}
