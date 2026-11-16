package client.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import net.minecraft.item.DefaultItemEntry;
import net.minecraft.item.IronBoots;

public class BaseMapEntry extends ListenerAdapter10 {
   public Map map;
   public List list = new ArrayList();

   @Override
   public double getValue() {
      double offset = 0.0;
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         offset = ((ListenerAdapter10)iterator.next()).getValue();
      }

      return offset;
   }

   public BaseMapEntry(DefaultItemEntry defaultItemEntry) {
      super(defaultItemEntry);
      this.map = new HashMap();
   }

   @Override
   public String toString() {
      StringJoiner stringJoiner = new StringJoiner("; ");

      for (ListenerAdapter10 listenerAdapter : (Iterable<ListenerAdapter10>)(Iterable<?>)this.list) {
         stringJoiner.add(listenerAdapter.toString());
         if (listenerAdapter instanceof IronBoots && ((IronBoots)listenerAdapter).enabled) {
            break;
         }
      }

      return stringJoiner.toString();
   }
}
