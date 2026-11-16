package client.setting;

import client.util.AuxiliaryNULLFunction;
import client.util.IBlockState;
import client.util.IBlockStateAdapter;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;

public class CannotSetPropertyManager extends IBlockStateAdapter {
   public final Block block;
   public final ImmutableMap immutableMap;
   public ImmutableTable immutableTable;

   @Override
   public boolean containsKey(IProperty iProperty) {
      return this.immutableMap.containsKey(iProperty);
   }

   @Override
   public Collection getCollection() {
      return Collections.unmodifiableCollection(this.immutableMap.keySet());
   }

   @Override
   public IBlockState resolveIBlockState(IProperty iProperty, Comparable comparable) {
      if (!this.immutableMap.containsKey(iProperty)) {
         throw new IllegalArgumentException("Cannot set property " + iProperty + " as it does not exist in " + this.block.getBlockManager());
      } else if (!iProperty.getCollection().contains(comparable)) {
         throw new IllegalArgumentException(
            "Cannot set property "
               + iProperty
               + " to "
               + comparable
               + " on block "
               + Block.iterableExtensionBridge.getObject2(this.block)
               + ", it is not an allowed value"
         );
      } else {
         return (IBlockState)(this.immutableMap.get(iProperty) == comparable ? this : (IBlockState)this.immutableTable.get(iProperty, comparable));
      }
   }

   public void applyMap(Map map) {
      if (this.immutableTable != null) {
         throw new IllegalStateException();
      } else {
         HashBasedTable hashBasedTable = HashBasedTable.create();
         UnmodifiableIterator iterator = this.immutableMap.keySet().iterator();

         while (iterator.hasNext()) {
            IProperty iProperty = (IProperty)iterator.next();

            for (Comparable comparable : (Iterable<Comparable>) (Iterable) iProperty.getCollection()) {
               if (comparable != this.immutableMap.get(iProperty)) {
                  hashBasedTable.put(iProperty, comparable, map.get(this.resolveMap(iProperty, comparable)));
               }
            }
         }

         this.immutableTable = ImmutableTable.copyOf(hashBasedTable);
      }
   }

   @Override
   public Block getBlock() {
      return this.block;
   }

   @Override
   public Comparable getComparable(IProperty iProperty) {
      Object entry = this.immutableMap.get(iProperty);
      if (entry == null) {
         throw new IllegalArgumentException("Cannot get property " + iProperty + " as it does not exist in " + this.block.getBlockManager());
      } else {
         return (Comparable)iProperty.getMemberRef().cast(entry);
      }
   }

   @Override
   public ImmutableMap getImmutableMap() {
      return this.immutableMap;
   }

   private Map resolveMap(IProperty iProperty, Comparable comparable) {
      HashMap map = Maps.newHashMap(this.immutableMap);
      map.put(iProperty, comparable);
      return map;
   }

   private CannotSetPropertyManager(Block block2, ImmutableMap immutableMap2) {
      this.block = block2;
      this.immutableMap = immutableMap2;
   }

   public CannotSetPropertyManager(Block block2, ImmutableMap immutableMap2, AuxiliaryNULLFunction auxiliaryNULLFunction) {
      this(block2, immutableMap2);
   }

   @Override
   public boolean equals(Object other) {
      return this == other;
   }

   @Override
   public int hashCode() {
      return this.immutableMap.hashCode();
   }
}
