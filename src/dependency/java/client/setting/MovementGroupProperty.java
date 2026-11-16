package client.setting;

import net.minecraft.util.EnumFacing;

public enum MovementGroupProperty {
   DOWN(
      new EnumFacing[]{EnumFacing.WEST, EnumFacing.EAST, EnumFacing.NORTH, EnumFacing.SOUTH},
      0.5F,
      false,
      new CombatColorProperty[0],
      new CombatColorProperty[0],
      new CombatColorProperty[0],
      new CombatColorProperty[0]
   ),
   UP(
      new EnumFacing[]{EnumFacing.EAST, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.SOUTH},
      1.0F,
      false,
      new CombatColorProperty[0],
      new CombatColorProperty[0],
      new CombatColorProperty[0],
      new CombatColorProperty[0]
   ),
   NORTH(
      new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN, EnumFacing.EAST, EnumFacing.WEST},
      0.8F,
      true,
      new CombatColorProperty[]{
         CombatColorProperty.UP,
         CombatColorProperty.FLIP_WEST,
         CombatColorProperty.UP,
         CombatColorProperty.WEST,
         CombatColorProperty.FLIP_UP,
         CombatColorProperty.WEST,
         CombatColorProperty.FLIP_UP,
         CombatColorProperty.FLIP_WEST
      },
      new CombatColorProperty[]{
         CombatColorProperty.UP,
         CombatColorProperty.FLIP_EAST,
         CombatColorProperty.UP,
         CombatColorProperty.EAST,
         CombatColorProperty.FLIP_UP,
         CombatColorProperty.EAST,
         CombatColorProperty.FLIP_UP,
         CombatColorProperty.FLIP_EAST
      },
      new CombatColorProperty[]{
         CombatColorProperty.DOWN,
         CombatColorProperty.FLIP_EAST,
         CombatColorProperty.DOWN,
         CombatColorProperty.EAST,
         CombatColorProperty.FLIP_DOWN,
         CombatColorProperty.EAST,
         CombatColorProperty.FLIP_DOWN,
         CombatColorProperty.FLIP_EAST
      },
      new CombatColorProperty[]{
         CombatColorProperty.DOWN,
         CombatColorProperty.FLIP_WEST,
         CombatColorProperty.DOWN,
         CombatColorProperty.WEST,
         CombatColorProperty.FLIP_DOWN,
         CombatColorProperty.WEST,
         CombatColorProperty.FLIP_DOWN,
         CombatColorProperty.FLIP_WEST
      }
   ),
   SOUTH(
      new EnumFacing[]{EnumFacing.WEST, EnumFacing.EAST, EnumFacing.DOWN, EnumFacing.UP},
      0.8F,
      true,
      new CombatColorProperty[]{
         CombatColorProperty.UP,
         CombatColorProperty.FLIP_WEST,
         CombatColorProperty.FLIP_UP,
         CombatColorProperty.FLIP_WEST,
         CombatColorProperty.FLIP_UP,
         CombatColorProperty.WEST,
         CombatColorProperty.UP,
         CombatColorProperty.WEST
      },
      new CombatColorProperty[]{
         CombatColorProperty.DOWN,
         CombatColorProperty.FLIP_WEST,
         CombatColorProperty.FLIP_DOWN,
         CombatColorProperty.FLIP_WEST,
         CombatColorProperty.FLIP_DOWN,
         CombatColorProperty.WEST,
         CombatColorProperty.DOWN,
         CombatColorProperty.WEST
      },
      new CombatColorProperty[]{
         CombatColorProperty.DOWN,
         CombatColorProperty.FLIP_EAST,
         CombatColorProperty.FLIP_DOWN,
         CombatColorProperty.FLIP_EAST,
         CombatColorProperty.FLIP_DOWN,
         CombatColorProperty.EAST,
         CombatColorProperty.DOWN,
         CombatColorProperty.EAST
      },
      new CombatColorProperty[]{
         CombatColorProperty.UP,
         CombatColorProperty.FLIP_EAST,
         CombatColorProperty.FLIP_UP,
         CombatColorProperty.FLIP_EAST,
         CombatColorProperty.FLIP_UP,
         CombatColorProperty.EAST,
         CombatColorProperty.UP,
         CombatColorProperty.EAST
      }
   ),
   WEST(
      new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.SOUTH},
      0.6F,
      true,
      new CombatColorProperty[]{
         CombatColorProperty.UP,
         CombatColorProperty.SOUTH,
         CombatColorProperty.UP,
         CombatColorProperty.FLIP_SOUTH,
         CombatColorProperty.FLIP_UP,
         CombatColorProperty.FLIP_SOUTH,
         CombatColorProperty.FLIP_UP,
         CombatColorProperty.SOUTH
      },
      new CombatColorProperty[]{
         CombatColorProperty.UP,
         CombatColorProperty.NORTH,
         CombatColorProperty.UP,
         CombatColorProperty.FLIP_NORTH,
         CombatColorProperty.FLIP_UP,
         CombatColorProperty.FLIP_NORTH,
         CombatColorProperty.FLIP_UP,
         CombatColorProperty.NORTH
      },
      new CombatColorProperty[]{
         CombatColorProperty.DOWN,
         CombatColorProperty.NORTH,
         CombatColorProperty.DOWN,
         CombatColorProperty.FLIP_NORTH,
         CombatColorProperty.FLIP_DOWN,
         CombatColorProperty.FLIP_NORTH,
         CombatColorProperty.FLIP_DOWN,
         CombatColorProperty.NORTH
      },
      new CombatColorProperty[]{
         CombatColorProperty.DOWN,
         CombatColorProperty.SOUTH,
         CombatColorProperty.DOWN,
         CombatColorProperty.FLIP_SOUTH,
         CombatColorProperty.FLIP_DOWN,
         CombatColorProperty.FLIP_SOUTH,
         CombatColorProperty.FLIP_DOWN,
         CombatColorProperty.SOUTH
      }
   ),
   EAST(
      new EnumFacing[]{EnumFacing.DOWN, EnumFacing.UP, EnumFacing.NORTH, EnumFacing.SOUTH},
      0.6F,
      true,
      new CombatColorProperty[]{
         CombatColorProperty.FLIP_DOWN,
         CombatColorProperty.SOUTH,
         CombatColorProperty.FLIP_DOWN,
         CombatColorProperty.FLIP_SOUTH,
         CombatColorProperty.DOWN,
         CombatColorProperty.FLIP_SOUTH,
         CombatColorProperty.DOWN,
         CombatColorProperty.SOUTH
      },
      new CombatColorProperty[]{
         CombatColorProperty.FLIP_DOWN,
         CombatColorProperty.NORTH,
         CombatColorProperty.FLIP_DOWN,
         CombatColorProperty.FLIP_NORTH,
         CombatColorProperty.DOWN,
         CombatColorProperty.FLIP_NORTH,
         CombatColorProperty.DOWN,
         CombatColorProperty.NORTH
      },
      new CombatColorProperty[]{
         CombatColorProperty.FLIP_UP,
         CombatColorProperty.NORTH,
         CombatColorProperty.FLIP_UP,
         CombatColorProperty.FLIP_NORTH,
         CombatColorProperty.UP,
         CombatColorProperty.FLIP_NORTH,
         CombatColorProperty.UP,
         CombatColorProperty.NORTH
      },
      new CombatColorProperty[]{
         CombatColorProperty.FLIP_UP,
         CombatColorProperty.SOUTH,
         CombatColorProperty.FLIP_UP,
         CombatColorProperty.FLIP_SOUTH,
         CombatColorProperty.UP,
         CombatColorProperty.FLIP_SOUTH,
         CombatColorProperty.UP,
         CombatColorProperty.SOUTH
      }
   );
   public final EnumFacing[] enumFacingArray;
   public final float factor;
   public final boolean enabled;
   public final CombatColorProperty[] combatColorPropertyArray4;
   public final CombatColorProperty[] combatColorPropertyArray;
   public final CombatColorProperty[] combatColorPropertyArray2;
   public final CombatColorProperty[] combatColorPropertyArray3;
   public static final MovementGroupProperty[] ENUM_VALUES = new MovementGroupProperty[6];
   public static final MovementGroupProperty[] ENUM_VALUES2 = values();

   public static MovementGroupProperty resolveMovementGroupProperty(EnumFacing enumFacing) {
      return ENUM_VALUES[enumFacing.getIndexOffset1()];
   }

   private MovementGroupProperty(
      EnumFacing[] enumFacingArray2,
      float factor2,
      boolean flag,
      CombatColorProperty[] combatColorPropertyArray5,
      CombatColorProperty[] combatColorPropertyArray6,
      CombatColorProperty[] combatColorPropertyArray7,
      CombatColorProperty[] combatColorPropertyArray8
   ) {
      this.enumFacingArray = enumFacingArray2;
      this.factor = factor2;
      this.enabled = flag;
      this.combatColorPropertyArray4 = combatColorPropertyArray5;
      this.combatColorPropertyArray = combatColorPropertyArray6;
      this.combatColorPropertyArray2 = combatColorPropertyArray7;
      this.combatColorPropertyArray3 = combatColorPropertyArray8;
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
