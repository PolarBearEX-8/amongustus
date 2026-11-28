package net.augustus.cosmetics.model;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import java.util.Iterator;
import java.util.Random;

public enum GeoDirectionPlane implements Iterable, Predicate {
   HORIZONTAL,
   VERTICAL;

public boolean apply2(GeoDirection geoDirection) {
      return geoDirection != null && geoDirection.getAxis().getPlane() == this;
   }

   public GeoDirection[] facings() {
      switch (this) {
         case HORIZONTAL:
            return new GeoDirection[]{GeoDirection.NORTH, GeoDirection.EAST, GeoDirection.SOUTH, GeoDirection.WEST};
         case VERTICAL:
            return new GeoDirection[]{GeoDirection.UP, GeoDirection.DOWN};
         default:
            throw new Error("Someone's been tampering with the universe!");
      }
   }

   public GeoDirection random(Random rand) {
      GeoDirection[] geoDirections = this.facings();
      return geoDirections[rand.nextInt(geoDirections.length)];
   }

   public boolean apply(Object obj) {
      return this.apply2((GeoDirection)obj);
   }

   @Override
   public Iterator iterator() {
      return Iterators.forArray(this.facings());
   }
}
