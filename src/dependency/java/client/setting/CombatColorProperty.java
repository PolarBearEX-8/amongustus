package client.setting;

import net.minecraft.util.EnumFacing;

public enum CombatColorProperty {
   DOWN(EnumFacing.DOWN, false),
   UP(EnumFacing.UP, false),
   NORTH(EnumFacing.NORTH, false),
   SOUTH(EnumFacing.SOUTH, false),
   WEST(EnumFacing.WEST, false),
   EAST(EnumFacing.EAST, false),
   FLIP_DOWN(EnumFacing.DOWN, true),
   FLIP_UP(EnumFacing.UP, true),
   FLIP_NORTH(EnumFacing.NORTH, true),
   FLIP_SOUTH(EnumFacing.SOUTH, true),
   FLIP_WEST(EnumFacing.WEST, true),
   FLIP_EAST(EnumFacing.EAST, true);

   public final int count;
   public static final CombatColorProperty[] ENUM_VALUES = values();

   private CombatColorProperty(EnumFacing enumFacing, boolean flag) {
      this.count = enumFacing.getIndexOffset1() + (flag ? EnumFacing.getEntries().length : 0);
   }
}
