package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.nbt.NBTTagList;

public class LISTManagerLISTManagerRec extends AbstractLISTManagerRecord {
   public List list = Lists.newArrayList();

   public LISTManagerLISTManagerRec(String text) {
      this.labelText = text;
   }

   @Override
   public LISTManager getLISTManager() throws SecondaryExceptionAdapter {
      NBTTagList nBTTagList = new NBTTagList();

      for (AbstractLISTManagerRecord abstractLISTManagerRecord : (Iterable<AbstractLISTManagerRecord>)(Iterable<?>)this.list) {
         nBTTagList.applyLISTManager(abstractLISTManagerRecord.getLISTManager());
      }

      return nBTTagList;
   }
}
