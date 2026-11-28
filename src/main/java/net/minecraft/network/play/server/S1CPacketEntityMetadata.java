package net.minecraft.network.play.server;

import java.io.IOException;
import java.util.List;
import net.minecraft.entity.DataWatcher;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S1CPacketEntityMetadata implements Packet {
   private int entityId;
   private List dataManagerEntries;

   public int getEntityId() {
      return this.entityId;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityId);
      DataWatcher.writeWatchedListToPacketBuffer(this.dataManagerEntries, buf);
   }

   public S1CPacketEntityMetadata() {
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readVarIntFromBuffer();
      this.dataManagerEntries = DataWatcher.readWatchedListFromPacketBuffer(buf);
   }

   public List getDataManagerEntries() {
      return this.dataManagerEntries;
   }

   public S1CPacketEntityMetadata(int n, DataWatcher dataWatcher, boolean flag) {
      this.entityId = n;
      if (flag) {
         this.dataManagerEntries = dataWatcher.getAllWatched();
      } else {
         this.dataManagerEntries = dataWatcher.getChanged();
      }
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleEntityMetadata(this);
   }
}
