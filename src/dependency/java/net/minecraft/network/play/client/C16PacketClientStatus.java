package net.minecraft.network.play.client;

import client.setting.AutoSoupPerformRespawnType;
import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class C16PacketClientStatus implements Packet {
   public AutoSoupPerformRespawnType autoSoupPerformRespawnType;

   public AutoSoupPerformRespawnType getAutoSoupPerformRespawnType() {
      return this.autoSoupPerformRespawnType;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.autoSoupPerformRespawnType = (AutoSoupPerformRespawnType)packet.getEnum(AutoSoupPerformRespawnType.class);
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC16PacketClientStatus(this);
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyEnum(this.autoSoupPerformRespawnType);
   }

   public C16PacketClientStatus() {
   }

   public C16PacketClientStatus(AutoSoupPerformRespawnType autoSoupPerformRespawnType2) {
      this.autoSoupPerformRespawnType = autoSoupPerformRespawnType2;
   }
}
