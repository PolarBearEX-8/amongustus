package net.minecraft.item;

import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.ClientManagerResolver;
import client.util.FlowerForestBiomeIDIsOutOfBoundsManager;
import java.util.Random;

public class ItemEntryDispatcher extends GoldenHoe {
   public final FlowerForestBiomeIDIsOutOfBoundsManager flowerForestBiomeIDIsOutOfBoundsManager;

   @Override
   public ClientManagerResolver nextInt(Random random) {
      return random.nextBoolean()
         ? FlowerForestBiomeIDIsOutOfBoundsManager.clientManagerCoordinator
         : FlowerForestBiomeIDIsOutOfBoundsManager.clientManagerCoordinator2;
   }

   public ItemEntryDispatcher(FlowerForestBiomeIDIsOutOfBoundsManager flowerForestBiomeIDIsOutOfBoundsManager2, int count, BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager) {
      super(count, biomeIDIsOutOfBoundsManager);
      this.flowerForestBiomeIDIsOutOfBoundsManager = flowerForestBiomeIDIsOutOfBoundsManager2;
   }
}
