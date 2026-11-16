package client.util;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Iterables;
import java.util.Collection;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.ResourceLocation;

public abstract class IBlockStateAdapter implements IBlockState {
   public static final Joiner joiner = Joiner.on(',');
   public static final Function function = new NULLFunction();
   public int count2 = -1;
   public int count3 = -1;
   public int count = -1;
   public ResourceLocation resourceLocation = null;

   public static Object resolveTarget(Collection collection, Object entry) {
      Iterator iterator = collection.iterator();

      while (iterator.hasNext()) {
         if (iterator.next().equals(entry)) {
            if (iterator.hasNext()) {
               return iterator.next();
            }

            return collection.iterator().next();
         }
      }

      return iterator.next();
   }

   public int getCount() {
      if (this.count3 < 0) {
         this.count3 = Block.processAction5(this);
      }

      return this.count3;
   }

   public ImmutableTable getImmutableTable() {
      return null;
   }

   public ResourceLocation getResourceLocation() {
      if (this.resourceLocation == null) {
         this.resourceLocation = (ResourceLocation)Block.iterableExtensionBridge.getObject2(this.getBlock());
      }

      return this.resourceLocation;
   }

   public int getCount2() {
      if (this.count2 < 0) {
         this.count2 = Block.getIdFromBlock(this.getBlock());
      }

      return this.count2;
   }

   @Override
   public IBlockState resolveIBlockState(IProperty iProperty) {
      return this.resolveIBlockState(iProperty, (Comparable)resolveTarget(iProperty.getCollection(), this.getComparable(iProperty)));
   }

   public int getCountAsIndex() {
      if (this.count < 0) {
         this.count = this.getBlock().getIndex(this);
      }

      return this.count;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(Block.iterableExtensionBridge.getObject2(this.getBlock()));
      if (!this.getImmutableMap().isEmpty()) {
         builder.append("[");
         joiner.appendTo(builder, Iterables.transform(this.getImmutableMap().entrySet(), function));
         builder.append("]");
      }

      return builder.toString();
   }
}
