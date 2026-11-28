package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S03PacketTimeUpdate implements Packet {
   private long totalWorldTime;
   private long worldTime;

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeLong(this.totalWorldTime);
      buf.writeLong(this.worldTime);
   }

   public S03PacketTimeUpdate() {
   }

   public S03PacketTimeUpdate(long l, long l2, boolean flag) {
      this.totalWorldTime = l;
      this.worldTime = l2;
      if (!flag) {
         this.worldTime = -this.worldTime;
         if (this.worldTime == 0L) {
            this.worldTime = -1L;
         }
      }
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleTimeUpdate(this);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.totalWorldTime = buf.readLong();
      this.worldTime = buf.readLong();
   }

   public long getTotalWorldTime() {
      return this.totalWorldTime;
   }

   public long getWorldTime() {
      return this.worldTime;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }
}
