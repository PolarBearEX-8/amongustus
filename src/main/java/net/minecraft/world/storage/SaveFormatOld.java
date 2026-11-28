package net.minecraft.world.storage;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IProgressUpdate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveFormatOld implements ISaveFormat {
   private static final Logger LOGGER = LogManager.getLogger();
   public final File savesDirectory;

   @Override
   public boolean convertMapFormat(String str, IProgressUpdate progressUpdate) {
      return false;
   }

   @Override
   public boolean isOldMapFormat(String str) {
      return false;
   }

   @Override
   public boolean deleteWorldDirectory(String str) {
      File file = new File(this.savesDirectory, str);
      if (!file.exists()) {
         return true;
      } else {
         LOGGER.info("Deleting level " + str);

         for (int i = 1; i <= 5; i++) {
            LOGGER.info("Attempt " + i + "...");
            if (deleteFiles(file.listFiles())) {
               break;
            }

            LOGGER.warn("Unsuccessful in deleting contents.");
            if (i < 5) {
               try {
                  Thread.sleep(500L);
               } catch (InterruptedException e) {
               }
            }
         }

         return file.delete();
      }
   }

   @Override
   public void renameWorld(String str, String str2) {
      File file = new File(this.savesDirectory, str);
      if (file.exists()) {
         File file2 = new File(file, "level.dat");
         if (file2.exists()) {
            try {
               NBTTagCompound tag = CompressedStreamTools.readCompressed(new FileInputStream(file2));
               tag.getCompoundTag("Data").setString("LevelName", str2);
               CompressedStreamTools.writeCompressed(tag, new FileOutputStream(file2));
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
   }

   @Override
   public boolean isConvertible(String str) {
      return false;
   }

   @Override
   public List getSaveList() throws AnvilConverterException {
      ArrayList list = Lists.newArrayList();

      for (int i = 0; i < 5; i++) {
         String str = "World" + (i + 1);
         WorldInfo worldInfo = this.getWorldInfo(str);
         if (worldInfo != null) {
            list.add(
               new SaveFormatComparator(
                  str, "", worldInfo.getLastTimePlayed(), worldInfo.getSizeOnDisk(), worldInfo.getGameType(), false, worldInfo.isHardcoreModeEnabled(), worldInfo.areCommandsAllowed()
               )
            );
         }
      }

      return list;
   }

   @Override
   public ISaveHandler getSaveLoader(String str, boolean flag) {
      return new SaveHandler(this.savesDirectory, str, flag);
   }

   @Override
   public WorldInfo getWorldInfo(String str) {
      File file = new File(this.savesDirectory, str);
      if (!file.exists()) {
         return null;
      } else {
         File file2 = new File(file, "level.dat");
         if (file2.exists()) {
            try {
               NBTTagCompound compoundTag = CompressedStreamTools.readCompressed(new FileInputStream(file2)).getCompoundTag("Data");
               return new WorldInfo(compoundTag);
            } catch (Exception e) {
               LOGGER.error("Exception reading " + file2, e);
            }
         }

         file2 = new File(file, "level.dat_old");
         if (file2.exists()) {
            try {
               NBTTagCompound compoundTag2 = CompressedStreamTools.readCompressed(new FileInputStream(file2)).getCompoundTag("Data");
               return new WorldInfo(compoundTag2);
            } catch (Exception e2) {
               LOGGER.error("Exception reading " + file2, e2);
            }
         }

         return null;
      }
   }

   @Override
   public String getName() {
      return "Old Format";
   }

   @Override
   public void flushCache() {
   }

   @Override
   public boolean isNewLevelIdAcceptable(String str) {
      File file = new File(this.savesDirectory, str);
      if (file.exists()) {
         return false;
      } else {
         try {
            file.mkdir();
            file.delete();
            return true;
         } catch (Throwable e) {
            LOGGER.warn("Couldn't make new level", e);
            return false;
         }
      }
   }

   public SaveFormatOld(File file) {
      if (!file.exists()) {
         file.mkdirs();
      }

      this.savesDirectory = file;
   }

   @Override
   public boolean canLoadWorld(String str) {
      return new File(this.savesDirectory, str).isDirectory();
   }

   public static boolean deleteFiles(File[] files) {
      for (int i = 0; i < files.length; i++) {
         File file = files[i];
         LOGGER.debug("Deleting " + file);
         if (file.isDirectory() && !deleteFiles(file.listFiles())) {
            LOGGER.warn("Couldn't delete directory " + file);
            return false;
         }

         if (!file.delete()) {
            LOGGER.warn("Couldn't delete file " + file);
            return false;
         }
      }

      return true;
   }
}
