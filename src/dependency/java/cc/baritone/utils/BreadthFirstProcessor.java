package cc.baritone.utils;

import client.util.IBlockState;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.Blocks;
import net.minecraft.block.properties.IProperty;

public class BreadthFirstProcessor extends AbstractInternalListener {
   public final InternalListener internalListener;
   public final Map map;
   public final Map map2 = new HashMap();

   private IBlockState resolveIBlockState(IBlockState blockState, IBlockState blockState2, IProperty iProperty) {
      return blockState2.resolveIBlockState(iProperty, blockState.getComparable(iProperty));
   }

   public BreadthFirstProcessor(InternalListener internalListener2, Map map3) {
      super(internalListener2.getCount(), internalListener2.getCount2(), internalListener2.getCount3());
      this.internalListener = internalListener2;
      this.map = map3;
   }

   private IBlockState resolveIBlockState(IBlockState blockState, Block block) {
      if (this.map2.containsKey(blockState) && ((Map)this.map2.get(blockState)).containsKey(block)) {
         return (IBlockState)((Map)this.map2.get(blockState)).get(block);
      } else {
         ImmutableSet keys = blockState.getImmutableMap().keySet();
         IBlockState defaultState = block.getDefaultState();
         UnmodifiableIterator iterator = keys.iterator();

         while (iterator.hasNext()) {
            IProperty iProperty = (IProperty)iterator.next();

            try {
               defaultState = this.resolveIBlockState(blockState, defaultState, iProperty);
            } catch (IllegalArgumentException illegalArgumentException) {
            }
         }

         ((Map)this.map2.computeIfAbsent(blockState, item -> BreadthFirstProcessor.resolveMap((IBlockState)item))).put(block, defaultState);
         return defaultState;
      }
   }

   private static Map resolveMap(IBlockState blockState) {
      return new HashMap<>();
   }

   @Override
   public boolean matchesIBlockState(int count, int count2, int count3, IBlockState blockState) {
      return this.internalListener.matchesIBlockState(count, count2, count3, blockState);
   }

   @Override
   public IBlockState resolveIBlockState(int count, int count2, int count3, IBlockState blockState, List list) {
      IBlockState blockState2 = this.internalListener.resolveIBlockState(count, count2, count3, blockState, list);
      Block block = blockState2.getBlock();
      if (!this.map.containsKey(block)) {
         return blockState2;
      } else {
         List list2 = (List)this.map.get(block);
         if (list2.contains(blockState.getBlock()) && !(blockState.getBlock() instanceof BlockAir)) {
            return this.resolveIBlockState(blockState2, blockState.getBlock());
         } else {
            for (Block block2 : (Iterable<Block>) (Iterable) list2) {
               if (block2 instanceof BlockAir) {
                  return blockState.getBlock() instanceof BlockAir ? blockState : Blocks.air.getDefaultState();
               }

               for (IBlockState blockState3 : (Iterable<IBlockState>) (Iterable) list) {
                  if (block2.equals(blockState3.getBlock())) {
                     return this.resolveIBlockState(blockState2, blockState3.getBlock());
                  }
               }
            }

            return ((Block)list2.get(0)).getDefaultState();
         }
      }
   }
}
