package net.minecraft.network.play.server;

import java.io.IOException;
import java.util.Collection;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.Vec4b;
import net.minecraft.world.storage.MapData;

public class S34PacketMaps implements Packet {
   private int mapId;
   private byte mapScale;
   private Vec4b[] mapVisiblePlayersVec4b;
   private int mapMinX;
   private int mapMinY;
   private int mapMaxX;
   private int mapMaxY;
   private byte[] mapDataBytes;

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.mapId = buf.readVarIntFromBuffer();
      this.mapScale = buf.readByte();
      this.mapVisiblePlayersVec4b = new Vec4b[buf.readVarIntFromBuffer()];

      for (int i = 0; i < this.mapVisiblePlayersVec4b.length; i++) {
         short s = (short)buf.readByte();
         this.mapVisiblePlayersVec4b[i] = new Vec4b((byte)(s >> 4 & 15), buf.readByte(), buf.readByte(), (byte)(s & 15));
      }

      this.mapMaxX = buf.readUnsignedByte();
      if (this.mapMaxX > 0) {
         this.mapMaxY = buf.readUnsignedByte();
         this.mapMinX = buf.readUnsignedByte();
         this.mapMinY = buf.readUnsignedByte();
         this.mapDataBytes = buf.readByteArray();
      }
   }

   public void setMapdataTo(MapData mapData) {
      mapData.scale = this.mapScale;
      mapData.mapDecorations.clear();

      for (int i = 0; i < this.mapVisiblePlayersVec4b.length; i++) {
         Vec4b vec4b = this.mapVisiblePlayersVec4b[i];
         mapData.mapDecorations.put("icon-" + i, vec4b);
      }

      for (int j = 0; j < this.mapMaxX; j++) {
         for (int k = 0; k < this.mapMaxY; k++) {
            mapData.colors[this.mapMinX + j + (this.mapMinY + k) * 128] = this.mapDataBytes[j + k * this.mapMaxX];
         }
      }
   }

   public S34PacketMaps(int n, byte b, Collection items, byte[] bytes, int n2, int n3, int n4, int n5) {
      this.mapId = n;
      this.mapScale = b;
      this.mapVisiblePlayersVec4b = (Vec4b[])items.toArray(new Vec4b[items.size()]);
      this.mapMinX = n2;
      this.mapMinY = n3;
      this.mapMaxX = n4;
      this.mapMaxY = n5;
      this.mapDataBytes = new byte[n4 * n5];

      for (int i = 0; i < n4; i++) {
         for (int j = 0; j < n5; j++) {
            this.mapDataBytes[i + j * n4] = bytes[n2 + i + (n3 + j) * 128];
         }
      }
   }

   public S34PacketMaps() {
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleMaps(this);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.mapId);
      buf.writeByte(this.mapScale);
      buf.writeVarIntToBuffer(this.mapVisiblePlayersVec4b.length);

      for (Vec4b vec4b : this.mapVisiblePlayersVec4b) {
         buf.writeByte((vec4b.getType() & 15) << 4 | vec4b.getRotation() & 15);
         buf.writeByte(vec4b.getX());
         buf.writeByte(vec4b.getY());
      }

      buf.writeByte(this.mapMaxX);
      if (this.mapMaxX > 0) {
         buf.writeByte(this.mapMaxY);
         buf.writeByte(this.mapMinX);
         buf.writeByte(this.mapMinY);
         buf.writeByteArray(this.mapDataBytes);
      }
   }

   public int getMapId() {
      return this.mapId;
   }
}
