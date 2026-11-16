package net.minecraft.util;

import client.event.PrimaryListener;
import client.setting.EnumFacingPositiveType;
import client.setting.EnumFacingXType;
import client.util.ContextIntArrayEntry;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;

public enum EnumFacing implements PrimaryListener {
   DOWN(0, 1, -1, "down", EnumFacingPositiveType.NEGATIVE, EnumFacingXType.Y, new Vec3i(0, -1, 0)),
   UP(1, 0, -1, "up", EnumFacingPositiveType.POSITIVE, EnumFacingXType.Y, new Vec3i(0, 1, 0)),
   NORTH(2, 3, 2, "north", EnumFacingPositiveType.NEGATIVE, EnumFacingXType.Z, new Vec3i(0, 0, -1)),
   SOUTH(3, 2, 0, "south", EnumFacingPositiveType.POSITIVE, EnumFacingXType.Z, new Vec3i(0, 0, 1)),
   WEST(4, 5, 1, "west", EnumFacingPositiveType.NEGATIVE, EnumFacingXType.X, new Vec3i(-1, 0, 0)),
   EAST(5, 4, 3, "east", EnumFacingPositiveType.POSITIVE, EnumFacingXType.X, new Vec3i(1, 0, 0));

   public static final EnumFacing[] VALUES = new EnumFacing[6];
   public static final EnumFacing[] ENUM_VALUES = new EnumFacing[4];
   public static final Map map = Maps.newHashMap();
   public final int indexOffset1;
   public final int count;
   public final int indexOffset;
   public final String text;
   public final EnumFacingXType enumFacingXType;
   public final EnumFacingPositiveType enumFacingPositiveType;
   public final Vec3i vec3i;
   public static final EnumFacing[] ENUM_VALUES2 = values();

   public EnumFacing getEnumFacing2() {
      switch (ContextIntArrayEntry.countArray2[this.ordinal()]) {
         case 1:
            return WEST;
         case 2:
            return NORTH;
         case 3:
            return EAST;
         case 4:
            return SOUTH;
         default:
            throw new IllegalStateException("Unable to get CCW facing of " + this);
      }
   }

   public static EnumFacing resolveEnumFacing(float posX, float posY, float posZ) {
      EnumFacing enumFacing = NORTH;
      float factor = Float.MIN_VALUE;

      for (EnumFacing enumFacing2 : getEntries()) {
         float factor2 = posX * (float)enumFacing2.vec3i.getX() + posY * (float)enumFacing2.vec3i.getY() + posZ * (float)enumFacing2.vec3i.getZ();
         if (factor2 > factor) {
            factor = factor2;
            enumFacing = enumFacing2;
         }
      }

      return enumFacing;
   }

   private EnumFacing getEnumFacing() {
      switch (ContextIntArrayEntry.countArray2[this.ordinal()]) {
         case 2:
            return DOWN;
         case 3:
         default:
            throw new IllegalStateException("Unable to get Z-rotated facing of " + this);
         case 4:
            return UP;
         case 5:
            return EAST;
         case 6:
            return WEST;
      }
   }

   public static EnumFacing resolveEnumFacing(int count2) {
      return ENUM_VALUES[MathHelper.computeCount(count2 % ENUM_VALUES.length)];
   }

   public EnumFacingPositiveType getEnumFacingPositiveType() {
      return this.enumFacingPositiveType;
   }

   @Override
   public String getText() {
      return this.text;
   }

   public int getInt() {
      return this.enumFacingXType == EnumFacingXType.Z ? this.enumFacingPositiveType.getCount() : 0;
   }

   public static EnumFacing resolveEnumFacing(EnumFacingPositiveType enumFacingPositiveType2, EnumFacingXType enumFacingXType2) {
      for (EnumFacing enumFacing : getEntries()) {
         if (enumFacing.getEnumFacingPositiveType() == enumFacingPositiveType2 && enumFacing.getEnumFacingXType() == enumFacingXType2) {
            return enumFacing;
         }
      }

      throw new IllegalArgumentException("No such direction: " + enumFacingPositiveType2 + " " + enumFacingXType2);
   }

