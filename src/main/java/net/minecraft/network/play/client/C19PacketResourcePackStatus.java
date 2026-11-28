package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C19PacketResourcePackStatus implements Packet {
   private String hash;
   private C19PacketResourcePackStatus.Action status;

   public C19PacketResourcePackStatus(String str, C19PacketResourcePackStatus.Action action) {
      if (str.length() > 40) {
         str = str.substring(0, 40);
      }

      this.hash = str;
      this.status = action;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeString(this.hash);
      buf.writeEnumValue(this.status);
   }

   public C19PacketResourcePackStatus() {
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.handleResourcePackStatus(this);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.hash = buf.readStringFromBuffer(40);
      this.status = (C19PacketResourcePackStatus.Action)buf.readEnumValue(C19PacketResourcePackStatus.Action.class);
   }


   public enum Action {
      SUCCESSFULLY_LOADED,
      DECLINED,
      FAILED_DOWNLOAD,
      ACCEPTED;
   }
}
