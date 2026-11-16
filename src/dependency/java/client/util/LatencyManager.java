package client.util;

import client.setting.WorldSettingsGameType;
import com.google.common.base.MoreObjects;
import com.mojang.authlib.GameProfile;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.util.ChatComponentSerializer;
import net.minecraft.util.IChatComponent;

public class LatencyManager {
   public final int count;
   public final WorldSettingsGameType worldSettingsGameType;
   public final GameProfile gameProfile;
   public final IChatComponent iChatComponent;
   public final S38PacketPlayerListItem s38PacketPlayerListItem;

   public WorldSettingsGameType getWorldSettingsGameType() {
      return this.worldSettingsGameType;
   }

   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   public IChatComponent getIChatComponent() {
      return this.iChatComponent;
   }

   public int getCount() {
      return this.count;
   }

   public LatencyManager(S38PacketPlayerListItem packet, GameProfile gameProfile2, int count2, WorldSettingsGameType worldSettingsGameType2, IChatComponent iChatComponent2) {
      this.s38PacketPlayerListItem = packet;
      this.gameProfile = gameProfile2;
      this.count = count2;
      this.worldSettingsGameType = worldSettingsGameType2;
      this.iChatComponent = iChatComponent2;
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this)
         .add("latency", this.count)
         .add("gameMode", this.worldSettingsGameType)
         .add("profile", this.gameProfile)
         .add("displayName", this.iChatComponent == null ? null : ChatComponentSerializer.toJson(this.iChatComponent))
         .toString();
   }
}
