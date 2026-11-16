package net.minecraft.block;

import client.setting.CombatProfileProperty;
import client.util.ImmutableSetEntry2Entry;

public class BlockRedFlower extends ImmutableSetEntry2Entry {
   @Override
   public CombatProfileProperty getYELLOW() {
      return CombatProfileProperty.RED;
   }
}
