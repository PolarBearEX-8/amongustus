package client.util;

import java.util.Random;
import net.minecraft.item.ItemEntryProvider;
import net.minecraft.world.World;

public class TargetBuffer extends AuxiliaryLinkedListEntry {
   public TargetBuffer(World world, Random random, int count, int count2) {
      super(count, count2);
      ItemEntryProvider itemEntryProvider = new ItemEntryProvider(0, random, (count << 4) + 2, (count2 << 4) + 2);
      this.linkedList.add(itemEntryProvider);
      itemEntryProvider.applyGoldenIngot(itemEntryProvider, this.linkedList, random);
      this.updateState();
      this.applyWorld(world, random, 10);
   }

   public TargetBuffer() {
   }
}
