package net.minecraft.network.play.server;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class S21PacketChunkData implements Packet {
   private int chunkX;
   private int chunkZ;
   private S21PacketChunkData.Extracted extractedData;
   private boolean fullChunk;

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.chunkX = buf.readInt();
      this.chunkZ = buf.readInt();
      this.fullChunk = buf.readBoolean();
      this.extractedData = new S21PacketChunkData.Extracted();
      this.extractedData.dataSize = buf.readShort();
      this.extractedData.data = buf.readByteArray();
   }

   public byte[] getExtractedDataBytes() {
      return this.extractedData.data;
   }

   public int getChunkZ() {
      return this.chunkZ;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   private static int copySectionData(byte[] bytes, byte[] bytes2, int n) {
      System.arraycopy(bytes, 0, bytes2, n, bytes.length);
      return n + bytes.length;
   }

   public boolean isFullChunk() {
      return this.fullChunk;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeInt(this.chunkX);
      buf.writeInt(this.chunkZ);
      buf.writeBoolean(this.fullChunk);
      buf.writeShort((short)(this.extractedData.dataSize & 65535));
      buf.writeByteArray(this.extractedData.data);
   }

   public int getExtractedSize() {
      return this.extractedData.dataSize;
   }

   public int getChunkX() {
      return this.chunkX;
   }

   public static S21PacketChunkData.Extracted getExtractedData(Chunk chunk, boolean flag, boolean flag2, int n) {
      ExtendedBlockStorage[] blockStorageArray = chunk.getBlockStorageArray();
      S21PacketChunkData.Extracted extracted = new S21PacketChunkData.Extracted();
      ArrayList list = Lists.newArrayList();

      for (int i = 0; i < blockStorageArray.length; i++) {
         ExtendedBlockStorage extendedBlockStorage = blockStorageArray[i];
         if (extendedBlockStorage != null && (!flag || !extendedBlockStorage.isEmpty()) && (n & 1 << i) != 0) {
            extracted.dataSize |= 1 << i;
            list.add(extendedBlockStorage);
         }
      }

      extracted.data = new byte[calculateChunkSize(Integer.bitCount(extracted.dataSize), flag2, flag)];
      int n2 = 0;

      for (ExtendedBlockStorage extendedBlockStorage2 : (Iterable<ExtendedBlockStorage>)(list)) {

         for (char ch : extendedBlockStorage2.getData()) {
            extracted.data[n2++] = (byte)(ch & 255);
            extracted.data[n2++] = (byte)(ch >> '\b' & 0xFF);
         }
      }

      for (ExtendedBlockStorage extendedBlockStorage3 : (Iterable<ExtendedBlockStorage>)(list)) {
         n2 = copySectionData(extendedBlockStorage3.getBlocklightArray().getData(), extracted.data, n2);
      }

      if (flag2) {
         for (ExtendedBlockStorage extendedBlockStorage4 : (Iterable<ExtendedBlockStorage>)(list)) {
            n2 = copySectionData(extendedBlockStorage4.getSkylightArray().getData(), extracted.data, n2);
         }
      }

      if (flag) {
         copySectionData(chunk.getBiomeArray(), extracted.data, n2);
      }

      return extracted;
   }

   public static int calculateChunkSize(int n, boolean flag, boolean flag2) {
      return (n * 2 * 16 * 16 * 16) + (n * 16 * 16 * 16 / 2) + (flag ? n * 16 * 16 * 16 / 2 : 0) + (flag2 ? 256 : 0);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleChunkData(this);
   }

   public S21PacketChunkData() {
   }

   public S21PacketChunkData(Chunk chunk, boolean flag, int n) {
      this.chunkX = chunk.xPosition;
      this.chunkZ = chunk.zPosition;
      this.fullChunk = flag;
      this.extractedData = getExtractedData(chunk, flag, !chunk.getWorld().provider.getHasNoSky(), n);
   }


   public static class Extracted {
      public byte[] data;
      public int dataSize;
   }
}