   public Vec3i getVec3i() {
      return this.vec3i;
   }

   public EnumFacing getEnumFacing5() {
      return VALUES[this.count];
   }

   public static EnumFacing getEnumFacing6(int count2) {
      return VALUES[MathHelper.computeCount(count2 % VALUES.length)];
   }

   public int getIndexOffset() {
      return this.indexOffset;
   }

   public String getText2() {
      return this.text;
   }

   public static EnumFacing resolveEnumFacing(Random random) {
      return getEntries()[random.nextInt(values().length)];
   }

   public static EnumFacing resolveEnumFacing(double offset) {
      return resolveEnumFacing(MathHelper.ceiling_double_int(offset / 90.0 + 0.5) & 3);
   }

   public int getInt2() {
      return this.enumFacingXType == EnumFacingXType.X ? this.enumFacingPositiveType.getCount() : 0;
   }

   public EnumFacingXType getEnumFacingXType() {
      return this.enumFacingXType;
   }

   public EnumFacing getEnumFacingAsEnumFacing() {
      switch (ContextIntArrayEntry.countArray2[this.ordinal()]) {
         case 1:
            return EAST;
         case 2:
            return SOUTH;
         case 3:
            return WEST;
         case 4:
            return NORTH;
         default:
            throw new IllegalStateException("Unable to get Y-rotated facing of " + this);
      }
   }

   public static EnumFacing[] getEntries() {
      return VALUES;
   }

   public int getInt3() {
      return this.enumFacingXType == EnumFacingXType.Y ? this.enumFacingPositiveType.getCount() : 0;
   }

   private EnumFacing getEnumFacing3() {
      switch (ContextIntArrayEntry.countArray2[this.ordinal()]) {
         case 1:
            return DOWN;
         case 2:
         case 4:
         default:
            throw new IllegalStateException("Unable to get X-rotated facing of " + this);
         case 3:
            return UP;
         case 5:
            return NORTH;
         case 6:
            return SOUTH;
      }
   }

   public int getIndexOffset1() {
      return this.indexOffset1;
   }

   public EnumFacing resolveEnumFacing(EnumFacingXType enumFacingXType2) {
      switch (ContextIntArrayEntry.countArray[enumFacingXType2.ordinal()]) {
         case 1:
            if (this != WEST && this != EAST) {
               return this.getEnumFacing3();
            }

            return this;
         case 2:
            if (this != UP && this != DOWN) {
               return this.getEnumFacingAsEnumFacing();
            }

            return this;
         case 3:
            if (this != NORTH && this != SOUTH) {
               return this.getEnumFacing();
            }

            return this;
         default:
            throw new IllegalStateException("Unable to get CW facing for axis " + enumFacingXType2);
      }
   }

   public static EnumFacing resolveEnumFacing(String text2) {
      return text2 == null ? null : (EnumFacing)map.get(text2.toLowerCase());
   }

   private EnumFacing(int count2, int count3, int count4, String text2, EnumFacingPositiveType enumFacingPositiveType2, EnumFacingXType enumFacingXType2, Vec3i vec) {
      this.indexOffset1 = count2;
      this.indexOffset = count4;
      this.count = count3;
      this.text = text2;
      this.enumFacingXType = enumFacingXType2;
      this.enumFacingPositiveType = enumFacingPositiveType2;
      this.vec3i = vec;
   }

   static {
      for (EnumFacing enumFacing : values()) {
         VALUES[enumFacing.indexOffset1] = enumFacing;
         if (enumFacing.getEnumFacingXType().isActiveAsFlag()) {
            ENUM_VALUES[enumFacing.indexOffset] = enumFacing;
         }

         map.put(enumFacing.getText2().toLowerCase(), enumFacing);
      }
   }

   @Override
   public String toString() {
      return this.text;
   }
}
