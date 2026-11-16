package client.setting;

import client.util.ImmutableSetEntry2Entry;
import net.minecraft.block.Blocks;

public enum CombatProfileProperty {
    YELLOW,
    RED;

   public static final CombatProfileProperty[] ENUM_VALUES = new CombatProfileProperty[]{YELLOW, RED};

   public ImmutableSetEntry2Entry getImmutableSetEntry2Entry() {
      return this == YELLOW ? Blocks.immutableSetEntry2Entry : Blocks.immutableSetEntry2Entry2;
   }


}
