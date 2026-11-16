package client.util;

import client.event.GenericListener;
import client.event.PostMotionFilter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.ISaveHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListenerAdapter17 implements ISaveHandler, PostMotionFilter {
   public static final Logger logger = LogManager.getLogger();
   public final File file;
   public final File file2;
   public final File file3;
   public final long timestamp = VanillaRunnable.currentTimeMillis();
   public final String labelText;

   @Override
   public void applyRandomSeedManager(RandomSeedManager randomSeedManager, NBTTagCompound tagCompound) {
      NBTTagCompound tagCompound2 = randomSeedManager.resolveNBTTagCompound(tagCompound);
      NBTTagCompound tagCompound3 = new NBTTagCompound();
      tagCompound3.applyText("Data", tagCompound2);

      try {
         File file4 = new File(this.file, "level.dat_new");
         File file5 = new File(this.file, "level.dat_old");
         File file6 = new File(this.file, "level.dat");
         LoadingNBTDataManager.applyNBTTagCompound(tagCompound3, new FileOutputStream(file4));
         if (file5.exists()) {
            file5.delete();
         }

         file6.renameTo(file5);
         if (file6.exists()) {
            file6.delete();
         }

         file4.renameTo(file6);
         if (file4.exists()) {
            file4.delete();
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   @Override
   public void applyRandomSeedManager(RandomSeedManager randomSeedManager) {
      NBTTagCompound nBTTagCompoundAsNBTTagCompound = randomSeedManager.getNBTTagCompoundAsNBTTagCompound();
      NBTTagCompound tagCompound = new NBTTagCompound();
      tagCompound.applyText("Data", nBTTagCompoundAsNBTTagCompound);

      try {
         File file4 = new File(this.file, "level.dat_new");
         File file5 = new File(this.file, "level.dat_old");
         File file6 = new File(this.file, "level.dat");
         LoadingNBTDataManager.applyNBTTagCompound(tagCompound, new FileOutputStream(file4));
         if (file5.exists()) {
            file5.delete();
         }

         file6.renameTo(file5);
         if (file6.exists()) {
            file6.delete();
         }

         file4.renameTo(file6);
         if (file4.exists()) {
            file4.delete();
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   @Override
   public void applyEntityPlayer(EntityPlayer player) {
      try {
         NBTTagCompound tagCompound = new NBTTagCompound();
         player.applyNBTTagCompound3(tagCompound);
         File file4 = new File(this.file2, player.getEntityUniqueID().toString() + ".dat.tmp");
         File file5 = new File(this.file2, player.getEntityUniqueID().toString() + ".dat");
         LoadingNBTDataManager.applyNBTTagCompound(tagCompound, new FileOutputStream(file4));
         if (file5.exists()) {
            file5.delete();
         }

         file4.renameTo(file5);
      } catch (Exception exception) {
         logger.warn("Failed to save player data for " + player.getText());
      }
   }

   @Override
   public File getFile() {
      return this.file;
   }

   @Override
   public NBTTagCompound resolveNBTTagCompoundByEntityPlayer(EntityPlayer player) {
      NBTTagCompound tagCompound = null;

      try {
         File file4 = new File(this.file2, player.getEntityUniqueID().toString() + ".dat");
         if (file4.exists() && file4.isFile()) {
            tagCompound = LoadingNBTDataManager.resolveNBTTagCompound(new FileInputStream(file4));
         }
      } catch (Exception exception) {
         logger.warn("Failed to load player data for " + player.getText());
      }

      if (tagCompound != null) {
         player.applyNBTTagCompound2(tagCompound);
      }

      return tagCompound;
   }

   @Override
   public void updateState() {
   }

   @Override
   public File resolveFile(String text) {
      return new File(this.file3, text + ".dat");
   }

   @Override
   public GenericListener resolveGenericListener(SecondaryFloatArrayEntry secondaryFloatArrayEntry) {
      throw new RuntimeException("Old Chunk Storage is no longer supported.");
   }

   public ListenerAdapter17(File file4, String text, boolean flag) {
      this.file = new File(file4, text);
      this.file.mkdirs();
      this.file2 = new File(this.file, "playerdata");
      this.file3 = new File(this.file, "data");
      this.file3.mkdirs();
      this.labelText = text;
      if (flag) {
         this.file2.mkdirs();
      }

      this.updateState3();
   }

   @Override
   public void updateState2() throws ExceptionAdapter {
      try {
         File file4 = new File(this.file, "session.lock");
         DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file4));

         try {
            if (dataInputStream.readLong() != this.timestamp) {
               throw new ExceptionAdapter("The save is being accessed from another location, aborting");
            }
         } finally {
            dataInputStream.close();
         }
      } catch (IOException ioException) {
         throw new ExceptionAdapter("Failed to check session lock, aborting");
      }
   }

   @Override
   public RandomSeedManager getRandomSeedManager() {
      File file4 = new File(this.file, "level.dat");
      if (file4.exists()) {
         try {
            NBTTagCompound nBTTagCompound = LoadingNBTDataManager.resolveNBTTagCompound(new FileInputStream(file4)).getNBTTagCompound("Data");
            return new RandomSeedManager(nBTTagCompound);
         } catch (Exception exception) {
            exception.printStackTrace();
         }
      }

      File file5 = new File(this.file, "level.dat_old");
      if (file5.exists()) {
         try {
            NBTTagCompound nBTTagCompound2 = LoadingNBTDataManager.resolveNBTTagCompound(new FileInputStream(file5)).getNBTTagCompound("Data");
            return new RandomSeedManager(nBTTagCompound2);
         } catch (Exception exception2) {
            exception2.printStackTrace();
         }
      }

      return null;
   }

   @Override
   public PostMotionFilter getPostMotionFilter() {
      return this;
   }

   @Override
   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public String[] getTextArray() {
      String[] textArray = this.file2.list();
      if (textArray == null) {
         textArray = new String[0];
      }

      for (int index = 0; index < textArray.length; index++) {
         if (textArray[index].endsWith(".dat")) {
            textArray[index] = textArray[index].substring(0, textArray[index].length() - 4);
         }
      }

      return textArray;
   }

   private void updateState3() {
      try {
         File file4 = new File(this.file, "session.lock");
         DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file4));

         try {
            dataOutputStream.writeLong(this.timestamp);
         } finally {
            dataOutputStream.close();
         }
      } catch (IOException ioException) {
         ioException.printStackTrace();
         throw new RuntimeException("Failed to check session lock, aborting");
      }
   }
}
