package net.minecraft.world.storage;

import java.io.File;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;

public interface ISaveHandler {
   WorldInfo loadWorldInfo();

   File getWorldDirectory();

   File getMapFileFromName(String str);

   IChunkLoader getChunkLoader(WorldProvider worldProvider);

   String getWorldDirectoryName();

   IPlayerFileData getPlayerNBTManager();

   void saveWorldInfoWithPlayer(WorldInfo worldInfo, NBTTagCompound tag);

   void checkSessionLock() throws MinecraftException;

   void saveWorldInfo(WorldInfo worldInfo);

   void flush();
}
