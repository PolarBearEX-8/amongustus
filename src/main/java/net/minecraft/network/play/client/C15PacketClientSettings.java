package net.minecraft.network.play.client;

import net.minecraft.entity.player.EntityPlayer;
import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C15PacketClientSettings implements Packet {
   private String lang;
   private int view;
   private EntityPlayer.EnumChatVisibility chatVisibility;
   private boolean enableColors;
   private int modelPartFlags;

   public void setEnableColors(boolean flag) {
      this.enableColors = flag;
   }

   public EntityPlayer.EnumChatVisibility getChatVisibility() {
      return this.chatVisibility;
   }

   public void setModelPartFlags(int n) {
      this.modelPartFlags = n;
   }

   public void setLang(String str) {
      this.lang = str;
   }

   public boolean isColorsEnabled() {
      return this.enableColors;
   }

   public void setView(int n) {
      this.view = n;
   }

   public void setChatVisibility(EntityPlayer.EnumChatVisibility enumChatVisibility) {
      this.chatVisibility = enumChatVisibility;
   }

   public C15PacketClientSettings(String str, int n, EntityPlayer.EnumChatVisibility enumChatVisibility, boolean flag, int n2) {
      this.lang = str;
      this.view = Math.max(n, 2);
      this.chatVisibility = enumChatVisibility;
      this.enableColors = flag;
      this.modelPartFlags = n2;
   }

   public C15PacketClientSettings() {
   }

   public int getModelPartFlags() {
      return this.modelPartFlags;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeString(this.lang);
      buf.writeByte(this.view);
      buf.writeByte(this.chatVisibility.getChatVisibility());
      buf.writeBoolean(this.enableColors);
      buf.writeByte(this.modelPartFlags);
   }

   public String getLang() {
      return this.lang;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processClientSettings(this);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.lang = buf.readStringFromBuffer(7);
      this.view = buf.readByte();
      this.chatVisibility = EntityPlayer.EnumChatVisibility.getEnumChatVisibility(buf.readByte());
      this.enableColors = buf.readBoolean();
      this.modelPartFlags = buf.readUnsignedByte();
   }

   public int getView() {
      return this.view;
   }
}
