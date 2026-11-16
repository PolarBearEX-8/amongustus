package net.minecraft.block;

import client.setting.CannotSetPropertyManager;
import client.util.AuxiliaryNULLFunction;
import client.util.GeometryDeserializer;
import client.util.IBlockState;
import client.util.SmoothFilter;
import client.util.TertiaryComparatorAdapter;
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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.properties.IProperty;

public class BlockManager {
   public static final Joiner joiner = Joiner.on(", ");
   public static final Function function = new AuxiliaryNULLFunction();
   public final Block block;
   public final ImmutableList immutableList2;
   public final ImmutableList immutableList;

   public Collection getCollection() {
      return this.immutableList2;
   }

   private List getList() {
      ArrayList list = Lists.newArrayList();

      for (int index = 0; index < this.immutableList2.size(); index++) {
         list.add(((IProperty)this.immutableList2.get(index)).getCollection());
      }

      return list;
   }

   public ImmutableList getImmutableList() {
      return this.immutableList;
   }

   public IBlockState get() {
      return (IBlockState)this.immutableList.get(0);
   }

   public Block getBlock() {
      return this.block;
   }

   public BlockManager(Block block2, IProperty[] iPropertyArray) {
      this.block = block2;
      Arrays.sort(iPropertyArray, new TertiaryComparatorAdapter(this));
      this.immutableList2 = ImmutableList.copyOf(iPropertyArray);
      LinkedHashMap map = Maps.newLinkedHashMap();
      ArrayList list = Lists.newArrayList();

      for (List list2 : (Iterable<List>)(Iterable)SmoothFilter.resolveIterable(this.getList())) {
         Map map2 = GeometryDeserializer.resolveMap(this.immutableList2, list2);
         CannotSetPropertyManager cannotSetPropertyManager = new CannotSetPropertyManager(block2, ImmutableMap.copyOf(map2), null);
         map.put(map2, cannotSetPropertyManager);
         list.add(cannotSetPropertyManager);
      }

      Iterator iterator = list.iterator();

      while (iterator.hasNext()) {
         ((CannotSetPropertyManager)iterator.next()).applyMap(map);
      }

      this.immutableList = ImmutableList.copyOf(list);
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this)
         .add("block", Block.iterableExtensionBridge.getObject2(this.block))
         .add("properties", Iterables.transform(this.immutableList2, function))
         .toString();
   }
}
