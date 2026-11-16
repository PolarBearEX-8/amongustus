package client.setting;

import net.minecraft.util.EnumFacing;

public enum RenderRangeProperty {
   DOWN(0, 1, 2, 3),
   UP(2, 3, 0, 1),
   NORTH(3, 0, 1, 2),
   SOUTH(0, 1, 2, 3),
   WEST(3, 0, 1, 2),
   EAST(1, 2, 3, 0);

   public final int count;
   public final int count2;
   public final int count4;
   public final int count3;
   public static final RenderRangeProperty[] ENUM_VALUES = new RenderRangeProperty[6];
   public static final RenderRangeProperty[] ENUM_VALUES2 = values();

   public static int computeCountByRenderRangePrope(RenderRangeProperty renderRangeProperty) {
      return renderRangeProperty.count;
   }

   public static int computeCount3(RenderRangeProperty renderRangeProperty) {
      return renderRangeProperty.count4;
   }

   public static int computeCount2(RenderRangeProperty renderRangeProperty) {
      return renderRangeProperty.count3;
   }

   public static int computeCount(RenderRangeProperty renderRangeProperty) {
      return renderRangeProperty.count2;
   }

   public static RenderRangeProperty resolveRenderRangeProperty(EnumFacing enumFacing) {
      return ENUM_VALUES[enumFacing.getIndexOffset1()];
   }

   private RenderRangeProperty(int count5, int count6, int count7, int count8) {
      this.count = count5;
      this.count2 = count6;
      this.count4 = count7;
      this.count3 = count8;
   }

   static {
      ENUM_VALUES[EnumFacing.DOWN.getIndexOffset1()] = DOWN;
      ENUM_VALUES[EnumFacing.UP.getIndexOffset1()] = UP;
      ENUM_VALUES[EnumFacing.NORTH.getIndexOffset1()] = NORTH;
      ENUM_VALUES[EnumFacing.SOUTH.getIndexOffset1()] = SOUTH;
      ENUM_VALUES[EnumFacing.WEST.getIndexOffset1()] = WEST;
      ENUM_VALUES[EnumFacing.EAST.getIndexOffset1()] = EAST;
   }
}
