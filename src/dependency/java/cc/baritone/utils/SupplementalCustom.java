package cc.baritone.utils;

import net.minecraft.client.multiplayer.WorldClient;

public final class SupplementalCustom {
   public final WorldClient worldClient;
   public final SecondaryOptionType secondaryOptionType;

   public final WorldClient getWorldClient() {
      return this.worldClient;
   }

   public SupplementalCustom(WorldClient world, SecondaryOptionType secondaryOptionType2) {
      this.worldClient = world;
      this.secondaryOptionType = secondaryOptionType2;
   }

   public final SecondaryOptionType getSecondaryOptionType() {
      return this.secondaryOptionType;
   }
}
