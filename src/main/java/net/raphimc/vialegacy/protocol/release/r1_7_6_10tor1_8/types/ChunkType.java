package net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.types;

import com.viaversion.viaversion.api.minecraft.chunks.BaseChunk;
import com.viaversion.viaversion.api.minecraft.chunks.Chunk;
import com.viaversion.viaversion.api.minecraft.chunks.ChunkSection;
import com.viaversion.viaversion.api.minecraft.chunks.ChunkSectionImpl;
import com.viaversion.viaversion.api.minecraft.chunks.PaletteType;
import com.viaversion.viaversion.api.type.Type;
import com.viaversion.viaversion.util.IdAndData;
import com.viaversion.viaversion.util.Pair;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.model.ExtendedBlockStorage;

public class ChunkType extends Type<Chunk> {
   private final boolean hasSkyLight;

   public ChunkType(boolean hasSkyLight) {
      super(Chunk.class);
      this.hasSkyLight = hasSkyLight;
   }

   protected void readUnusedInt(ByteBuf byteBuf) {
   }

   protected void writeUnusedInt(ByteBuf byteBuf, Chunk chunk) {
   }

   public Chunk read(ByteBuf byteBuf) {
      int chunkX = byteBuf.readInt();
      int chunkZ = byteBuf.readInt();
      boolean fullChunk = byteBuf.readBoolean();
      short primaryBitMask = byteBuf.readShort();
      short additionalBitMask = byteBuf.readShort();
      int compressedSize = byteBuf.readInt();
      this.readUnusedInt(byteBuf);
      byte[] data = new byte[compressedSize];
      byteBuf.readBytes(data);
      byte[] uncompressedData = new byte[getSize(primaryBitMask, additionalBitMask, fullChunk, this.hasSkyLight)];
      Inflater inflater = new Inflater();

      try {
         inflater.setInput(data, 0, compressedSize);
         inflater.inflate(uncompressedData);
      } catch (DataFormatException e) {
         throw new RuntimeException("Bad compressed data format");
      } finally {
         inflater.end();
      }

      return (Chunk)(fullChunk && primaryBitMask == 0
         ? new BaseChunk(chunkX, chunkZ, true, false, 0, new ChunkSection[16], null, new ArrayList())
         : deserialize(chunkX, chunkZ, fullChunk, this.hasSkyLight, primaryBitMask, additionalBitMask, uncompressedData));
   }

   public void write(ByteBuf byteBuf, Chunk chunk) {
      Pair<byte[], Short> chunkData = serialize(chunk);
      byte[] data = (byte[])chunkData.key();
      short additionalBitMask = (Short)chunkData.value();
      Deflater deflater = new Deflater();

      byte[] compressedData;
      int compressedSize;
      try {
         deflater.setInput(data, 0, data.length);
         deflater.finish();
         compressedData = new byte[data.length];
         compressedSize = deflater.deflate(compressedData);
      } finally {
         deflater.end();
      }

      byteBuf.writeInt(chunk.getX());
      byteBuf.writeInt(chunk.getZ());
      byteBuf.writeBoolean(chunk.isFullChunk());
      byteBuf.writeShort(chunk.getBitmask());
      byteBuf.writeShort(additionalBitMask);
      byteBuf.writeInt(compressedSize);
      this.writeUnusedInt(byteBuf, chunk);
      byteBuf.writeBytes(compressedData, 0, compressedSize);
   }

