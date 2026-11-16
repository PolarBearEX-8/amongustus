package client.util;

import client.event.DefaultListener;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;

public abstract class DampenerEngine implements DefaultListener {
   public Map map = Maps.newLinkedHashMap();

   public abstract ResourceLocationAdapter resolveResourceLocationAdapter(IBlockState blockState);

   @Override
   public Map resolveMap(Block block) {
      UnmodifiableIterator iterator = block.getBlockManager().getImmutableList().iterator();

      while (iterator.hasNext()) {
         IBlockState blockState = (IBlockState)iterator.next();
         this.map.put(blockState, this.resolveResourceLocationAdapter(blockState));
      }

      return this.map;
   }

   public String resolveText(Map map2) {
      StringBuilder builder = new StringBuilder();

      for (Entry entry : (Iterable<Entry>)(Iterable)map2.entrySet()) {
         if (builder.length() != 0) {
            builder.append(",");
         }

         IProperty key = (IProperty)entry.getKey();
         Comparable value = (Comparable)entry.getValue();
         builder.append(key.getLabelText());
         builder.append("=");
         builder.append(key.resolveText(value));
      }

      if (builder.length() == 0) {
         builder.append("normal");
      }

      return builder.toString();
   }
}
