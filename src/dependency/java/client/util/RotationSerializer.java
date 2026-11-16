package client.util;

import java.util.concurrent.Callable;
import net.minecraft.nbt.NBTTagCompound;

public class RotationSerializer implements Callable {
   public final int count;
   public final NBTTagCompound nBTTagCompound;

   public RotationSerializer(NBTTagCompound tagCompound, int count2) {
      this.nBTTagCompound = tagCompound;
      this.count = count2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      return LISTManager.stringArray[this.count];
   }
}
