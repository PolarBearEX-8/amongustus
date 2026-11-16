package client.util;

import client.event.GenericListener;
import client.event.PostMotionFilter;
import java.io.File;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.ISaveHandler;

public class InternalNoneManager implements ISaveHandler {
   @Override
   public File resolveFile(String text) {
      return null;
   }

   @Override
   public File getFile() {
      return null;
   }

   @Override
   public void updateState() {
   }

   @Override
   public void applyRandomSeedManager(RandomSeedManager randomSeedManager) {
   }

   @Override
   public GenericListener resolveGenericListener(SecondaryFloatArrayEntry secondaryFloatArrayEntry) {
      return null;
   }

   @Override
   public RandomSeedManager getRandomSeedManager() {
      return null;
   }

   @Override
   public String getLabelText() {
      return "none";
   }

   @Override
   public PostMotionFilter getPostMotionFilter() {
      return null;
   }

   @Override
   public void applyRandomSeedManager(RandomSeedManager randomSeedManager, NBTTagCompound tagCompound) {
   }

   @Override
   public void updateState2() throws ExceptionAdapter {
   }
}
