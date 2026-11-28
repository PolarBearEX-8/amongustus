package net.minecraft.block.state;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.Cartesian;
import net.minecraft.util.MapPopulator;

public class BlockState {
   private static final Joiner COMMA_JOINER = Joiner.on(", ");
   private static final Function GET_NAME_FUNC = new BlockState.TransformFunction();
   private final Block block;
   private final ImmutableList properties;
   private final ImmutableList validStates;

   private List getAllowedValues() {
      ArrayList list = Lists.newArrayList();

      for (int i = 0; i < this.properties.size(); i++) {
         list.add(((IProperty)this.properties.get(i)).getAllowedValues());
      }

      return list;
   }

   public Block getBlock() {
      return this.block;
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this)
         .add("block", Block.blockRegistry.getNameForObject(this.block))
         .add("properties", Iterables.transform(this.properties, GET_NAME_FUNC))
         .toString();
   }

   public ImmutableList getValidStates() {
      return this.validStates;
   }

   public IBlockState getBaseState() {
      return (IBlockState)this.validStates.get(0);
   }

   public BlockState(Block block2, IProperty[] propertys) {
      this.block = block2;
      Arrays.sort(propertys, new BlockState.EntryComparator(this));
      this.properties = ImmutableList.copyOf(propertys);
      LinkedHashMap map = Maps.newLinkedHashMap();
      ArrayList list = Lists.newArrayList();

      for (List list2 : (Iterable<List>)(Cartesian.cartesianProduct(this.getAllowedValues()))) {
         Map map2 = MapPopulator.createMap(this.properties, list2);
         BlockState.StateImplementation stateImplementation = new BlockState.StateImplementation(block2, ImmutableMap.copyOf(map2), null);
         map.put(map2, stateImplementation);
         list.add(stateImplementation);
      }

      for (BlockState.StateImplementation stateImplementation2 : (Iterable<BlockState.StateImplementation>)(list)) {
         stateImplementation2.buildPropertyValueTable(map);
      }

      this.validStates = ImmutableList.copyOf(list);
   }

   public Collection getProperties() {
      return this.properties;
   }


   public static final class TransformFunction implements Function {
      public String apply(IProperty property) {
         return property == null ? "<NULL>" : property.getName();
      }

      public Object apply(Object obj) {
         return this.apply((IProperty)obj);
      }
   }

   public static class EntryComparator implements Comparator {
      public final BlockState blockState;

      public int compare(IProperty a, IProperty b) {
         return a.getName().compareTo(b.getName());
      }

      public EntryComparator(BlockState blockState2) {
         this.blockState = blockState2;
      }

      @Override
      public int compare(Object a, Object b) {
         return this.compare((IProperty)a, (IProperty)b);
      }
   }

   public static class StateImplementation extends BlockStateBase {
      private final Block block;
      private final ImmutableMap properties;
      private ImmutableTable propertyValueTable;

      public StateImplementation(Block block2, ImmutableMap immutableMap, BlockState.TransformFunction obj) {
         this(block2, immutableMap);
      }

      @Override
      public IBlockState withProperty(IProperty property, Comparable comparable) {
         if (!this.properties.containsKey(property)) {
            throw new IllegalArgumentException("Cannot set property " + property + " as it does not exist in " + this.block.getBlockState());
         } else if (!property.getAllowedValues().contains(comparable)) {
            throw new IllegalArgumentException(
               "Cannot set property " + property + " to " + comparable + " on block " + Block.blockRegistry.getNameForObject(this.block) + ", it is not an allowed value"
            );
         } else {
            return (IBlockState)(this.properties.get(property) == comparable ? this : (IBlockState)this.propertyValueTable.get(property, comparable));
         }
      }

      @Override
      public Comparable getValue(IProperty property) {
         Object obj = this.properties.get(property);
         if (obj == null) {
            throw new IllegalArgumentException("Cannot get property " + property + " as it does not exist in " + this.block.getBlockState());
         } else {
            return (Comparable)property.getValueClass().cast(obj);
         }
      }

      @Override
      public boolean hasProperty(IProperty property) {
         return this.properties.containsKey(property);
      }

      public void buildPropertyValueTable(Map map) {
         if (this.propertyValueTable != null) {
            throw new IllegalStateException();
         } else {
            HashBasedTable hashBasedTable = HashBasedTable.create();
            UnmodifiableIterator it = this.properties.keySet().iterator();

            while (it.hasNext()) {
               IProperty property = (IProperty)it.next();

               for (Comparable comparable : (Iterable<Comparable>)(property.getAllowedValues())) {
                  if (comparable != this.properties.get(property)) {
                     hashBasedTable.put(property, comparable, map.get(this.getPropertiesWithValue(property, comparable)));
                  }
               }
            }

            this.propertyValueTable = ImmutableTable.copyOf(hashBasedTable);
         }
      }

      @Override
      public Collection getPropertyNames() {
         return Collections.unmodifiableCollection(this.properties.keySet());
      }

      @Override
      public int hashCode() {
         return this.properties.hashCode();
      }

      @Override
      public Block getBlock() {
         return this.block;
      }

      @Override
      public boolean equals(Object obj) {
         return this == obj;
      }

      private StateImplementation(Block block2, ImmutableMap immutableMap) {
         this.block = block2;
         this.properties = immutableMap;
      }

      private Map getPropertiesWithValue(IProperty property, Comparable comparable) {
         HashMap map = Maps.newHashMap(this.properties);
         map.put(property, comparable);
         return map;
      }

      @Override
      public ImmutableMap getProperties() {
         return this.properties;
      }
   }
}
