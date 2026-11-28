package net.minecraft.world.chunk.storage;

import java.io.File;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.storage.SaveHandler;
import net.minecraft.world.storage.ThreadedFileIOBase;
import net.minecraft.world.storage.WorldInfo;

public class AnvilSaveHandler extends SaveHandler {
   @Override
   public IChunkLoader getChunkLoader(WorldProvider worldProvider) {
      File worldDirectory2 = this.getWorldDirectory();
      if (worldProvider instanceof WorldProviderHell) {
         File file = new File(worldDirectory2, "DIM-1");
         file.mkdirs();
         return new AnvilChunkLoader(file);
      } else if (worldProvider instanceof WorldProviderEnd) {
         File file2 = new File(worldDirectory2, "DIM1");
         file2.mkdirs();
         return new AnvilChunkLoader(file2);
      } else {
         return new AnvilChunkLoader(worldDirectory2);
      }
   }

   @Override
   public void flush() {
      try {
         ThreadedFileIOBase.getThreadedIOInstance().waitForFinish();
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

      RegionFileCache.clearRegionFileReferences();
   }

   @Override
   public void saveWorldInfoWithPlayer(WorldInfo worldInfo, NBTTagCompound tag) {
      worldInfo.setSaveVersion(19133);
      super.saveWorldInfoWithPlayer(worldInfo, tag);
   }

   public AnvilSaveHandler(File file, String str, boolean flag) {
      super(file, str, flag);
   }
}
