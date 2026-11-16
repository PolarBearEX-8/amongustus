package net.minecraft.network.play.client;

import client.setting.GameSettingsFullType;
import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class C15PacketClientSettings implements Packet {
   public String labelText;
   public int count2;
   public GameSettingsFullType gameSettingsFullType;
   public boolean enabled;
   public int count;

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setGameSettingsFullType(GameSettingsFullType gameSettingsFullType2) {
      this.gameSettingsFullType = gameSettingsFullType2;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.labelText = packet.resolveTextByIndex(7);
      this.count2 = packet.readByte();
      this.gameSettingsFullType = GameSettingsFullType.resolveGameSettingsFullType(packet.readByte());
      this.enabled = packet.readBoolean();
      this.count = packet.readUnsignedByte();
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC15PacketClientSettings(this);
   }

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   public void setActive(boolean active) {
      this.enabled = active;
   }

   public GameSettingsFullType getGameSettingsFullType() {
      return this.gameSettingsFullType;
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.getPacketBuffer(this.labelText);
      packet.writeByte(this.count2);
      packet.writeByte(this.gameSettingsFullType.getCount());
      packet.writeBoolean(this.enabled);
      packet.writeByte(this.count);
   }

   public void updateText(String text) {
      this.labelText = text;
   }

   public void setCount(int count3) {
      this.count = count3;
   }

   public void processAction(int count3) {
      this.count2 = count3;
   }

   public C15PacketClientSettings() {
   }

   public C15PacketClientSettings(String text, int count3, GameSettingsFullType gameSettingsFullType2, boolean flag, int count4) {
      this.labelText = text;
      this.count2 = Math.max(count3, 2);
      this.gameSettingsFullType = gameSettingsFullType2;
      this.enabled = flag;
      this.count = count4;
   }
}
