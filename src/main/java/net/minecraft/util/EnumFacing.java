package net.minecraft.util;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import java.util.Iterator;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;

public enum EnumFacing implements IStringSerializable {
   DOWN(0, 1, -1, "down", EnumFacing.AxisDirection.NEGATIVE, EnumFacing.Axis.Y, new Vec3i(0, -1, 0)),
   UP(1, 0, -1, "up", EnumFacing.AxisDirection.POSITIVE, EnumFacing.Axis.Y, new Vec3i(0, 1, 0)),
   NORTH(2, 3, 2, "north", EnumFacing.AxisDirection.NEGATIVE, EnumFacing.Axis.Z, new Vec3i(0, 0, -1)),
   SOUTH(3, 2, 0, "south", EnumFacing.AxisDirection.POSITIVE, EnumFacing.Axis.Z, new Vec3i(0, 0, 1)),
   WEST(4, 5, 1, "west", EnumFacing.AxisDirection.NEGATIVE, EnumFacing.Axis.X, new Vec3i(-1, 0, 0)),
   EAST(5, 4, 3, "east", EnumFacing.AxisDirection.POSITIVE, EnumFacing.Axis.X, new Vec3i(1, 0, 0));

public static final EnumFacing[] VALUES = new EnumFacing[6];
   private static final EnumFacing[] HORIZONTALS = new EnumFacing[4];
   private static final Map NAME_LOOKUP = Maps.newHashMap();
   private final int index;
   private final int opposite;
   private final int horizontalIndex;
   private final String name;
   private final EnumFacing.Axis axis;
   private final EnumFacing.AxisDirection axisDirection;
   private final Vec3i directionVec;
public static EnumFacing getFront(int n) {
      return VALUES[MathHelper.abs_int(n % VALUES.length)];
   }

   public static EnumFacing random(Random rand) {
      return getValues()[rand.nextInt(values().length)];
   }

   private EnumFacing rotateX() {
      switch (this) {
         case NORTH:
            return DOWN;
         case EAST:
         case WEST:
         default:
            throw new IllegalStateException("Unable to get X-rotated facing of " + this);
         case SOUTH:
            return UP;
         case UP:
            return NORTH;
         case DOWN:
            return SOUTH;
      }
   }

   public int getFrontOffsetY() {
      return this.axis == EnumFacing.Axis.Y ? this.axisDirection.getOffset() : 0;
   }

   public static EnumFacing getFacingFromVector(float f, float f2, float f3) {
      EnumFacing facing = NORTH;
      float minvalue = Float.MIN_VALUE;

      for (EnumFacing facing2 : getValues()) {
         float f4 = f * (float)facing2.directionVec.getX() + f2 * (float)facing2.directionVec.getY() + f3 * (float)facing2.directionVec.getZ();
         if (f4 > minvalue) {
            minvalue = f4;
            facing = facing2;
         }
      }

      return facing;
   }

   public Vec3i getDirectionVec() {
      return this.directionVec;
   }

   public EnumFacing rotateYCCW() {
      switch (this) {
         case NORTH:
            return WEST;
         case EAST:
            return NORTH;
         case SOUTH:
            return EAST;
         case WEST:
            return SOUTH;
         default:
            throw new IllegalStateException("Unable to get CCW facing of " + this);
      }
   }

   public EnumFacing getOpposite() {
      return VALUES[this.opposite];
   }

   public static EnumFacing byName(String str) {
      return str == null ? null : (EnumFacing)NAME_LOOKUP.get(str.toLowerCase());
   }

   public EnumFacing.AxisDirection getAxisDirection() {
      return this.axisDirection;
   }

   @Override
   public String getName() {
      return this.name;
   }

   public int getIndex() {
      return this.index;
   }

   public EnumFacing.Axis getAxis() {
      return this.axis;
   }

   public int getFrontOffsetX() {
      return this.axis == EnumFacing.Axis.X ? this.axisDirection.getOffset() : 0;
   }

   public static EnumFacing fromAngle(double d) {
      return getHorizontal(MathHelper.floor_double(d / 90.0 + 0.5) & 3);
   }

   private EnumFacing rotateZ() {
      switch (this) {
         case EAST:
            return DOWN;
         case SOUTH:
         default:
            throw new IllegalStateException("Unable to get Z-rotated facing of " + this);
         case WEST:
            return UP;
         case UP:
            return EAST;
         case DOWN:
            return WEST;
      }
   }

   public String getName2() {
      return this.name;
   }

   public int getHorizontalIndex() {
      return this.horizontalIndex;
   }

   private EnumFacing(int n, int n2, int n3, String str, EnumFacing.AxisDirection axisDirection2, EnumFacing.Axis axis2, Vec3i vec) {
      this.index = n;
      this.horizontalIndex = n3;
      this.opposite = n2;
      this.name = str;
      this.axis = axis2;
      this.axisDirection = axisDirection2;
      this.directionVec = vec;
   }