   public static Chunk deserialize(int chunkX, int chunkZ, boolean fullChunk, boolean skyLight, int primaryBitMask, int additionalBitMask, byte[] chunkData) {
      ExtendedBlockStorage[] storageArrays = new ExtendedBlockStorage[16];
      int dataPosition = 0;

      for (int i = 0; i < storageArrays.length; i++) {
         if ((primaryBitMask & 1 << i) != 0) {
            if (storageArrays[i] == null) {
               storageArrays[i] = new ExtendedBlockStorage(skyLight);
            }

            byte[] blockLSBArray = storageArrays[i].getBlockLSBArray();
            System.arraycopy(chunkData, dataPosition, blockLSBArray, 0, blockLSBArray.length);
            dataPosition += blockLSBArray.length;
         }
      }

      for (int ix = 0; ix < storageArrays.length; ix++) {
         if ((primaryBitMask & 1 << ix) != 0 && storageArrays[ix] != null) {
            byte[] blockMetadataArray = storageArrays[ix].getBlockMetadataArray().getHandle();
            System.arraycopy(chunkData, dataPosition, blockMetadataArray, 0, blockMetadataArray.length);
            dataPosition += blockMetadataArray.length;
         }
      }

      for (int ixx = 0; ixx < storageArrays.length; ixx++) {
         if ((primaryBitMask & 1 << ixx) != 0 && storageArrays[ixx] != null) {
            byte[] blockLightArray = storageArrays[ixx].getBlockLightArray().getHandle();
            System.arraycopy(chunkData, dataPosition, blockLightArray, 0, blockLightArray.length);
            dataPosition += blockLightArray.length;
         }
      }

      if (skyLight) {
         for (int ixxx = 0; ixxx < storageArrays.length; ixxx++) {
            if ((primaryBitMask & 1 << ixxx) != 0 && storageArrays[ixxx] != null) {
               byte[] skyLightArray = storageArrays[ixxx].getSkyLightArray().getHandle();
               System.arraycopy(chunkData, dataPosition, skyLightArray, 0, skyLightArray.length);
               dataPosition += skyLightArray.length;
            }
         }
      }

      for (int ixxxx = 0; ixxxx < storageArrays.length; ixxxx++) {
         if ((additionalBitMask & 1 << ixxxx) != 0) {
            if (storageArrays[ixxxx] != null) {
               byte[] blockMSBArray = storageArrays[ixxxx].getOrCreateBlockMSBArray().getHandle();
               System.arraycopy(chunkData, dataPosition, blockMSBArray, 0, blockMSBArray.length);
               dataPosition += blockMSBArray.length;
            } else {
               dataPosition += 2048;
            }
         }
      }

      int[] biomeData = null;
      if (fullChunk) {
         biomeData = new int[256];

         for (int ixxxxx = 0; ixxxxx < biomeData.length; ixxxxx++) {
            biomeData[ixxxxx] = chunkData[dataPosition + ixxxxx] & 255;
         }

         dataPosition += biomeData.length;
      }

      ChunkSection[] sections = new ChunkSection[16];

      for (int ixxxxx = 0; ixxxxx < storageArrays.length; ixxxxx++) {
         ExtendedBlockStorage storage = storageArrays[ixxxxx];
         if (storage != null) {
            ChunkSection section = sections[ixxxxx] = new ChunkSectionImpl(true);
            section.palette(PaletteType.BLOCKS).addId(0);

            for (int x = 0; x < 16; x++) {
               for (int z = 0; z < 16; z++) {
                  for (int y = 0; y < 16; y++) {
                     section.palette(PaletteType.BLOCKS).setIdAt(x, y, z, IdAndData.toRawData(storage.getBlockId(x, y, z), storage.getBlockMetadata(x, y, z)));
                  }
               }
            }

            section.getLight().setBlockLight(storage.getBlockLightArray().getHandle());
            if (skyLight) {
               section.getLight().setSkyLight(storage.getSkyLightArray().getHandle());
            }
         }
      }

      return new BaseChunk(chunkX, chunkZ, fullChunk, false, primaryBitMask, sections, biomeData, new ArrayList());
   }

