package client.util;

import com.google.common.base.Function;
import java.util.Map.Entry;
import net.minecraft.block.properties.IProperty;

public final class NULLFunction implements Function {
   public String resolveText(Entry entry) {
      if (entry == null) {
         return "<NULL>";
      } else {
         IProperty key = (IProperty)entry.getKey();
         return key.getLabelText() + "=" + key.resolveText((Comparable)entry.getValue());
      }
   }

   public Object apply(Object entry) {
      return this.resolveText((Entry)entry);
   }
}
