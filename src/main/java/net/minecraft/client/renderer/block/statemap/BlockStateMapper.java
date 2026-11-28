package net.minecraft.client.renderer.block.statemap;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.Block;

public class BlockStateMapper {
   private Map blockStateMap = Maps.newIdentityHashMap();
   private Set setBuiltInBlocks = Sets.newIdentityHashSet();

   public Map putAllStateModelLocations() {
      IdentityHashMap identityHashMap = Maps.newIdentityHashMap();

      for (Block block : (Iterable<Block>)(Block.blockRegistry)) {
         if (!this.setBuiltInBlocks.contains(block)) {
            identityHashMap.putAll(((IStateMapper)MoreObjects.firstNonNull(this.blockStateMap.get(block), new DefaultStateMapper())).putStateModelLocations(block));
         }
      }

      return identityHashMap;
   }

   public void registerBuiltInBlocks(Block[] blocks) {
      Collections.addAll(this.setBuiltInBlocks, blocks);
   }

   public void registerBlockStateMapper(Block block, IStateMapper stateMapper) {
      this.blockStateMap.put(block, stateMapper);
   }
}
