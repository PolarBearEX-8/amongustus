package client.util;

import client.event.GenericListener;
import java.io.File;
import net.minecraft.nbt.NBTTagCompound;

public class BezierObserver extends ListenerAdapter17 {
   public BezierObserver(File file, String text, boolean flag) {
      super(file, text, flag);
   }

   @Override
   public GenericListener resolveGenericListener(SecondaryFloatArrayEntry secondaryFloatArrayEntry) {
      File file = this.getFile();
      if (secondaryFloatArrayEntry instanceof NetherFloatArrayEntry) {
         File file2 = new File(file, "DIM-1");
         file2.mkdirs();
         return new RotationDeserializer(file2);
      } else if (secondaryFloatArrayEntry instanceof EndManager) {
         File file3 = new File(file, "DIM1");
         file3.mkdirs();
         return new RotationDeserializer(file3);
      } else {
         return new RotationDeserializer(file);
      }
   }

   @Override
   public void applyRandomSeedManager(RandomSeedManager randomSeedManager, NBTTagCompound tagCompound) {
      randomSeedManager.setCount7(19133);
      super.applyRandomSeedManager(randomSeedManager, tagCompound);
   }

   @Override
   public void updateState() {
      try {
         RunnableHandler.getRunnableHandler().sleep();
      } catch (InterruptedException interruptedException) {
         interruptedException.printStackTrace();
      }

      SpecialMapEntry.updateState();
   }
}
