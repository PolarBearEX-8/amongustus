package client.util;

import client.event.CompositeListener;
import com.google.common.collect.Lists;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import net.minecraft.item.DiamondFragment;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.ISaveHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LevelDatISaveFormatAdapter extends LevelDatISaveFormat {
   public static final Logger logger2 = LogManager.getLogger();

   private void applyText(String text) {
      File file = new File(this.file, text);
      if (!file.exists()) {
         logger2.warn("Unable to create level.dat_mcr backup");
      } else {
         File file2 = new File(file, "level.dat");
         if (!file2.exists()) {
            logger2.warn("Unable to create level.dat_mcr backup");
         } else {
            File file3 = new File(file, "level.dat_mcr");
            if (!file2.renameTo(file3)) {
               logger2.warn("Unable to create level.dat_mcr backup");
            }
         }
      }
   }

   private void applyFile(File file, Collection collection) {
      File[] fileArray = new File(file, "region").listFiles(new FilenameFilterAdapter(this));
      if (fileArray != null) {
         Collections.addAll(collection, fileArray);
      }
   }

   @Override
   public ISaveHandler resolveISaveHandler(String text, boolean flag) {
      return new BezierObserver(this.file, text, flag);
   }

   private void applyFile(File file, File file2, InvalidBiomeIdManager invalidBiomeIdManager, int count, int count2, CompositeListener compositeListener) {
      try {
         String name = file2.getName();
         ExtraByteArrayEntry extraByteArrayEntry = new ExtraByteArrayEntry(file2);
         ExtraByteArrayEntry extraByteArrayEntry2 = new ExtraByteArrayEntry(new File(file, name.substring(0, name.length() - ".mcr".length()) + ".mca"));

         for (int index = 0; index < 32; index++) {
            for (int index2 = 0; index2 < 32; index2++) {
               if (extraByteArrayEntry.checkIndexByIndex(index, index2) && !extraByteArrayEntry2.checkIndexByIndex(index, index2)) {
                  DataInputStream dataInputStream = extraByteArrayEntry.resolveDataInputStream(index, index2);
                  if (dataInputStream == null) {
                     logger2.warn("Failed to fetch input stream");
                  } else {
                     NBTTagCompound tagCompound = LoadingNBTDataManager.resolveNBTTagCompound(dataInputStream);
                     dataInputStream.close();
                     FovFilter fovFilter = FovMatcher.resolveFovFilter(tagCompound.getNBTTagCompound("Level"));
                     NBTTagCompound tagCompound2 = new NBTTagCompound();
                     NBTTagCompound tagCompound3 = new NBTTagCompound();
                     tagCompound2.applyText("Level", tagCompound3);
                     FovMatcher.applyFovFilter(fovFilter, tagCompound3, invalidBiomeIdManager);
                     DataOutputStream dataOutputStream = extraByteArrayEntry2.resolveDataOutputStream(index, index2);
                     LoadingNBTDataManager.applyNBTTagCompound(tagCompound2, (DataOutput)dataOutputStream);
                     dataOutputStream.close();
                  }
               }
            }

            int count3 = (int)Math.round(100.0 * (double)(count * 1024) / (double)(count2 * 1024));
            int count4 = (int)Math.round(100.0 * (double)((index + 1) * 32 + count * 1024) / (double)(count2 * 1024));
            if (count4 > count3) {
               compositeListener.setCount2(count4);
            }
         }

         extraByteArrayEntry.close();
         extraByteArrayEntry2.close();
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }

   @Override
   public List getList() throws TertiaryExceptionAdapter {
      if (this.file != null && this.file.exists() && this.file.isDirectory()) {
         ArrayList list = Lists.newArrayList();

         for (File file : this.file.listFiles()) {
            if (file.isDirectory()) {
               String name = file.getName();
               RandomSeedManager randomSeedManager = this.resolveRandomSeedManager(name);
               if (randomSeedManager != null && (randomSeedManager.getCount7() == 19132 || randomSeedManager.getCount7() == 19133)) {
                  boolean flag = randomSeedManager.getCount7() != this.getCount();
                  String labelText = randomSeedManager.getLabelText();
                  if (StringUtils.isEmpty(labelText)) {
                     labelText = name;
                  }

                  list.add(
                     new ExtendedCustom(name, labelText, randomSeedManager.getTimestamp4(), 0L, randomSeedManager.getWorldSettingsGameType(), flag, randomSeedManager.isEnabled2(), randomSeedManager.isEnabled3())
                  );
               }
            }
         }

         return list;
      } else {
         throw new TertiaryExceptionAdapter("Unable to read or access folder where game worlds are saved!");
      }
   }

   @Override
   public String getText() {
      return "Anvil";
   }

   public int getCount() {
      return 19133;
   }

   @Override
   public boolean matchesText(String text, CompositeListener compositeListener) {
      compositeListener.setCount2(0);
      ArrayList list = Lists.newArrayList();
      ArrayList list2 = Lists.newArrayList();
      ArrayList list3 = Lists.newArrayList();
      File file = new File(this.file, text);
      File file2 = new File(file, "DIM-1");
      File file3 = new File(file, "DIM1");
      logger2.info("Scanning folders...");
      this.applyFile(file, list);
      if (file2.exists()) {
         this.applyFile(file2, list2);
      }

      if (file3.exists()) {
         this.applyFile(file3, list3);
      }

      int count = list.size() + list2.size() + list3.size();
      logger2.info("Total conversion count is " + count);
      RandomSeedManager randomSeedManager = this.resolveRandomSeedManager(text);
      Object entry;
      if (randomSeedManager.getInfoManager() == InfoManager.infoManager2) {
         entry = new DiamondFragment(BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35, 0.5F);
      } else {
         entry = new InvalidBiomeIdManager(randomSeedManager.getTimestamp5(), randomSeedManager.getInfoManager(), randomSeedManager.getLabelText2());
      }

      this.applyFile(new File(file, "region"), list, (InvalidBiomeIdManager)entry, 0, count, compositeListener);
      this.applyFile(
         new File(file2, "region"), list2, new DiamondFragment(BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager38, 0.0F), list.size(), count, compositeListener
      );
      this.applyFile(
         new File(file3, "region"),
         list3,
         new DiamondFragment(BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager3, 0.0F),
         list.size() + list2.size(),
         count,
         compositeListener
      );
      randomSeedManager.setCount7(19133);
      if (randomSeedManager.getInfoManager() == InfoManager.infoManager6) {
         randomSeedManager.setInfoManager(InfoManager.infoManager7);
      }

      this.applyText(text);
      this.resolveISaveHandler(text, false).applyRandomSeedManager(randomSeedManager);
      return true;
   }

   private void applyFile(File file, Iterable collection, InvalidBiomeIdManager invalidBiomeIdManager, int count, int count2, CompositeListener compositeListener) {
      int count3 = count;

      for (File file2 : (Iterable<File>)(collection)) {
         this.applyFile(file, file2, invalidBiomeIdManager, count3, count2, compositeListener);
         count3++;
         int count4 = (int)Math.round(100.0 * (double)count3 / (double)count2);
         compositeListener.setCount2(count4);
      }
   }

   @Override
   public boolean matchesText3(String text) {
      RandomSeedManager randomSeedManager = this.resolveRandomSeedManager(text);
      return randomSeedManager != null && randomSeedManager.getCount7() == 19132;
   }

   @Override
   public void updateState() {
      SpecialMapEntry.updateState();
   }

   @Override
   public boolean matchesText(String text) {
      RandomSeedManager randomSeedManager = this.resolveRandomSeedManager(text);
      return randomSeedManager != null && randomSeedManager.getCount7() != this.getCount();
   }

   public LevelDatISaveFormatAdapter(File file) {
      super(file);
   }
}
