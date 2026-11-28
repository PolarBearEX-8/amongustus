package net.augustus.cosmetics.model;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.util.Map;

public enum GeoAxis implements Predicate {
   X("x", GeoDirectionPlane.HORIZONTAL),
   Y("y", GeoDirectionPlane.VERTICAL),
   Z("z", GeoDirectionPlane.HORIZONTAL);

private final String name;
   private static final Map NAME_LOOKUP = Maps.newHashMap();
   private final GeoDirectionPlane plane;

   @Override
   public String toString() {
      return this.name;
   }

   public static GeoAxis byName(String str) {
      return str == null ? null : (GeoAxis)NAME_LOOKUP.get(str.toLowerCase());
   }

   private GeoAxis(String str, GeoDirectionPlane geoDirectionPlane) {
      this.name = str;
      this.plane = geoDirectionPlane;
   }

   public boolean apply(Object obj) {
      return this.apply2((GeoDirection)obj);
   }

   public String getName2() {
      return this.name;
   }

   public boolean isVertical() {
      return this.plane == GeoDirectionPlane.VERTICAL;
   }

   static {
      for (GeoAxis geoAxis : values()) {
         NAME_LOOKUP.put(geoAxis.getName().toLowerCase(), geoAxis);
      }
   }

   public String getName() {
      return this.name;
   }

   public boolean apply2(GeoDirection geoDirection) {
      return geoDirection != null && geoDirection.getAxis() == this;
   }

   public boolean isHorizontal() {
      return this.plane == GeoDirectionPlane.HORIZONTAL;
   }

   public GeoDirectionPlane getPlane() {
      return this.plane;
   }
}
