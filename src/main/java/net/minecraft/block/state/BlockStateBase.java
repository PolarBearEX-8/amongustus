package net.minecraft.block.state;

import java.util.Map.Entry;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Iterables;
import java.util.Collection;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.ResourceLocation;

public abstract class BlockStateBase implements IBlockState {
   private static final Joiner COMMA_JOINER = Joiner.on(',');
   private static final Function MAP_ENTRY_TO_STRING = new BlockStateBase.TransformFunction();
   private int blockId = -1;
   private int blockStateId = -1;
   private int metadata = -1;
   private ResourceLocation blockLocation = null;

   public int getBlockStateId() {
      if (this.blockStateId < 0) {
         this.blockStateId = Block.getStateId(this);
      }

      return this.blockStateId;
   }

   public ResourceLocation getBlockLocation() {
      if (this.blockLocation == null) {
         this.blockLocation = (ResourceLocation)Block.blockRegistry.getNameForObject(this.getBlock());
      }

      return this.blockLocation;
   }

   public int getMetadata() {
      if (this.metadata < 0) {
         this.metadata = this.getBlock().getMetaFromState(this);
      }

      return this.metadata;
   }

   @Override
   public IBlockState cycleProperty(IProperty property) {
      return this.withProperty(property, (Comparable)cyclePropertyValue(property.getAllowedValues(), this.getValue(property)));
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(Block.blockRegistry.getNameForObject(this.getBlock()));
      if (!this.getProperties().isEmpty()) {
         sb.append("[");
         COMMA_JOINER.appendTo(sb, Iterables.transform(this.getProperties().entrySet(), MAP_ENTRY_TO_STRING));
         sb.append("]");
      }

      return sb.toString();
   }

   public ImmutableTable getPropertyValueTable() {
      return null;
   }

   public static Object cyclePropertyValue(Collection items, Object obj) {
      Iterator it = items.iterator();

      while (it.hasNext()) {
         if (it.next().equals(obj)) {
            if (it.hasNext()) {
               return it.next();
            }

            return items.iterator().next();
         }
      }

      return it.next();
   }

   public int getBlockId() {
      if (this.blockId < 0) {
         this.blockId = Block.getIdFromBlock(this.getBlock());
      }

      return this.blockId;
   }


   public static final class TransformFunction implements Function {
      public Object apply(Object obj) {
         return this.apply((Entry)obj);
      }

      public String apply(Entry entry) {
         if (entry == null) {
            return "<NULL>";
         } else {
            IProperty key = (IProperty)entry.getKey();
            return key.getName() + "=" + key.getName((Comparable)entry.getValue());
         }
      }
   }
}
