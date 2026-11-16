package client.util;

import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class FovMatcher {
   public static FovFilter resolveFovFilter(NBTTagCompound tagCompound) {
      int count = tagCompound.computeCount("xPos");
      int count2 = tagCompound.computeCount("zPos");
      FovFilter fovFilter = new FovFilter(count, count2);
      fovFilter.byteFlagArray2 = tagCompound.resolveByteArray("Blocks");
      fovFilter.recordNode3 = new RecordNode25(tagCompound.resolveByteArray("Data"), 7);
      fovFilter.recordNode2 = new RecordNode25(tagCompound.resolveByteArray("SkyLight"), 7);
      fovFilter.recordNode25 = new RecordNode25(tagCompound.resolveByteArray("BlockLight"), 7);
      fovFilter.byteFlagArray = tagCompound.resolveByteArray("HeightMap");
      fovFilter.enabled = tagCompound.matchesText("TerrainPopulated");
      fovFilter.nBTTagList = tagCompound.getExtendedLoggerEntry("Entities", 10);
      fovFilter.nBTTagList2 = tagCompound.getExtendedLoggerEntry("TileEntities", 10);
      fovFilter.nBTTagList3 = tagCompound.getExtendedLoggerEntry("TileTicks", 10);

      try {
         fovFilter.timestamp = tagCompound.computeTimestamp("LastUpdate");
      } catch (ClassCastException classCastException) {
         fovFilter.timestamp = (long)tagCompound.computeCount("LastUpdate");
      }

      return fovFilter;
   }

   public static void applyFovFilter(FovFilter fovFilter, NBTTagCompound tagCompound, InvalidBiomeIdManager invalidBiomeIdManager) {
      tagCompound.put("xPos", fovFilter.count);
      tagCompound.put("zPos", fovFilter.count2);
      tagCompound.put("LastUpdate", fovFilter.timestamp);
      int[] countArray = new int[fovFilter.byteFlagArray.length];

      for (int index = 0; index < fovFilter.byteFlagArray.length; index++) {
         countArray[index] = fovFilter.byteFlagArray[index];
      }

      tagCompound.put("HeightMap", countArray);
      tagCompound.applyText("TerrainPopulated", fovFilter.enabled);
      NBTTagList nBTTagList = new NBTTagList();

      for (int index2 = 0; index2 < 8; index2++) {
         boolean flag = true;

         for (int index3 = 0; index3 < 16 && flag; index3++) {
            for (int index4 = 0; index4 < 16 && flag; index4++) {
               for (int index5 = 0; index5 < 16; index5++) {
                  int count = index3 << 11 | index5 << 7 | index4 + (index2 << 4);
                  if (fovFilter.byteFlagArray2[count] != 0) {
                     flag = false;
                     break;
                  }
               }
            }
         }

         if (!flag) {
            byte[] byteValueArray = new byte[4096];
            SpecialByteArrayEntry specialByteArrayEntry = new SpecialByteArrayEntry();
            SpecialByteArrayEntry specialByteArrayEntry2 = new SpecialByteArrayEntry();
            SpecialByteArrayEntry specialByteArrayEntry3 = new SpecialByteArrayEntry();

            for (int index6 = 0; index6 < 16; index6++) {
               for (int index7 = 0; index7 < 16; index7++) {
                  for (int index8 = 0; index8 < 16; index8++) {
                     int count2 = index6 << 11 | index8 << 7 | index7 + (index2 << 4);
                     byte byteValue = fovFilter.byteFlagArray2[count2];
                     byteValueArray[index7 << 8 | index8 << 4 | index6] = (byte)(byteValue & 255);
                     specialByteArrayEntry.applyIndex(index6, index7, index8, fovFilter.recordNode3.computeCount(index6, index7 + (index2 << 4), index8));
                     specialByteArrayEntry2.applyIndex(index6, index7, index8, fovFilter.recordNode2.computeCount(index6, index7 + (index2 << 4), index8));
                     specialByteArrayEntry3.applyIndex(index6, index7, index8, fovFilter.recordNode25.computeCount(index6, index7 + (index2 << 4), index8));
                  }
               }
            }

            NBTTagCompound tagCompound2 = new NBTTagCompound();
            tagCompound2.put("Y", (byte)(index2 & 0xFF));
            tagCompound2.put("Blocks", byteValueArray);
            tagCompound2.put("Data", specialByteArrayEntry.getByteFlagArray());
            tagCompound2.put("SkyLight", specialByteArrayEntry2.getByteFlagArray());
            tagCompound2.put("BlockLight", specialByteArrayEntry3.getByteFlagArray());
            nBTTagList.applyLISTManager(tagCompound2);
         }
      }

      tagCompound.applyText("Sections", nBTTagList);
      byte[] byteValueArray2 = new byte[256];
      AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

      for (int index9 = 0; index9 < 16; index9++) {
         for (int index10 = 0; index10 < 16; index10++) {
            auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(fovFilter.count << 4 | index9, 0, fovFilter.count2 << 4 | index10);
            byteValueArray2[index10 << 4 | index9] = (byte)(
               invalidBiomeIdManager.resolveBiomeIDIsOutOfBoundsManager(auxiliaryBlockPosAdapter.getX(), auxiliaryBlockPosAdapter.getZ(), BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager10).count & 0xFF
            );
         }
      }

      tagCompound.put("Biomes", byteValueArray2);
      tagCompound.applyText("Entities", fovFilter.nBTTagList);
      tagCompound.applyText("TileEntities", fovFilter.nBTTagList2);
      if (fovFilter.nBTTagList3 != null) {
         tagCompound.applyText("TileTicks", fovFilter.nBTTagList3);
      }
   }
}
