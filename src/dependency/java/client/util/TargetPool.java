package client.util;

import java.util.Random;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class TargetPool extends AuxiliaryLinkedListEntry {
   public TargetPool() {
   }

   public TargetPool(World world, Random random, int count, int count2) {
      super(count, count2);
      BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = world.resolveBiomeIDIsOutOfBoundsManager(new BlockPos(count * 16 + 8, 0, count2 * 16 + 8));
      if (biomeIDIsOutOfBoundsManager == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager23 || biomeIDIsOutOfBoundsManager == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager40) {
         ClientManagerProvider clientManagerProvider = new ClientManagerProvider(random, count * 16, count2 * 16);
         this.linkedList.add(clientManagerProvider);
      } else if (biomeIDIsOutOfBoundsManager == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager16) {
         DistanceEvaluator distanceEvaluator = new DistanceEvaluator(random, count * 16, count2 * 16);
         this.linkedList.add(distanceEvaluator);
      } else if (biomeIDIsOutOfBoundsManager == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17 || biomeIDIsOutOfBoundsManager == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager27) {
         BooleanArrayEntry booleanArrayEntry = new BooleanArrayEntry(random, count * 16, count2 * 16);
         this.linkedList.add(booleanArrayEntry);
      }

      this.updateState();
   }
}
