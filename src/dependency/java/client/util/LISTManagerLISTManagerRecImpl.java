package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;

public class LISTManagerLISTManagerRecImpl extends AbstractLISTManagerRecord {
   public List list = Lists.newArrayList();

   public LISTManagerLISTManagerRecImpl(String text) {
      this.labelText = text;
   }

   @Override
   public LISTManager getLISTManager() throws SecondaryExceptionAdapter {
      NBTTagCompound tagCompound = new NBTTagCompound();

      for (AbstractLISTManagerRecord abstractLISTManagerRecord : (Iterable<AbstractLISTManagerRecord>)(Iterable<?>)this.list) {
         tagCompound.applyText(abstractLISTManagerRecord.labelText, abstractLISTManagerRecord.getLISTManager());
      }

      return tagCompound;
   }
}
