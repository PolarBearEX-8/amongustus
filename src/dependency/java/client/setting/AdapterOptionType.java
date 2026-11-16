package client.setting;

import client.event.PrimaryListener;
import client.util.SmoothConverter;
import net.minecraft.util.EnumFacing;

public enum AdapterOptionType implements PrimaryListener {
   DOWN_X(0, "down_x", EnumFacing.DOWN),
   EAST(1, "east", EnumFacing.EAST),
   WEST(2, "west", EnumFacing.WEST),
   SOUTH(3, "south", EnumFacing.SOUTH),
   NORTH(4, "north", EnumFacing.NORTH),
   UP_Z(5, "up_z", EnumFacing.UP),
   UP_X(6, "up_x", EnumFacing.UP),
   DOWN_Z(7, "down_z", EnumFacing.DOWN);

   public static final AdapterOptionType[] ENUM_VALUES2 = new AdapterOptionType[values().length];
   public final int count;
   public final String labelText;
   public final EnumFacing enumFacing;
   public static final AdapterOptionType[] ENUM_VALUES = values();

   public static AdapterOptionType resolveAdapterOptionType(int count2) {
      int count3 = count2;
      if (count2 < 0 || count2 >= ENUM_VALUES2.length) {
         count3 = 0;
      }

      return ENUM_VALUES2[count3];
   }

   public static AdapterOptionType resolveAdapterOptionType(EnumFacing enumFacing2, EnumFacing enumFacing3) {
      switch (SmoothConverter.countArray2[enumFacing2.ordinal()]) {
         case 1:
            switch (SmoothConverter.countArray3[enumFacing3.getEnumFacingXType().ordinal()]) {
               case 1:
                  return DOWN_X;
               case 2:
                  return DOWN_Z;
               default:
                  throw new IllegalArgumentException("Invalid entityFacing " + enumFacing3 + " for facing " + enumFacing2);
            }
         case 2:
            switch (SmoothConverter.countArray3[enumFacing3.getEnumFacingXType().ordinal()]) {
               case 1:
                  return UP_X;
               case 2:
                  return UP_Z;
               default:
                  throw new IllegalArgumentException("Invalid entityFacing " + enumFacing3 + " for facing " + enumFacing2);
            }
         case 3:
            return NORTH;
         case 4:
            return SOUTH;
         case 5:
            return WEST;
         case 6:
            return EAST;
         default:
            throw new IllegalArgumentException("Invalid facing: " + enumFacing2);
      }
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public EnumFacing getEnumFacing() {
      return this.enumFacing;
   }

   public int getCount() {
      return this.count;
   }

   private AdapterOptionType(int count2, String text, EnumFacing enumFacing2) {
      this.count = count2;
      this.labelText = text;
      this.enumFacing = enumFacing2;
   }

   static {
      for (AdapterOptionType adapterOptionType : values()) {
         ENUM_VALUES2[adapterOptionType.getCount()] = adapterOptionType;
      }
   }

   @Override
   public String toString() {
      return this.labelText;
   }
}
