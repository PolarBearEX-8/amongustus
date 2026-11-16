package net.minecraft.network.play.server;

import client.setting.GameSettingsPeacefulType;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S41PacketServerDifficulty implements Packet {
   public GameSettingsPeacefulType gameSettingsPeacefulType;
   public boolean enabled;

   public boolean isEnabled() {
      return this.enabled;
   }

   public GameSettingsPeacefulType getGameSettingsPeacefulType() {
      return this.gameSettingsPeacefulType;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.gameSettingsPeacefulType = GameSettingsPeacefulType.resolveGameSettingsPeacefulType(packet.readUnsignedByte());
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS41PacketServerDifficulty(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeByte(this.gameSettingsPeacefulType.getIndex());
   }

   public S41PacketServerDifficulty() {
   }

   public S41PacketServerDifficulty(GameSettingsPeacefulType gameSettingsPeacefulType2, boolean flag) {
      this.gameSettingsPeacefulType = gameSettingsPeacefulType2;
      this.enabled = flag;
   }
}
