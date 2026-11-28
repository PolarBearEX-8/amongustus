package net.minecraft.block.state.pattern;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;

public class BlockStateHelper implements Predicate {
   private final BlockState blockstate;
   private final Map propertyPredicates = Maps.newHashMap();

   public BlockStateHelper where(IProperty property, Predicate predicate) {
      if (!this.blockstate.getProperties().contains(property)) {
         throw new IllegalArgumentException(this.blockstate + " cannot support property " + property);
      } else {
         this.propertyPredicates.put(property, predicate);
         return this;
      }
   }

   public boolean apply(Object obj) {
      return this.apply((IBlockState)obj);
   }

   private BlockStateHelper(BlockState blockState) {
      this.blockstate = blockState;
   }

   public static BlockStateHelper forBlock(Block block) {
      return new BlockStateHelper(block.getBlockState());
   }

   public boolean apply(IBlockState state) {
      if (state != null && state.getBlock().equals(this.blockstate.getBlock())) {
         for (Entry entry : (Iterable<Entry>)(this.propertyPredicates.entrySet())) {
            Comparable value = state.getValue((IProperty)entry.getKey());
            if (!((Predicate)entry.getValue()).apply(value)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }
}
