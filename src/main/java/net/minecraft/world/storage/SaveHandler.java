package net.minecraft.world.storage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveHandler implements IPlayerFileData, ISaveHandler {
   private static final Logger LOGGER = LogManager.getLogger();
   private final File worldDirectory;
   private final File playersDirectory;
   private final File mapDataDir;
   private final long initializationTime = MinecraftServer.getCurrentTimeMillis();
   private final String saveDirectoryName;

   @Override
   public void flush() {
   }

   @Override
   public IChunkLoader getChunkLoader(WorldProvider worldProvider) {
      throw new RuntimeException("Old Chunk Storage is no longer supported.");
   }

   @Override
   public File getWorldDirectory() {
      return this.worldDirectory;
   }

   @Override
   public NBTTagCompound readPlayerData(EntityPlayer player) {
      NBTTagCompound tag = null;

      try {
         File file = new File(this.playersDirectory, player.getUniqueID().toString() + ".dat");
         if (file.exists() && file.isFile()) {
            tag = CompressedStreamTools.readCompressed(new FileInputStream(file));
         }
      } catch (Exception e) {
         LOGGER.warn("Failed to load player data for " + player.getName());
      }

      if (tag != null) {
         player.readFromNBT(tag);
      }

      return tag;
   }

   @Override
   public String getWorldDirectoryName() {
      return this.saveDirectoryName;
   }

   @Override
   public void saveWorldInfo(WorldInfo worldInfo) {
      NBTTagCompound nbtTagCompound = worldInfo.getNBTTagCompound();
      NBTTagCompound tag = new NBTTagCompound();
      tag.setTag("Data", nbtTagCompound);

      try {
         File file = new File(this.worldDirectory, "level.dat_new");
         File file2 = new File(this.worldDirectory, "level.dat_old");
         File file3 = new File(this.worldDirectory, "level.dat");
         CompressedStreamTools.writeCompressed(tag, new FileOutputStream(file));
         if (file2.exists()) {
            file2.delete();
         }

         file3.renameTo(file2);
         if (file3.exists()) {
            file3.delete();
         }

         file.renameTo(file3);
         if (file.exists()) {
            file.delete();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @Override
   public void saveWorldInfoWithPlayer(WorldInfo worldInfo, NBTTagCompound tag) {
      NBTTagCompound tag2 = worldInfo.cloneNBTCompound(tag);
      NBTTagCompound tag3 = new NBTTagCompound();
      tag3.setTag("Data", tag2);

      try {
         File file = new File(this.worldDirectory, "level.dat_new");
         File file2 = new File(this.worldDirectory, "level.dat_old");
         File file3 = new File(this.worldDirectory, "level.dat");
         CompressedStreamTools.writeCompressed(tag3, new FileOutputStream(file));
         if (file2.exists()) {
            file2.delete();
         }

         file3.renameTo(file2);
         if (file3.exists()) {
            file3.delete();
         }

         file.renameTo(file3);
         if (file.exists()) {
            file.delete();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @Override
   public String[] getAvailablePlayerDat() {
      String[] strings = this.playersDirectory.list();
      if (strings == null) {
         strings = new String[0];
      }

      for (int i = 0; i < strings.length; i++) {
         if (strings[i].endsWith(".dat")) {
            strings[i] = strings[i].substring(0, strings[i].length() - 4);
         }
      }

      return strings;
   }

   @Override
   public IPlayerFileData getPlayerNBTManager() {
      return this;
   }

   @Override
   public WorldInfo loadWorldInfo() {
      File file = new File(this.worldDirectory, "level.dat");
      if (file.exists()) {
         try {
            NBTTagCompound compoundTag = CompressedStreamTools.readCompressed(new FileInputStream(file)).getCompoundTag("Data");
            return new WorldInfo(compoundTag);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      file = new File(this.worldDirectory, "level.dat_old");
      if (file.exists()) {
         try {
            NBTTagCompound compoundTag2 = CompressedStreamTools.readCompressed(new FileInputStream(file)).getCompoundTag("Data");
            return new WorldInfo(compoundTag2);
         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }

      return null;
   }

   @Override
   public File getMapFileFromName(String str) {
      return new File(this.mapDataDir, str + ".dat");
   }

   private void setSessionLock() {
      try {
         File file = new File(this.worldDirectory, "session.lock");
         DataOutputStream out = new DataOutputStream(new FileOutputStream(file));

         try {
            out.writeLong(this.initializationTime);
         } finally {
            out.close();
         }
      } catch (IOException e) {
         e.printStackTrace();
         throw new RuntimeException("Failed to check session lock, aborting");
      }
   }

   public SaveHandler(File file, String str, boolean flag) {
      this.worldDirectory = new File(file, str);
      this.worldDirectory.mkdirs();
      this.playersDirectory = new File(this.worldDirectory, "playerdata");
      this.mapDataDir = new File(this.worldDirectory, "data");
      this.mapDataDir.mkdirs();
      this.saveDirectoryName = str;
      if (flag) {
         this.playersDirectory.mkdirs();
      }

      this.setSessionLock();
   }

   @Override
   public void writePlayerData(EntityPlayer player) {
      try {
         NBTTagCompound tag = new NBTTagCompound();
         player.writeToNBT(tag);
         File file = new File(this.playersDirectory, player.getUniqueID().toString() + ".dat.tmp");
         File file2 = new File(this.playersDirectory, player.getUniqueID().toString() + ".dat");
         CompressedStreamTools.writeCompressed(tag, new FileOutputStream(file));
         if (file2.exists()) {
            file2.delete();
         }

         file.renameTo(file2);
      } catch (Exception e) {
         LOGGER.warn("Failed to save player data for " + player.getName());
      }
   }

   @Override
   public void checkSessionLock() throws MinecraftException {
      try {
         File file = new File(this.worldDirectory, "session.lock");
         DataInputStream in = new DataInputStream(new FileInputStream(file));

         try {
            if (in.readLong() != this.initializationTime) {
               throw new MinecraftException("The save is being accessed from another location, aborting");
            }
         } finally {
            in.close();
         }
      } catch (IOException e) {
         throw new MinecraftException("Failed to check session lock, aborting");
      }
   }
}
