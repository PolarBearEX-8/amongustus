package client.util;

import java.util.concurrent.Callable;
import net.minecraft.nbt.NBTTagCompound;

public class SmoothAccumulator implements Callable {
   public final String labelText;
   public final NBTTagCompound nBTTagCompound;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      return LISTManager.stringArray[((LISTManager)NBTTagCompound.resolveMap(this.nBTTagCompound).get(this.labelText)).getByteCode()];
   }

   public SmoothAccumulator(NBTTagCompound tagCompound, String text) {
      this.nBTTagCompound = tagCompound;
      this.labelText = text;
   }
}
