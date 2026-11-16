package client.util;

import client.event.CompositeListener;
import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LevelDatISaveFormat implements ISaveFormat {
   public static final Logger logger = LogManager.getLogger();
   public final File file;

   @Override
   public RandomSeedManager resolveRandomSeedManager(String text) {
      File file2 = new File(this.file, text);
      if (!file2.exists()) {
         return null;
      } else {
         File file3 = new File(file2, "level.dat");
         if (file3.exists()) {
            try {
               NBTTagCompound nBTTagCompound = LoadingNBTDataManager.resolveNBTTagCompound(new FileInputStream(file3)).getNBTTagCompound("Data");
               return new RandomSeedManager(nBTTagCompound);
            } catch (Exception exception) {
               logger.error("Exception reading " + file3, exception);
            }
         }

         File file4 = new File(file2, "level.dat_old");
         if (file4.exists()) {
            try {
               NBTTagCompound nBTTagCompound2 = LoadingNBTDataManager.resolveNBTTagCompound(new FileInputStream(file4)).getNBTTagCompound("Data");
               return new RandomSeedManager(nBTTagCompound2);
            } catch (Exception exception2) {
               logger.error("Exception reading " + file4, exception2);
            }
         }

         return null;
      }
   }

   @Override
   public boolean matchesText2(String text) {
      File file2 = new File(this.file, text);
      if (!file2.exists()) {
         return true;
      } else {
         logger.info("Deleting level " + text);

         for (int index = 1; index <= 5; index++) {
            logger.info("Attempt " + index + "...");
            if (matchesFileArray(file2.listFiles())) {
               break;
            }

            logger.warn("Unsuccessful in deleting contents.");
            if (index < 5) {
               try {
                  Thread.sleep(500L);
               } catch (InterruptedException interruptedException) {
               }
            }
         }

         return file2.delete();
      }
   }

   @Override
   public boolean isDirectory(String text) {
      return new File(this.file, text).isDirectory();
   }

   @Override
   public ISaveHandler resolveISaveHandler(String text, boolean flag) {
      return new ListenerAdapter17(this.file, text, flag);
   }

   @Override
   public String getText() {
      return "Old Format";
   }

   @Override
   public List getList() throws TertiaryExceptionAdapter {
      ArrayList list = Lists.newArrayList();

      for (int index = 0; index < 5; index++) {
         String text = "World" + (index + 1);
         RandomSeedManager randomSeedManager = this.resolveRandomSeedManager(text);
         if (randomSeedManager != null) {
            list.add(
               new ExtendedCustom(
                  text, "", randomSeedManager.getTimestamp4(), randomSeedManager.getTimestamp3(), randomSeedManager.getWorldSettingsGameType(), false, randomSeedManager.isEnabled2(), randomSeedManager.isEnabled3()
               )
            );
         }
      }

      return list;
   }

   @Override
   public boolean matchesTextByText(String text) {
      File file2 = new File(this.file, text);
      if (file2.exists()) {
         return false;
      } else {
         try {
            file2.mkdir();
            file2.delete();
            return true;
         } catch (Throwable throwable) {
            logger.warn("Couldn't make new level", throwable);
            return false;
         }
      }
   }

   @Override
   public boolean matchesText(String text, CompositeListener compositeListener) {
      return false;
   }

   @Override
   public boolean matchesText3(String text) {
      return false;
   }

   @Override
   public void applyText(String text, String text2) {
      File file2 = new File(this.file, text);
      if (file2.exists()) {
         File file3 = new File(file2, "level.dat");
         if (file3.exists()) {
            try {
               NBTTagCompound tagCompound = LoadingNBTDataManager.resolveNBTTagCompound(new FileInputStream(file3));
               tagCompound.getNBTTagCompound("Data").put("LevelName", text2);
               LoadingNBTDataManager.applyNBTTagCompound(tagCompound, new FileOutputStream(file3));
            } catch (Exception exception) {
               exception.printStackTrace();
            }
         }
      }
   }

   @Override
   public void updateState() {
   }

   @Override
   public boolean matchesText(String text) {
      return false;
   }

   public static boolean matchesFileArray(File[] fileArray) {
      for (int index = 0; index < fileArray.length; index++) {
         File file2 = fileArray[index];
         logger.debug("Deleting " + file2);
         if (file2.isDirectory() && !matchesFileArray(file2.listFiles())) {
            logger.warn("Couldn't delete directory " + file2);
            return false;
         }

         if (!file2.delete()) {
            logger.warn("Couldn't delete file " + file2);
            return false;
         }
      }

      return true;
   }

   public LevelDatISaveFormat(File file2) {
      if (!file2.exists()) {
         file2.mkdirs();
      }

      this.file = file2;
   }
}
