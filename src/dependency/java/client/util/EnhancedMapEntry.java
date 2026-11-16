package client.util;

import client.event.DefaultListener;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.Block;

public class EnhancedMapEntry {
   public Map map = Maps.newIdentityHashMap();
   public Set set = Sets.newIdentityHashSet();

   public void put(Block block, DefaultListener defaultListener) {
      this.map.put(block, defaultListener);
   }

   public Map getMap() {
      IdentityHashMap identityHashMap = Maps.newIdentityHashMap();

      for (Block block : (Iterable<Block>)(Iterable)Block.iterableExtensionBridge) {
         if (!this.set.contains(block)) {
            identityHashMap.putAll(((DefaultListener)MoreObjects.firstNonNull(this.map.get(block), new DampenerSynchronizer())).resolveMap(block));
         }
      }

      return identityHashMap;
   }

   public void addAll(Block[] blockArray) {
      Collections.addAll(this.set, blockArray);
   }
}
