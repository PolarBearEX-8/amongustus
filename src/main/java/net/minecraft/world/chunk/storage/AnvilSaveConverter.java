package net.minecraft.world.chunk.storage;

import java.io.FilenameFilter;
import com.google.common.collect.Lists;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.SaveFormatOld;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnvilSaveConverter extends SaveFormatOld {
   private static final Logger LOGGER2 = LogManager.getLogger();

   @Override
   public boolean convertMapFormat(String str, IProgressUpdate progressUpdate) {
      progressUpdate.setLoadingProgress(0);
      ArrayList list = Lists.newArrayList();
      ArrayList list2 = Lists.newArrayList();
      ArrayList list3 = Lists.newArrayList();
      File file = new File(this.savesDirectory, str);
      File file2 = new File(file, "DIM-1");
      File file3 = new File(file, "DIM1");
      LOGGER2.info("Scanning folders...");
      this.addRegionFilesToCollection(file, list);
      if (file2.exists()) {
         this.addRegionFilesToCollection(file2, list2);
      }

      if (file3.exists()) {
         this.addRegionFilesToCollection(file3, list3);
      }

      int n = list.size() + list2.size() + list3.size();
      LOGGER2.info("Total conversion count is " + n);
      WorldInfo worldInfo = this.getWorldInfo(str);
      Object obj = null;
      if (worldInfo.getTerrainType() == WorldType.FLAT) {
         obj = new WorldChunkManagerHell(BiomeGenBase.plains, 0.5F);
      } else {
         obj = new WorldChunkManager(worldInfo.getSeed(), worldInfo.getTerrainType(), worldInfo.getGeneratorOptions());
      }

      this.convertFile(new File(file, "region"), list, (WorldChunkManager)obj, 0, n, progressUpdate);
      this.convertFile(new File(file2, "region"), list2, new WorldChunkManagerHell(BiomeGenBase.hell, 0.0F), list.size(), n, progressUpdate);
      this.convertFile(new File(file3, "region"), list3, new WorldChunkManagerHell(BiomeGenBase.sky, 0.0F), list.size() + list2.size(), n, progressUpdate);
      worldInfo.setSaveVersion(19133);
      if (worldInfo.getTerrainType() == WorldType.DEFAULT_1_1) {
         worldInfo.setTerrainType(WorldType.DEFAULT);
      }

      this.createFile(str);
      this.getSaveLoader(str, false).saveWorldInfo(worldInfo);
      return true;
   }

   private void convertFile(File file, Iterable items, WorldChunkManager worldChunkManager, int n, int n2, IProgressUpdate progressUpdate) {
      for (File file2 : (Iterable<File>)(items)) {
         this.convertChunks(file, file2, worldChunkManager, n, n2, progressUpdate);
         n++;
         progressUpdate.setLoadingProgress((int)Math.round(100.0 * (double)n / (double)n2));
      }
   }

   @Override
   public boolean isConvertible(String str) {
      WorldInfo worldInfo = this.getWorldInfo(str);
      return worldInfo != null && worldInfo.getSaveVersion() == 19132;
   }

   @Override
   public ISaveHandler getSaveLoader(String str, boolean flag) {
      return new AnvilSaveHandler(this.savesDirectory, str, flag);
   }

   public AnvilSaveConverter(File file) {
      super(file);
   }

   @Override
   public List getSaveList() throws AnvilConverterException {
      if (this.savesDirectory != null && this.savesDirectory.exists() && this.savesDirectory.isDirectory()) {
         ArrayList list = Lists.newArrayList();

         for (File file : this.savesDirectory.listFiles()) {
            if (file.isDirectory()) {
               String name = file.getName();
               WorldInfo worldInfo = this.getWorldInfo(name);
               if (worldInfo != null && (worldInfo.getSaveVersion() == 19132 || worldInfo.getSaveVersion() == 19133)) {
                  boolean flag = worldInfo.getSaveVersion() != this.getSaveVersion();
                  String worldName = worldInfo.getWorldName();
                  if (StringUtils.isEmpty(worldName)) {
                     worldName = name;
                  }

                  list.add(
                     new SaveFormatComparator(
                        name, worldName, worldInfo.getLastTimePlayed(), 0L, worldInfo.getGameType(), flag, worldInfo.isHardcoreModeEnabled(), worldInfo.areCommandsAllowed()
                     )
                  );
               }
            }
         }

         return list;
      } else {
         throw new AnvilConverterException("Unable to read or access folder where game worlds are saved!");
      }
   }

   @Override
   public String getName() {
      return "Anvil";
   }

   private void createFile(String str) {
      File file = new File(this.savesDirectory, str);
      if (!file.exists()) {
         LOGGER2.warn("Unable to create level.dat_mcr backup");
      } else {
         File file2 = new File(file, "level.dat");
         if (!file2.exists()) {
            LOGGER2.warn("Unable to create level.dat_mcr backup");
         } else {
            if (!file2.renameTo(new File(file, "level.dat_mcr"))) {
               LOGGER2.warn("Unable to create level.dat_mcr backup");
            }
         }
      }
   }

   public int getSaveVersion() {
      return 19133;
   }

   @Override
   public boolean isOldMapFormat(String str) {
      WorldInfo worldInfo = this.getWorldInfo(str);
      return worldInfo != null && worldInfo.getSaveVersion() != this.getSaveVersion();
   }

   private void convertChunks(File file, File file2, WorldChunkManager worldChunkManager, int n, int n2, IProgressUpdate progressUpdate) {
      try {
         String name = file2.getName();
         RegionFile regionFile = new RegionFile(file2);
         RegionFile regionFile2 = new RegionFile(new File(file, name.substring(0, name.length() - ".mcr".length()) + ".mca"));

         for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
               if (regionFile.isChunkSaved(i, j) && !regionFile2.isChunkSaved(i, j)) {
                  DataInputStream chunkDataInputStream = regionFile.getChunkDataInputStream(i, j);
                  if (chunkDataInputStream == null) {
                     LOGGER2.warn("Failed to fetch input stream");
                  } else {
                     NBTTagCompound tag = CompressedStreamTools.read(chunkDataInputStream);
                     chunkDataInputStream.close();
                     ChunkLoader.AnvilConverterData anvilConverterData = ChunkLoader.load(tag.getCompoundTag("Level"));
                     NBTTagCompound tag2 = new NBTTagCompound();
                     NBTTagCompound tag3 = new NBTTagCompound();
                     tag2.setTag("Level", tag3);
                     ChunkLoader.convertToAnvilFormat(anvilConverterData, tag3, worldChunkManager);
                     DataOutputStream chunkDataOutputStream = regionFile2.getChunkDataOutputStream(i, j);
                     CompressedStreamTools.write(tag2, chunkDataOutputStream);
                     chunkDataOutputStream.close();
                  }
               }
            }

            int n3 = (int)Math.round(100.0 * (double)(n * 1024) / (double)(n2 * 1024));
            int n4 = (int)Math.round(100.0 * (double)((i + 1) * 32 + n * 1024) / (double)(n2 * 1024));
            if (n4 > n3) {
               progressUpdate.setLoadingProgress(n4);
            }
         }

         regionFile.close();
         regionFile2.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void addRegionFilesToCollection(File file, Collection items) {
      File[] files = new File(file, "region").listFiles(new AnvilSaveConverter.DefaultFilenameFilter(this));
      if (files != null) {
         Collections.addAll(items, files);
      }
   }

   @Override
   public void flushCache() {
      RegionFileCache.clearRegionFileReferences();
   }


   public static class DefaultFilenameFilter implements FilenameFilter {
      public final AnvilSaveConverter converter;

      public DefaultFilenameFilter(AnvilSaveConverter anvilSaveConverter) {
         this.converter = anvilSaveConverter;
      }

      @Override
      public boolean accept(File file, String str) {
         return str.endsWith(".mcr");
      }
   }
}