   public static EnumFacing getFacingFromAxis(EnumFacing.AxisDirection axisDirection2, EnumFacing.Axis axis2) {
      for (EnumFacing facing : getValues()) {
         if (facing.getAxisDirection() == axisDirection2 && facing.getAxis() == axis2) {
            return facing;
         }
      }

      throw new IllegalArgumentException("No such direction: " + axisDirection2 + " " + axis2);
   }

   public static EnumFacing[] getValues() {
      return VALUES;
   }

   public EnumFacing rotateY() {
      switch (this) {
         case NORTH:
            return EAST;
         case EAST:
            return SOUTH;
         case SOUTH:
            return WEST;
         case WEST:
            return NORTH;
         default:
            throw new IllegalStateException("Unable to get Y-rotated facing of " + this);
      }
   }

   @Override
   public String toString() {
      return this.name;
   }

   public static EnumFacing getHorizontal(int n) {
      return HORIZONTALS[MathHelper.abs_int(n % HORIZONTALS.length)];
   }

   static {
      for (EnumFacing facing : values()) {
         VALUES[facing.index] = facing;
         if (facing.getAxis().isHorizontal()) {
            HORIZONTALS[facing.horizontalIndex] = facing;
         }

         NAME_LOOKUP.put(facing.getName2().toLowerCase(), facing);
      }
   }

   public EnumFacing rotateAround(EnumFacing.Axis axis2) {
      switch (axis2) {
         case X:
            if (this != WEST && this != EAST) {
               return this.rotateX();
            }

            return this;
         case Y:
            if (this != UP && this != DOWN) {
               return this.rotateY();
            }

            return this;
         case Z:
            if (this != NORTH && this != SOUTH) {
               return this.rotateZ();
            }

            return this;
         default:
            throw new IllegalStateException("Unable to get CW facing for axis " + axis2);
      }
   }

   public int getFrontOffsetZ() {
      return this.axis == EnumFacing.Axis.Z ? this.axisDirection.getOffset() : 0;
   }

public enum Axis implements Predicate, IStringSerializable {
      X("x", EnumFacing.Plane.HORIZONTAL),
      Y("y", EnumFacing.Plane.VERTICAL),
      Z("z", EnumFacing.Plane.HORIZONTAL);

   private static final Map NAME_LOOKUP = Maps.newHashMap();
      private final String name;
      private final EnumFacing.Plane plane;
   @Override
      public String getName() {
         return this.name;
      }

      public boolean apply(EnumFacing facing) {
         return facing != null && facing.getAxis() == this;
      }

      public static Axis byName(String str) {
         return str == null ? null : (Axis)NAME_LOOKUP.get(str.toLowerCase());
      }

      private Axis(String str, EnumFacing.Plane plane2) {
         this.name = str;
         this.plane = plane2;
      }

      public boolean isVertical() {
         return this.plane == EnumFacing.Plane.VERTICAL;
      }

      public boolean apply(Object obj) {
         return this.apply((EnumFacing)obj);
      }

      @Override
      public String toString() {
         return this.name;
      }

      public String getName2() {
         return this.name;
      }

      public boolean isHorizontal() {
         return this.plane == EnumFacing.Plane.HORIZONTAL;
      }

      public EnumFacing.Plane getPlane() {
         return this.plane;
      }

      static {
         for (Axis axis : values()) {
            NAME_LOOKUP.put(axis.getName2().toLowerCase(), axis);
         }
      }
   }

   public enum Plane implements Predicate, Iterable {
      HORIZONTAL,
      VERTICAL;

   public boolean apply(EnumFacing facing) {
         return facing != null && facing.getAxis().getPlane() == this;
      }

      @Override
      public Iterator iterator() {
         return Iterators.forArray(this.facings());
      }

      public EnumFacing[] facings() {
         switch (this) {
            case HORIZONTAL:
               return new EnumFacing[]{EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST};
            case VERTICAL:
               return new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN};
            default:
               throw new Error("Someone's been tampering with the universe!");
         }
      }

      public boolean apply(Object obj) {
         return this.apply((EnumFacing)obj);
      }

      public EnumFacing random(Random rand) {
         EnumFacing[] facings = this.facings();
         return facings[rand.nextInt(facings.length)];
      }
   }

   public enum AxisDirection {
      POSITIVE(1, "Towards positive"),
      NEGATIVE(-1, "Towards negative");

   private final int offset;
      private final String description;
   private AxisDirection(int n, String str) {
         this.offset = n;
         this.description = str;
      }

      @Override
      public String toString() {
         return this.description;
      }

      public int getOffset() {
         return this.offset;
      }
   }
}
