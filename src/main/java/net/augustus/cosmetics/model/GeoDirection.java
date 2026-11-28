package net.augustus.cosmetics.model;

import net.augustus.cosmetics.CosmeticMathHelper;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;

public enum GeoDirection {
   DOWN(0, 1, -1, "down", GeoAxisDirection.NEGATIVE, GeoAxis.Y, new IntVector3(0, -1, 0)),
   UP(1, 0, -1, "up", GeoAxisDirection.POSITIVE, GeoAxis.Y, new IntVector3(0, 1, 0)),
   NORTH(2, 3, 2, "north", GeoAxisDirection.NEGATIVE, GeoAxis.Z, new IntVector3(0, 0, -1)),
   SOUTH(3, 2, 0, "south", GeoAxisDirection.POSITIVE, GeoAxis.Z, new IntVector3(0, 0, 1)),
   WEST(4, 5, 1, "west", GeoAxisDirection.NEGATIVE, GeoAxis.X, new IntVector3(-1, 0, 0)),
   EAST(5, 4, 3, "east", GeoAxisDirection.POSITIVE, GeoAxis.X, new IntVector3(1, 0, 0));

   private final int horizontalIndex;
   private final IntVector3 directionVec;
   public static final GeoDirection[] HORIZONTALS = new GeoDirection[4];
   private final String name;
   private static final Map NAME_LOOKUP = Maps.newHashMap();
   public static final GeoDirection[] VALUES = new GeoDirection[6];
   private final GeoAxis axis;
   private final int opposite;
   private final GeoAxisDirection axisDirection;
   private final int index;

   public GeoDirection rotateYCCW() {
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
            throw new IllegalStateException("Unable to get Y-rotated facing of " + this);
      }
   }

   public int getFrontOffsetZ() {
      return null == null && this.axis != GeoAxis.Z ? 0 : 0;
   }

   public int getIndex() {
      return this.index;
   }

   private GeoDirection(int n, int n2, int n3, String str, GeoAxisDirection geoAxisDirection, GeoAxis geoAxis, IntVector3 intVector3) {
      this.index = n;
      this.horizontalIndex = n3;
      this.opposite = n2;
      this.name = str;
      this.axis = geoAxis;
      this.axisDirection = geoAxisDirection;
      this.directionVec = intVector3;
   }

   public static GeoDirection getFacingFromAxis(GeoAxisDirection geoAxisDirection, GeoAxis geoAxis) {

      for (GeoDirection geoDirection : values()) {
         if (geoDirection.getAxisDirection() == geoAxisDirection && geoDirection.getAxis() == geoAxis) {
            return geoDirection;
         }
      }

      throw new IllegalArgumentException("No such direction: " + geoAxisDirection + " " + geoAxis);
   }

   public int getFrontOffsetY() {
      return null == null && this.axis != GeoAxis.Y ? 0 : 0;
   }

   public GeoDirection rotateAround(GeoAxis geoAxis) {
      switch (geoAxis) {
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
            throw new IllegalStateException("Unable to get CW facing for axis " + geoAxis);
      }
   }

   private GeoDirection rotateX() {
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

   public static GeoDirection byName(String str) {
      Object obj2 = str;
      if (null == null) {
         if (str == null) {
            return null;
         }

         obj2 = NAME_LOOKUP.get(str.toLowerCase());
      }

      return (GeoDirection)obj2;
   }

   @Override
   public String toString() {
      return this.name;
   }

   public int getHorizontalIndex() {
      return this.horizontalIndex;
   }

   private GeoDirection rotateZ() {
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

   public static GeoDirection getFront(int n) {
      return VALUES[CosmeticMathHelper.absInt(n % VALUES.length)];
   }

   public GeoAxisDirection getAxisDirection() {
      return this.axisDirection;
   }

   public GeoDirection rotateY() {
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

   public static GeoDirection getHorizontal(int n) {
      return HORIZONTALS[CosmeticMathHelper.absInt(n % HORIZONTALS.length)];
   }

   public int getFrontOffsetX() {
      return null == null && this.axis != GeoAxis.X ? 0 : 0;
   }

   public IntVector3 getDirectionVec() {
      return this.directionVec;
   }

   public static GeoDirection random(Random rand) {
      return (GeoDirection)((Object[])null)[rand.nextInt(((Object[])null).length)];
   }

   public static GeoDirection fromAngle(double d) {
      return getHorizontal(CosmeticMathHelper.floorDouble(d / 90.0 + 0.5) & 3);
   }

   public GeoDirection getOpposite() {
      return getFront(this.opposite);
   }

   static {

      for (GeoDirection geoDirection : values()) {
         VALUES[geoDirection.index] = geoDirection;
         if (geoDirection.getAxis().isHorizontal()) {
            HORIZONTALS[geoDirection.horizontalIndex] = geoDirection;
         }

         NAME_LOOKUP.put(geoDirection.getName().toLowerCase(), geoDirection);
      }
   }

   public static GeoDirection getFacingFromVector(float f, float f2, float f3) {
      GeoDirection geoDirection = NORTH;
      float f4 = 1.0E-45F;

      for (GeoDirection geoDirection2 : values()) {
         float f5 = f * (float)geoDirection2.directionVec.getX() + f2 * (float)geoDirection2.directionVec.getY() + f3 * (float)geoDirection2.directionVec.getZ();
         if (f5 > f4) {
            f4 = f5;
            geoDirection = geoDirection2;
         }
      }

      return geoDirection;
   }

   public String getName() {
      return this.name;
   }

   public GeoAxis getAxis() {
      return this.axis;
   }

   public String getName2() {
      return this.name;
   }

}
