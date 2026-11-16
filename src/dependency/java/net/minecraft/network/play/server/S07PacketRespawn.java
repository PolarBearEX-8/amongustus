package net.minecraft.network.play.server;

import client.setting.GameSettingsPeacefulType;
import client.setting.WorldSettingsGameType;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.InfoManager;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S07PacketRespawn implements Packet {
   public int entityId;
   public GameSettingsPeacefulType gameSettingsPeacefulType;
   public WorldSettingsGameType worldSettingsGameType;
   public InfoManager infoManager;

   public WorldSettingsGameType getWorldSettingsGameType() {
      return this.worldSettingsGameType;
   }

   public InfoManager getInfoManager() {
      return this.infoManager;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.entityId = packet.readInt();
      this.gameSettingsPeacefulType = GameSettingsPeacefulType.resolveGameSettingsPeacefulType(packet.readUnsignedByte());
      this.worldSettingsGameType = WorldSettingsGameType.resolveWorldSettingsGameType(packet.readUnsignedByte());
      this.infoManager = InfoManager.resolveInfoManager(packet.resolveTextByIndex(16));
      if (this.infoManager == null) {
         this.infoManager = InfoManager.infoManager7;
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processAction((INetHandlerPlayClient)iNetHandler);
   }

   public void processAction(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.handleS07PacketRespawn(this);
   }

   public int getEntityId() {
      return this.entityId;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeInt(this.entityId);
      packet.writeByte(this.gameSettingsPeacefulType.getIndex());
      packet.writeByte(this.worldSettingsGameType.getIndex());
      packet.getPacketBuffer(this.infoManager.getText());
   }

   public GameSettingsPeacefulType getGameSettingsPeacefulType() {
      return this.gameSettingsPeacefulType;
   }

   public S07PacketRespawn(int count, GameSettingsPeacefulType gameSettingsPeacefulType2, InfoManager infoManager2, WorldSettingsGameType worldSettingsGameType2) {
      this.entityId = count;
      this.gameSettingsPeacefulType = gameSettingsPeacefulType2;
      this.worldSettingsGameType = worldSettingsGameType2;
      this.infoManager = infoManager2;
   }

   public S07PacketRespawn() {
   }
}