   public static Pair<byte[], Short> serialize(Chunk chunk) {
      ExtendedBlockStorage[] storageArrays = new ExtendedBlockStorage[16];

      for (int i = 0; i < storageArrays.length; i++) {
         ChunkSection section = chunk.getSections()[i];
         if (section != null) {
            ExtendedBlockStorage storage = storageArrays[i] = new ExtendedBlockStorage(section.getLight().hasSkyLight());

            for (int x = 0; x < 16; x++) {
               for (int z = 0; z < 16; z++) {
                  for (int y = 0; y < 16; y++) {
                     int flatBlock = section.palette(PaletteType.BLOCKS).idAt(x, y, z);
                     storage.setBlockId(x, y, z, flatBlock >> 4);
                     storage.setBlockMetadata(x, y, z, flatBlock & 15);
                  }
               }
            }

            storage.getBlockLightArray().setHandle(section.getLight().getBlockLight());
            if (section.getLight().hasSkyLight()) {
               storage.getSkyLightArray().setHandle(section.getLight().getSkyLight());
            }
         }
      }

      boolean biomes = chunk.isFullChunk() && chunk.getBiomeData() != null;
      byte[] chunkData = new byte[getSize(storageArrays, (short)chunk.getBitmask(), biomes)];
      int dataPosition = 0;

      for (int ix = 0; ix < storageArrays.length; ix++) {
         if ((chunk.getBitmask() & 1 << ix) != 0) {
            byte[] blockLSBArray = storageArrays[ix].getBlockLSBArray();
            System.arraycopy(blockLSBArray, 0, chunkData, dataPosition, blockLSBArray.length);
            dataPosition += blockLSBArray.length;
         }
      }

      for (int ixx = 0; ixx < storageArrays.length; ixx++) {
         if ((chunk.getBitmask() & 1 << ixx) != 0) {
            byte[] blockMetadataArray = storageArrays[ixx].getBlockMetadataArray().getHandle();
            System.arraycopy(blockMetadataArray, 0, chunkData, dataPosition, blockMetadataArray.length);
            dataPosition += blockMetadataArray.length;
         }
      }

      for (int ixxx = 0; ixxx < storageArrays.length; ixxx++) {
         if ((chunk.getBitmask() & 1 << ixxx) != 0) {
            byte[] blockLightArray = storageArrays[ixxx].getBlockLightArray().getHandle();
            System.arraycopy(blockLightArray, 0, chunkData, dataPosition, blockLightArray.length);
            dataPosition += blockLightArray.length;
         }
      }

      for (int ixxxx = 0; ixxxx < storageArrays.length; ixxxx++) {
         if ((chunk.getBitmask() & 1 << ixxxx) != 0 && storageArrays[ixxxx].getSkyLightArray() != null) {
            byte[] skyLightArray = storageArrays[ixxxx].getSkyLightArray().getHandle();
            System.arraycopy(skyLightArray, 0, chunkData, dataPosition, skyLightArray.length);
            dataPosition += skyLightArray.length;
         }
      }

      short additionalBitMask = 0;

      for (int ixxxxx = 0; ixxxxx < storageArrays.length; ixxxxx++) {
         if ((chunk.getBitmask() & 1 << ixxxxx) != 0 && storageArrays[ixxxxx].hasBlockMSBArray()) {
            additionalBitMask |= (short)(1 << ixxxxx);
            byte[] blockMSBArray = storageArrays[ixxxxx].getOrCreateBlockMSBArray().getHandle();
            System.arraycopy(blockMSBArray, 0, chunkData, dataPosition, blockMSBArray.length);
            dataPosition += blockMSBArray.length;
         }
      }

      if (biomes) {
         for (int biome : chunk.getBiomeData()) {
            chunkData[dataPosition++] = (byte)biome;
         }
      }

      return new Pair(chunkData, additionalBitMask);
   }

   public static int getSize(short primaryBitMask, short additionalBitMask, boolean fullChunk, boolean skyLight) {
      int primarySectionCount = Integer.bitCount(primaryBitMask & '\uffff');
      int size = 8192 * primarySectionCount + 2048 * Integer.bitCount(additionalBitMask & '\uffff');
      if (skyLight) {
         size += 2048 * primarySectionCount;
      }

      if (fullChunk) {
         size += 256;
      }

      return size;
   }

   public static int getSize(ExtendedBlockStorage[] storageArrays, short bitmask, boolean biomes) {
      int size = 0;

      for (int i = 0; i < storageArrays.length; i++) {
         if ((bitmask & 1 << i) != 0) {
            size += 4096;
            size += 2048;
            size += 2048;
            if (storageArrays[i].getSkyLightArray() != null) {
               size += 2048;
            }

            if (storageArrays[i].hasBlockMSBArray()) {
               size += 2048;
            }
         }
      }

      if (biomes) {
         size += 256;
      }

      return size;
   }
}
