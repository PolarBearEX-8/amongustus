package net.minecraft.network.play.server;

import client.setting.GameSettingsPeacefulType;
import client.setting.WorldSettingsGameType;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.InfoManager;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S01PacketJoinGame implements Packet {
   public int count2;
   public boolean enabled;
   public WorldSettingsGameType worldSettingsGameType;
   public int count3;
   public GameSettingsPeacefulType gameSettingsPeacefulType;
   public int count;
   public InfoManager infoManager;
   public boolean enabled2;

   public WorldSettingsGameType getWorldSettingsGameType() {
      return this.worldSettingsGameType;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public InfoManager getInfoManager() {
      return this.infoManager;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count2 = packet.readInt();
      short shortValue = packet.readUnsignedByte();
      this.enabled = (shortValue & 8) == 8;
      int count4 = shortValue & -9;
      this.worldSettingsGameType = WorldSettingsGameType.resolveWorldSettingsGameType(count4);
      this.count3 = packet.readByte();
      this.gameSettingsPeacefulType = GameSettingsPeacefulType.resolveGameSettingsPeacefulType(packet.readUnsignedByte());
      this.count = packet.readUnsignedByte();
      this.infoManager = InfoManager.resolveInfoManager(packet.resolveTextByIndex(16));
      if (this.infoManager == null) {
         this.infoManager = InfoManager.infoManager7;
      }

      this.enabled2 = packet.readBoolean();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public GameSettingsPeacefulType getGameSettingsPeacefulType() {
      return this.gameSettingsPeacefulType;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS01PacketJoinGame(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeInt(this.count2);
      int index = this.worldSettingsGameType.getIndex();
      if (this.enabled) {
         index |= 8;
      }

      packet.writeByte(index);
      packet.writeByte(this.count3);
      packet.writeByte(this.gameSettingsPeacefulType.getIndex());
      packet.writeByte(this.count);
      packet.getPacketBuffer(this.infoManager.getText());
      packet.writeBoolean(this.enabled2);
   }

   public int getCount3() {
      return this.count3;
   }

   public int getCount2() {
      return this.count2;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public int getCount() {
      return this.count;
   }

   public S01PacketJoinGame(
      int count4, WorldSettingsGameType worldSettingsGameType2, boolean flag, int count5, GameSettingsPeacefulType gameSettingsPeacefulType2, int count6, InfoManager infoManager2, boolean flag2
   ) {
      this.count2 = count4;
      this.count3 = count5;
      this.gameSettingsPeacefulType = gameSettingsPeacefulType2;
      this.worldSettingsGameType = worldSettingsGameType2;
      this.count = count6;
      this.enabled = flag;
      this.infoManager = infoManager2;
      this.enabled2 = flag2;
   }

   public S01PacketJoinGame() {
   }
}
