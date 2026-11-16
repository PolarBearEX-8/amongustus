package net.minecraft.world.storage;

import client.event.GenericListener;
import client.event.PostMotionFilter;
import client.util.ExceptionAdapter;
import client.util.RandomSeedManager;
import client.util.SecondaryFloatArrayEntry;
import java.io.File;
import net.minecraft.nbt.NBTTagCompound;

public interface ISaveHandler {
   File resolveFile(String text);

   void applyRandomSeedManager(RandomSeedManager randomSeedManager);

   void updateState();

   File getFile();

   GenericListener resolveGenericListener(SecondaryFloatArrayEntry secondaryFloatArrayEntry);

   RandomSeedManager getRandomSeedManager();

   String getLabelText();

   PostMotionFilter getPostMotionFilter();

   void applyRandomSeedManager(RandomSeedManager randomSeedManager, NBTTagCompound tagCompound);

   void updateState2() throws ExceptionAdapter;
}
