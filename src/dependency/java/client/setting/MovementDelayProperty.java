package client.setting;

import net.minecraft.util.EnumFacing;

public enum MovementDelayProperty {
   UP(EnumFacing.UP, 0, -1),
   DOWN(EnumFacing.DOWN, 0, 1),
   LEFT(EnumFacing.EAST, -1, 0),
   RIGHT(EnumFacing.WEST, 1, 0);

   public final EnumFacing enumFacing;
   public final int count;
   public final int count2;
   public static final MovementDelayProperty[] ENUM_VALUES = values();

   public static boolean matchesMovementDelayProperty(MovementDelayProperty movementDelayProperty) {
      return movementDelayProperty.isActive();
   }

   public EnumFacing getEnumFacing() {
      return this.enumFacing;
   }

   private boolean isActive() {
      return this == DOWN || this == UP;
   }

   public int getCount2() {
      return this.count2;
   }

   public int getCount() {
      return this.count;
   }

   private MovementDelayProperty(EnumFacing enumFacing2, int count3, int count4) {
      this.enumFacing = enumFacing2;
      this.count = count3;
      this.count2 = count4;
   }
}
