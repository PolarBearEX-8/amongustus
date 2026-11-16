package client.setting;

import client.util.ContextIntArrayEntry;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public enum MovementFactorProperty implements Predicate, Iterable {
   HORIZONTAL,
   VERTICAL;

   public static final MovementFactorProperty[] ENUM_VALUES = values();

   public EnumFacing resolveEnumFacing(Random random) {
      EnumFacing[] enumFacingArray = this.getEnumFacingArray();
      return enumFacingArray[random.nextInt(enumFacingArray.length)];
   }

   public boolean matchesEnumFacing(EnumFacing enumFacing) {
      return enumFacing != null && enumFacing.getEnumFacingXType().getMovementFactorProperty() == this;
   }

   public EnumFacing[] getEnumFacingArray() {
      switch (ContextIntArrayEntry.countArray3[this.ordinal()]) {
         case 1:
            return new EnumFacing[]{EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST};
         case 2:
            return new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN};
         default:
            throw new Error("Someone's been tampering with the universe!");
      }
   }

   @Override
   public Iterator iterator() {
      return Iterators.forArray(this.getEnumFacingArray());
   }

   public boolean apply(Object entry) {
      return this.matchesEnumFacing((EnumFacing)entry);
   }
}
