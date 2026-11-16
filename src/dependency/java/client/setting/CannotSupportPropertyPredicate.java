package client.setting;

import client.util.IBlockState;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockManager;
import net.minecraft.block.properties.IProperty;

public class CannotSupportPropertyPredicate implements Predicate {
   public final BlockManager blockManager;
   public final Map map = Maps.newHashMap();

   public static CannotSupportPropertyPredicate resolveCannotSupportPropertyPredica(Block block) {
      return new CannotSupportPropertyPredicate(block.getBlockManager());
   }

   public boolean matchesIBlockState(IBlockState blockState) {
      if (blockState != null && blockState.getBlock().equals(this.blockManager.getBlock())) {
         for (Entry entry : (Iterable<Entry>) (Iterable) (this.map.entrySet())) {
            Comparable comparable = blockState.getComparable((IProperty)entry.getKey());
            if (!((Predicate)entry.getValue()).apply(comparable)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public CannotSupportPropertyPredicate resolveCannotSupportPropertyPredica(IProperty iProperty, Predicate predicate) {
      if (!this.blockManager.getCollection().contains(iProperty)) {
         throw new IllegalArgumentException(this.blockManager + " cannot support property " + iProperty);
      } else {
         this.map.put(iProperty, predicate);
         return this;
      }
   }

   private CannotSupportPropertyPredicate(BlockManager blockManager2) {
      this.blockManager = blockManager2;
   }

   public boolean apply(Object entry) {
      return this.matchesIBlockState((IBlockState)entry);
   }
}
