package baritone.api.schematic;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class SubstituteSchematic extends AbstractSchematic {
   private final ISchematic schematic;
   private final Map substitutions;
   private final Map blockStateCache = new HashMap();

   public SubstituteSchematic(ISchematic schematic2, Map map) {
      super(schematic2.widthX(), schematic2.heightY(), schematic2.lengthZ());
      this.schematic = schematic2;
      this.substitutions = map;
   }

   private IBlockState withBlock(IBlockState state, Block block) {
      if (this.blockStateCache.containsKey(state) && ((Map)this.blockStateCache.get(state)).containsKey(block)) {
         return (IBlockState)((Map)this.blockStateCache.get(state)).get(block);
      } else {
         ImmutableSet keys = state.getProperties().keySet();
         IBlockState defaultState = block.getDefaultState();
         UnmodifiableIterator it = keys.iterator();

         while (it.hasNext()) {
            IProperty property = (IProperty)it.next();

            try {
               defaultState = this.copySingleProp(state, defaultState, property);
            } catch (IllegalArgumentException e) {
            }
         }

         ((Map)this.blockStateCache.computeIfAbsent(state, obj -> new HashMap<>())).put(block, defaultState);
         return defaultState;
      }
   }

   @Override
   public IBlockState desiredState(int n, int n2, int n3, IBlockState state, List list) {
      IBlockState state2 = this.schematic.desiredState(n, n2, n3, state, list);
      Block block = state2.getBlock();
      if (!this.substitutions.containsKey(block)) {
         return state2;
      } else {
         List list2 = (List)this.substitutions.get(block);
         if (list2.contains(state.getBlock()) && !(state.getBlock() instanceof BlockAir)) {
            return this.withBlock(state2, state.getBlock());
         } else {
            for (Block block2 : (Iterable<Block>)(list2)) {
               if (block2 instanceof BlockAir) {
                  return state.getBlock() instanceof BlockAir ? state : Blocks.air.getDefaultState();
               }

               for (IBlockState state3 : (Iterable<IBlockState>)(list)) {
                  if (block2.equals(state3.getBlock())) {
                     return this.withBlock(state2, state3.getBlock());
                  }
               }
            }

            return ((Block)list2.get(0)).getDefaultState();
         }
      }
   }

   private IBlockState copySingleProp(IBlockState state, IBlockState state2, IProperty property) {
      return state2.withProperty(property, state.getValue(property));
   }

   @Override
   public boolean inSchematic(int n, int n2, int n3, IBlockState state) {
      return this.schematic.inSchematic(n, n2, n3, state);
   }
}
