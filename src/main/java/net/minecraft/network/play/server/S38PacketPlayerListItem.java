package net.minecraft.network.play.server;

import net.minecraft.world.WorldSettings;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.io.IOException;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S38PacketPlayerListItem implements Packet {
   private S38PacketPlayerListItem.Action action;
   private final List players = Lists.newArrayList();

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.action = (S38PacketPlayerListItem.Action)buf.readEnumValue(S38PacketPlayerListItem.Action.class);
      int n = buf.readVarIntFromBuffer();

      for (int i = 0; i < n; i++) {
         GameProfile profile = null;
         int n2 = 0;
         WorldSettings.GameType gameType = null;
         IChatComponent component = null;
         switch (this.action) {
            case ADD_PLAYER:
               profile = new GameProfile(buf.readUuid(), buf.readStringFromBuffer(16));
               int n3 = buf.readVarIntFromBuffer();
               int n4 = 0;

               for (; n4 < n3; n4++) {
                  String str = buf.readStringFromBuffer(32767);
                  String str2 = buf.readStringFromBuffer(32767);
                  if (buf.readBoolean()) {
                     profile.getProperties().put(str, new Property(str, str2, buf.readStringFromBuffer(32767)));
                  } else {
                     profile.getProperties().put(str, new Property(str, str2));
                  }
               }

               gameType = WorldSettings.GameType.getByID(buf.readVarIntFromBuffer());
               n2 = buf.readVarIntFromBuffer();
               if (buf.readBoolean()) {
                  component = buf.readChatComponent();
               }
               break;
            case UPDATE_GAME_MODE:
               profile = new GameProfile(buf.readUuid(), (String)null);
               gameType = WorldSettings.GameType.getByID(buf.readVarIntFromBuffer());
               break;
            case UPDATE_LATENCY:
               profile = new GameProfile(buf.readUuid(), (String)null);
               n2 = buf.readVarIntFromBuffer();
               break;
            case UPDATE_DISPLAY_NAME:
               profile = new GameProfile(buf.readUuid(), (String)null);
               if (buf.readBoolean()) {
                  component = buf.readChatComponent();
               }
               break;
            case REMOVE_PLAYER:
               profile = new GameProfile(buf.readUuid(), (String)null);
         }

         this.players.add(new S38PacketPlayerListItem.AddPlayerData(this, profile, n2, gameType, component));
      }
   }

   public S38PacketPlayerListItem.Action getAction() {
      return this.action;
   }

   public List getPlayers() {
      return this.players;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handlePlayerListItem(this);
   }

   public S38PacketPlayerListItem(S38PacketPlayerListItem.Action action2, Iterable items) {
      this.action = action2;

      for (EntityPlayerMP entityPlayerMP : (Iterable<EntityPlayerMP>)(items)) {
         this.players
            .add(
               new S38PacketPlayerListItem.AddPlayerData(
                  this, entityPlayerMP.getGameProfile(), entityPlayerMP.ping, entityPlayerMP.theItemInWorldManager.getGameType(), entityPlayerMP.getTabListDisplayName()
               )
            );
      }
   }

   public S38PacketPlayerListItem(S38PacketPlayerListItem.Action action2, EntityPlayerMP[] entityPlayerMPs) {
      this.action = action2;

      for (EntityPlayerMP entityPlayerMP : entityPlayerMPs) {
         this.players
            .add(
               new S38PacketPlayerListItem.AddPlayerData(
                  this, entityPlayerMP.getGameProfile(), entityPlayerMP.ping, entityPlayerMP.theItemInWorldManager.getGameType(), entityPlayerMP.getTabListDisplayName()
               )
            );
      }
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeEnumValue(this.action);
      buf.writeVarIntToBuffer(this.players.size());

      for (S38PacketPlayerListItem.AddPlayerData addPlayerData : (Iterable<S38PacketPlayerListItem.AddPlayerData>)(this.players)) {
         switch (this.action) {
            case ADD_PLAYER:
               buf.writeUuid(addPlayerData.getProfile().getId());
               buf.writeString(addPlayerData.getProfile().getName());
               buf.writeVarIntToBuffer(addPlayerData.getProfile().getProperties().size());

               for (Property property : addPlayerData.getProfile().getProperties().values()) {
                  buf.writeString(property.getName());
                  buf.writeString(property.getValue());
                  if (property.hasSignature()) {
                     buf.writeBoolean(true);
                     buf.writeString(property.getSignature());
                  } else {
                     buf.writeBoolean(false);
                  }
               }

               buf.writeVarIntToBuffer(addPlayerData.getGameMode().getID());
               buf.writeVarIntToBuffer(addPlayerData.getPing());
               if (addPlayerData.getDisplayName() == null) {
                  buf.writeBoolean(false);
               } else {
                  buf.writeBoolean(true);
                  buf.writeChatComponent(addPlayerData.getDisplayName());
               }
               break;
            case UPDATE_GAME_MODE:
               buf.writeUuid(addPlayerData.getProfile().getId());
               buf.writeVarIntToBuffer(addPlayerData.getGameMode().getID());
               break;
            case UPDATE_LATENCY:
               buf.writeUuid(addPlayerData.getProfile().getId());
               buf.writeVarIntToBuffer(addPlayerData.getPing());
               break;
            case UPDATE_DISPLAY_NAME:
               buf.writeUuid(addPlayerData.getProfile().getId());
               if (addPlayerData.getDisplayName() == null) {
                  buf.writeBoolean(false);
               } else {
                  buf.writeBoolean(true);
                  buf.writeChatComponent(addPlayerData.getDisplayName());
               }
               break;
            case REMOVE_PLAYER:
               buf.writeUuid(addPlayerData.getProfile().getId());
         }
      }
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this).add("action", this.action).add("entries", this.players).toString();
   }

   public S38PacketPlayerListItem() {
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

public enum Action {
      ADD_PLAYER,
      UPDATE_GAME_MODE,
      UPDATE_LATENCY,
      UPDATE_DISPLAY_NAME,
      REMOVE_PLAYER;
   }

   public static class AddPlayerData {
      private final int ping;
      private final WorldSettings.GameType gamemode;
      private final GameProfile profile;
      private final IChatComponent displayName;
      public final S38PacketPlayerListItem parentPacket;

      public int getPing() {
         return this.ping;
      }

      public WorldSettings.GameType getGameMode() {
         return this.gamemode;
      }

      public AddPlayerData(S38PacketPlayerListItem packet, GameProfile profile2, int n, WorldSettings.GameType gameType, IChatComponent component) {
         this.parentPacket = packet;
         this.profile = profile2;
         this.ping = n;
         this.gamemode = gameType;
         this.displayName = component;
      }

      public IChatComponent getDisplayName() {
         return this.displayName;
      }

      @Override
      public String toString() {
         return MoreObjects.toStringHelper(this)
            .add("latency", this.ping)
            .add("gameMode", this.gamemode)
            .add("profile", this.profile)
            .add("displayName", this.displayName == null ? null : IChatComponent.Serializer.componentToJson(this.displayName))
            .toString();
      }

      public GameProfile getProfile() {
         return this.profile;
      }
   }
}
