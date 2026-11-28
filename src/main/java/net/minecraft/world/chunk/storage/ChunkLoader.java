package net.minecraft.world.chunk.storage;

import net.minecraft.util.BlockPos;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.NibbleArray;

public class ChunkLoader {
   public static ChunkLoader.AnvilConverterData load(NBTTagCompound tag) {
      int integer = tag.getInteger("xPos");
      int integer2 = tag.getInteger("zPos");
      ChunkLoader.AnvilConverterData anvilConverterData = new ChunkLoader.AnvilConverterData(integer, integer2);
      anvilConverterData.blocks = tag.getByteArray("Blocks");
      anvilConverterData.data = new NibbleArrayReader(tag.getByteArray("Data"), 7);
      anvilConverterData.skyLight = new NibbleArrayReader(tag.getByteArray("SkyLight"), 7);
      anvilConverterData.blockLight = new NibbleArrayReader(tag.getByteArray("BlockLight"), 7);
      anvilConverterData.heightmap = tag.getByteArray("HeightMap");
      anvilConverterData.terrainPopulated = tag.getBoolean("TerrainPopulated");
      anvilConverterData.entities = tag.getTagList("Entities", 10);
      anvilConverterData.tileEntities = tag.getTagList("TileEntities", 10);
      anvilConverterData.tileTicks = tag.getTagList("TileTicks", 10);

      try {
         anvilConverterData.lastUpdated = tag.getLong("LastUpdate");
      } catch (ClassCastException e) {
         anvilConverterData.lastUpdated = (long)tag.getInteger("LastUpdate");
      }

      return anvilConverterData;
   }

   public static void convertToAnvilFormat(ChunkLoader.AnvilConverterData anvilConverterData, NBTTagCompound tag, WorldChunkManager worldChunkManager) {
      tag.setInteger("xPos", anvilConverterData.x);
      tag.setInteger("zPos", anvilConverterData.z);
      tag.setLong("LastUpdate", anvilConverterData.lastUpdated);
      int[] ints = new int[anvilConverterData.heightmap.length];

      for (int i = 0; i < anvilConverterData.heightmap.length; i++) {
         ints[i] = anvilConverterData.heightmap[i];
      }

      tag.setIntArray("HeightMap", ints);
      tag.setBoolean("TerrainPopulated", anvilConverterData.terrainPopulated);
      NBTTagList tagList = new NBTTagList();

      for (int j = 0; j < 8; j++) {
         boolean flag = true;

         for (int k = 0; k < 16 && flag; k++) {
            for (int m = 0; m < 16 && flag; m++) {
               for (int idx = 0; idx < 16; idx++) {
                  int n = k << 11 | idx << 7 | m + (j << 4);
                  if (anvilConverterData.blocks[n] != 0) {
                     flag = false;
                     break;
                  }
               }
            }
         }

         if (!flag) {
            byte[] bytes = new byte[4096];
            NibbleArray nibbleArray = new NibbleArray();
            NibbleArray nibbleArray2 = new NibbleArray();
            NibbleArray nibbleArray3 = new NibbleArray();

            for (int index = 0; index < 16; index++) {
               for (int i2 = 0; i2 < 16; i2++) {
                  for (int i3 = 0; i3 < 16; i3++) {
                     int n2 = index << 11 | i3 << 7 | i2 + (j << 4);
                     bytes[i2 << 8 | i3 << 4 | index] = (byte)(anvilConverterData.blocks[n2] & 255);
                     nibbleArray.set(index, i2, i3, anvilConverterData.data.get(index, i2 + (j << 4), i3));
                     nibbleArray2.set(index, i2, i3, anvilConverterData.skyLight.get(index, i2 + (j << 4), i3));
                     nibbleArray3.set(index, i2, i3, anvilConverterData.blockLight.get(index, i2 + (j << 4), i3));
                  }
               }
            }

            NBTTagCompound tag2 = new NBTTagCompound();
            tag2.setByte("Y", (byte)(j & 0xFF));
            tag2.setByteArray("Blocks", bytes);
            tag2.setByteArray("Data", nibbleArray.getData());
            tag2.setByteArray("SkyLight", nibbleArray2.getData());
            tag2.setByteArray("BlockLight", nibbleArray3.getData());
            tagList.appendTag(tag2);
         }
      }

      tag.setTag("Sections", tagList);
      byte[] bytes2 = new byte[256];
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

      for (int i4 = 0; i4 < 16; i4++) {
         for (int i5 = 0; i5 < 16; i5++) {
            pos.set(anvilConverterData.x << 4 | i4, 0, anvilConverterData.z << 4 | i5);
            bytes2[i5 << 4 | i4] = (byte)(worldChunkManager.getBiome(pos.getX(), pos.getZ(), BiomeGenBase.DEFAULT).biomeID & 0xFF);
         }
      }

      tag.setByteArray("Biomes", bytes2);
      tag.setTag("Entities", anvilConverterData.entities);
      tag.setTag("TileEntities", anvilConverterData.tileEntities);
      if (anvilConverterData.tileTicks != null) {
         tag.setTag("TileTicks", anvilConverterData.tileTicks);
      }
   }


   public static class AnvilConverterData {
      public long lastUpdated;
      public boolean terrainPopulated;
      public byte[] heightmap;
      public NibbleArrayReader blockLight;
      public NibbleArrayReader skyLight;
      public NibbleArrayReader data;
      public byte[] blocks;
      public NBTTagList entities;
      public NBTTagList tileEntities;
      public NBTTagList tileTicks;
      public final int x;
      public final int z;

      public AnvilConverterData(int n, int n2) {
         this.x = n;
         this.z = n2;
      }
   }
}
