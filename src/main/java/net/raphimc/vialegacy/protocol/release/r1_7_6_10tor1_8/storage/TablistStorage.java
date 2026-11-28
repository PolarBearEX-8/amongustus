package net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.storage;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.StoredObject;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.packet.PacketWrapper;
import com.viaversion.viaversion.api.type.Types;
import com.viaversion.viaversion.protocols.v1_8to1_9.packet.ClientboundPackets1_8;
import java.util.HashMap;
import java.util.Map;
import net.raphimc.vialegacy.api.util.GameProfileUtil;
import net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.Protocolr1_7_6_10Tor1_8;
import net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.model.TabListEntry;

public class TablistStorage extends StoredObject {
   public final Map<String, TabListEntry> tablist = new HashMap<>();

   public TablistStorage(UserConnection user) {
      super(user);
   }

   public void sendTempEntry(TabListEntry entry) {
      entry.ping = -1;
      this.sendAddEntry(entry);
      Via.getPlatform().runSync(() -> this.sendRemoveEntry(entry), GameProfileUtil.isOfflineGameProfile(entry.gameProfile) ? 2L : 60L);
   }

   public void sendAddEntry(TabListEntry entry) {
      PacketWrapper addPlayerListItemPacket = PacketWrapper.create(ClientboundPackets1_8.PLAYER_INFO, this.user());
      addPlayerListItemPacket.write(Types.VAR_INT, 0);
      addPlayerListItemPacket.write(Types.VAR_INT, 1);
      addPlayerListItemPacket.write(Types.UUID, entry.gameProfile.id());
      addPlayerListItemPacket.write(Types.STRING, entry.gameProfile.name());
      addPlayerListItemPacket.write(Types.PROFILE_PROPERTY_ARRAY, entry.gameProfile.properties());
      addPlayerListItemPacket.write(Types.VAR_INT, entry.gameMode);
      addPlayerListItemPacket.write(Types.VAR_INT, entry.ping);
      addPlayerListItemPacket.write(Types.OPTIONAL_STRING, null);
      addPlayerListItemPacket.send(Protocolr1_7_6_10Tor1_8.class);
   }

   public void sendRemoveEntry(TabListEntry entry) {
      PacketWrapper removePlayerListItemPacket = PacketWrapper.create(ClientboundPackets1_8.PLAYER_INFO, this.user());
      removePlayerListItemPacket.write(Types.VAR_INT, 4);
      removePlayerListItemPacket.write(Types.VAR_INT, 1);
      removePlayerListItemPacket.write(Types.UUID, entry.gameProfile.id());
      removePlayerListItemPacket.send(Protocolr1_7_6_10Tor1_8.class);
   }
}
